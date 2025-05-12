package cn.xilio.leopard.repository.impl;



import cn.xilio.leopard.adapter.portal.dto.response.BrowserAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.DeviceAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.IpAccessCountResponse;
import cn.xilio.leopard.adapter.portal.dto.response.OsAccessCountResponse;
import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.repository.dao.AccessRecordEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {
    @Resource
    private AccessRecordEntityRepository accessRecordEntityRepository;


    @Override
    public List<IpAccessCountResponse> findTopAccessIps(String shortCode) {
       return accessRecordEntityRepository.findTopAccessIps(shortCode);

    }

    @Override
    public List<OsAccessCountResponse> findOsAccessCount(String shortCode) {
        return accessRecordEntityRepository.findOsDistribution(shortCode);
    }

    @Override
    public List<BrowserAccessCountResponse> findBrowserDistribution(String shortCode) {
        return accessRecordEntityRepository.findBrowserDistribution(shortCode);
    }

    @Override
    public List<DeviceAccessCountResponse> findDeviceDistribution(String shortCode) {
        return accessRecordEntityRepository.findDeviceDistribution(shortCode);
    }
}

