package cn.xilio.leopard.domain.group.model;

import cn.xilio.leopard.infrastructure.repository.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_group")
public class Group extends Auditable {
    /**
     * 分组ID
     */
    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @CreatedBy
    @Column(name = "created_by", length = 64, nullable = false)
    private String createdBy;

    /**
     * 更新者ID
     */
    @LastModifiedBy
    @Column(name = "updated_by", length = 64)
    private String updatedBy;
}
