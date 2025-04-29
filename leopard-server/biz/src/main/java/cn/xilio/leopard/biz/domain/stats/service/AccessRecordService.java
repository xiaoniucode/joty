package cn.xilio.leopard.biz.domain.stats.service;


import cn.xilio.leopard.biz.domain.stats.model.AccessRecord;

public interface AccessRecordService {
    /**
     * Record access records
     *
     * @param accessRecord AccessInformation
     */
    public void record(AccessRecord accessRecord);
}
