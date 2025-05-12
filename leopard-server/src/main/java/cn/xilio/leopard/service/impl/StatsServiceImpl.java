package cn.xilio.leopard.service.impl;

import cn.xilio.leopard.adapter.portal.dto.response.*;
import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private AccessRecordRepository accessRecordRepository;
    public GetStatsByUrlResponse getByUrl(String id, String userId) {
        List<IpAccessCountResponse> topAccessIps = accessRecordRepository.findTopAccessIps("1001");
        System.out.println(topAccessIps);
        return null;
    }

    @Override
    public List<IpAccessCountResponse> getTopAccessIps(String shortCode) {
        return accessRecordRepository.findTopAccessIps(shortCode);
    }

    @Override
    public List<OsAccessCountResponse> getOsAccess(String shortCode) {
        return accessRecordRepository.findOsAccessCount(shortCode);
    }

    @Override
    public List<BrowserAccessCountResponse> getBrowserAccess(String shortCode) {
        return accessRecordRepository.findBrowserDistribution(shortCode);
    }

    @Override
    public List<DeviceAccessCountResponse> getDeviceAccess(String shortCode) {
        return accessRecordRepository.findDeviceDistribution(shortCode);
    }
}
