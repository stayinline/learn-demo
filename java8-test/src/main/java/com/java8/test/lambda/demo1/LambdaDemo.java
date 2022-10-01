package com.java8.test.lambda.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LambdaDemo {

    private static final String FILE_PATH = "java8-test/src/main/java/com/java8/test/lambda/demo1/test.txt";

    public static void testReadFile() throws IOException {
        // 第四步：传递lambda，将要执行的"读取数据"这一行为，用lambda的方式，通过参数传递过去
        String result = processFile(BufferedReader::readLine);
        System.out.println(result);
    }

    /**
     * 第一步：行为参数化
     * 也就是用 BufferReaderProcessor 这样一个函数式接口来承担要执行的"读取数据"这一行为
     *
     * @param processor 第二步：BufferReaderProcessor 函数式接口来传递行为，也就是自定义这个函数式接口
     * @return
     * @throws IOException
     */
    private static String processFile(BufferReaderProcessor processor) throws IOException {
        FileReader fileReader = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fileReader);
        // 第三步：执行这个行为，也就是调用函数式接口中定义的好的那一个方法
        return processor.process(br);
    }


    public static void main(String[] args) {
        try {
            testReadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
