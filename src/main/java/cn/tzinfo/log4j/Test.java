package cn.tzinfo.log4j;

import cn.tzinfo.log4j.es.entity.AlarmLog;
import cn.tzinfo.log4j.es.repositroy.AlarmLogRepository;
import cn.tzinfo.log4j.util.ExceptionUtil;
import cn.tzinfo.log4j.util.JSONUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author:shenk
 * @Date: 2020/4/7
 * @Description:
 */
public class Test {

    static Logger logger = LoggerFactory.getLogger(Test.class);

    private final int a;

    Test(int a) {
        this.a = a;
    }

    public static void main(String[] args) {
        //ThreadContext.put("DEVICECODE", "A003");
        int i=0;
        while(i++<100) {
            AlarmLog log = new AlarmLog();
            log.setMessage("报警信息");
            log.setClientCode("FT-01");
            log.setDeviceCode("A0001");
            log.setAlarmId("10001");
            log.setAlarmCode("A01");
            String str = new Gson().toJson(log);
            logger.info(str);
            Map<String, String> map = JSONUtil.fromJsonStr(str, Map.class);
            try {
                int a = 1/0;
            } catch (Exception e) {
                //e.printStackTrace();
                long s = System.currentTimeMillis();
                String error = ExceptionUtil.getStackInfo(e);
                System.out.println(System.currentTimeMillis() - s);
                System.out.println(error);
                logger.info("", e);
            }
        }
//        ThreadContext.put("user_id", "sk");
//        System.out.println(logger);
//        logger.info("i love {}", "you");
//        System.out.println(MDC.get("user_id"));
//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.showCurrentThread();
          //SpringApplication.run(Application.class, args);//start a demon thread
//        System.out.println(SpringBeanUtil.getBean(AlarmLogRepository.class));
    }
}
