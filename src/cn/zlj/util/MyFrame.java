package cn.zlj.util;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame{
	/**
	 * ���ش���
	 */
	public void launchFrame() {
		setSize(Constant.GAME_WIDTH, Constant.GAME_HIGHT);
		setLocation(100, 100);
		setVisible(true);

		new PaintTread().start();// �����ػ��߳�

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
	 * ��һ���ػ����ڵ��߳��࣬��һ���ڲ��ࡣ
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
