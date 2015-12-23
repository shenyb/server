package com.need.schedule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.need.schedule.zk.TaskDefine;


/**
 * @author  Git@OSC 2015年12月17日 下午8:38:53
 * @ClassName ConsoleManager
 * @Description 展示方法
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月17日
 * @modify by reason:{方法名}:{原因}
 */
public class ConsoleManager {
	
    protected static transient Logger log = LoggerFactory.getLogger(ConsoleManager.class);
    
    private static Gson GSON = new GsonBuilder().create();

    private static ZKScheduleManager scheduleManager;
    
    public static ZKScheduleManager getScheduleManager() throws Exception {
    	if(null == ConsoleManager.scheduleManager){
    		ConsoleManager.scheduleManager = (ZKScheduleManager)ZKScheduleManager.getApplicationcontext().getBean(ZKScheduleManager.class);
    	}
        return ConsoleManager.scheduleManager;
    }

    public static void addScheduleTask(TaskDefine taskDefine) {
        try {
			ConsoleManager.scheduleManager.getScheduleDataManager().addTask(taskDefine);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    public static void delScheduleTask(String targetBean, String targetMethod) {
        try {
			ConsoleManager.scheduleManager.getScheduleDataManager().delTask(targetBean, targetMethod);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    public static List<TaskDefine> queryScheduleTask() {
    	List<TaskDefine> taskDefines = new ArrayList<TaskDefine>();
        try {
			List<TaskDefine> tasks = ConsoleManager.scheduleManager.getScheduleDataManager().selectTask();
			taskDefines.addAll(tasks);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
        return taskDefines;
    }
    
}
