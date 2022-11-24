package com.github.lalifeier.utils;

public class SignUtil {

    //private String signature(String timestamp, String nonceStr, HttpServletRequest request) throws UnsupportedEncodingException {
    //    Map<String, Object> params = new HashMap<>(16);
    //    Enumeration<String> enumeration = request.getParameterNames();
    //    if (enumeration.hasMoreElements()) {
    //        String name = enumeration.nextElement();
    //        String value = request.getParameter(name);
    //        params.put(name, URLEncoder.encode(value, CommonConstants.UTF_8));
    //    }
    //    String qs = String.format("%s×tamp=%s&nonceStr=%s&key=%s", this.sortQueryParamString(params), timestamp, nonceStr, key);
    //    //log.info("qs:{}", qs);
    //    String sign = SecureUtil.md5(qs).toLowerCase();
    //    //log.info("sign:{}", sign);
    //    return sign;
    //}
    //
    ///**
    // * 按照字母顺序进行升序排序
    // *
    // * @param params 请求参数 。注意请求参数中不能包含key
    // * @return 排序后结果
    // */
    //private String sortQueryParamString(Map<String, Object> params) {
    //    List<String> listKeys = Lists.newArrayList(params.keySet());
    //    Collections.sort(listKeys);
    //    StrBuilder content = StrBuilder.create();
    //    for (String param : listKeys) {
    //        content.append(param).append("=").append(params.get(param).toString()).append("&");
    //    }
    //    if (content.length() > 0) {
    //        return content.subString(0, content.length() - 1);
    //    }
    //    return content.toString();
    //}
}
