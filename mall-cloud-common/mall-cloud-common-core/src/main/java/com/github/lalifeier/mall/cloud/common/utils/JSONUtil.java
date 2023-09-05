package com.github.lalifeier.mall.cloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONUtil {
  private static Gson gson = new GsonBuilder().create();

  public static String toJson(Object obj) {
    return gson.toJson(obj);
  }

  public static <T> T fromJson(String jsonStr, Class<T> clazz) {
    return gson.fromJson(jsonStr, clazz);
  }

  public static String toPrettyJson(String uglyJsonStr) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement je = JsonParser.parseString(uglyJsonStr);
    String prettyJsonString = gson.toJson(je);
    return prettyJsonString;
  }
}
