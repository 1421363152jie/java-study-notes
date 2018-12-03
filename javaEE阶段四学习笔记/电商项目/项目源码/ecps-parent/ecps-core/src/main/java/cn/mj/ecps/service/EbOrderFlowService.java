package cn.mj.ecps.service;

import cn.mj.ecps.model.TaskBean;

import java.util.List;

public interface EbOrderFlowService {


    /**
     * 部署流程
     */
    public void deployFlow();

    /**
     * 启动流程
     */
    public String startInstance(Long orderId);


    /**
     * 执行流程
     */
    public void completeTask(String processInstanceId,String outcome);


    /**
     *查询任务
     */
    public List<TaskBean> selectTaskByAssignee(String assignee);

    /**
     * 根据任务id查询任务
     */
    public TaskBean selectTaskById(String taskId);


    /**
     * 根据向外走的线完成任务
     */
    public void  completeTaskByOutcome(String taskId,String outcome);

}
