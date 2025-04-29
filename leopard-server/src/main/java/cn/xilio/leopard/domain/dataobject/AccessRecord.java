package cn.xilio.leopard.domain.dataobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 访问记录实体
 */
@Data
@Entity
@Table(name = "access_record")
public class AccessRecord {

    /**
     * 访问记录ID
     */
    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * 访问IP
     */
    @Column(name = "ip_address", length = 45, nullable = false)
    private String ipAddress;

    /**
     * 操作系统
     */
    @Column(name = "os", length = 20)
    private String os;

    /**
     * 设备类型
     */
    @Column(name = "device_type", length = 20)
    private String deviceType;

    /**
     * 浏览器
     */
    @Column(name = "browser", length = 20)
    private String browser;

    /**
     * 访问来源
     */
    @Column(name = "referer", length = 1024)
    private String referer;

    /**
     * 短码
     */
    @Column(name = "short_code", length = 7, nullable = false)
    private String shortCode;

    /**
     * 访问时间
     */
    @Column(name = "access_time", nullable = false)
    private LocalDateTime accessTime;
}
