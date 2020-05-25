package lc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChildClassLoader extends ClassLoader {


    private String path;
    private String classLoaderName;

    public ChildClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }


    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] b = loadClassData(name);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassData(String name) throws IOException {
        name = name.replace(".", "/");
        name = path + name + ".class";
        InputStream is = null;
        ByteArrayOutputStream outputStream = null;
        try {
            is = new FileInputStream(new File(name));
            outputStream = new ByteArrayOutputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                outputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (is != null) {
                is.close();
            }
        }

        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ChildClassLoader childClassLoader = new ChildClassLoader("/workspace/java/leetcode/src/", "customer");
        //自定义loader加载器
        Class c = null;
        try {
            c = childClassLoader.findClass("lc.easy.LC225");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //app classloader加载

        try {
            final Class<?> aClass = Class.forName("lc.easy.LC225");
            //不同
            System.out.println(c.equals(aClass));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //app classloader加载
        try {
            final Object o = c.newInstance();
            final Method testCast = o.getClass().getMethod("testCast");
            testCast.invoke(o);

        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
