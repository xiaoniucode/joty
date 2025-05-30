package cn.xilio.leopard.adapter.portal.dto.request;


public record DailyAccessStatsRequest(
        /** 短链接编码 */
        String shortCode, 
        /** 开始日期 */
        String startDate,
        /** 结束日期 */
        String endDate
) {
}
