package cn.xilio.joty.repository;

import cn.xilio.joty.adapter.portal.dto.response.StatsResponse;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.model.DailyStatsDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessRecordRepository {
    public List<StatsResponse> findStatsCountByType(String shortCode, String type);

    PageResponse<AccessRecord> findAccessRecords(String shortCode, int page, int size);

    AccessRecord saveAccessRecord(AccessRecord record);

    boolean existsByIpAddressAndUserAgent(String shortCode, String ipAddress, String userAgent);

    List<DailyStatsDTO> getDailyAccessStats(String startDate, String endDate, String shortCode);
}
