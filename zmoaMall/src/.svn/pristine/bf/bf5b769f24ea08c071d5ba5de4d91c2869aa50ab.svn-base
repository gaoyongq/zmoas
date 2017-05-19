package com.zm.mall.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 * Created by Bochao on 2017/4/5.
 */
@Component
public class QRCodeUtils {

    /**
     * 生产二维码
     * @param content 二维码内容。例：http://www.zhongguozhaomei.com/
     * @param width 宽度。例：100
     * @param height 高度。例：100
     * @param format 图像格式。例：png
     * @param outputStream 输出目标流
     * @throws WriterException
     * @throws IOException
     */
    public void generateQRCode(String content, int width, int height, String format, OutputStream outputStream) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
    }
}
