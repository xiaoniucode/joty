package cn.xilio.leopard.domain.file.service;

import java.io.InputStream;

public interface UploadService {

    public String upload(InputStream inputStream,String fileType);
}
