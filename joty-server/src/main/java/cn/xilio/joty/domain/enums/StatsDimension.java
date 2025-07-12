package cn.xilio.joty.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatsDimension {
    IP("IP", "IP统计"),
    OS("OS", "操作系统统计"),
    BROWSER("BROWSER", "浏览器统计"),
    DEVICE("DEVICE", "设备统计"),
    PROVINCE("PROVINCE", "省份统计"),
    COUNTRY("COUNTRY", "国家统计");

    private final String code;
    private final String desc;
    public static StatsDimension fromCode(String code) {
        return Arrays.stream(values())
                .filter(e -> e.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("无效的统计维度: " + code));
    }
}
