package com.fei;

import javax.swing.*;

/**
 * 游戏主窗口和启动类，直接继承 JFrame 生成游戏窗口
 * 注：也可以不继承，直接在该类中，new 一个JFrame 对象，使用继承代码更加清晰
 * @author dengtengfei
 *
 */
public class GameFrame extends JFrame{

	/**
	 * 序列化室版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法，构造时游戏窗口时，设定一下必要的参数
	 */
	public GameFrame() {
		//创建一个游戏组件，在窗口中添加一个游戏控制器的组件
		GameController jpan = new GameController();
		this.setContentPane(jpan);
		//调用父类的构造方法，并初始化窗口的名称
		this.setTitle(GameProperties.GAME_NAME);
		//设置窗口是否可视
		this.setVisible(true);
		//设置游戏窗口的大小
		this.setSize(GameProperties.GAME_FRAME_WIDTH, GameProperties.GAME_FRAME_HEIGHT);
		//设置游戏在屏幕中的位置
		this.setLocation(GameProperties.GAME_WINDOW_X, GameProperties.GAME_WINDOW_Y);  
		//固定窗口大小
		this.setResizable(false);
		//设置窗口关闭时结束程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 通过main方法启动游戏，实际就是启动一个java的应用程序（进程）
	 * 其中main方法会开启一个进程后，main方法所启动的线程就是这个进程的主线程
	 * @param args 启动参数
	 */
	public static void main(String[] args) {
		
		 new GameFrame();
		 
	}
}
