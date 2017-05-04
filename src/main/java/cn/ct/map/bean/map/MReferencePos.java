package cn.ct.map.bean.map;

import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MReferencePos extends MPos {
	private CoordinateReferenceSystem crs = DefaultGeographicCRS.WGS84;

	public MReferencePos() {
		this(0, 0);
	}

	public MReferencePos(MReferencePos pos) {
		super(pos);
		if (pos != null) {
			this.crs = pos.crs;
		} else {
			this.crs = DefaultGeographicCRS.WGS84;
		}
	}

	public MReferencePos(double x, double y) {
		this(x, y, DefaultGeographicCRS.WGS84);
	}

	public MReferencePos(MPos pos) {
		super(pos);
		this.crs = DefaultGeographicCRS.WGS84;
	}

	public MReferencePos(MPos pos, CoordinateReferenceSystem crs) {
		super(pos);
		this.crs = crs;
	}

	public MReferencePos(double x, double y, CoordinateReferenceSystem crs) {
		super(x, y);
		this.crs = crs;
	}

	public CoordinateReferenceSystem getCrs() {
		return crs;
	}

	public void setCrs(CoordinateReferenceSystem crs) {
		this.crs = crs;
	}
	
	public void setWGS84(){
		this.crs = DefaultGeographicCRS.WGS84;
	}
}
