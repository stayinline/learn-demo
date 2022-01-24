package com.java8.test.lambda.demo1;


import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcessor {

    String process(BufferedReader reader) throws IOException;
}
