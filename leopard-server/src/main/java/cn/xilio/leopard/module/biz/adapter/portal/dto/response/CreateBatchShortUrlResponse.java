package cn.xilio.leopard.module.biz.adapter.portal.dto.response;

import java.util.List;

public record CreateBatchShortUrlResponse(
        List<CreateSingleShortUrlResponse>shortUrls
) {
}
