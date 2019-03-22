package cn.orgtec.jpa.spring;

import cn.orgtec.jpa.entity.JobExecutionLogEntity;

public class TestAppContext{
    //需要获取的bean
	private JobExecutionLogEntity person;
	public String getPersonName(){
		//通过bean的名称来获取bean
		person = (JobExecutionLogEntity)SpringUtils.getBean("person");
		return person.getJobName();
	}
}