package cn.xilio.leopard.domain.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "id", length = 32)
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
    private Byte status;

    /**
     * 逻辑删除标志
     */
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * 是否管理员
     */
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

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
}
