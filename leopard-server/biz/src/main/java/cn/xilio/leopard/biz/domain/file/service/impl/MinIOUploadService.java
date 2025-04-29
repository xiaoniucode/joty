package cn.xilio.leopard.biz.domain.file.service.impl;

import cn.xilio.leopard.biz.domain.file.service.AbstractUploadService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "leopard.file.uploadModel", havingValue = "MinIO")
public class MinIOUploadService extends AbstractUploadService {

}
