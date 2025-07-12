package cn.xilio.joty.adapter.portal.dto.request;

import cn.xilio.joty.core.common.page.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShortUrlPageRequest extends PageQuery {
    /** 分组ID */
    private String groupId;
}
