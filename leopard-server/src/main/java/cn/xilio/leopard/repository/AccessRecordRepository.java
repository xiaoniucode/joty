package cn.xilio.leopard.repository;

import cn.xilio.leopard.adapter.portal.dto.response.BrowserAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.DeviceAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.IpAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.OsAccessCountResponse;

import java.util.List;

public interface AccessRecordRepository {
    List<IpAccessCountResponse> findTopAccessIps(String shortCode);

    List<OsAccessCountResponse> findOsAccessCount(String shortCode);

    List<BrowserAccessCountResponse> findBrowserDistribution(String shortCode);

    List<DeviceAccessCountResponse> findDeviceDistribution(String shortCode);
}
