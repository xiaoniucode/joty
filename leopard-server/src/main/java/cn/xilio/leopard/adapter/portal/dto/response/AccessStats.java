package cn.xilio.leopard.adapter.portal.dto.response;

import lombok.Data;

@Data
public    class AccessStats {
    private Long totalVisits;
    private Long todayVisits;
    private Long totalVisitors;  // 累计访问人数(基于IP)
    private Long todayVisitors;  // 今日访问人数(基于IP)

    public AccessStats(Long totalVisits, Long todayVisits, Long totalVisitors, Long todayVisitors) {
        this.totalVisits = totalVisits;
        this.todayVisits = todayVisits;
        this.totalVisitors = totalVisitors;
        this.todayVisitors = todayVisitors;
    }
}
