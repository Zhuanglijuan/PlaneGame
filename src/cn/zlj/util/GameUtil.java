package cn.zlj.util;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏开发中常用的工具类
 * @author ASUS
 *
 */

public class GameUtil {
	private GameUtil() {
		
	}
	public static Image getImage(String path){
		BufferedImage bi=null;
		try {
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi = javax.imageio.ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
