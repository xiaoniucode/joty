package cn.xilio.leopard.service.impl;

import cn.xilio.leopard.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.AccessRecord;
import cn.xilio.leopard.domain.enums.StatsDimension;
import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.service.StatsService;
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
}
