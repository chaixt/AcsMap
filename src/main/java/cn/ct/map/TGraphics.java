package cn.ct.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.StyleFactory;
import org.opengis.filter.FilterFactory2;

import cn.ct.map.bean.IGDraw;

public class TGraphics {

	private static StyleFactory sf = CommonFactoryFinder.getStyleFactory();
	private static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

	public static StyleFactory getSF() {
		return sf;
	}

	public static FilterFactory2 getFilterFactory2() {
		return ff;
	}
	
	private int width = 100;
	private int height = 100;
	private List<IGDraw> glist = new LinkedList<IGDraw>();

	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void addElement(IGDraw drawElement){
		if(drawElement != null){
			glist.add(drawElement);
		}
	}
	
	public BufferedImage draw(int imageType){
		BufferedImage bimg = new BufferedImage(width, height, imageType);
		if(glist != null){
			for(int i = 0; i < glist.size(); i++){
				if(glist.get(i) != null){
					Graphics2D g2d = bimg.createGraphics();
					glist.get(i).draw(g2d);
					g2d.dispose();
				}
			}
		}
		return bimg;
	}
}
