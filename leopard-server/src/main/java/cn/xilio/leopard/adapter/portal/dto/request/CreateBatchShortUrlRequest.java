package cn.xilio.leopard.adapter.portal.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateBatchShortUrlRequest(
        /**
         * 需要生成短链接的原始URL列表
         */
        @NotNull
        List<String> urls,
        /**
         * 短链接过期时间
         */
        LocalDate expiredAt,
        /**
         * 短链接所属分组ID
         */
        @NotEmpty
        String groupId
) {
}
