package cn.xilio.leopard.domain.service;


import cn.xilio.leopard.core.common.util.MD5;
import org.springframework.stereotype.Component;

/**
 * 随机码生成
 */
@Component
@SuppressWarnings("all")
public class ShortCodeGenerator {
    private static final String randomStr = "leopard.xilio.cn";

    public String genShortCode(String url, String randomStr) {
        return shortUrl(url, randomStr)[0];
    }

    public String genShortCode(String url) {
        return shortUrl(url, randomStr)[0];
    }

    private String[] shortUrl(String url, String randomStr) {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        String encrypt = (MD5.encrypt(randomStr + url));
        String hex = encrypt;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            String t = hex.substring(i * 8, i * 8 + 8);
            long lHex = 0x3FFFFFFF & Long.parseLong(t, 16);
            String charTemp = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHex;
                charTemp += chars[(int) index];
                lHex = lHex >> 5;
            }
            resUrl[i] = charTemp;
        }
        return resUrl;
    }
}
