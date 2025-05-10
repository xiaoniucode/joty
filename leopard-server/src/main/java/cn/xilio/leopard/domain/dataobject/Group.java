package cn.xilio.leopard.domain.dataobject;


import cn.xilio.leopard.core.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    /**
     * 分组所属用户
     */
    @Column(name = "user_id", length = 64, nullable = false)
    private String userId;
}
