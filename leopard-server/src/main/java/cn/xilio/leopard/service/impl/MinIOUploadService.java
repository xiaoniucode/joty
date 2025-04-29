package cn.xilio.leopard.service.impl;

import cn.xilio.leopard.service.AbstractUploadService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "leopard.file.uploadModel", havingValue = "MinIO")
public class MinIOUploadService extends AbstractUploadService {

}
