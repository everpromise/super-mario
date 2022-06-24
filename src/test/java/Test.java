public class Test {
    public static void main(String[] args) {
        // 在哪里执行java命令 那么 System.getProperty("user.dir") 返回的就是哪里 不一定是 项目目录
        System.out.println(System.getProperty("user.dir"));
    }
}
