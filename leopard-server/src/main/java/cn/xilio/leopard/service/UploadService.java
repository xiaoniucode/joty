package cn.xilio.leopard.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface UploadService {

    public String upload(InputStream inputStream,String fileType);

    String upload(MultipartFile file);
}
