package cn.xilio.leopard.domain.file.service;

import java.io.InputStream;

public abstract class AbstractUploadService implements UploadService {
    @Override
    public String upload(InputStream inputStream) {
        return "";
    }
}
