package cn.ct.map.bean.map;

public class UnitInfo {
	private String name = "";
	private double step = 1;
	private double multiple = 1;
	
	public UnitInfo() {
		super();
	}
	
	public UnitInfo(String name, double step) {
		super();
		this.name = name;
		this.step = step;
	}
	
	public UnitInfo(String name, double step, double multiple) {
		super();
		this.name = name;
		this.step = step;
		this.multiple = multiple;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStep() {
		return step;
	}
	public void setStep(double step) {
		this.step = step;
	}

	public double getMultiple() {
		return multiple;
	}

	public void setMultiple(double multiple) {
		this.multiple = multiple;
	}
	
	
}
