package com.mydesign.example.strategy.demo1;

/**
 * 基于抽象类的策略模式
 */
public class Test {


    public void yourMethod(CommonReq req) {
        String serviceInfo = getServiceInfo();
        boolean checkRet = ServiceStrategy.getService(serviceInfo).checkReq(req);
        if (!checkRet) {
            System.out.println("校验未通过");
            return;
        }
        // deal you business

    }


    /**
     * 没什么卵用，该方法只是减少了各种子类的具体实现
     * 但是，复杂的业务场景是有大量的逻辑必须要通过子类来处理
     * 所以没用
     *
     * @param req
     */
    public void testLambda(CommonReq req) {


        ServiceStrategyLambda strategyLambda = (serviceName) -> req.getTargetServiceName().equals(serviceName);

        Context context = new Context(strategyLambda);

        // 查询当前提供服务的服务名称，当做参数，传递到策略中
        boolean checkRet = context.executeStrategy(getServiceInfo());
        if (!checkRet) {
            System.out.println("校验未通过");
        } else {
            System.out.println("校验通过");
        }
    }

    private String getServiceInfo() {
        return "A";
    }

    public static void main(String[] args) {
//        new Test().yourMethod(new CommonReq());
        CommonReq req = new CommonReq();
        req.setTargetServiceName("A");
        new Test().testLambda(req);
        // 输出：校验通过

        req.setTargetServiceName("B");
        new Test().testLambda(req);
        // 输出：校验未通过


    }

}
