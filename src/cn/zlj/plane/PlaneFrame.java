package cn.zlj.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import cn.zlj.util.Constant;
import cn.zlj.util.GameUtil;
import cn.zlj.util.MyFrame;

public class PlaneFrame extends MyFrame {
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/plane.png", 50, 50);
	Date starttime;
	Date endtime;
	Explode bao;
	ArrayList bulletList = new ArrayList();

	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);

			boolean peng = b.getRect().intersects(p.getRect());
			if (peng) {
				p.setLive(false);// 飞机阵亡！
				if (bao == null) {
					endtime = new Date();
					bao = new Explode(p.x, p.y);
					scrennStop();
				}
				bao.draw(g);
				break;

			}
			if (!p.isLive()) {
				printInfo(g, "Game Over", 50, Color.RED, 100, 200);
				long period = (endtime.getTime() - starttime.getTime()) / 1000;
				printInfo(g, "时间:" + period + "秒", 20, Color.white, 120, 260);

			}

		}
	}
	public void scrennStop() {
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet b = (Bullet) bulletList.get(i);
			b.speed = 0;
		}
	}

	public void printInfo(Graphics g, String str, int size, Color color, int x, int y) {
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("宋体", Font.BOLD, size);
		g.setFont(f);
		g.drawString(str, x, y);
		g.setColor(c);
	}

	public static void main(String[] args) {
		new PlaneFrame().launchFrame();
	}

	public void launchFrame() {

		super.launchFrame();
		// 键盘监听
		addKeyListener(new KeyMonitor());
		// 生成子弹

		for (int i = 0; i < 30; i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		starttime = new Date();
	}

	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			p.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			p.minusDirection(e);
		}

	}
}
