package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;

import cn.ct.map.bean.IRectangle;

public class TRectangle extends GPos implements IRectangle<Integer> {
	private int width;
	private int height;

	public TRectangle() {
		super();
	}

	public TRectangle(TRectangle rectangle) {
		super(rectangle);
		if (rectangle != null) {
			width = rectangle.width;
			height = rectangle.height;
		}
	}

	public TRectangle(GPos pos, int width, int height) {
		super(pos);
		this.width = width;
		this.height = height;
	}

	public TRectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public TRectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public Integer getWidth() {
		return width;
	}

	@Override
	public Integer getHeight() {
		return height;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		return g2d;
	}
}
