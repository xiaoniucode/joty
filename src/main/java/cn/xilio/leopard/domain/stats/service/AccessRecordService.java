package cn.xilio.leopard.domain.stats.service;

import cn.xilio.leopard.domain.stats.model.AccessRecord;

public interface AccessRecordService {
    /**
     * Record access records
     *
     * @param accessRecord AccessInformation
     */
    public void record(AccessRecord accessRecord);
}
