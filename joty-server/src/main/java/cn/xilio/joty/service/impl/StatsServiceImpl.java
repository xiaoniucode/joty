package cn.xilio.joty.service.impl;

import cn.xilio.joty.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.joty.adapter.portal.dto.response.StatsResponse;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.enums.AccessUserType;
import cn.xilio.joty.domain.enums.StatsDimension;
import cn.xilio.joty.domain.model.DailyStatsDTO;
import cn.xilio.joty.repository.AccessRecordRepository;
import cn.xilio.joty.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private AccessRecordRepository accessRecordRepository;

    @Override
    public List<StatsResponse> getAccessCountByType(StatsAccessCountRequest request) {
        String type = StatsDimension.fromCode(request.type()).getCode();
        return accessRecordRepository.findStatsCountByType(request.shortCode(), type);
    }

    @Override
    public PageResponse<AccessRecord> records(String shortCode, PageQuery request) {
        return accessRecordRepository.findAccessRecords(shortCode,request.getPage(),request.getSize());
    }

    @Override
    public List<DailyStatsDTO> getDailyAccessStats(String startDate, String endDate, String shortCode) {
        return accessRecordRepository.getDailyAccessStats(startDate,endDate,shortCode);
    }

    public AccessUserType getAccessUserType(String shortCode,String ipAddress, String userAgent) {
       return !accessRecordRepository.existsByIpAddressAndUserAgent(shortCode,ipAddress, userAgent)? AccessUserType.NEW_USER: AccessUserType.OLD_USER;

    }
}
