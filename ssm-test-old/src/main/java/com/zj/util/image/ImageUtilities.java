package com.zj.util.image;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import org.apache.commons.io.FilenameUtils;

/*
 * @author ZhouJian
 * @modifyTime  2019��12��30�� 10:29:36
 * @finished  false
 * @finishTime   
 * @version  1.0
 */

/**
 * <p>
 * ͼƬ�����࣬������ͼƬ�Ļ���������ߴ硢��ʽ���޸� ��Ϊ��չ�����������ˮӡ���ܣ�ʹ�ø��ӷ���
 * <p>
 */
public class ImageUtilities {

	private ImageUtilities() {
	}

	/**
	 * ��File��ȡBufferedImage����
	 * 
	 * @param  file  ͼƬFile
	 */
	public static BufferedImage getImageFromLocal(File file) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bufferedImage;
	}
	
	/**
	 * ��BufferedImage���������������ţ��������µ�BufferedImage
	 * 
	 * @param  originImage  ������BufferedImage����
	 * @param  factor  ��������
	 * */
	public static BufferedImage getScaledImage(BufferedImage originImage,double factor) {
		BufferedImage biTemp;
		Image temp;
		
		int newWidth,newHeight;
		newWidth = (int) (originImage.getWidth(null) * factor);
		newHeight = (int) (originImage.getHeight(null) * factor);
		
		temp = originImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		biTemp = new BufferedImage(newWidth,newHeight,originImage.getType());
		
		Graphics g = biTemp.createGraphics();
		g.drawImage(temp, 0, 0,null);
		g.dispose();
		
		return biTemp;
	}

	/**
	 * ��·������ȡBufferedImage����
	 * 
	 * 
	 * @param  pathname  ͼƬ����·����
	 */
	public static BufferedImage getImageFromLocal(String pathname) {
		return getImageFromLocal(new File(pathname));
	}
		
	
	/**
	 * see @{@link #scaleLocal2LocalByFactor(File, File, double)}
	 * */
	public static void scaleLocal2LocalByFactor(String scrPath, String srcPath, double factor) {
		scaleLocal2LocalByFactor(new File(scrPath),new File(srcPath),factor);
	}

	/**
	 * �����������ţ����ճ������ԭʼ����*factor(��������)
	 * 
	 * @param  srcFile  ����ļ��������׳�java.io.FileNotFoundException
	 * @param  desFile  ����ļ��������׳�java.io.FileNotFoundException
	 * @param factor ��������
	 * 
	 */
	public static void scaleLocal2LocalByFactor(File srcFile, File desFile, double factor) {

		if (factor <= 0) {
			throw new IllegalArgumentException("Parameter factor cannot be less than or equal to 0");
		}

		BufferedImage biSrc = null;
		Image temp = null;
		try {
			biSrc = getImageFromLocal(srcFile);
			
			int newWidth = (int) (biSrc.getWidth() * factor);
			int newHeight = (int) (biSrc.getHeight() * factor);

			temp = biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			
			// TYPE_3BYTE_BGR(5) - jpg
			// TYPE_4BYTE_ABGR(6) - png
			// getType���������Ǽ�ͨ����׺������ȡ���ͣ�����ͨ�����ݽṹ��
			// BufferedImage�����;;�����ImageIO.write��������ݽṹ���統BufferedImage����Ϊpng,�洢Ϊjpg������png,�����ܿ���͸�����ء�
			BufferedImage biTemp = new BufferedImage(newWidth, newHeight,biSrc.getType());
			Graphics g = biTemp.createGraphics();
			g.drawImage(temp, 0, 0, null);
			g.dispose();
			
			/*AffineTransformOp affineTransformOp = new AffineTransformOp(AffineTransform
					.getScaleInstance(factor, factor), null);*/
			//save
			ImageIO.write(biTemp, FilenameUtils.getExtension(desFile.getName()), desFile);
			
			/*
			 * ����2�� 
			 * double fw,fh; fw = width / bi.getWidth(); 
			 * fh = height / bi.getHeight();
			 * System.out.println(fw + "" + fh);
			 *  AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(fw, fh),null); 
			 *  temp = ato.filter(bi, null);
			 */
			
			/*
			 * ����3������ͼ���豸����ͼ�� 
			 * create a BufferedImage compatible width the screen
			 * BufferedImage biOut; 
			 * GraphicsEnvironment ge = raphicsEnvironment.getLocalGraphicsEnvironment(); 
			 * GraphicsDevice gd = ge.getDefaultScreenDevice(); 
			 * GraphicsConfiguration gcf =gd.getDefaultConfiguration(); 
			 * int transparency = Transparency.OPAQUE;
			 * //BITMASK �� TRANSLUCENT��ɫ��죬OPAQUE�������� biOut
			 * image=gcf.createCompatibleImage(width, height, transparency);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * see @{@link #scaleLocal2LocalByRagid(File, File, int, int)}
	 * */
	public static void scaleLocal2LocalByRagid(String srcPath, String desPath, int width, int height) {
		scaleLocal2LocalByRagid(new File(srcPath), new File(desPath), width, height);
	}

	/** 
	 * ����ֵ����
	 * <p>
	 * <b>NOTE��</b>�˷������ܻᵼ��ͼ�ε�ʧ��ͱ���
	 * <p>
	 * 
	 * @see #scaleLocalImage2LocalByFactor(File, File, double)
	 *  */
	public static void scaleLocal2LocalByRagid(File srcFile, File desFile, int newWidth, int newHeight) {
		if(newWidth <= 0 || newHeight <= 0) {
			throw new IllegalArgumentException("Parameters newWidth or newHeight cannot be less than or equal to 0");
		}
		
		BufferedImage biSrc = null;
		Image temp = null;
		try {
			biSrc = getImageFromLocal(srcFile);
			temp = biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			BufferedImage biTemp = new BufferedImage(newWidth, newHeight, biSrc.getType());
			Graphics g = biTemp.createGraphics();
			g.drawImage(temp, 0, 0, null);
			g.dispose();
			ImageIO.write(biTemp, FilenameUtils.getExtension(desFile.getName()), desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * ͨ������ѹ��ͼƬ
	 * <p>
	 * quality����ԽСѹ��Խ��,�ͻ�Խ�����,��������0.4ʱѹ����ã��ļ�С��ͼƬû�����Ե�ģ������ʱѹ����Լ70%�ڴ� 
	 * <p>
	 * */
	public static byte[] compressImageByQuality(byte[] imgByte, float quality) {
		byte[] inByte = null;
		try {
			ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
			BufferedImage image = ImageIO.read(byteInput);
			if (image == null) {
				return null;
			}
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");// �õ�������
			ImageWriter writer = iter.next(); // �õ�writer
			ImageWriteParam iwp = writer.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);// ��ȷ�ģ���ʾ��
			// 0��1 0��ѹ����������ͨ��������Ϊ����ѹ������Ҫ�ġ���
			iwp.setCompressionQuality(quality); // ����ѹ����������
			// ���ý���ģʽд��ͼ��
			iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
			ColorModel colorModel = ColorModel.getRGBdefault();
			// ����Ŀ������
			iwp.setDestinationType(
					new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			// ��ʼ���ͼƬ��д��byte[]
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // ȡ���ڴ������
			// ����д��IIOImage
			IIOImage iIamge = new IIOImage(image, null, null);
			// ͨ��ImageIo�еľ�̬�������õ�byteArrayOutputStream��ImageOutput
			// ���������
			writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
			writer.write(null, iIamge, iwp);
			inByte = byteArrayOutputStream.toByteArray();
			byteInput.close();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inByte;
	}
	
	public static BufferedImage addWaterMake(String srcPath, 
			String logoPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		return addWaterMake(new File(srcPath),new File(logoPath),leftTop,markAlpha,rotateDegree);
		
	}

	/**
	 * Ϊ����ͼƬ���ˮӡ������BufferedImage����
	 * 
	 * 
	 * @param  srcFile  ��Ҫ���ˮӡ��ͼƬFile
	 * @param  markPath  ˮӡFile
	 * @param  leftTop  ˮӡͼ���Ͻ��ڱ���ͼ�е�λ�ã���λpx
	 * @param  markAlpha  ˮӡͼ��͸����
	 * @param  rotateDegree  ˮӡͼ��ת�Ƕȣ���λ�ȣ�˳ʱ��Ϊ����
	 */
	public static BufferedImage addWaterMake(File srcFile, 
			File markPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		// ���ᱨ��
		BufferedImage biBg = null;
		BufferedImage biMark = null;
		BufferedImage biResult;
		int width, height;
		
		biBg = getImageFromLocal(srcFile);
		biMark = getImageFromLocal(markPath);
		
		width = biBg.getWidth(null);
		height = biBg.getHeight(null);
		biResult = new BufferedImage(width, height, judgeType(biBg, biMark));
		
		// �õ����ʶ���
		// Graphics g= buffImg.getGraphics();
		Graphics2D g = biResult.createGraphics();
		// ���ö��߶εľ��״��Ե���� ,��ֵ�����š���ת����������ʽת��������ϵ�г���ʱ��������ɫȡ��
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(biBg, 0, 0, null);
		g.translate(leftTop.x, leftTop.y);
		// ����ˮӡ��ת ,��ת���ܻ��ԭ���ƶ���ͼƬ����֮��
		// ��ת��ԭ��ԭ���ָ�������ߣ��Ƹ����������ת
		g.rotate(Math.toRadians(rotateDegree), // ת������ֵ
				biMark.getWidth(null) / 2, biMark.getHeight(null) / 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));
		// ��ʱԭ���Ѿ�ƫ�ƣ�����Ҫ���ƫ����
		g.drawImage(biMark, 0,0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.dispose();
		return biResult;
	}
	
	/**
	 * ΪBufferedImage������ӱ��ش���ˮӡ��������BufferedImage����
	 * 
	 * @param  biBg  ��Ҫ���ˮӡ��BufferedImage����
	 * @param  logoPath  ˮӡ�ļ�File
	 * @param  leftTop  ˮӡͼ���Ͻ��ڱ���ͼ�е�λ��,��λpx
	 * @param  markAlpha  ˮӡͼ��͸����
	 * @param  rotateDegree  ˮӡͼ��ת�Ƕȣ���λ�ȣ�˳ʱ��Ϊ����
	 * */
	public static BufferedImage addWaterMake(BufferedImage biBg, 
			File logoPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		// ���ᱨ��
		BufferedImage biMark = null;
		BufferedImage biResult;
		int width, height;
		
		//biBg = getImageFromLocal(srcPath);
		biMark = getImageFromLocal(logoPath);
		
		width = biBg.getWidth(null);
		height = biBg.getHeight(null);
		biResult = new BufferedImage(width, height, judgeType(biBg,biMark));
		
		// �õ����ʶ���
		// Graphics g= buffImg.getGraphics();
		Graphics2D g = biResult.createGraphics();
		// ���ö��߶εľ��״��Ե���� ,��ֵ�����š���ת����������ʽת��������ϵ�г���ʱ��������ɫȡ��
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(biBg, 0, 0, null);
		g.translate(leftTop.x, leftTop.y);
		// ����ˮӡ��ת ,��ת���ܻ��ԭ���ƶ���ͼƬ����֮��
		// ��ת��ԭ��ԭ���ָ�������ߣ��Ƹ����������ת
		// ת������ֵ��˳ʱ��Ϊ��
		g.rotate(Math.toRadians(rotateDegree), 
				biMark.getWidth(null) / 2, biMark.getHeight(null) / 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));
		// ��ʱԭ���Ѿ�ƫ�ƣ�����Ҫ���ƫ����
		g.drawImage(biMark, 0,0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.dispose();
		return biResult;
	}
	
	/**
	 * ���ݱ���ͼ��ˮӡͼ���ͣ��ж�Ӧ�����ɵ�BufferImage type
	 * 
	 * �������ͼ��ˮӡͼƬ֮һΪpng,����������png�������ձ���ͼ��ʽ���ɡ�
	 * 
	 * @param  bk  ����ͼ
	 * @param  mark  ˮӡͼ
	 * 
	 * @return  
	 * <pre>
	 * JPEG - {@link java.awt.image.BufferedImage#TYPE_3BYTE_BGR}
	 * PNG - {@link java.awt.image.BufferedImage#TYPE_4BYTE_ABGR}
	 * </pre>
	 * */
	private static int judgeType(BufferedImage bk,BufferedImage mark) {
		int bgType = bk.getType() ;
		int markType = mark.getType();
		int type = bgType;
		if(markType ==BufferedImage.TYPE_4BYTE_ABGR) {
			type = BufferedImage.TYPE_4BYTE_ABGR;
		}
		
		return type;
	}
}
