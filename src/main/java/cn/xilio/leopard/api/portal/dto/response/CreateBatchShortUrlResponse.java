package cn.xilio.leopard.api.portal.dto.response;

import java.util.List;

public record CreateBatchShortUrlResponse(
        List<CreateSingleShortUrlResponse>shortUrls
) {
}
