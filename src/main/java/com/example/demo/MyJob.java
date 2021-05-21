package com.example.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Job executed at : "+new java.util.Date().toString()+" by trigger "+arg0.getTrigger()+" job id "+arg0.getJobDetail().getDescription());
	}

}
