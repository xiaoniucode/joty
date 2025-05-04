package cn.xilio.leopard.adapter.portal.dto.request;

import cn.xilio.leopard.domain.dataobject.ShortUrl;

public record UpdateShortUrlRequest (
        String id,
        String title,
        String groupId,
        Integer status,
        String remark
){

}
