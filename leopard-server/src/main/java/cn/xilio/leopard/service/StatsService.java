package cn.xilio.leopard.service;

import cn.xilio.leopard.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.AccessRecord;
import cn.xilio.leopard.domain.enums.AccessUserType;

import java.util.List;

public interface StatsService {
    public AccessUserType getAccessUserType(String shortCode,String ipAddress, String userAgent);

    List<StatsResponse> getAccessCountByType(StatsAccessCountRequest request);

    PageResponse<AccessRecord> records(String shortCode, PageQuery request);
}
