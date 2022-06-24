package com.songjz.componet;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Mario implements Runnable{
    private int x;
    private int y;

    private String status;

    private BufferedImage show = null;

    private BackGround backGround = new BackGround();

    // 实现马里奥的动作
    private Thread thread = null;

    private int xSpeed;

    private int ySpeed;

    private int index;

    // 表示马里奥的上升时间
    private int upTime=0;

    private boolean isOK = false;

    private boolean isDeath = false;
    private int score;


    public Mario() {

    }

    public void death() {
        isDeath =true;
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

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticComponent.standRight;
        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    // 马里奥向左移动
    public void leftMove() {
        xSpeed = -5;
        // 判断马里奥是否碰到旗子
        if (backGround.isDropFlagstaff()) {
            xSpeed = 0;
        }
        if (status.contains("jump")) {
            status = "jump--left";
        }else {
            status = "move--left";
        }
    }
    // 向左停止
    public void leftStop() {
        xSpeed = 0;
        if (status.contains("jump")) {
            status = "jump--left";
        }else {
            status = "stop--left";
        }
    }
    // 马里奥向右移动
    public void rightMove() {
        xSpeed = 5;
        // 判断马里奥是否碰到旗子
        if (backGround.isDropFlagstaff()) {
            xSpeed = 0;
        }
        if (status.contains("jump")) {
            status = "jump--right";
        }else {
            status = "move--right";
        }
    }

    public boolean isOK() {
        return isOK;
    }

    // 向右停止
    public void rightStop() {
        xSpeed = 0;
        if (status.contains("jump")) {
            status = "jump--right";
        }else {
            status = "stop--right";
        }
    }
    // 马里奥跳跃
    public void jump() {

        if (status.contains("left")) {
            status = "jump--left";
        }else {
            status = "jump--right";
        }
        ySpeed = -10;
        upTime = 7;
        // 判断马里奥是否碰到旗子
        if (backGround.isDropFlagstaff()) {
            ySpeed = 0;
        }

    }

    // 马里奥向下移动
    public void fall() {
        if (status.contains("left")) {
            status = "jump--left";
        }else {
            status = "jump--right";
        }
        ySpeed = 10;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public void setDeath(boolean death) {
        isDeath = death;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void run() {
        while (true) {
            boolean onObstacle = false;

            boolean canRunLeft = true;

            boolean canRunRight = true;

            if (backGround.isLastScene() && this.x >= 500) {
                this.backGround.setReachFlagstaff(true);

                // 判断旗子是否下落完成
                if (this.backGround.isDropFlagstaff()) {
                    status = "move--right";
                    if (x < 690) {
                        x += 5;
                    }else {
                        isOK = true;
                    }
                }else {
                    if (y < 395) {
                        xSpeed = 0;
                        this.y += 5;
                        status = "jump--right";
                    }

                    if (y > 395) {
                        this.y = 395;
                        status = "stop--right";
                        this.backGround.setDropFlagstaff(true);
                    }
                }
            }else {
                for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                    Obstacle obstacle = backGround.getObstacleList().get(i);

                    // 判断马里奥是否在障碍物上
                    if (obstacle.getY() == this.y + 25 && (obstacle.getX() > this.x - 30 && obstacle.getX() < this.x + 25)) {
                        onObstacle = true;
                    }

                    // 判断跳起来是否顶到砖块
                    if ((obstacle.getY() >= this.y - 30 && obstacle.getY() <= this.y - 20) && (obstacle.getX() > this.x - 30 && obstacle.getX() < this.x + 25)) {
                        if (obstacle.getType() == 0) {
                            backGround.getObstacleList().remove(obstacle);
                            this.score += 1;
                        }
                        upTime = 0;
                    }

                    // 判断是否可以向右走
                    if (obstacle.getX() == this.x + 25 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) {
                        canRunRight = false;
                    }

                    // 判断是否可以向左走
                    if (obstacle.getX() == this.x - 30 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) {
                        canRunLeft = false;
                    }
                }

                // 判断马里奥是否碰到敌人死亡或者踩死蘑菇敌人
                for (int i=0; i<backGround.getEnemyList().size(); i++) {
                    Enemy enemy = backGround.getEnemyList().get(i);

                    if (enemy.getY() == this.y + 20 && (enemy.getX() - 25 < this.x && enemy.getX() + 35 >= this.x)) {
                        if (enemy.getType() == 1) {
                            enemy.death();
                            this.score += 2;
                            upTime = 3;
                            ySpeed = -10;
                        }else if (enemy.getType() == 2) {
                            // 碰到食人花 直接死
                            this.death();
                        }
                    }

                    if ((enemy.getX() + 35 > this.x && enemy.getX() - 25 < this.x) && (enemy.getY() + 35 > this.y && enemy.getY() - 20 < this.y)) {
                        // 马里奥死亡
                        this.death();
                    }


                }

                // 进行马里奥的跳跃动作
                if (onObstacle && upTime == 0) {
                    if (status.contains("left")) {
                        if (xSpeed != 0) {
                            status = "move--left";
                        }else {
                            status = "stop--left";
                        }
                    }else {
                        if (xSpeed != 0) {
                            status = "move--right";
                        }else {
                            status = "stop--right";
                        }
                    }
                }else {
                    if (upTime != 0) {
                        upTime--;
                    }else {
                        fall();

                    }
                    y += ySpeed;
                }

                if ((canRunRight && xSpeed >0) || (canRunLeft && xSpeed < 0)) {
                    x += xSpeed;
                    if (x < 0) {
                        x = 0;
                    }
                }

                if (status.contains("move")) {
                    index = index == 0 ? 1 : 0;
                }

                if ("move--left".equals(status)) {
                    show = StaticComponent.runLeft.get(index);
                }

                if ("move--right".equals(status)) {
                    show = StaticComponent.runRight.get(index);
                }

                if ("stop--left".equals(status)) {
                    show = StaticComponent.standLeft;
                }

                if ("stop--right".equals(status)) {
                    show = StaticComponent.standRight;
                }

                if ("jump--left".equals(status)) {
                    show = StaticComponent.jumpLeft;
                }

                if ("jump--right".equals(status)) {
                    show = StaticComponent.jumpRight;
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
