package cn.xilio.joty.service;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractUploadService implements UploadService {
    @Override
    public String upload(InputStream inputStream,String fileType)  {
        return "";
    }
}
