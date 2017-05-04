package cn.ct.map.bean;

public interface IContainer<T> {
	public T getLeftPadding();
	public T getRightPadding();
	public T getTopPadding();
	public T getBottomPadding();
	public void setPadding(T left, T right, T top, T bottom);
}
