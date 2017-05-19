package com.zm.mall.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * <p>
 * Title: ImageUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Email: icerainsoft@hotmail.com
 * </p>
 * 
 * @author Ares
 * @date 2014��10��28�� ����10:24:26
 */
public class ImageUtil {

	private static String DEFAULT_THUMB_PREVFIX = "thumb_";
	private static String DEFAULT_CUT_PREVFIX = "cut_";
	private static Boolean DEFAULT_FORCE = false;

	/**
	 * <p>
	 * Title: cutImage
	 * </p>
	 * <p>
	 * Description: ����ԭͼ�����size��ȡ�ֲ�ͼƬ
	 * </p>
	 * 
	 * @param srcImg
	 *            ԴͼƬ
	 * @param output
	 *            ͼƬ�����
	 * @param rect
	 *            ��Ҫ��ȡ���ֵ�����ʹ�С
	 */
	public void cutImage(File srcImg, OutputStream output, java.awt.Rectangle rect) {
		if (srcImg.exists()) {
			FileInputStream fis = null;
			ImageInputStream iis = null;
			try {
				fis = new FileInputStream(srcImg);
				// ImageIO ֧�ֵ�ͼƬ���� : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
				String suffix = null;
				// ��ȡͼƬ��׺
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
				} // ���ͺ�ͼƬ��׺ȫ��Сд��Ȼ���жϺ�׺�Ƿ�Ϸ�
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
					System.err.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
					return;
				}
				// ��FileInputStream ת��ΪImageInputStream
				iis = ImageIO.createImageInputStream(fis);
				// ����ͼƬ���ͻ�ȡ�������͵�ImageReader
				ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				param.setSourceRegion(rect);
				BufferedImage bi = reader.read(0, param);
				ImageIO.write(bi, suffix, output);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
					if (iis != null)
						iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("the src image is not exist.");
		}
	}

	public void cutImage(File srcImg, OutputStream output, int x, int y, int width, int height) {
		cutImage(srcImg, output, new java.awt.Rectangle(x, y, width, height));
	}

	public void cutImage(File srcImg, String destImgPath, java.awt.Rectangle rect) {
		File destImg = new File(destImgPath);
		if (destImg.exists()) {
			String p = destImg.getPath();
			try {
				if (!destImg.isDirectory())
					p = destImg.getParent();
				if (!p.endsWith(File.separator))
					p = p + File.separator;
				cutImage(srcImg, new java.io.FileOutputStream(
						p + DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + srcImg.getName()), rect);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else
			System.err.println("the dest image folder is not exist.");
	}

	public void cutImage(File srcImg, String destImg, int x, int y, int width, int height) {
		cutImage(srcImg, destImg, new java.awt.Rectangle(x, y, width, height));
	}

	public void cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
		cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width, height));
	}

	/**
	 * <p>
	 * Title: thumbnailImage
	 * </p>
	 * <p>
	 * Description: ����ͼƬ·����������ͼ
	 * </p>
	 * 
	 * @param imagePath
	 *            ԭͼƬ·��
	 * @param w
	 *            ����ͼ��
	 * @param h
	 *            ����ͼ��
	 * @param prevfix
	 *            ��������ͼ��ǰ׺
	 * @param force
	 *            �Ƿ�ǿ�ư��տ����������ͼ(���Ϊfalse����������ѱ�������ͼ)
	 */
	public void thumbnailImage(File srcImg, OutputStream output, int w, int h, String prevfix, boolean force) {
		if (srcImg.exists()) {
			try {
				// ImageIO ֧�ֵ�ͼƬ���� : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
				String suffix = null;
				// ��ȡͼƬ��׺
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
				} // ���ͺ�ͼƬ��׺ȫ��Сд��Ȼ���жϺ�׺�Ƿ�Ϸ�
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
					System.err.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
					return;
				}
				System.err.printf("target image's size, width:%d, height:%d.", w, h);
				Image img = ImageIO.read(srcImg);
				// ����ԭͼ��Ҫ�������ͼ�������ҵ�����ʵ�����ͼ����
				if (!force) {
					int width = img.getWidth(null);
					int height = img.getHeight(null);
					if ((width * 1.0) / w < (height * 1.0) / h) {
						if (width > w) {
							h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
							System.err.printf("change image's height, width:%d, height:%d.", w, h);
						}
					} else {
						if (height > h) {
							w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
							System.err.printf("change image's width, width:%d, height:%d.", w, h);
						}
					}
				}
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
				g.dispose();
				// ��ͼƬ������ԭĿ¼������ǰ׺
				ImageIO.write(bi, suffix, output);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("the src image is not exist.");
		}
	}

	public void thumbnailImage(File srcImg, int w, int h, String prevfix, boolean force) {
		String p = srcImg.getAbsolutePath();
		try {
			if (!srcImg.isDirectory())
				p = srcImg.getParent();
			if (!p.endsWith(File.separator))
				p = p + File.separator;
			thumbnailImage(srcImg, new java.io.FileOutputStream(p + prevfix + srcImg.getName()), w, h, prevfix, force);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force) {
		File srcImg = new File(imagePath);
		thumbnailImage(srcImg, w, h, prevfix, force);
	}

	public void thumbnailImage(String imagePath, int w, int h, boolean force) {
		thumbnailImage(imagePath, w, h, DEFAULT_THUMB_PREVFIX, DEFAULT_FORCE);
	}

	public void thumbnailImage(String imagePath, int w, int h) {
		thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
	}

	public static void main(String[] args) {
		new ImageUtil().thumbnailImage("imgs/Tulips.jpg", 150, 100);
		new ImageUtil().cutImage("imgs/Tulips.jpg", "imgs", 250, 70, 300, 400);
	}

}
