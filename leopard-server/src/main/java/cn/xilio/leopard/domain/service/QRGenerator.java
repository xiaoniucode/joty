package cn.xilio.leopard.domain.service;


import cn.xilio.leopard.core.common.exception.BizException;
import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class QRGenerator {
    public static InputStream generateQRCode(String shortUrl) {
        try {
            BufferedImage image = new SimpleQrcodeGenerator().generate(shortUrl).getImage();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "png", imOut);
            return new ByteArrayInputStream(bs.toByteArray());
        } catch (Exception e) {
            throw new BizException("1006");
        }
    }
}
