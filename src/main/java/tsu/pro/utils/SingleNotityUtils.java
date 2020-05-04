package tsu.pro.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleNotityUtils {

    // STEP1：获取应用基本信息
    private static String appId = "Gf3ip7k23dAAOfqXgbttP7";
    private static String appKey = "uBu0HK1UKX95QJSMYJt8O6";
    private static String masterSecret = "ttZeyTmPTv8YUtUKMMFem5";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void sendAllUserMsg(String title, String text) throws IOException {

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        Style0 style = new Style0();
        // STEP2：设置推送标题、推送内容
        style.setTitle("title");
        style.setText("text");
        style.setLogo("push.png");  // 设置推送图标
        // STEP3：设置响铃、震动等推送效果
        style.setRing(true);  // 设置响铃
        style.setVibrate(true);  // 设置震动


        // STEP4：选择通知模板
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);


        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒

        // STEP6：执行推送
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }


    static String CID = "";

    public static void main(String[] args) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        NotificationTemplate template = getNotificationTemplate();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        // 厂商通道下发策略
//        message.setStrategyJson("{\"ios\":4,\"st\":4}");
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    public static NotificationTemplate getNotificationTemplate() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        template.setStyle(style);

        template.setTransmissionType(1);  // 透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
        template.setTransmissionContent("请输入您要透传的内容");

        //template.setAPNInfo(getAPNPayload()); //详见【推送模板说明】iOS通知样式设置
        return template;
    }
}
