//package com.github.lalifeier.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.PrintWriter;
//
//@Slf4j
//public class WebUtils {
//    private static Gson gson = new GsonBuilder().serializeNulls().create();
//
//    public static void writeJson(HttpServletResponse response, int status, Object object) {
//        response.setHeader("Content-Type", "application/json;charset=UTF-8");
//        response.setContentType("application/json;charset=UTF-8");
//        response.setStatus(status);
//        PrintWriter out = null;
//        try {
//            String data = object instanceof String ? (String) object : gson.toJson(object);
//            out = response.getWriter();
//            out.print(data);
//            out.flush();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//
//
//    public static void writeJson(HttpServletResponse response, Object object) {
//        writeJson(response, HttpServletResponse.SC_OK, object);
//    }
//
//
//    public static void writeJson(ServletResponse response, Object object) {
//        if (response instanceof HttpServletResponse) {
//            writeJson((HttpServletResponse) response, object);
//        }
//    }
//}
