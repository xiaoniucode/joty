package cn.xilio.leopard.adapter.portal.validator;

import java.util.regex.Pattern;

public class ShortUrlValidator {

    // 增强版正则（支持IDN国际化域名）
    private static final String URL_REGEX =
            "^(https?|ftp)://" +
                    "(([a-z0-9-]+\\.)+[a-z]{2,6}|" +  // 域名
                    "(([0-9]{1,3}\\.){3}[0-9]{1,3}))" +  // 或IPv4
                    "(:[0-9]{1,5})?" +  // 端口
                    "(/[-a-z0-9_\\@\\:\\+\\~#%\\?\\&=\\;\\.]*)*$";  // 路径参数

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * 验证URL格式有效性
     *
     * @param url 待验证的URL
     * @return 是否通过基础格式验证
     */
    public static boolean validateFormat(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return URL_PATTERN.matcher(url).matches();
    }
}
