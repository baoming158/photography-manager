package com.dmwys.photography.exception;

import org.apache.commons.lang3.StringUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.dmwys.photography.util.ResourceHelper;
import com.dmwys.photography.util.SMSSender;

/**
 * 抛出该异常将会发送短信
 * 
 * @author xwq
 * 
 */
public class SMSException extends RuntimeException {
    private RateLimiter       limiter          = RateLimiter.create(0.0001);          // 最大10000秒发送一条 ~2.5小时一条

    private static String     mobiles          = ResourceHelper.get("error.sms.to");
    private static final long serialVersionUID = 1L;

    public SMSException(String sms) {
        sendSms(sms);
    }

    public SMSException(Exception e) {
        sendSms(e.getMessage());
    }

    private void sendSms(String sms) {
        if (StringUtils.isNotBlank(sms) && StringUtils.isNotBlank(mobiles)) {
            if (sms.length() > 60) {
                sms = sms.substring(0, 60) + "...";
            }
            if (limiter.tryAcquire()) {
                SMSSender.getSMSSender().sendSms(mobiles, sms);
            }
        }
    }
}
