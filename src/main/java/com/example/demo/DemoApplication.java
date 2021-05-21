package com.example.demo;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private Scheduler scheduler;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/startScheduler")
	public String start() throws Exception{
		
		scheduler.start();
		
		return "started";
	}
	
	@GetMapping("/scheduleJob")
	public String scheduleJob() throws Exception{
		
		scheduleMyJob();
		
		return "started";
	}
	
	
	void scheduleMyJob() throws Exception{ 
		//SchedulerFactory factory = new StdSchedulerFactory();
		//scheduler = factory.getScheduler();
		long l = new Date().getTime();
		JobDetail job1 = JobBuilder.newJob(MyJob.class)
                .withIdentity("job"+l, "group"+l).build();

        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger"+l, "group"+l)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
	
        scheduler.scheduleJob(job1, trigger1);
        
	}
	
	
	
	
	
}
