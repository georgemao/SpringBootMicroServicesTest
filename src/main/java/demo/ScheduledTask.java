package demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by George.Mao on 11/5/2014.
 */

@Component // so our Auto Component scan picks this Component up
@EnableScheduling
public class ScheduledTask {

    @Scheduled(fixedDelay = 50000)
    public void myTask(){
        System.out.println("task executed at: " + new Date().toString());
    }


}
