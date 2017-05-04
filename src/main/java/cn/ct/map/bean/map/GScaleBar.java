package cn.ct.map.bean.map;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.measure.unit.Unit;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

import cn.ct.map.bean.IGDraw;
import cn.ct.map.bean.ILineDrawer;
import cn.ct.map.bean.TextStyle;
import cn.ct.map.bean.graphics.GRectangle;

public class GScaleBar extends GRectangle implements IGDraw {

	public static class UnitGetter implements IUnitGetter {

		@Override
		public UnitInfo getUnit(CoordinateReferenceSystem crs) {
			String sunit = "";
			Unit<?> unit = crs.getCoordinateSystem().getAxis(0).getUnit();
			sunit = unit == null ? "" : unit.toString();
			sunit = "[" + sunit + "]";
			return new UnitInfo(sunit, 1);
		}
	}

	public static class LineDrawer implements ILineDrawer {

		@Override
		public void draw(Graphics2D g2d, int sx, int sy, int ex, int ey, int idx) {
			g2d.drawLine(sx, sy, ex, ey);
		}

	}

	private TextStyle labelStyle;
	private TextStyle unitStyle;
	private IUnitGetter unitGetter;
	private int spaceWidth = 3;
	private ILineDrawer lineDrawer;
	private GMap gmap;

	public GScaleBar(GMap map, GRectangle rectangle) {
		super(rectangle);
		this.gmap = map;
	}

	public GScaleBar(GMap map, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.gmap = map;
	}

	public TextStyle getLabelStyle() {
		return labelStyle;
	}

	public void setLabelStyle(TextStyle labelStyle) {
		this.labelStyle = labelStyle;
	}

	public TextStyle getUnitStyle() {
		return unitStyle;
	}

	public void setUnitStyle(TextStyle unitStyle) {
		this.unitStyle = unitStyle;
	}

	public IUnitGetter getUnitGetter() {
		if (unitGetter == null) {
			unitGetter = new UnitGetter();
		}
		return unitGetter;
	}

	public void setUnitGetter(IUnitGetter unitGetter) {
		this.unitGetter = unitGetter;
	}

	public int getSpaceWidth() {
		if (spaceWidth < 0) {
			spaceWidth = 0;
		}
		return spaceWidth;
	}

	public void setSpaceWidth(int spaceWidth) {
		this.spaceWidth = spaceWidth;
	}

	public ILineDrawer getLineDrawer() {
		if (lineDrawer == null) {
			lineDrawer = new LineDrawer();
		}
		return lineDrawer;
	}

	public void setLineDrawer(ILineDrawer lineDrawer) {
		this.lineDrawer = lineDrawer;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if (g2d != null) {
			int x = getX();
			int y = getY();
			int w = getWidth();
			int h = getHeight();
			double oneUnitPxCount = 1 / gmap.getPxSize();
			w = w <= 0 ? width : w;
			h = h <= 0 ? height : h;

			UnitInfo unitinfo = getUnitGetter().getUnit(gmap.getCenter().getCrs());
			ILineDrawer vlineDrawer = getLineDrawer();

			if (unitStyle != null) {
				unitStyle.setGraphics2D(g2d);
			}
			Rectangle2D uarea = g2d.getFontMetrics()
					.getStringBounds(unitinfo.getName(), g2d);
			int drawPx = (int) (w - spaceWidth - uarea.getWidth() + 0.5);
			double curPx = 0;
			int curLength = (int) unitinfo.getStep();
			int curHeightPx = (int) (y + h / 2);
			if (labelStyle != null) {
				labelStyle.setGraphics2D(g2d);
			}
			int laststartx = x;
			oneUnitPxCount = oneUnitPxCount * unitinfo.getMultiple();
			while (curPx < drawPx) {
				String label = curLength + "";
				uarea = g2d.getFontMetrics().getStringBounds(label, g2d);
				int curEndPx = (int) (laststartx + oneUnitPxCount * curLength);
				int curLabelPx = (int) (x + curPx + oneUnitPxCount * curLength / 2 - uarea.getWidth() / 2);
				vlineDrawer.draw(g2d, laststartx, curHeightPx, curEndPx, curHeightPx, (int) (curLength / unitinfo.getStep()));
				g2d.drawLine(laststartx, curHeightPx - 3, laststartx, curHeightPx);
				g2d.drawLine(curEndPx, curHeightPx - 3, curEndPx, curHeightPx);
				this.drawStringLU(g2d, label, curLabelPx, curHeightPx + 3);
				laststartx = curEndPx;
				curPx = curPx + oneUnitPxCount * curLength;
				curLength += unitinfo.getStep();
				if (curPx + oneUnitPxCount * curLength > drawPx) {
					break;
				}
			}
			if (unitStyle != null) {
				unitStyle.setGraphics2D(g2d);
			}
			this.drawStringLC(g2d, unitinfo.getName(), laststartx, curHeightPx);
		}
		return g2d;
	}
}
