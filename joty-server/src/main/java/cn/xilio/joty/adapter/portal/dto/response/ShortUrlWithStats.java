package cn.xilio.joty.adapter.portal.dto.response;

import cn.xilio.joty.domain.dataobject.ShortUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ShortUrlWithStats extends ShortUrl {
    private Long totalVisits;
    private Long todayVisits;
    private Long totalVisitors;  // 累计访问人数(基于IP)
    private Long todayVisitors;  // 今日访问人数(基于IP)
}
