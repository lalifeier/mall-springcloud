package com.github.lalifeier.mall.cloud.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtil {
    /**
     * 最实用的正则
     */
    private static final String REGEX_ONLY_CHINESE_CHAR = "^[\u4e00-\u9fa5]+$";

    private static final String REGEX_ID_CARD_15 =
            "^((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\\d{4})((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229)(\\d{3})$";

    private static final String REGEX_ID_CARD_18 =
            "^((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\\d{4})((\\d{4}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229)(\\d{3}(\\d|X))$";

    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    private static final String REGEX_EMAIL =
            "^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$";

    private static final String REGEX_URI = "^(ht|f)(tp|tps)\\://[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3})?(/\\S*)?$";

    private static final String REGEX_IPV4 =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final String REGEX_IPV6 =
            "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    private static final String REGEX_TIME = "^([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";

    private static final String REGEX_DATE =
            "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$";

    private static final String REGEX_MOBILE = "^((\\+)?86\\s*)?((13[0-9])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$";

    private static final String REGEX_PHONE = "^(010|02[0-9])(\\s|-)\\d{8}|(0[3-9]\\d{2})(\\s|-)\\d{7}$";

    /**
     * 特定数字
     */
    private static final String REGEX_NUM_ALL = "^[\\d]*$";

    private static final String REGEX_NUM_NONE = "^[\\D]*$";

    private static final String REGEX_N_NUMBER = "^\\d{%d}$";

    private static final String REGEX_MORE_THAN_N_NUMBER = "^\\d{%d,}$";

    private static final String REGEX_M_TO_N_NUMBER = "^\\d{%d,%d}$";

    /**
     * 特定字符
     */
    private static final String REGEX_CHAR_EN_ALL = "^[A-Za-z]+$";

    private static final String REGEX_CHAR_EN_UPPER_ALL = "^[A-Z]+$";

    private static final String REGEX_CHAR_EN_LOWER_ALL = "^[a-z]+$";

    private static final String REGEX_CHAR_UNI_WORD_ALL = "^\\w+$";

    private static final String REGEX_CHAR_UNI_WORD_NONE = "^\\W+$";

    // 字符类型正则表达式
    // -----------------------------------------------------------------------------

    /**
     * 英文字符
     */
    public static final String EN_CHAR = "[A-Za-z]+";

    /**
     * 英文小写字符
     */
    public static final String EN_LOWER_CHAR = "[a-z]+";

    /**
     * 英文大写字符
     */
    public static final String EN_UPPER_CHAR = "[A-Z]+";

    /**
     * 中文字符
     */
    public static final String ZH_CHAR = "[\\u4e00-\\u9fa5]+";

    /**
     * 中文字、英文字母、数字和下划线
     */
    public static final String ALL_GENERAL_AND_ZH_CHAR = "[\u4E00-\u9FFF\\w]+";

    /**
     * 英文字母 、数字和下划线
     */
    public static final String GENERAL_CHAR = "\\w+";

    /**
     * 15位身份证号码
     */
    public static final String CITIZEN_ID15 =
            "((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\\d{4})((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229)(\\d{3})";

    /**
     * 18位身份证号码
     */
    public static final String CITIZEN_ID18 =
            "((1[1-5]|2[1-3]|3[1-7]|4[1-3]|5[0-4]|6[1-5])\\d{4})((\\d{4}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229)(\\d{3}(\\d|X))";

    /**
     * 日期正则
     */
    public static final String DATE =
            "(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))";

    // 数字类型正则表达式
    // -----------------------------------------------------------------------------

    /**
     * 邮件，符合RFC 5322规范，正则来自：http://emailregex.com/
     */
    public static final String EMAIL =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

    /**
     * 浮点数
     */
    public static final String FLOAT = "-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)";

    /**
     * 分组
     */
    public static final String GROUP_VAR = "\\$(\\d+)";

    /**
     * 16进制字符串
     */
    public static final String HEX = "[a-f0-9]+";

    /**
     * 整数
     */
    public static final String INTEGER = "-?[1-9]\\d*";

    /**
     * IP v4
     */
    public static final String IPV4 =
            "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

    /**
     * IP v6
     */
    public static final String IPV6 =
            "(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]+|::(ffff(:0{1,4})?:)?((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9]))";

    /**
     * MAC地址正则
     */
    public static final String MAC =
            "((([a-fA-F0-9][a-fA-F0-9]+[-]){5}|([a-fA-F0-9][a-fA-F0-9]+[:]){5})([a-fA-F0-9][a-fA-F0-9])$)|(^([a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9]+[.]){2}([a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9]))";

    /**
     * 移动电话
     * <p>
     * 匹配所有支持短信功能的号码（手机卡 + 上网卡）
     *
     * @see <a href="https://github.com/VincentSit/ChinaMobilePhoneNumberRegex/blob/master/README-CN.md">ChinaMobilePhoneNumberRegex</a>
     */
    public static final String MOBILE =
            "(?:\\+?86)?(\\s)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[01356789]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|6[567]\\d{2}|4[579]\\d{2})\\d{6}";

    /**
     * 浮点数
     */
    public static final String NEGATIVE_FLOAT = "-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)";

    /**
     * 负整数
     */
    public static final String NEGATIVE_INTEGER = "-[1-9]\\d*";

    /**
     * 不含英文字母 、数字和下划线
     */
    public static final String NONE_GENERAL_CHAR = "\\W+";

    /**
     * 不含数字字符
     */
    public static final String NONE_NUM_CHAR = "[\\D]*";

    /**
     * 非负浮点数
     */
    public static final String NOT_NEGATIVE_FLOAT = "[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0";

    // 应用场景类型正则表达式
    // -----------------------------------------------------------------------------

    /**
     * 非负整数
     */
    public static final String NOT_NEGATIVE_INTEGER = "[1-9]\\d*|0";

    /**
     * 全是数字字符
     */
    public static final String NUMBERS = "\\d+";

    /**
     * N 位的数字
     */
    public static final String NUMBER_DIGIT = "\\d{%d}";

    /**
     * 大于 N 位的数字
     */
    public static final String NUMBER_DIGIT_MORE = "\\d{%d,}";

    /**
     * 位数在 [M,N] 之间的数字
     */
    public static final String NUMBER_DIGIT_RANGE = "\\d{%d,%d}";

    /**
     * 固定电话
     */
    public static final String PHONE = "(\\d{3,4}-)?\\d{6,8}";

    /**
     * 中国车牌号码（包含绿色车牌）
     */
    public static final String PLATE_NUMBER =
            "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z][A-Z][DF]?[A-Z0-9]{4}[A-Z0-9挂学警港澳]";

    /**
     * 正浮点数
     */
    public static final String POSITIVE_FLOAT = "[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*";

    /**
     * 正整数
     */
    public static final String POSITIVE_INTEGER = "[1-9]\\d*";

    /**
     * 正则中需要被转义的关键字
     */
    public static final Set<Character> REGEX_ESCAPE_CHARS =
            Set.of('$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|');

    public static final String REGEX_MARKDOWN_IMAGE_TAG = "!\\[.+\\]";

    /**
     * 文本长度在指定范围内
     */
    public static final String TEXT_LENGTH_RANGE = ".{%d,%d}";

    /**
     * 时间正则
     */
    public static final String TIME = "([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])";

    /**
     * URL
     *
     * @see <a href="https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url">what-is-a-good-regular-expression-to-match-a-url</a>
     */
    public static final String URL = "(ht|f)(tp|tps)\\://[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3})?(/\\S*)?";

    /**
     * Http URL
     *
     * @see <a href="https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url">what-is-a-good-regular-expression-to-match-a-url</a>
     */
    public static final String URL_HTTP =
            "https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,}";

    /**
     * UUID
     */
    public static final String UUID = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";

    /**
     * 邮政编码
     */
    public static final String ZIP_CODE = "[1-9]\\d{5}(?!\\d)";

    public static Boolean match(String rex, String str) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
