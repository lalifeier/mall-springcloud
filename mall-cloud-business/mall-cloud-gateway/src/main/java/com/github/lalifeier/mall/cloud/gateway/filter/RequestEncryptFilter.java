package com.github.lalifeier.mall.cloud.gateway.filter;

//@Slf4j
//@Component
//public class RequestEncryptFilter implements GlobalFilter, Ordered {
//  private String privateKey;
//  private List<String> whiteList;
//  private List<String> blackList;
//
//  public RequestEncryptFilter(String privateKey, List<String> whiteList, List<String> blackList) {
//    this.privateKey = privateKey;
//    this.whiteList = whiteList;
//    this.blackList = blackList;
//  }
//
//  private static final ObjectMapper objectMapper = new ObjectMapper();
//
//  private boolean shouldEncrypt(ServerWebExchange exchange) {
//    ServerHttpRequest request = exchange.getRequest();
//    String path = request.getPath().toString();
//
//    // 判断白名单和黑名单
//    if (WebFluxUtil.isPathMatch(path, whiteList)) {
//      return false;
//    }
//    if (!WebFluxUtil.isPathMatch(path, blackList)) {
//      return false;
//    }
//
//    // 判断是否为 JSON 请求
//    if (!WebFluxUtil.isJsonRequest(exchange)) {
//      return false;
//    }
//
//    HttpMethod method = request.getMethod();
//    if (method != HttpMethod.POST) {
//      return false;
//    }
//
//    return true;
//  }
//
//  @Override
//  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//    ServerHttpRequest request = exchange.getRequest();
//
//    if (!shouldEncrypt(exchange)) {
//      return chain.filter(exchange);
//    }
//
//    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(request) {
//      @Override
//      public Flux<DataBuffer> getBody() {
//        String rawData = WebFluxUtil.resolveBodyFromRequest(request);
//
//        try {
//          Map<String, Object> dataMap = objectMapper.readValue(rawData, new TypeReference<>() {
//          });
//          if (dataMap.containsKey("data")) {
//            String encryptedValue = (String) dataMap.get("data");
//            String decryptedValue = RSAUtil.decrypt(encryptedValue, privateKey);
//            dataMap.put("data", decryptedValue);
//          }
//          byte[] decryptedBytes = objectMapper.writeValueAsBytes(dataMap);
//          DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(decryptedBytes);
//          return Flux.just(buffer);
//        } catch (JsonProcessingException e) {
//          log.error("Error processing request body", e);
//          throw new RuntimeException("Error processing request body", e);
//        } catch (Exception e) {
//          throw new RuntimeException(e);
//        }
//      }
//    };
//    return chain.filter(exchange.mutate().request(decorator).build());
//  }
//
//  @Override
//  public int getOrder() {
//    return Ordered.LOWEST_PRECEDENCE;
//  }
//}
