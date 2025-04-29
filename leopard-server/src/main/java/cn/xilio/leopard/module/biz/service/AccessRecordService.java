package cn.xilio.leopard.module.biz.service;


import cn.xilio.leopard.module.biz.domain.dataobject.AccessRecord;

public interface AccessRecordService {
    /**
     * Record access records
     *
     * @param accessRecord AccessInformation
     */
    public void record(AccessRecord accessRecord);
}
