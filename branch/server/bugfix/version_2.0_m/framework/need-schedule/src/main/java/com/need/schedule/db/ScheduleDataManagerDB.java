package com.need.schedule.db;

import java.util.List;

import com.need.schedule.zk.IScheduleDataManager;
import com.need.schedule.zk.ScheduleServer;
import com.need.schedule.zk.TaskDefine;
import com.need.schedule.zk.ZKManager;
/**
 * <p></p>
 * @author Rylan 2015年12月15日 下午2:50:57
 * @ClassName ScheduleDataManagerDB
 * @Description 基于数据库实现
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月15日
 * @modify by reason:{方法名}:{原因}
 */
public class ScheduleDataManagerDB implements IScheduleDataManager{

	private ZKManager zkManager;
	
	@Override
	public boolean refreshScheduleServer(ScheduleServer server)
			throws Exception {
		/** TODO Auto-generated method stub*/
		return false;
	}

	@Override
	public void registerScheduleServer(ScheduleServer server) throws Exception {
		/** TODO Auto-generated method stub*/
		
	}

	@Override
	public boolean isLeader(String uuid, List<String> serverList) {
		/** TODO Auto-generated method stub*/
		return false;
	}

	@Override
	public void clearExpireScheduleServer() throws Exception {
		/** TODO Auto-generated method stub*/
		
	}

	@Override
	public List<String> loadScheduleServerNames() throws Exception {
		/** TODO Auto-generated method stub*/
		return null;
	}

	@Override
	public void assignTask(String currentUuid, List<String> taskServerList)
			throws Exception {
		/** TODO Auto-generated method stub*/
		
	}

	@Override
	public boolean isOwner(String name, String uuid) throws Exception {
		/** TODO Auto-generated method stub*/
		return false;
	}

	@Override
	public void addTask(TaskDefine taskDefine) throws Exception {
		/** TODO Auto-generated method stub*/
		
	}

	@Override
	public void delTask(String targetBean, String targetMethod)
			throws Exception {
		/** TODO Auto-generated method stub*/
		
	}

	@Override
	public List<TaskDefine> selectTask() throws Exception {
		/** TODO Auto-generated method stub*/
		return null;
	}

	@Override
	public boolean checkLocalTask(String currentUuid) throws Exception {
		/** TODO Auto-generated method stub*/
		return false;
	}
	
}
