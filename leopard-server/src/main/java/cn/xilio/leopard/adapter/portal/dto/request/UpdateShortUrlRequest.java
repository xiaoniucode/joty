package cn.xilio.leopard.adapter.portal.dto.request;

import cn.xilio.leopard.domain.dataobject.ShortUrl;

public record UpdateShortUrlRequest (
        /** 短链接ID */
        String id,
        /** 标题 */
        String title,
        /** 分组ID */
        String groupId,
        /** 状态 */
        Integer status,
        /** 备注 */
        String remark
){

}
