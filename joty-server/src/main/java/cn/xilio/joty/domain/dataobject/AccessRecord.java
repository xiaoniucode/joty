package cn.xilio.joty.domain.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 国家
     */
    @Column(name = "country", length = 30)
    private String country;
    /**
     * 省份
     */
    @Column(name = "province", length = 30)
    private String province;
    /**
     * 城市
     */
    @Column(name = "city", length = 100)
    private String city;

    /**
     * 短码
     */
    @Column(name = "short_code", length = 7, nullable = false)
    private String shortCode;
    /**
     * User-Agent
     */
    @Column(name = "ua"  )
    private String userAgent;

    /**
     * 用户类型：老用户或新用户
     */
    @Column(name = "user_type"  )
    private String userType;
    /**
     * 网络类型
     */
    @Column(name = "networkType"  )
    private String networkType;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss", timezone = "GMT+8")
    @Column(name = "access_time", nullable = false)
    private LocalDateTime accessTime;
}
