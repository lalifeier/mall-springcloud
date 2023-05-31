package com.github.lalifeier.mall.cloud.http.config;

import okhttp3.OkHttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    /**
     * 最大连接数
     */
    @Value("${http.maxTotal:500}")
    private Integer maxTotal;

    /**
     * 同路由并发数（每个主机的并发）
     */
    @Value("${http.defaultMaxPerRoute:50}")
    private Integer defaultMaxPerRoute;

    /**
     * 重试次数
     */
    @Value("${http.retryCount:3}")
    private Integer retryCount;

    /**
     * 连接超时时间/毫秒 超出该时间抛出connect timeout
     */
    @Value("${http.connectTimeout:5000}")
    private Integer connectTimeout;

    /**
     * 数据读取超时时间(socketTimeout)/毫秒 超出该时间抛出 read timeout
     */
    @Value("${http.readTimeout:10000}")
    private Integer readTimeout;

    /**
     * 请求连接的超时时间/毫秒  从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException:
     */
    @Value("${http.connectionRequestTimeout:10000}")
    private Integer connectionRequestTimeout;

    /**
     * Http连接管理
     */
    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        // 同路由并发数（每个主机的并发）
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return poolingHttpClientConnectionManager;
    }

    ///**
    // * Http客户端
    // */
    //@Bean
    //public HttpClient httpClient(HttpClientConnectionManager httpClientConnectionManager) {
    //    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    //
    //    // 请求配置  可以在Http客户端构建的过程中配置, 也可以在请求工厂类中配置
    //    //RequestConfig requestConfig = RequestConfig.custom()
    //    //          .setSocketTimeout(readTimeout)
    //    //          .setConnectTimeout(connectTimeout)
    //    //          .setConnectionRequestTimeout(connectionRequestTimeout)
    //    //          .build();
    //
    //    // 请求头参数
    //    List<Header> headers = new ArrayList<>();
    //    headers.add(new BasicHeader("User-Agent",
    //            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
    //    headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
    //    headers.add(new BasicHeader("Accept-Language", "zh-CN"));
    //    headers.add(new BasicHeader("Connection", "Keep-Alive"));
    //    headers.add(new BasicHeader("Content-type", "application/json;charset=UTF-8"));
    //
    //    return httpClientBuilder
    //            // 添加请求配置
    //            // .setDefaultRequestConfig(requestConfig)
    //            // 添加Http连接管理
    //            .setConnectionManager(httpClientConnectionManager)
    //            // 添加请求头
    //            //.setDefaultHeaders(headers)
    //            // 保持长连接配置，需要在头添加Keep-Alive
    //            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
    //            // 设置重试次数 默认是3次
    //            .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, true))
    //            .build();
    //}
    //

    ///**
    // * 客户端请求工厂配置
    // */
    //@Bean
    //public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
    //    // 创建org.apache.http.client请求工厂
    //    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    //    // 连接超时时间/毫秒（连接上服务器(握手成功)的时间，超出抛出connect timeout）
    //    clientHttpRequestFactory.setConnectTimeout(connectTimeout);
    //    // 数据读取超时时间(socketTimeout)/毫秒（务器返回数据(response)的时间，超过抛出read timeout）
    //    clientHttpRequestFactory.setReadTimeout(readTimeout);
    //    // 连接池获取请求连接的超时时间，不宜过长，必须设置/毫秒（超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool）
    //    clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
    //
    //    return clientHttpRequestFactory;
    //}

    /**
     * okHttp客户端
     */
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();

        return okHttpClient;
    }

    /**
     * 客户端请求工厂配置
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(OkHttpClient okHttpClient) {
        // 创建OkHttpClient请求工厂
        OkHttp3ClientHttpRequestFactory clientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        // 连接超时时间/毫秒（连接上服务器(握手成功)的时间，超出抛出connect timeout）
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        // 数据读取超时时间(socketTimeout)/毫秒（务器返回数据(response)的时间，超过抛出read timeout）
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        // 连接池获取请求连接的超时时间，不宜过长，必须设置/毫秒（超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool）
        clientHttpRequestFactory.setWriteTimeout(connectionRequestTimeout);
        return clientHttpRequestFactory;
    }

    @Bean
    @Primary  // 表示优先使用该注解的Bean
    //@LoadBalanced // 让RestTemplate具备负载均衡的能力
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        // 初始化RestTemplate, 添加默认的类型转换
        RestTemplate restTemplate = new RestTemplate();

        // 添加请求工厂
        restTemplate.setRequestFactory(clientHttpRequestFactory);

        //// 重新设置 StringHttpMessageConverter 字符集为UTF-8，解决中文乱码问题
        //List<HttpMessageConverter<?>> httpMessageConverterList = restTemplate.getMessageConverters();
        //HttpMessageConverter<?> httpMessageConverter = null;
        //for (HttpMessageConverter<?> messageConverter : httpMessageConverterList) {
        //    if (messageConverter instanceof StringHttpMessageConverter) {
        //        httpMessageConverter = messageConverter;
        //        break;
        //    }
        //}
        //// 先删除 StringHttpMessageConverter 类型转换
        //if (null != httpMessageConverter) {
        //    httpMessageConverterList.remove(httpMessageConverter);
        //}
        //// 添加 StringHttpMessageConverter 类型转换   设置字符集为UTF-8 (该处默认的是 ISO-8859-1 )
        //httpMessageConverterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // 加入FastJson转换器
        //httpMessageConverterList.add(new FastJsonHttpMessageConverter());

        // 添加请求头拦截器  在请求头添加一些信息如 token等 (可选,不次不用,故不添加)
        // List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList = new ArrayList<>();
        // clientHttpRequestInterceptorList.add(new MyInterceptor());
        // restTemplate.setInterceptors(clientHttpRequestInterceptorList);

        return restTemplate;
    }
}
