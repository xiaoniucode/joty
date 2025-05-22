package cn.xilio.leopard.adapter.portal.dto.request;


public record DailyAccessStatsRequest(
        String shortCode, String startDate,
        String endDate
) {
}
