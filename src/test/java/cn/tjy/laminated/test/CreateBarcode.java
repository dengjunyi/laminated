package cn.tjy.laminated.test;

/*import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateBarcode {
    public static void main(String[] args) throws IOException {
       /* BarcodeSettings settings = new BarcodeSettings();//创建BarcodeSettings实例
        settings.setType(BarCodeType.Code_128);//指定条码类型
        settings.setData("123456789");//设置条码数据
        settings.setData2D("123456789");//设置条码显示数据
        settings.setShowTextOnBottom(true);//设置数据文本显示在条码底部
        settings.setX(0.8f);//设置黑白条宽度
        settings.setImageHeight(50);//设置生成的条码图片高度
        settings.setImageWidth(70);//设置生成的条码图片宽度
        settings.hasBorder(true);//设置边框可见
        settings.setBorderColor(new Color(135,206,250));//设置条码边框颜色
        settings.setBorderWidth(1);//设置条码边框宽度
        settings.setBackColor(new Color(240,255,255));//设置条码背景色

        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);//创建BarCodeGenerator实例
        BufferedImage bufferedImage = barCodeGenerator.generateImage();//根据settings生成图像数据，保存至BufferedImage实例
        ImageIO.write(bufferedImage, "png", new File("CODE128.png"));//保存条码为PNG图片
        System.out.println("Complete!");*/
    }
}