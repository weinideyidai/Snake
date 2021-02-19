package com.fei;

import com.fei.Snake.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏控制器, 实现3个接口 1. ActionListener 事件动作监听接口，本控制器主要用来监听 panel 上的按钮事件[开始，暂停，退出] 2.
 * KeyListener 计算机外部键盘输入设备监听器，主要监听 上下左右键盘输入事件，控制贪吃蛇的蛇头方向 3. Runnable
 * 线程接口，用来实现开启蛇运动的线程 控制器继承了 JPanel 类，用来生成面板，布置[开始，暂停，退出]按钮
 * 
 * @author dengtengfei
 */
public class GameController extends JPanel implements ActionListener, KeyListener, Runnable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 定义3个控制按钮，开始，暂停，退出
	 */
	private JButton startButton;
	private JButton stopButton;
	private JButton quitButton;

	/**
	 * 初始一条贪吃蛇
	 */
	private Snake snake = new Snake();

	/**
	 * 初始化食物
	 */
	private Food food = new Food();

	private GameProperties gameProperties = new GameProperties();

	/**
	 * 构造方法，初始化
	 */
	public GameController() {
		// 生成相关按钮
		startButton = new JButton("开始");
		stopButton = new JButton("暂停");
		quitButton = new JButton("结束");

		// 设置布局
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(startButton);
		this.add(stopButton);
		this.add(quitButton);

		// 注册事件
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		quitButton.addActionListener(this);
		this.addKeyListener(this);
		new Thread(this).start();
	}

	/**
	 * 重写 JPanel的 paint 方法，绘制游戏界面
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// 调用父类绘制方法
		super.paint(g);

		// 绘制游戏区域
		g.setColor(Color.WHITE);
		g.fillRect(GameProperties.GAME_LOCAL_X, GameProperties.GAME_LOCAL_Y, GameProperties.GAME_WIDTH,
				GameProperties.GAME_HEIGHT);

		// 绘制游戏边界
		g.setColor(Color.BLACK);
		g.drawRect(GameProperties.GAME_LOCAL_X, GameProperties.GAME_LOCAL_Y, GameProperties.GAME_WIDTH,
				GameProperties.GAME_HEIGHT);

		// 绘制游戏信息
		g.drawString("分数:" + gameProperties.getScore() + "   等级 :" + gameProperties.getGrade(), 300, 30);

		// 绘制食物
		food.draw(g);

		// 绘制蛇
		snake.draw(g);

	}

	/**
	 * 蛇移动线程处理
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				// 设置游戏等级
				gameProperties.setGrade();
				// 线程休眠，每隔getSpeed() 毫秒执行一次
				Thread.sleep(gameProperties.getSpeed());
				// 刷新界面
				this.repaint();
				// 如果游戏为开启状态，执行蛇的运动方法
				if (gameProperties.isGameBeginning()) {
					boolean eatted = snake.moveAndEat(food);
					// 吃成功，积分增加 1分
					if (eatted) {
						gameProperties.setScore(gameProperties.getScore() + 1);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 打字输入事件，不使用，不做任何处理
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 键盘按下事件，对上下左右四个键按下事件进行处理
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// 判断游戏是否开始
		if (gameProperties.isGameBeginning()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				// 当前蛇的运动为左、右时， 按下向上键启有效，否则不起作用
				if (snake.getDirection() == Direction.RIGHT || snake.getDirection() == Direction.LEFT)
					snake.setDirection(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				// 当前蛇的运动为左、右时， 按下向下键启有效，否则不起作用
				if (snake.getDirection() == Direction.RIGHT || snake.getDirection() == Direction.LEFT)
					snake.setDirection(Direction.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				// 当前蛇的运动为上、下时， 按下向左键启有效，否则不起作用
				if (snake.getDirection() == Direction.DOWN || snake.getDirection() == Direction.UP)
					snake.setDirection(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				// 当前蛇的运动为上、下时， 按下向右键启有效，否则不起作用
				if (snake.getDirection() == Direction.DOWN || snake.getDirection() == Direction.UP)
					snake.setDirection(Direction.RIGHT);
				break;
			}
		}
	}

	/**
	 * 键盘松开事件（不使用，啥也不做）
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 按钮事件监听处理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 得到触发的按钮
		JButton jbt = (JButton) e.getSource();

		// 开始按钮触发，游戏开始，开始按钮灰掉，暂停按钮启用
		if (jbt.getText().equals("开始")) {
			gameProperties.setGameBeginning(true);
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}

		// 暂停按钮触发，游戏停止，开始按钮启用，暂停按钮灰掉
		if (jbt.getText().equals("暂停")) {
			gameProperties.setGameBeginning(false);
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
		}

		// 退出按钮触发，退出游戏
		if (jbt.getText().equals("结束")) {
			System.exit(0);
		}
		this.requestFocus();
	}
}
