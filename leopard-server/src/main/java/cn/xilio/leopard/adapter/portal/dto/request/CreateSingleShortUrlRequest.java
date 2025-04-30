package cn.xilio.leopard.adapter.portal.dto.request;


import cn.xilio.leopard.domain.dataobject.ShortUrl;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record CreateSingleShortUrlRequest(
        @NotEmpty(message = "短链接标题不能为空")
        String title,
        @NotEmpty(message = "原网址链接不能为空")
        @Pattern(
                regexp = "^(https?|ftp)://[a-zA-Z0-9.-]+(:[0-9]+)?(/[^\\s?#]*)?(\\?[^\\s#]*)?(#[^\\s]*)?$",
                message = "请输入有效的URL链接（需包含协议头）"
        )
        String originalUrl,
        @NotEmpty(message = "分组ID不能为空")
        String groupId,
        LocalDateTime expiredAt,
        String remark
) {
    public ShortUrl toEntity() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setTitle(title);
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setGroupId(groupId);
        shortUrl.setRemark(remark);
        shortUrl.setExpiredAt(expiredAt);
        return shortUrl;
    }
}
