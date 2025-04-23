package cn.xilio.leopard.domain.group.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "group")
public class Group {
    /**
     * 分组ID
     */
    @Id
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 分组名称
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 备注
     */
    @Column(name = "remark", length = 128)
    private String remark;

    /**
     * 创建者ID
     */
    @Column(name = "created_by", length = 32, nullable = false)
    private String createdBy;

    /**
     * 更新者ID
     */
    @Column(name = "updated_by", length = 32)
    private String updatedBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
