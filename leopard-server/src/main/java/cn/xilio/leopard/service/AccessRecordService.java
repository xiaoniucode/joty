package cn.xilio.leopard.service;


import cn.xilio.leopard.domain.dataobject.AccessRecord;

public interface AccessRecordService {
    /**
     * Record access records
     *
     * @param accessRecord AccessInformation
     */
    public void record(AccessRecord accessRecord);
}
