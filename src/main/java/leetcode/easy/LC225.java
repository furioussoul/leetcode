package leetcode.easy;

/**
 * 用队列实现栈
 */
public class LC225 {
    private static final int a = 1;
    public static final int b = 2;
    public static  int c = 3;
    public int d = 4;
    public static  int sss = Integer.valueOf(5);
    final int f = 6;
    final String x = "7";

    //替换classloader为自定义classloader
    public void setClassLoader(ClassLoader loader){
        Thread.currentThread().setContextClassLoader(loader);
    }

    //隐式转换和classforname都用了当前classloader
    public void testCast() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = this.getClass().getClassLoader().loadClass("main.java.lc.easy.LC225");
        final LC225 lc225 = (LC225)c.newInstance();
        System.out.println(lc225.getClass().equals(c));
        System.out.println(new LC225().getClass().getClassLoader());
        final Class<?> aClass = Class.forName("main.java.lc.easy.LC225");
        System.out.println(c.equals(aClass));
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}

