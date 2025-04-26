package cn.xilio.leopard.api.portal.dto.request;

import java.util.List;

public record CreateBatchShortUrlRequest(
        List<String> urls
) {
}
