package cn.xilio.joty.adapter.portal.dto.response;

public record StatsResponse(
    /** 统计类型 */
    String type,
    /** 名称 */
    String name,
    /** 数量 */
    Long count
) {}
