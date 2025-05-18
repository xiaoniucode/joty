package cn.xilio.leopard.repository;

import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.AccessRecord;

import java.util.List;

public interface AccessRecordRepository {
    public List<StatsResponse> findStatsCountByType(String shortCode, String type);

    PageResponse<AccessRecord> findAccessRecords(String shortCode, int page, int size);

    AccessRecord saveAccessRecord(AccessRecord record);
    boolean existsByIpAddressAndUserAgent(String ipAddress, String userAgent);
}
