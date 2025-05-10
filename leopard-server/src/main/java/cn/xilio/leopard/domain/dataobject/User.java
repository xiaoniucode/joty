package cn.xilio.leopard.domain.dataobject;


import cn.xilio.leopard.core.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.data.annotation.CreatedBy;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nickname", length = 50)
    private String nickname;

    /**
     * 密码
     */
    @Column(name = "password", length = 512, nullable = false)
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email", length = 50)
    private String email;

    /**
     * 头像
     */
    @Column(name = "avatar", length = 1024)
    private String avatar;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 逻辑删除标志
     */
    @Column(name = "deleted", nullable = false)
    private Integer deleted;

    /**
     * 创建者ID
     */
    @CreatedBy
    @Column(name = "created_by", length = 64, nullable = false)
    private String createdBy;

    /**
     * 更新者ID
     */
    @LiquibaseDataSource
    @Column(name = "updated_by", length = 64)
    private String updatedBy;
    /**
     * 备注
     */
    @Column(name = "remark", length = 50)
    private String remark;

    /**
     * API 开放接口密钥
     */
    @Column(name = "api_key", length = 512)
    private String apiKey;
    /**
     * 用户角色
     */
    @Column(name = "role", length = 256)
    private String role;
}
