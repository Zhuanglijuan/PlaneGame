package cn.zlj.util;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame{
	/**
	 * 加载窗口
	 */
	public void launchFrame() {
		setSize(Constant.GAME_WIDTH, Constant.GAME_HIGHT);
		setLocation(100, 100);
		setVisible(true);

		new PaintTread().start();// 启动重画线程

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	private Image offScrennImage = null;
	public void update(Graphics g) {
		if(offScrennImage == null) 
			offScrennImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HIGHT);
		Graphics gOff = offScrennImage.getGraphics();
		
		paint(gOff);
		g.drawImage(offScrennImage, 0, 0,null);
	}
	
	/**
	 * 定一个重画窗口的线程类，是一个内部类。
	 * 
	 * @author ASUS
	 *
	 */
	class PaintTread extends Thread {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
