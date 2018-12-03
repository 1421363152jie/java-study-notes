package cn.mj.service.impl;

import cn.mj.dao.ConsoleLogDao;
import cn.mj.model.ConsoleLog;
import cn.mj.query.ConsoleLogQuery;
import cn.mj.service.ConsoleLogService;

public class ConsoleLogServiceImpl extends BaseServiceImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogService {
	private ConsoleLogDao consoleLogDao;

	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
		this.baseDao=consoleLogDao;
	}
	
	
	

}
