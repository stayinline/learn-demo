package com.java8.test.lambda.demo2;


public class Consumer {

    /**
     * 模拟一个消费kafka消息的消费者业务
     *
     * @param args
     */
    public static void main(String[] args) {
        String msg = "{\"id\":\" 123 \",\"name\":\"zhangsan\"}";
        process((id)->BusinessTest.alert(3456), msg);
    }

    public static void process(BinlogProcess p, String msg) {
        int id = paseMsg(msg);
        BusinessTest b = new BusinessTest();
        p.process(b);
    }

    private static int paseMsg(String msg) {
        // 省略JSON解析
        return 123;
    }

}
