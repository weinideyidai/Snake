package com.fei;

import java.awt.*;

/**
 * 节点，比如：一条蛇由N节组成，一个食物一是一个节点
 * 每个节点有共同属性，节点的X坐标，节点Y坐标，节点的颜色， 节点大小，节点形状等
 * @author dengtengfei 
 */
public class Node {

	/**
	 * X坐标
	 */
	private int x;
	
	/**
	 * Y坐标
	 */
	private int y;
	
	/**
	 * 颜色, 默认蓝色
	 */
	private Color color = Color.BLUE;
	
	/**
	 * 大小，默认 10 个像素
	 */
	private int size;

	/**
	 * 节点构造方法
	 */
	public Node(Node node) {
		this.x = node.x;
		this.y = node.y;
		this.color = node.color;
		this.size = node.size;
	}

	/**
	 * 全方位构造
	 * @param x
	 * @param y
	 * @param color
	 * @param size
	 */
	public Node(int x, int y, Color color, int size) {
		this(x, y, color);
		this.size = size;
	}

	/**
	 * 通过坐标颜色构造
	 * @param x
	 * @param y
	 * @param color
	 */
	public Node(int x, int y, Color color) {
		this(x, y);
		this.color = color;
	}

	/**
	 * 通过坐标构造
	 * @param x
	 * @param y
	 */
	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 空参构造
	 */
	public Node() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 节点渲染
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.size, this.size);
	}
}
