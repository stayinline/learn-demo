package com.myself.exception;

public class AutoReleaseTest implements AutoCloseable {
    @Override
    public void close() throws Exception {
        //这个会自动释放资源


    }

    public static void main(String[] args) {
        Long l = 123456L;
        System.out.println(l.toString());
    }
}
