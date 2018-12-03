package cn.mj.ecps.service.impl;

import cn.mj.ecps.dao.EbOrderDao;
import cn.mj.ecps.dao.EbOrderDetailDao;
import cn.mj.ecps.dao.EbSkuDao;
import cn.mj.ecps.model.*;
import cn.mj.ecps.service.EbCartService;
import cn.mj.ecps.service.EbOrderFlowService;
import cn.mj.ecps.service.EbOrderService;
import cn.mj.ecps.service.EbSkuService;
import cn.mj.ecps.utils.EbMJUtis;
import cn.mj.ecps.utils.EbStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EbOrderServiceImpl implements EbOrderService {

    @Autowired
    private EbSkuDao skuDao;

    @Autowired
    private EbOrderDao orderDao;

    @Autowired
    private EbOrderDetailDao detailDao;

    @Autowired
    private EbCartService cartService;

    @Autowired
    private EbOrderFlowService flowService;



    public String savaOrder(HttpServletRequest request, HttpServletResponse response, EbOrder order, List<EbOrderDetail> detailList) {
        //保存订单
        orderDao.savaOrder(order);
        for (EbOrderDetail detail:detailList){
            detail.setOrderId(order.getOrderId());
            detailDao.savaOrderDetail(detail);

            //扣减数据库的库存
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("skuId",detail.getSkuId());
            map.put("quantity",detail.getQuantity());
            int updateStock = skuDao.updateStock(map);
            if(updateStock==0){
               throw  new EbStockException();
            }
            //扣减redi中的库存
           skuDao.updateStockRedis(map);
        }

        //清空购物车
        cartService.clearCart(response,request);
        //启动流程
        String processInstanceId= flowService.startInstance(order.getOrderId());
        return processInstanceId;
    }

    public void updatePayOrder(String processInstanceId, Long OrderId) {
        EbOrder order=new EbOrder();
        order.setOrderId(OrderId);
        order.setIsPaid((short)1);
        orderDao.updateOrder(order);
        flowService.completeTask(processInstanceId,"付款");
    }

    /**
     * 查询未外呼的订单
     */
    public List<TaskBean> selectTaskBeanByAssignee(String assignee, Short isCall) {
        List<TaskBean> tbList = flowService.selectTaskByAssignee(assignee);
        List<TaskBean> tbList1=new ArrayList<TaskBean>();
        for (TaskBean tb:tbList){
            EbOrder order = orderDao.selectOrderById(tb.getBusinessKey());
            if(order.getIsCall().shortValue()==isCall.shortValue()){
                tb.setOrder(order);
                tbList1.add(tb);
            }
        }
        return tbList1;
    }

    public TaskBean selectTbOrderDetail(String taskId, Long orderId) {
        TaskBean tb = flowService.selectTaskById(taskId);
        EbOrder order = orderDao.selectDetailByOrderId(orderId);
        tb.setOrder(order);
        return tb;
    }

    public void updateCompleteCall(Long orderId) {
        EbOrder order =new EbOrder();
        order.setOrderId(orderId);
        order.setIsCall((short) 1);
        orderDao.updateOrder(order);
    }

    public List<TaskBean> selectTaskBeanByAssignee(String assignee) {
        List<TaskBean> tbList = flowService.selectTaskByAssignee(assignee);
        for (TaskBean tb:tbList){
            EbOrder order = orderDao.selectOrderById(tb.getBusinessKey());
                tb.setOrder(order);
        }
        return tbList;
    }

    public void updateCompleteTask(String taskId, Long orderId, String outcome) {
        EbOrder order=new EbOrder();
        order.setOrderId(orderId);
        order.setIsCall((short)1);
        orderDao.updateOrder(order);
        flowService.completeTaskByOutcome(taskId,outcome);
    }

}