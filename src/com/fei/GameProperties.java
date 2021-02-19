package com.fei;

import java.awt.*;

/**
 * 游戏参数配置类
 * @author dengtengfei
 */
public class GameProperties {
	
	/**
	 * 游戏名称
	 */
	public static final String GAME_NAME = "飞飞的贪吃蛇";

	/**
	 * 游戏窗口的宽度
	 */
	public static final int GAME_FRAME_WIDTH = 500;
	
	/**
	 * 游戏窗口的高度
	 */
	public static final int GAME_FRAME_HEIGHT = 500;
	
	/**
	 * 游戏窗口位置（相对显示器的 X[水平]坐标）
	 */
	public static final int GAME_WINDOW_X = 500;
	
	/**
	 * 游戏窗口位置（相对显示器的 Y[垂直]坐标）
	 */
	public static final int GAME_WINDOW_Y = 250;
	
	/**
	 * 游戏在窗口中位置，相对 X
	 */
	public static final int GAME_LOCAL_X = 50;
	
	/**
	 * 游戏在窗口中位置，相对 Y
	 */
	public static final int GAME_LOCAL_Y = 50;
	
	/**
	 * 游戏界面在窗口中的宽度
	 */
	public static final int GAME_WIDTH = 400;
	
	/**
	 * 游戏界面在窗口中的高度
	 */
	public static final int GAME_HEIGHT = 400;
	
	/**
	 * 游戏升级，所需要的积分
	 */
	public static final int NEXT_SPEED_SCORE = 10;
	
	/**
	 * 食物的颜色 默认  Color.GRAY
	 */
	public static final Color FOOD_COLOR = Color.GRAY;
	
	/**
	 * 蛇身颜色
	 */
	public static final Color SNAKE_COLOR =  Color.BLACK;
	
	/**
	 * 蛇头颜色
	 */
	public static final Color SNAKE_HEADER_COLOR =  Color.BLUE;
	
	/**
	 * 蛇，食物节点的尺寸，指的粗细， 默认10 个像素
	 */
	public static final int NODE_SIZE = 10;
	
	/**
	 * 蛇的初始长度, 默认长度为3
	 */
	public static final int INIT_SNAKE_LENGTH = 3;
	
	/**
	 * 游戏的开始状态，默认为未开使（打开游戏蛇不会动）
	 */
	private boolean gameBeginning = false;
	
	/**
	 * 游戏得分,初始分为0分
	 */
	private int score = 0;
	
	/**
	 * 游戏等级
	 */
	private int grade;
	
	/**
	 * 获取游戏得分
	 * @return score 游戏得分
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 设置游戏得分
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 获取游戏级别
	 * @return 游戏级别，等级
	 */
	public int getGrade() {
		return grade;
	}
	
	/**
	 * 设置游戏等级
	 * 每吃10个食物升一级
	 * @param grade
	 */
	public void setGrade() {
		if (getScore() <= 10) {
			this.grade = 1;
		} else
			this.grade = getScore() / GameProperties.NEXT_SPEED_SCORE;
	}

	/**
	 * 获取游戏 （运动速度）线程休眠的时间，单位毫秒
	 * 等级每升一级，速度加快100毫秒
	 * @return 游戏速度（休眠的时间）
	 */
	public int getSpeed() {
		return 500 - getGrade() * 100;
	}

	/**
	 * 获取游戏开始状态
	 * @return true/false
	 */
	public boolean isGameBeginning() {
		return gameBeginning;
	}

	/**
	 * 设置游戏开始状态
	 * @param gameBeginning
	 */
	public void setGameBeginning(boolean gameBeginning) {
		this.gameBeginning = gameBeginning;
	}
	
}
