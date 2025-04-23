package cn.xilio.leopard.api.portal.dto.request;

import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import jakarta.validation.constraints.NotEmpty;

public record CreateSingleShortUrlRequest(
        @NotEmpty(message = "短链接标题不能为空")
        String title,
        @NotEmpty(message = "原网址链接不能为空")
        String originalUrl,
        @NotEmpty(message = "分组ID不能为空")
        String groupId,
        String remark
) {
    public ShortUrl toEntity() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setTitle(title);
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setGroupId(groupId);
        shortUrl.setRemark(remark);
        return shortUrl;
    }
}
