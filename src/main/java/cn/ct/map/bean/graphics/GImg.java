package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.ct.map.bean.IGDraw;

public class GImg extends GRectangle implements IGDraw{
	private Image img;
	
	public void autoWH(){
		if(this.img != null){
			setWidth(this.img.getWidth(null));
			setHeight(this.img.getHeight(null));
		}
	}
	
	public GImg(Image img) {
		super(0, 0);
		this.img = img;
		autoWH();
	}
	
	public GImg(String img_file) throws IOException {
		super(0, 0);
		this.img = ImageIO.read(new File(img_file));
		autoWH();
	}

	public GImg(Image img, int x, int y){
		super(x, y);
		this.img = img;
		autoWH();
	}
	public GImg(String img_file, int x, int y) throws IOException{
		super(x, y);
		this.img = ImageIO.read(new File(img_file));
		autoWH();
	}
	public GImg(Image img, int x, int y, int w, int h){
		super(x, y, w, h);
		this.img = img;
	}
	public GImg(String img_file, int x, int y, int w, int h) throws IOException{
		super(x, y, w, h);
		this.img = ImageIO.read(new File(img_file));
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null && img != null){
			g2d.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
		}
		return g2d;
	}
	
	
}
