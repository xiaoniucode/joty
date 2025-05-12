package cn.xilio.leopard.service;

import cn.xilio.leopard.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;

import java.util.List;

public interface StatsService {


    List<StatsResponse> getAccessCountByType(StatsAccessCountRequest request);
}
