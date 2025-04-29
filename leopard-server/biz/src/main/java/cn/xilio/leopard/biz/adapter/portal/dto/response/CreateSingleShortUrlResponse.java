package cn.xilio.leopard.biz.adapter.portal.dto.response;

public record CreateSingleShortUrlResponse(
        String shortUrl,
        String originalUrl,
        String qrcode

) {
}
