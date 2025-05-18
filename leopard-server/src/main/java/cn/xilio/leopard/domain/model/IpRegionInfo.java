package cn.xilio.leopard.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IpRegionInfo {
    private String country;  // 国家
    private String region;   // 区域（通常为0或大区，如“华南”）
    private String province; // 省份
    private String city;     // 城市
    private String isp;      // 运营商

    // 解析方法
    public static IpRegionInfo parse(String regionStr) {
        String[] parts = regionStr.split("\\|");
        IpRegionInfo info = new IpRegionInfo();
        info.setCountry(parts.length > 0 ? parts[0] : "");
        info.setRegion(parts.length > 1 ? parts[1] : "");
        info.setProvince(parts.length > 2 ? parts[2] : "");
        info.setCity(parts.length > 3 ? parts[3] : "");
        info.setIsp(parts.length > 4 ? parts[4] : "");
        return info;
    }

}
