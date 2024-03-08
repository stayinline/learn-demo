package com.my.clean.code.first_name;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Junit4样例
 */
@RunWith(Parameterized.class)
public class FirstTest {

    private static First first;

    @BeforeClass
    public static void init() {
        first = new First();
    }

//    @Before 是在每个用例之前执行
//    public static void init() {
//        first = new First();
//    }

    @After // 每个用例后执行
    public void clear() {
        first = null;
    }

    @Test
    public void testExecute() {
        String args = "";

        int ret = first.execute(args);
        assertEquals(args, args);
        assertEquals(0, ret);

        assertTrue(ret == 0);


        assertNull(args);
        assertNotNull(args);


    }

    @Test
    public void testSame() {
        String args = "abcd";

        String ret = first.testSame(args);

        assertSame(ret, args);

    }


    /**********参数化(很重要！！！！)
     * 当运行某个方法时，必须要先构建某个类作为参数的时候，就这样解决
     * **********/


    @Parameterized.Parameter
    public int fInput;

    @Parameterized.Parameter(value = 1)
    //这个用来取出Parameters定义好的数据，value=1表示取{0, "Bob"} 中的Bob
    // 并且这个注解会遍历整个Collection的所有元素
    public String fExpected;

    /**
     * @return 返回值必须是Collection，{0, "Bob"} 的含义是 0的时候对应的期望值是 "Bob"这个字符串
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Bob"}, {5, "Tom"}, {6, "zhangsan"}
        });
    }

    @Test
    public void testParam() {
        System.out.println(fInput + ":" + fExpected);

        /**
         *    会输出：
         * 0:Bob
         * 5:Tom
         * 6:zhangsan
         */

    }



}