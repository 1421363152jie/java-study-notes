package cn.mj.ecps.service.impl;

import cn.mj.ecps.model.TaskBean;
import cn.mj.ecps.service.EbOrderFlowService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EbOrderFlowServiceImpl implements EbOrderFlowService {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;


    @Autowired
    private TaskService taskService;


    public void deployFlow() {
        //创建流程部署·对象
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.addClasspathResource("diagramgs/OrderFlow.bpmn")
                .name("订单流程")
                .category("订单")
                .addClasspathResource("diagramgs/OrderFlow.png");
         deployment.deploy();

    }

    public String  startInstance(Long orderId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("OrderFlow", orderId + "");
        return processInstance.getId();
    }

    /**
     * 执行流程
     */
    public void completeTask(String processInstanceId, String outcome) {
        List<Task> taskLisk = taskService.createTaskQuery().processDefinitionKey("OrderFlow")
                .processInstanceId(processInstanceId).orderByTaskCreateTime().desc().list();
        Task task = taskLisk.get(0);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("outcome",outcome);
        taskService.complete(task.getId(),map);
    }

    public List<TaskBean> selectTaskByAssignee(String assignee) {
        List<TaskBean> tbList=new ArrayList<TaskBean>();
        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey("OrderFlow").taskAssignee(assignee)
                .orderByTaskCreateTime().desc().list();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey("OrderFlow");
        for (Task task:taskList){
         TaskBean tb=new TaskBean();
         tb.setTask(task);
         ProcessInstance processInstance = processInstanceQuery.processInstanceId(task.getProcessInstanceId()).singleResult();
         String businessKey = processInstance.getBusinessKey();
         tb.setBusinessKey(new Long(businessKey));
         tbList.add(tb);
        }
        return tbList;
    }

    public TaskBean selectTaskById(String taskId) {
        TaskBean tb=new TaskBean();
        Task task = taskService.createTaskQuery().processDefinitionKey("OrderFlow").taskId(taskId).singleResult();
        tb.setTask(task);
        //获得当前任务节点向外的线
        List<String> outcomes = this.getOutcome(task);
        tb.setOutcomes(outcomes);
        return  tb;
    }

    public void completeTaskByOutcome(String taskId, String outcome) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("outcome",outcome);
        taskService.complete(taskId,map);
    }

    /**
     * 获得向外的线
     */
   public List<String> getOutcome(Task task){
       List<String> outcomeList=new ArrayList<String>();
       ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
       ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processDefinitionKey("OrderFlow").processInstanceId(task.getProcessInstanceId()).singleResult();
       String activityId = processInstance.getActivityId();
       ActivityImpl activityimpl = processDefinitionEntity.findActivity(activityId);
       List<PvmTransition> pvmList = activityimpl.getOutgoingTransitions();
       for (PvmTransition pvm:pvmList){
           String outcome= (String) pvm.getProperty("name");
            outcomeList.add(outcome);
       }
       return outcomeList;
   }


}
