package cn.xilio.joty.service.impl;

import cn.xilio.joty.service.AbstractUploadService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@ConditionalOnProperty(name = "joty.file.uploadModel", havingValue = "MinIO")
public class MinIOUploadService extends AbstractUploadService {

    @Override
    public String upload(MultipartFile file) {
        return "";
    }
}
