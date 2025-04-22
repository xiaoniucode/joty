package cn.xilio.leopard.domain.stats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "access_og")
public class AccessLog {
    @Id
    private String id;
}
