package com.mydesign.example.lsp;


import com.sun.tools.internal.ws.processor.model.Request;
import org.apache.commons.lang3.StringUtils;
import sun.net.www.http.HttpClient;

/**
 * @author hemaoling
 */
public class SecurityTransporter extends Transporter {

    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }


    /**
     * 里式替换原则---子类完全能替换父类，并且保证父类的逻辑不变
     *
     * @param request
     */
    @Override
    public void sendRequest(Request request) {
        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
            //处理自己的业务问题
        }
        super.sendRequest(request);
    }


    /**
     * 不符合里氏置换原则，因为子类抛出的异常，强制父类也做了变动
     * <p>
     * 但是如果子类try-catch了该异常，也不会影响父类，那么也符合里氏置换原则
     *
     * @param request
     */
    @Override
    public void sendRequestV2(Request request) throws Exception {
        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
            //处理自己的业务问题
        } else {
            // 报错或者告警
            throw new Exception("校验失败！！！");
        }
        super.sendRequest(request);
    }
}
