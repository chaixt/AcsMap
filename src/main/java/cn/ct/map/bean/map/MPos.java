package cn.ct.map.bean.map;

import cn.ct.map.bean.IPos;

public class MPos implements IPos<Double> {
	private double x;
	private double y;

	public MPos() {
		this(0, 0);
	}
	
	public MPos(MPos pos) {
		super();
		if(pos != null){
			this.x = pos.x;
			this.y = pos.y;
		}else{
			this.x = 0;
			this.y = 0;
		}
	}

	public MPos(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setX(Double x) {
		this.x = x;
	}

	@Override
	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public Double getX() {
		return this.x;
	}

	@Override
	public Double getY() {
		return this.y;
	}
}
