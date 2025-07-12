package cn.xilio.joty.service;

import cn.xilio.joty.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.joty.adapter.portal.dto.response.StatsResponse;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.enums.AccessUserType;
import cn.xilio.joty.domain.model.DailyStatsDTO;

import java.util.List;

public interface StatsService {
    public AccessUserType getAccessUserType(String shortCode,String ipAddress, String userAgent);

    List<StatsResponse> getAccessCountByType(StatsAccessCountRequest request);

    PageResponse<AccessRecord> records(String shortCode, PageQuery request);

    public List<DailyStatsDTO> getDailyAccessStats(String startDate, String endDate, String shortCode);
}
