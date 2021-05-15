package com.myself.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static String test(String s) {
        s = "abcd";
        System.out.println("变更：s=" + s);
        return s;
    }

    class Moudle {
        //执行测试用例
        public void executeCase() {
        }
    }

    public static void execute10Module(List<Moudle> moudles) {
        moudles.forEach(moudle -> {
            new Thread(() -> {
                moudle.executeCase();
            }).start();
        });
    }

    public synchronized void test() {


    }

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
        int a = 0;

        TestMyThread myThread = new TestMyThread();
        //myThread.run(atomicInteger); //准确的
        myThread.run(a); //准确的
//
//        synchronized () {
//            //1
//            //2
//            //3
//        }
//
//        Lock lock = new ReentrantLock(false);
//        Lock lock2 = new ReentrantLock(true);
//        boolean isGet = lock.tryLock();
//        if(isGet){
//            //dosomething
//
//            lock.lock();
//
//            lock.unlock();
//        }else {
//
//        }

//        for (int i = 0; i < 10; i++) {
//            Thread thread2 = new Thread(
//                    () -> {
//                        System.out.println("thread2 方式做的事情");
//                        atomicInteger++;
//                        a++;
//                    }
//            );

//            thread2.start();

//        }
    }
}