package cn.xilio.leopard.service;

import cn.xilio.leopard.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.AccessRecord;

import java.util.List;

public interface StatsService {


    List<StatsResponse> getAccessCountByType(StatsAccessCountRequest request);

    PageResponse<AccessRecord> records(String shortCode,PageQuery request);
}
