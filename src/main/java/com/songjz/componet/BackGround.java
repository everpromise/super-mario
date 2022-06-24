package com.songjz.componet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    // 当前场景要显示的图像
    private BufferedImage bgImage = null;
    // 记录当前是第几个场景
    private int sceneCnt;
    // 判断是否是最后一个场景
    private boolean isLastScene;
    // 用于存放所有的障碍物
    private List<Obstacle> obstacleList = new ArrayList<>();
    // 用于显示旗杆
    private BufferedImage flagstaff = null;
    // 用于显示城堡
    private BufferedImage tower = null;
    // 判断马里奥是否到达旗杆位置
    private boolean isReachFlagstaff = false;
    // 判断旗子是否落地
    private boolean isDropFlagstaff = false;

    // 存放所有敌人
    private List<Enemy> enemyList = new ArrayList<>();

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public boolean isDropFlagstaff() {
        return isDropFlagstaff;
    }

    public void setDropFlagstaff(boolean dropFlagstaff) {
        isDropFlagstaff = dropFlagstaff;
    }

    public boolean isReachFlagstaff() {
        return isReachFlagstaff;
    }

    public void setReachFlagstaff(boolean reachFlagstaff) {
        isReachFlagstaff = reachFlagstaff;
    }

    public BufferedImage getFlagstaff() {
        return flagstaff;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public BackGround() {

    }

    public BackGround(int sceneCnt, boolean isLastScene) {
        this.sceneCnt = sceneCnt;
        this.isLastScene = isLastScene;

        if (isLastScene) {
            bgImage = StaticComponent.bg2;
        }else {
            bgImage = StaticComponent.bg1;
        }

        // 第一关
        if (sceneCnt == 1) {
            // 绘制地面， 上地面1， 下地面2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i*30, 420, 1, this));
            }

            for (int i = 0; i <= 120; i+=30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j*30, 570-i, 2, this));
                }
            }

            // 绘制砖块
            for (int i = 120; i <= 150; i+=30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }

            for (int i = 300; i <= 570 ; i+=30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 7, this));
                }else {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }

            // 绘制砖块
            for (int i= 420; i<=450; i+= 30) {
                obstacleList.add(new Obstacle(i, 240, 7, this));
            }

            // 绘制水管
            for (int i = 360; i < 600; i+=25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                }else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            // 绘制第一关蘑菇敌人
            enemyList.add(new Enemy(580, 385, 1, true, this));
            // 绘制第一关食人花敌人
            enemyList.add(new Enemy(635, 420, 2, true, this , 328, 428));

        }

        // 判断是不是第二关
        if (sceneCnt == 2) {
            // 绘制地面， 上地面1， 下地面2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i*30, 420, 1, this));
            }

            for (int i = 0; i <= 120; i+=30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j*30, 570-i, 2, this));
                }
            }
            // 绘制第一个水管
            for (int i = 360; i < 600; i+=25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60, i, 3, this));
                    obstacleList.add(new Obstacle(85, i, 4, this));
                }else {
                    obstacleList.add(new Obstacle(60, i, 5, this));
                    obstacleList.add(new Obstacle(85, i, 6, this));
                }
            }

            // 绘制第二个水管
            for (int i = 330; i < 600; i+=25) {
                if (i == 330) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                }else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            // 绘制砖块C
            obstacleList.add(new Obstacle(300, 330, 0, this));

            // 绘制砖块BEG
            for (int i = 270; i<=330; i+=30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i ,360, 0, this));
                }else {
                    obstacleList.add(new Obstacle(i, 360, 7, this));
                }
            }

            // 绘制砖块ADFHI
            for (int i=240; i<=360; i+=30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i, 390, 0, this));
                }else {
                    obstacleList.add(new Obstacle(i, 390, 7, this));
                }
            }

            // 绘制妨碍1砖块
            obstacleList.add(new Obstacle(240, 300, 0, this));

            // 绘制空1-4砖块
            for (int i=360; i<=540; i+=60) {
                obstacleList.add(new Obstacle(i , 270, 7, this));
            }

            // 绘制蘑菇
            enemyList.add(new Enemy(75, 420, 2, true, this, 312, 418));
            enemyList.add(new Enemy(635, 420, 2, true, this, 298, 388));


            // 绘制食人花敌人
            enemyList.add(new Enemy(200, 385, 1, true, this));
            enemyList.add(new Enemy(500, 385, 1, true, this));

        }


        // 判断是不是第三关
        if (sceneCnt == 3) {
            // 绘制地面， 上地面1， 下地面2
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i*30, 420, 1, this));
            }

            for (int i = 0; i <= 120; i+=30) {
                for (int j = 0; j < 27; j++) {
                    obstacleList.add(new Obstacle(j*30, 570-i, 2, this));
                }
            }

            // 绘制第三个背景的A-O砖块
            int temp = 290;
            for (int i= 390; i>= 270; i -=30) {
                for (int j=temp; j<=410; j+=30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            // 绘制第三个背景的P-R连接
            temp = 60;
            for (int i=390; i>=360 ;i-=30) {
                for(int j=temp; j<=90; j+=30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            // 绘制旗杆
            flagstaff = StaticComponent.flagStaff;

            // 绘制城堡
            tower = StaticComponent.tower;

            obstacleList.add(new Obstacle(515, 220, 8, this));

            // 绘制第三关敌人
            enemyList.add(new Enemy(150, 385, 1, true, this));
        }
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSceneCnt() {
        return sceneCnt;
    }

    public boolean isLastScene() {
        return isLastScene;
    }
}
