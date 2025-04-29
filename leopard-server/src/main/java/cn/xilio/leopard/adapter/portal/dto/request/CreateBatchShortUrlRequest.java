package cn.xilio.leopard.adapter.portal.dto.request;

import java.util.List;

public record CreateBatchShortUrlRequest(
        List<String> urls
) {
}
