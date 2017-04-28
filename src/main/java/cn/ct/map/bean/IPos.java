package cn.ct.map.bean;

public interface IPos<T> {
	public void setX(T x);
	public void setY(T y);
	public T getX();
	public T getY();
}
