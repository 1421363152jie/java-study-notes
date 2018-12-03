package cn.mj.ecps.dao;

import cn.mj.ecps.model.EbOrder;

import java.util.List;

public interface EbOrderDao {

 public void savaOrder(EbOrder order);


 public void updateOrder(EbOrder order);


 public EbOrder selectOrderById(Long orderId);

 public EbOrder selectDetailByOrderId(Long orderId);

}
