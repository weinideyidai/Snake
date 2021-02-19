package com.fei;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 注:蛇是由每个节点组成的，创建一个集合list进行存取
 * @author dengtengfei
 *
 */
public class Snake {

	/**
	 * 蛇身结构
	 */
	private List<Node> body = new ArrayList<>();

	/**
	 * 蛇的当前运动方向
	 */
	private Direction direction;

	/**
	 * 蛇初始化
	 */
	public Snake() {
		// 设置蛇初始运动方向
		this.direction = Direction.RIGHT;

		// 设置蛇的body
		for (int i = 0; i < GameProperties.INIT_SNAKE_LENGTH; i++) {
			// 初始化蛇放在中间
			int x = 200 - i * 10;
			int y = 200;
			Color color = (i == 0 ? GameProperties.SNAKE_HEADER_COLOR : GameProperties.SNAKE_COLOR);
			Node node = new Node(x, y, color, 10);
			body.add(node);
		}
	}

	/**
     * 蛇头移动，返回一个新的蛇头
     * @return Node
     */
	public Node headerMove() {
		Node header = body.get(0);
		int x = header.getX();
		int y = header.getY();
		switch (this.direction) {
		case RIGHT:
			x = x + 10;
			break;
		case LEFT:
			x = x - 10;
			break;
		case UP:
			y = y - 10;
			break;
		case DOWN:
			y = y + 10;
			break;
		}
		return new Node(x, y, header.getColor(), header.getSize());
	}

	/**
     * 蛇身移动，吃食物
     * @param food
     * @return true 吃到食物， false 没吃到
     */
	public boolean moveAndEat(Food food) {
		//头移动，得到新的头
		Node header = this.	headerMove();
		Node newTail = null;
		boolean eatted = false;
		
		//身体移动
		for (int i = body.size() - 1; i > 0; i--) {
			Node node = body.get(i - 1);
			if (i == 1)
				node.setColor(Color.BLACK);
			body.set(i, node);
		}
		
		//设置新的头
		body.set(0, header);
		
		//判断是否死亡
		if (isDead()) {
			JOptionPane.showMessageDialog(null, "GAME OVER!!!");
			System.exit(1);
		}
		
		//舌头靠近食物时，吃掉
		if (Math.abs(header.getX() - food.getX()) < 10 && Math.abs(header.getY() - food.getY()) < 10) {
			eatted = true;
			newTail = new Node(body.get(body.size() - 1));
		}
		
		//如果吃到食物，设置尾巴加一节。食物重新出现
		if (eatted) {
			body.add(newTail);
			food.refresh();
		}
		return eatted;
	}

	/**
	 * 判断是不是游戏失败
	 * 
	 * @return true/false
	 */
	public boolean isDead() {
		// 蛇头节点
		Node header = body.get(0);

		// 超出宽度
		if (header.getX() < 50 || header.getX() > 450) {
			return true;
		}

		// 超出高度
		if (header.getY() < 50 || header.getY() > 430) {
			return true;
		}
		
		
		for (int i = 1; i < body.size(); i++) {
			//第二种情况，蛇头咬到自己
			if ((body.get(i).getX() == header.getX()) && (body.get(i).getY() == header.getY())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 绘制一条蛇，绘制方法，蛇的每个节点进行绘制即可
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		for (Node node : body) {
			node.draw(g);
		}
	}

	/**
     * 获取蛇的当前运动方向
     * @return direction 运动方向
     */
	public Direction getDirection() {
		return direction;
	}

	/**
     * 设置蛇的当前运动方向
     * @param direction 运动方向
     */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
     * 蛇运动方向的枚举
     *@author dengtengfei
     */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
}
