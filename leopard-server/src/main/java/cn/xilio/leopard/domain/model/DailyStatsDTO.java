package cn.xilio.leopard.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Data
public class DailyStatsDTO {
    private LocalDate accessDate;
    private Long uniqueVisitors;
    private Long totalVisits;

    // 构造函数需与SELECT字段顺序完全一致
    public DailyStatsDTO(Date accessDate, Long uniqueVisitors, Long totalVisits) {
        this.accessDate = accessDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.uniqueVisitors = uniqueVisitors;
        this.totalVisits = totalVisits;
    }

}
