package com.fei;

/**
 * 食物类，继承Node类，食物出了有相关属性外
 * 由于食物位置是随机生成的，所有本来提供一个构造方法，用于随机生成食物
 * 另外，食物被吃之后，需要重新生成食物的位置，故还需要提供位置刷新方法
 * @author dengtengfei
 *
 */
public class Food extends Node {

	/**
	 * 构造方法，随机生成食物位置
	 */
	public Food() {
		this.refresh();
		this.setColor(GameProperties.FOOD_COLOR);
		this.setSize(GameProperties.NODE_SIZE);
	}
	
	/**
	 * 刷新食物的位置
	 */
	public void refresh() {
		int x = (int)(Math.random()*400)+50 ,y = (int)(Math.random()*380)+50;
		this.setX(x);
		this.setY(y);
	}
}
