package cn.xilio.leopard.domain.file.service.impl;

import cn.xilio.leopard.domain.file.service.AbstractUploadService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Primary
@Component
@ConditionalOnProperty(name = "leopard.file.uploadModel", havingValue = "Local")
public class LocalUploadService extends AbstractUploadService {
    @Override
    public String upload(InputStream inputStream) {
        return super.upload(inputStream);
    }
}
