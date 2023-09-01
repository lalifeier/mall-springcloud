package com.github.lalifeier.mall.cloud.common.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil extends DateUtils {

  /**
   * 日期格式(yyyy-MM-dd)
   */
  public final static String DATE_PATTERN = "yyyy-MM-dd";

  /**
   * 时间格式(HH:mm:ss)
   */
  public final static String TIME_PATTERN = "HH:mm:ss";

  /**
   * 日期时间格式(yyyy-MM-dd HH:mm:ss)
   */
  public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * 默认时区
   */
  public static final String DATE_TIMEZONE = "GMT+8";

  /**
   * 获取当前日期
   */
  public static Date getCurrentDate() {
    return new Date();
  }

  /**
   * 获取当前时间戳（毫秒）
   */
  public static long getCurrentTimestamp() {
    return System.currentTimeMillis();
  }

  /**
   * 获取当前时间戳（秒）
   */
  public static long getCurrentSecondTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(Date date) {
    return formatDate(date, DATE_PATTERN);
  }

  /**
   * 格式化时间为字符串
   */
  public static String formatTime(Date date) {
    return formatDate(date, TIME_PATTERN);
  }

  /**
   * 格式化日期时间为字符串
   */
  public static String formatDateTime(Date date) {
    return formatDate(date, DATE_TIME_PATTERN);
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(Date date, String pattern) {
    return DateFormatUtils.format(date, pattern);
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(LocalDateTime dateTime) {
    return formatDate(dateTime, DATE_PATTERN);
  }

  /**
   * 格式化时间为字符串
   */
  public static String formatTime(LocalDateTime dateTime) {
    return formatDate(dateTime, TIME_PATTERN);
  }

  /**
   * 格式化日期时间为字符串
   */
  public static String formatDateTime(LocalDateTime dateTime) {
    return formatDate(dateTime, DATE_TIME_PATTERN);
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(LocalDateTime dateTime, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return dateTime.format(formatter);
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(LocalDate date) {
    return formatDate(date, DATE_PATTERN);
  }

  /**
   * 格式化日期为字符串
   */
  public static String formatDate(LocalDate date, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return date.format(formatter);
  }

  /**
   * 格式化时间为字符串
   */
  public static String formatTime(LocalTime time) {
    return formatDate(time, TIME_PATTERN);
  }

  /**
   * 格式化时间为字符串
   */
  public static String formatDate(LocalTime time, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return time.format(formatter);
  }

  /**
   * 解析字符串为日期
   */
  public static Date parseDate(String dateStr, String pattern) throws ParseException {
    return DateUtils.parseDate(dateStr, pattern);
  }
}
