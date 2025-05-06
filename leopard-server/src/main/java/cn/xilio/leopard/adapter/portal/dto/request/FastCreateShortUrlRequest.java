package cn.xilio.leopard.adapter.portal.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record FastCreateShortUrlRequest(@NotEmpty String url) {
}
