package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cn.ct.map.bean.IContainer;
import cn.ct.map.bean.IGDraw;
import cn.ct.map.bean.IRectangle;

public class GContainer implements IGDraw, IContainer<Integer> {
	IRectangle<Integer> box;
	private int leftPadding;
	private int rightPadding;
	private int topPadding;
	private int bottomPadding;
	private List<IGDraw> subElements = new LinkedList<IGDraw>();
	
	public GContainer(IRectangle<Integer> box){
		this.box = box;
	}

	public GContainer(IRectangle<Integer> box, int leftPadding,
			int rightPadding, int topPadding, int bottomPadding, IGDraw...subs) {
		super();
		this.box = box;
		this.leftPadding = leftPadding;
		this.rightPadding = rightPadding;
		this.topPadding = topPadding;
		this.bottomPadding = bottomPadding;
		if(subs != null && subs.length > 0){
			this.subElements.addAll(Arrays.asList(subs));
		}
	}
	public GContainer(IRectangle<Integer> box, int padding, IGDraw...subs) {
		super();
		this.box = box;
		this.leftPadding = padding;
		this.rightPadding = padding;
		this.topPadding = padding;
		this.bottomPadding = padding;
		if(subs != null && subs.length > 0){
			this.subElements.addAll(Arrays.asList(subs));
		}
	}

	@Override
	public Integer getLeftPadding() {
		return this.leftPadding;
	}

	@Override
	public Integer getRightPadding() {
		return this.rightPadding;
	}

	@Override
	public Integer getTopPadding() {
		return this.topPadding;
	}

	@Override
	public Integer getBottomPadding() {
		return this.bottomPadding;
	}

	public void addElement(IGDraw gdraw){
		if(gdraw != null){
			this.subElements.add(gdraw);
		}
	}

	@Override
	public void setPadding(Integer left, Integer right, Integer top,
			Integer bottom) {
		this.leftPadding = left;
		this.rightPadding = right;
		this.topPadding = top;
		this.bottomPadding = bottom;
	}
	

	public IRectangle<Integer> getBox() {
		return box;
	}

	public void setBox(IRectangle<Integer> box) {
		this.box = box;
	}

	public List<IGDraw> getSubElements() {
		return subElements;
	}

	public void addSubElements(List<IGDraw> subElements) {
		if(subElements != null){
			this.subElements.addAll(subElements);
		}
	}

	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}

	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}

	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}

	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null && box != null){
			if(box instanceof IGDraw){
				((IGDraw)box).draw(g2d, width, height);
			}
			int w = box.getWidth() - this.getLeftPadding() - this.getRightPadding();
			int h = box.getHeight() - this.getTopPadding() - this.getBottomPadding();
			if(w > 0 && h > 0){
				Graphics2D sg2d = (Graphics2D) g2d.create(box.getX() + this.leftPadding, box.getY() + this.topPadding, w, h);
				for(int i =0; i < this.subElements.size(); i++){
					IGDraw gdraw = this.subElements.get(i);
					gdraw.draw((Graphics2D)sg2d.create(), w, h);
				}
			}
		}
		return g2d;
	}

}
