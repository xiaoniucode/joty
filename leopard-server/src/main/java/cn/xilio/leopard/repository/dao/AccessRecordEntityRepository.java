package cn.xilio.leopard.repository.dao;

import cn.xilio.leopard.adapter.portal.dto.response.BrowserAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.DeviceAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.IpAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.OsAccessCountResponse;
import cn.xilio.leopard.domain.dataobject.AccessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String> {
    @Query("SELECT new cn.xilio.leopard.adapter.portal.dto.response.IpAccessCountResponse(ar.ipAddress, COUNT(ar.id)) " +
            "FROM AccessRecord ar WHERE ar.shortCode = :shortCode " +
            "GROUP BY ar.ipAddress ORDER BY COUNT(ar.id) DESC")
    List<IpAccessCountResponse> findTopAccessIps(@Param("shortCode") String shortCode);

    @Query("SELECT new cn.xilio.leopard.adapter.portal.dto.response.OsAccessCountResponse(" +
            "COALESCE(ar.os, 'Unknown'), " +
            "COUNT(ar.id)) " +
            "FROM AccessRecord ar " +
            "WHERE ar.shortCode = :shortCode " +
            "GROUP BY ar.os " +
            "ORDER BY COUNT(ar.id) DESC")
    List<OsAccessCountResponse> findOsDistribution(@Param("shortCode") String shortCode);

    @Query("SELECT new cn.xilio.leopard.adapter.portal.dto.response.BrowserAccessCountResponse(" +
            "COALESCE(ar.browser, 'Unknown'), " +
            "COUNT(ar.id)) " +
            "FROM AccessRecord ar " +
            "WHERE ar.shortCode = :shortCode " +
            "GROUP BY ar.browser " +
            "ORDER BY COUNT(ar.id) DESC")
    List<BrowserAccessCountResponse> findBrowserDistribution(@Param("shortCode") String shortCode);
    @Query("SELECT new cn.xilio.leopard.adapter.portal.dto.response.DeviceAccessCountResponse(" +
            "COALESCE(ar.deviceType, 'Unknown'), " +
            "COUNT(ar.id)) " +
            "FROM AccessRecord ar " +
            "WHERE ar.shortCode = :shortCode " +
            "GROUP BY ar.deviceType " +
            "ORDER BY COUNT(ar.id) DESC")
    List<DeviceAccessCountResponse> findDeviceDistribution(@Param("shortCode") String shortCode);
}
