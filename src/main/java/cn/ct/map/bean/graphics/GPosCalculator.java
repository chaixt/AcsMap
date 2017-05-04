package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import cn.ct.map.bean.ICalculatePos;
import cn.ct.map.bean.IGDraw;

public class GPosCalculator<T extends Number> implements IGDraw {
	private EHorizontalPosition align = EHorizontalPosition.none;
	private EVerticalPosition valign = EVerticalPosition.none;
	private ICalculatePos<T> pos;

	public GPosCalculator(ICalculatePos<T> pos) {
		this.pos = pos;
	}

	public GPosCalculator(ICalculatePos<T> pos, EVerticalPosition valign) {
		super();
		this.valign = valign;
		this.pos = pos;
	}


	public GPosCalculator(ICalculatePos<T> pos, EHorizontalPosition align) {
		super();
		this.align = align;
		this.pos = pos;
	}


	public GPosCalculator(ICalculatePos<T> pos, EHorizontalPosition align, EVerticalPosition valign) {
		super();
		this.align = align;
		this.valign = valign;
		this.pos = pos;
	}
	public EHorizontalPosition getAlign() {
		return align;
	}

	public void setAlign(EHorizontalPosition align) {
		this.align = align == null ? EHorizontalPosition.none : align;
	}

	public EVerticalPosition getValign() {
		return valign;
	}

	public void setValign(EVerticalPosition valign) {
		this.valign = valign == null ? EVerticalPosition.none : valign;
	}

	public ICalculatePos<T> getPos() {
		return pos;
	}

	public void setPos(ICalculatePos<T> pos) {
		this.pos = pos;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if (g2d != null && this.pos != null) {
			if (valign != EVerticalPosition.none
					|| align != EHorizontalPosition.none) {
				Rectangle2D r2d = this.pos.calculate(g2d, width, height);
				if (r2d != null) {
					switch (align) {
					case left:
						this.pos.setX(0);
						break;
					case right:
						this.pos.setX(width - r2d.getWidth());
						break;
					case center:
						this.pos.setX((width - r2d.getWidth()) / 2);
						break;
					case none:
					default:
						break;
					}
					switch (valign) {
					case top:
						this.pos.setY(0);
						break;
					case middle:
						this.pos.setY((height - r2d.getHeight()) / 2);
						break;
					case bottom:
						this.pos.setY(height - r2d.getHeight());
						break;
					case none:
					default:
						break;
					}
				}
			}
			if (this.pos instanceof IGDraw) {
				((IGDraw) this.pos).draw(g2d, width, height);
			}
		}
		return null;
	}

}
