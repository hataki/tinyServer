package webServer;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Future;

@RestController
public class MyController {

    @RequestMapping(value = "/tom")
    public Object tomcat() {
        System.out.println("来啦 老弟！" + Thread.currentThread().getName());

        Date date = new Date();
        return "data:" + date.getTime() +  " \n\n";
    }


    HashMap map = new HashMap() ;


}
