package cn.xilio.leopard.api.portal.dto.response;

public record CreateSingleShortUrlResponse(
        String shortUrl,
        String originalUrl,
        String qrcode

) {
}
