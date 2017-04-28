package cn.ct.map.bean;

public interface IRectangle<T> extends IPos<T>{
	public void setWidth(T width);
	public void setHeight(T height);
	public T getWidth();
	public T getHeight();

}
