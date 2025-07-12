package cn.xilio.joty.core.common.util;

import cn.hutool.core.lang.UUID;

public class APIKeyGenerator {

    public static String generateKey(){
        return UUID.fastUUID().toString().replaceAll("-","");
    }
}
