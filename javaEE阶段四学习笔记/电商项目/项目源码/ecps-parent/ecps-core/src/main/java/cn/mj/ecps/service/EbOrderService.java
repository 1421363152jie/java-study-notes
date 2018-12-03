package cn.mj.ecps.service;


import cn.mj.ecps.model.EbOrder;
import cn.mj.ecps.model.EbOrderDetail;
import cn.mj.ecps.model.TaskBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EbOrderService {

  public String savaOrder(HttpServletRequest request, HttpServletResponse response, EbOrder order, List<EbOrderDetail> detailList);


  public void updatePayOrder(String processInstanceId,Long OrderId);


  public List<TaskBean> selectTaskBeanByAssignee(String assignee,Short isCall);

  public TaskBean  selectTbOrderDetail(String taskId,Long orderId);

  public void updateCompleteCall(Long orderId);

  public List<TaskBean> selectTaskBeanByAssignee(String assignee);


  public void updateCompleteTask(String taskId,Long orderId,String outcome);
}
