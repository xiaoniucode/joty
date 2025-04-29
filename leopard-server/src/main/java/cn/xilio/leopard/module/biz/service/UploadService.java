package cn.xilio.leopard.module.biz.service;

import java.io.InputStream;

public interface UploadService {

    public String upload(InputStream inputStream,String fileType);
}
