package cn.xilio.leopard.adapter.admin.dto.request;

import cn.xilio.leopard.core.common.page.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageQueryRequest extends PageQuery {
    /** 用户名 */
    private String username;
}
