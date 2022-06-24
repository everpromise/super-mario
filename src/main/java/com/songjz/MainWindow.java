package com.songjz;

import com.songjz.componet.*;
import javazoom.jl.decoder.JavaLayerException;
import sun.awt.image.OffScreenImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements KeyListener , Runnable{
    private Integer winWeight = 800;
    private Integer winHeight = 600;
    // 用于存储所有的背景
    private List<BackGround> allBg =new ArrayList<>();
    // 用于存储当前背景
    private BackGround nowBg = new BackGround();
    // 用于双缓存
    private Image offScreenImage = null;
    // 定义马里奥对象
    private Mario mario = new Mario();

    private Thread thread = new Thread(this);

    private Music music;

    {
        try {
            music = new Music();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public MainWindow() {
        this.setSize(winWeight, winHeight);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setTitle("超级马里奥-金洲学java");
        StaticComponent.init();
        mario = new Mario(10, 355);

        // 创建全部场景
        for (int i = 1; i < 4; i++) {
            allBg.add(new BackGround(i, i == 3 ? true:false));
        }
        nowBg = allBg.get(0);
        mario.setBackGround(nowBg);
        repaint();
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(winWeight, winHeight);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, winWeight, winHeight);

        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);

        for (Enemy enemy : nowBg.getEnemyList()) {
            graphics.drawImage(enemy.getShow(), enemy.getX(), enemy.getY(), this);
        }

        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }


        graphics.drawImage(nowBg.getTower(), 620, 270, this);
        graphics.drawImage(nowBg.getFlagstaff(), 500, 220, this);


        graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);

        // 绘制分数
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体", Font.BOLD, 25));
        graphics.drawString("当前的分数为：" + mario.getScore(), 300, 100);
        graphics.setColor(color);

        g.drawImage(offScreenImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mario.rightMove();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mario.leftMove();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            mario.jump();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mario.leftStop();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mario.rightStop();
        }
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();

    }

    @Override
    public void run() {

        while (true) {
            repaint();
            try {
                Thread.sleep(50);
                if (mario.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSceneCnt());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(355);
                }

                if (mario.isDeath()) {
                    JOptionPane.showMessageDialog(this, "很遗憾！游戏失败");
                    music.setPlay(false);
                    System.exit(0);
                }

                // 判断游戏是否结束
                if (mario.isOK()) {
                    JOptionPane.showMessageDialog(this, "恭喜你！成功通关了");
                    music.setPlay(false);
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
