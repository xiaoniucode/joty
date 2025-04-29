package cn.xilio.leopard.adapter.portal.dto.response;

import java.util.List;

public record CreateBatchShortUrlResponse(
        List<CreateSingleShortUrlResponse>shortUrls
) {
}
