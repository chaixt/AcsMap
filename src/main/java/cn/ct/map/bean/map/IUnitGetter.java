package cn.ct.map.bean.map;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface IUnitGetter {
	public UnitInfo getUnit(CoordinateReferenceSystem crs);
}
