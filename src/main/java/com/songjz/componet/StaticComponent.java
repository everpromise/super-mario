package com.songjz.componet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class StaticComponent {
    // 背景
    public static BufferedImage bg1 = null;
    public static BufferedImage bg2 = null;
    // 马里奥向左跳跃
    public static BufferedImage jumpLeft = null;
    // 马里奥向右跳跃
    public static BufferedImage jumpRight = null;
    // 马里奥向左站立
    public static BufferedImage standLeft = null;
    // 马里奥向右站立
    public static BufferedImage standRight = null;
    // 城堡
    public static BufferedImage tower = null;
    // 旗杆
    public static BufferedImage flagStaff = null;
    // 障碍物
    public static List<BufferedImage> obstacle = new ArrayList<>();
    // 马里奥向左跑
    public static List<BufferedImage> runLeft = new ArrayList<>();
    // 马里奥向右跑
    public static List<BufferedImage> runRight = new ArrayList<>();
    // 蘑菇敌人
    public static List<BufferedImage> mushroom = new ArrayList<>();
    // 食人花敌人
    public static List<BufferedImage> chomper = new ArrayList<>();
    // 路径的前缀，方便后续调用
    public static String path = System.getProperty("user.dir") + "/src/main/resources/images/";
    // 音乐
    public static String musicPath = System.getProperty("user.dir") + "/src/main/resources/music/";

    // 初始化方法
    public static void init() {
        try {
            // 加载背景图片
            bg1 = ImageIO.read(new File(path + "bg.png"));
            bg2 = ImageIO.read(new File(path + "bg2.png"));
            // 加载马里奥向左站立
            standLeft = ImageIO.read(new File(path + "s_mario_stand_L.png"));
            // 加载马里奥向右站立
            standRight = ImageIO.read(new File(path + "s_mario_stand_R.png"));
            // 加载城堡
            tower = ImageIO.read(new File(path + "tower.png"));
            // 加载旗杆
            flagStaff = ImageIO.read(new File(path + "gan.png"));
            // 加载马里奥向左跳跃
            jumpLeft = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
            // 加载马里奥向右跳跃
            jumpRight = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载马里奥向左（右）跑
        for (int i = 1; i < 3; i++) {
            try {
                runLeft.add(ImageIO.read(new File(path + "s_mario_run" + i + "_L.png")));
                runRight.add(ImageIO.read(new File(path + "s_mario_run" + i + "_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // 加载障碍物
            obstacle.add(ImageIO.read(new File(path + "brick.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载水管
        for (int i = 1; i < 5; i++) {
            try {
                obstacle.add(ImageIO.read(new File(path + "pipe" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 加载不可破坏的砖块和旗子
        try {
            obstacle.add(ImageIO.read(new File(path + "brick2.png")));
            obstacle.add(ImageIO.read(new File(path + "flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载蘑菇敌人
        for (int i = 1; i < 4; i++) {
            try {
                mushroom.add(ImageIO.read(new File(path + "fungus" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 加载食人花敌人
        for (int i = 1; i < 3; i++) {
            try {
                chomper.add(ImageIO.read(new File(path + "flower1." + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
