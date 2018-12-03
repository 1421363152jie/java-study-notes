package cn.mj.ecps.utils;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class EbOrderFlowListener implements TaskListener {


    private static final long serialVersionUID = 1814249446136633769L;

    public void notify(DelegateTask delegateTask) {
        String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
        delegateTask.setAssignee(taskDefinitionKey+"er");
    }
}
