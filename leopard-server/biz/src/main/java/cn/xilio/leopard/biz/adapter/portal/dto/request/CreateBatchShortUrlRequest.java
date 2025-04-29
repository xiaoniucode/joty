package cn.xilio.leopard.biz.adapter.portal.dto.request;

import java.util.List;

public record CreateBatchShortUrlRequest(
        List<String> urls
) {
}
