package com.songjz.componet;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable{
    private int x;
    private int y;
    private int type;

    private boolean face_to = true;
    private BufferedImage show;
    private BackGround bg;

    private int max_up = 0;
    private int max_down = 0;

    private int image_type = 0;

    private Thread thread = new Thread(this);

    private boolean alive = true;

    public BufferedImage getShow() {
        return show;
    }

    public Enemy(int x, int y, int type, boolean face_to, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        show = StaticComponent.mushroom.get(0);
        thread.start();
    }

    public int getType() {
        return type;
    }

    public boolean isFace_to() {
        return face_to;
    }

    public BackGround getBg() {
        return bg;
    }

    public int getMax_up() {
        return max_up;
    }

    public int getMax_down() {
        return max_down;
    }

    public int getImage_type() {
        return image_type;
    }

    public Thread getThread() {
        return thread;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Enemy(int x, int y, int type, boolean face_to, BackGround bg, int max_up, int max_down) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        this.max_up = max_up;
        this.max_down = max_down;
        this.show = StaticComponent.chomper.get(0);
        thread.start();

    }

    public Enemy(int x, int y, int type, boolean face_to, int max_up, int max_down) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.max_up = max_up;
        this.max_down = max_down;
        thread.start();
    }

    // 死亡敌人
    public void death() {
        show = StaticComponent.mushroom.get(2);
        this.y += 15;
        alive =false;
    }

    @Override
    public void run() {
        while(true) {
            if (!alive) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.bg.getEnemyList().remove(this);
            }

            // 蘑菇
            if (type == 1) {
                if (face_to) {
                    this.x -= 2;
                }else{
                    this.x += 2;
                }

                image_type = image_type == 1 ? 0:1;
                show = StaticComponent.mushroom.get(image_type);
            }

            boolean canLeft = true;
            boolean canRight = true;

            for (int i = 0; i < bg.getObstacleList().size(); i++) {
                Obstacle obstacle = bg.getObstacleList().get(i);
                if (obstacle.getX() == this.x + 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) {
                    canRight = false;
                }

                if (obstacle.getX() == this.x - 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) {
                    canLeft = false;
                }
            }

            if (face_to && !canLeft || this.x == 0) {
                face_to = false;
            }
            else if ((!face_to) && (!canRight) || this.x == 764) {
                face_to = true;
            }

            if (type == 2) {
                if (face_to) {
                    this.y -= 2;
                }else {
                    this.y += 2;
                }

                image_type = image_type==1?0:1;

                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }

                if (!face_to && (this.y == max_down)) {
                    face_to = true;
                }

                show = StaticComponent.chomper.get(image_type);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
