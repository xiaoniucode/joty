package cn.xilio.leopard.service;

import cn.xilio.leopard.adapter.portal.dto.response.GetStatsByUrlResponse;

public interface StatsService {
    GetStatsByUrlResponse getByUrl(String id, String userId);
}
