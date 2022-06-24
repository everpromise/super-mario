package com.songjz.componet;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
    private int x;
    private int y;
    // 记录障碍物类型
    private int type;
    // 障碍物源，用于显示
    private BufferedImage show = null;
    // 用于和scene对接
    private BackGround bg = null;
    // 定义一个线程对象
    private Thread thread = new Thread(this);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }

    public BackGround getBg() {
        return bg;
    }

    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        this.show = StaticComponent.obstacle.get(this.type);
        // 如果是旗子的话启动线程
        if (type == 8) {
            thread.start();
        }
    }

    public Obstacle() {
    }

    @Override
    public void run() {
        while (true) {
            if (this.bg.isReachFlagstaff()) {
                if (this.y < 374) {
                    this.y += 5;
                }else {
                    this.bg.setDropFlagstaff(true);
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
