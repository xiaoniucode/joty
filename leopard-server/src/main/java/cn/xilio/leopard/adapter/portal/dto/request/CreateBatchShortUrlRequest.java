package cn.xilio.leopard.adapter.portal.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateBatchShortUrlRequest(
        @NotNull
        List<String> urls,
        LocalDate expiredAt,
        @NotEmpty
        String groupId
) {
}
