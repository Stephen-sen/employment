package com.zhangmin.base.dao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.action.support.BaseController;

public class PictureCheckCode extends BaseController {

	// 生成色彩验证码的代码
	public void getVerify() throws ServletException, IOException {
		Random random = new Random();
		// 1、绘制背景图片
		// 设置不缓冲图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应图片
		response.setContentType("image/jpeg");
		int width = 75;
		int height = 25;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D) g;// 创建Graphics2D对象

		Font mFont = new Font("宋体", Font.ITALIC, 20);// 设置字体样式
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // 绘制背景
		g.setFont(mFont); // 设置字体
		g.setColor(getRandColor(180, 200));

		// 2、绘制干扰线
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line);
		}

		// 3、随机生成中文、英文、数字的验证文字
		String sRand = "";
		// 输出随机的验证文字
		String ctmp = "";
		int itmp = 0;
		ArrayList<Integer> alist = new ArrayList<Integer>();
		alist.add(0);
		alist.add(1);
		alist.add(2);
		alist.add(3);

		for (int i = 0; i < 4; i++) {
			int temp = random.nextInt(4 - i);
			int one = alist.remove(temp);

			if (one == 0) {
				itmp = random.nextInt(26) + 65; // 获得A~Z的字母
				ctmp = String.valueOf((char) itmp);
				// System.out.println("生成的字母"+ctmp);
			}

			if (one == 1) {
				itmp = random.nextInt(26) + 65; // 获得A~Z的字母
				ctmp = String.valueOf((char) itmp);
				// System.out.println("生成的字母"+ctmp);
			}

			if (one == 2) {
				itmp = random.nextInt(10) + 48;// 生成数字
				ctmp = String.valueOf((char) itmp);
				// System.out.println("生成的数字"+ctmp);
			}

			if (one == 3) {
				itmp = random.nextInt(26) + 97; // 获得a~z的字母
				ctmp = String.valueOf((char) itmp);
				// System.out.println("生成的字母"+ctmp);
			}

			// case 2:
			// if(i==1){
			// // 1、生成机内码
			// String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			// "9",
			// "a", "b", "c", "d", "e", "f" };
			// // 生成第1位区码
			// int r1 = random.nextInt(3) + 11;// 生成11~14之间的随机数
			// String str_r1 = rBase[r1];
			// // 生成第2位的区码
			// int r2 = 0;
			// if (r1 == 14) {
			// r2 = random.nextInt(7);
			// } else {
			// r2 = random.nextInt(16);
			// }
			// String str_r2 = rBase[r2];
			// // 生成第1位的位码
			// int r3 = random.nextInt(6) + 10;
			// String str_r3 = rBase[r3];
			// // 生成第2位的位码
			// int r4 = 0;
			// if (r3 == 10) {
			// r4 = random.nextInt(15) + 1;
			// } else if (r3 == 15) {
			// r4 = random.nextInt(15);
			// } else {
			// r4 = random.nextInt(16);
			// }
			// String str_r4 = rBase[r4];
			//
			// // 2、将生成的机内码转换成汉字
			// byte[] bytes = new byte[2];
			// String str_r12 = str_r1 + str_r2;
			// int tempHigh = Integer.parseInt(str_r12, 16);
			// bytes[0] = (byte) tempHigh;
			//
			// String str_r34 = str_r3 + str_r4;
			// int tempLow = Integer.parseInt(str_r34, 16);
			// bytes[1] = (byte) tempLow;
			// // 根据字节数组生成汉字
			// ctmp = new String(bytes);
			// System.out.println("生成的汉字：" + ctmp);
			// }
			// break;

			// default:

			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			// 将生成的随机数随机缩放并旋转指定角度
			// 将文字旋转任意角度
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8.7);
			// 将文字缩放
			int scaleSize = (int) (random.nextInt() + 20f);
			// if(scaleSize>1f) scaleSize=1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.drawString(ctmp, 15 * i + 18, 14);
		}
		// 将生成的验证码保存到session中
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		// 输出生成的验证码图片
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	// 随机生成内容的颜色
	public Color getRandColor(int s, int c) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (c > 255)
			c = 255;
		int r = s + random.nextInt(c - s);// 随机生成RGB中r的值
		int g = s + random.nextInt(c - s);
		int b = s + random.nextInt(c - s);

		return new Color(r, g, b);
	}

}
