package cn.xilio.leopard.repository;

import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;

import java.util.List;

public interface AccessRecordRepository {
    public List<StatsResponse> findStatsCountByType(String shortCode, String type);

}
