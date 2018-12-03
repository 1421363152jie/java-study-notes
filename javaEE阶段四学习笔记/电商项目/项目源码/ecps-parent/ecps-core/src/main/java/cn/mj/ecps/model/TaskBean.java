package cn.mj.ecps.model;

import org.activiti.engine.task.Task;

import java.util.List;

public class TaskBean {

    private Long BusinessKey;

    private Task task;

    private String incomes;

    private List<String> outcomes;

    private EbOrder order;

    public Long getBusinessKey() {
        return BusinessKey;
    }

    public void setBusinessKey(Long businessKey) {
        BusinessKey = businessKey;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getIncomes() {
        return incomes;
    }

    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }

    public List<String> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<String> outcomes) {
        this.outcomes = outcomes;
    }

    public EbOrder getOrder() {
        return order;
    }

    public void setOrder(EbOrder order) {
        this.order = order;
    }
}
