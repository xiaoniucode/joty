package cn.xilio.leopard.domain.shorturl.model;

import cn.xilio.leopard.infrastructure.repository.jpa.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "short_url")
@SQLDelete(sql = "UPDATE short_url SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ShortUrl extends Auditable {
    /**
     * 短链接ID
     */
    @Id
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 短链接标题
     */
    @Column(name = "title", nullable = true, length = 32)
    private String title;

    /**
     * 分组ID
     */
    @Column(name = "group_id", length = 32, nullable = false, unique = true)
    private String groupId;

    /**
     * 原始长链接
     */
    @Column(name = "original_url", nullable = false, length = 4096)
    private String originalUrl;

    /**
     * 二维码链接
     */
    @Column(name = "qr_url", length = 1024)
    private String qrUrl;

    /**
     * 短链接码
     */
    @Column(name = "short_code", nullable = false, unique = true, length = 7)
    private String shortCode;

    /**
     * 逻辑删除标志
     */
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Byte status = 1;

    /**
     * 域名
     */
    @Column(name = "domain", nullable = false, length = 32)
    private String domain;

    /**
     * 创建者ID
     */
    @CreatedBy
    @Column(name = "created_by", length = 32, nullable = false)
    private String createdBy;

    /**
     * 更新者ID
     */
    @LastModifiedBy
    @Column(name = "updated_by", length = 32)
    private String updatedBy;

    /**
     * 备注
     */
    @Column(name = "remark", length = 50)
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
