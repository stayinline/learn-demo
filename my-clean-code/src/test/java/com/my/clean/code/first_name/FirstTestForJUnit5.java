package com.my.clean.code.first_name;


import com.sun.tools.javac.util.Assert;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * JUnit5的单测接口API和注解等有所更新
 */


public class FirstTestForJUnit5 {


    @BeforeAll
    public static void init() {
        // 这个注解的作用是在所有的方执行之前运行该方法，用来初始化
    }


    @AfterAll
    public static void clear() {
        // 这个注解的作用是在所有的方执行之后运行该方法，用来清理善后
    }


    @Mock
    private UserReq userReq1;

    @Ignore
    public void testMock1() {
        System.out.println(userReq1.getName());
    }

    /**
     * 上面这种写法，会直接报错npe，@Mock只是标记一个需要mock的对象，还差对象的创建和填充过程
     */

    @Mock
    private UserReq userReq2;

    // 需要配合这个注解来创建
    @BeforeEach
    public void createUserReq() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock2() {
        System.out.println(userReq2.getName());
        //这里会输出 null
    }

    /**
     * 创建了对象，如何设置对象的属性呢？
     */


    @Mock
    private UserReq userReq3;

    @BeforeEach
    public void createUserReq3() {
        MockitoAnnotations.initMocks(this);
        userReq3.setName("Bob");
        userReq3.setUid(222L);
    }

    @Test
    public void testMethod2() {
        System.out.println(userReq3.getName() + ":" + userReq3.getUid());
        //这里会输出  null:0 ,所以注意作用域
    }

    @Test
    public void testMethod() {
        // 这行mock代码只在这个testMethod方法的作用域内生效
        Mockito.when(userReq3.getName()).thenReturn("zhangsan");

        System.out.println(userReq3.getName());
        // 这里会输出 shangsan 这个字符串

        Mockito.when(userReq3.getUid()).thenReturn(1212L);
        System.out.println(userReq3);

    }



    @Test
    public void testMethodInvoke() {
        Mockito.when(userReq3.getName()).thenReturn("李四");

        String name = userReq3.getName();
        System.out.println(name);
        // 用来验证userReq3.getName()这个方法是否被执行过
        Mockito.verify(userReq3).getName();

    }

    /**
     * 以下mock业务主类First，并且在mock一个固定的first.login返回值为FALSE，
     * 这样一来，好处是：mock的对象和实际spring注入的类没有任何关系，完全灵活，可自由操控完成自己想要的业务场景单元测试
     * 缺陷是：集成测试就需要spring的依赖注入对象模拟真实的运行场景和参数，这部分代码就需要重新编写
     */

    @Mock
    private First first;

    @BeforeEach
    public void createFirst() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestLogin() {
        Mockito.when(userReq3.getName()).thenReturn("zhangsan");

        Mockito.when(first.login(userReq3)).thenReturn(false);

        boolean loginRet = first.login(userReq3);

        System.out.println(loginRet);

    }


    /**
     * 总结：
     * 1、Mock是用来模拟对象的行为，比如数据库访问，RPC接口访问等，这种重量级接口，在mock的时候非常方便，
     * 因为我在开发自己代码的时候，并不关注对方rpc接口的开发进度和可用情况
     *
     */
}
