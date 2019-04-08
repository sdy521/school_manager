package com.study.study_manager.test;

/**
 * 测试扩展Thread类实现的多线程程序
 * @author sdy
 * @date 2019/4/2 10:23
 */
public class TestThread extends Thread{
    private String name;

    public TestThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0;i<5;i++){
            for(long k= 0; k <100000000;k++);
            System.out.println(this.getName()+" :"+i);
        }
    }

    public static void main(String[] args) {
        TestThread t1 = new TestThread("张三");
        TestThread t2 = new TestThread("李四");
        t1.start();
        t2.start();
    }
}
