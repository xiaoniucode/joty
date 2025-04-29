package cn.xilio.leopard.module.biz.domain.dataobject;


import cn.xilio.leopard.core.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "short_url")
public class ShortUrl extends Auditable {
    /**
     * 短链接ID
     */
    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * 短链接标题
     */
    @Column(name = "title", nullable = true, length = 64)
    private String title;

    /**
     * 分组ID
     */
    @Column(name = "group_id", length = 64, nullable = false)
    private String groupId;

    /**
     * Original Long Link
     */
    @Column(name = "original_url", nullable = false, length = 4096)
    private String originalUrl;
    /**
     * Short Link
     */
    @Column(name = "short_url", nullable = false, length = 100)
    private String shortUrl;
    /**
     * 二维码链接
     */
    @Column(name = "qr_url", length = 1024)
    private String qrUrl;

    /**
     * 短链接码
     */
    @Column(name = "short_code", nullable = false, length = 7)
    private String shortCode;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Byte status = 1;

    /**
     * 域名
     */
    @Column(name = "domain", nullable = false, length = 64)
    private String domain;

    /**
     * 创建者ID
     */
    @CreatedBy
    @Column(name = "created_by", length = 64, nullable = false)
    private String createdBy;

    /**
     * 更新者ID
     */
    @LastModifiedBy
    @Column(name = "updated_by", length = 64)
    private String updatedBy;

    /**
     * 过期时间，空表示永远不过期
     */
    @Column(name = "expire_at")
    private LocalDateTime expiredAt;
    /**
     * 备注
     */
    @Column(name = "remark", length = 50)
    private String remark;
}
