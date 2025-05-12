package cn.xilio.leopard.service;

import cn.xilio.leopard.adapter.portal.dto.response.*;

import java.util.List;

public interface StatsService {
    GetStatsByUrlResponse getByUrl(String id, String userId);

    List<IpAccessCountResponse> getTopAccessIps(String shortCode);

    List<OsAccessCountResponse> getOsAccess(String shortCode);

    List<BrowserAccessCountResponse> getBrowserAccess(String shortCode);

    List<DeviceAccessCountResponse> getDeviceAccess(String shortCode);
}
