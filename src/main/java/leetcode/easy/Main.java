package leetcode.easy;

import java.util.HashMap;

public class Main {

    static int i = 111;
    final int x = 222;
    String a = "aaa";
    Object fooo = new Object();
    static HashMap<String,Object> map = new HashMap<>(10000);

    public final void testFinalMethod(){

    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        while(true){
            Class.forName("main.java.lc.easy.Main");
            Main x = new Main();
            map.put(x.toString(),x);
            Thread.sleep(100);
        }
//        final Integer i1 = Integer.valueOf(1);
//        System.out.println(i1==1);
//        final Main main = new Main();
//        main.testFinalMethod();
//        String c = "a";
//        String b = new String("a").intern();
//        String a = new String(c);
//
//        System.out.println(a==b);
//        System.out.println(a==c);
//        System.out.println(b==c);
    }
}
