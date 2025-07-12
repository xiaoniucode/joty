package cn.xilio.joty.adapter.portal.dto.request;

public record StatsAccessCountRequest(
        /** 统计类型 */
        String type, 
        /** 短链接编码 */
        String shortCode
) {
}
