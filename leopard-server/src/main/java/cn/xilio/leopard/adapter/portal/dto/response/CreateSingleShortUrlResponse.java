package cn.xilio.leopard.adapter.portal.dto.response;

public record CreateSingleShortUrlResponse(
        String shortUrl,
        String originalUrl,
        String qrcode

) {
}
