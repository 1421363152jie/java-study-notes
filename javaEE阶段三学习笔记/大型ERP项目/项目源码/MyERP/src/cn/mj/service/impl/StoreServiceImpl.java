package cn.mj.service.impl;

import java.util.Set;

import cn.mj.dao.OrderDetailDao;
import cn.mj.dao.OrderModelDao;
import cn.mj.dao.ProductDao;
import cn.mj.dao.StoreDao;
import cn.mj.model.OrderDetail;
import cn.mj.model.OrderModel;
import cn.mj.model.Product;
import cn.mj.model.Store;
import cn.mj.model.StoreDetail;
import cn.mj.query.StoreQuery;
import cn.mj.service.StoreService;

public class StoreServiceImpl extends BaseServiceImpl<Store, StoreQuery> implements StoreService {
	private StoreDao storeDao;
	
	private OrderDetailDao orderDetailDao;
	
	private ProductDao productDao;
	
	private OrderModelDao orderModelDao;
	
	

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao=storeDao;
	}

	@Override
	public void updateInStock(Integer storeId, Integer productNum,
			Integer productId, Integer orderDetailId) {
		//获得仓库
		Store store = storeDao.getObj(storeId);
		//获得订单明细
		 OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//获得仓库下的明细
		Set<StoreDetail> details = store.getsDetails();
		boolean isExet=false;
		for (StoreDetail sd : details) {
			//判断仓库明细中是否存在该商品
			if(sd.getProduct().getProductId().intValue()==productId.intValue()){
				//如果存在直接在原有的商品上添加
				sd.setNum(sd.getNum()+productNum);
				//减去订单明细中的商品剩余数量
				orderDetail.setSurplus(orderDetail.getSurplus()-productNum);
				isExet=true;
				break;
			}
		}
		if(!isExet){
			//创建一个订单
			StoreDetail sDetail =new StoreDetail();
			//仓库id
	         sDetail.setStoreId(storeId);
	         //商品
	         Product product = productDao.getObj(productId);
			 sDetail.setProduct(product);
			 //设置商品的数量
			 sDetail.setNum(productNum);
			 //将明细加入到仓库
			 details.add(sDetail);
		
		}
		//根据订单明细获得订单的id
		String orderId = orderDetail.getOrderId();
		//根据id查询订单
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//查询订单下的订单明细
		Set<OrderDetail> details2 = order.getDetails();
	    boolean isFinsh=true;
		//遍历订单的明细
		for (OrderDetail dt : details2) {
			//判断明细下的商品数量
			if(dt.getSurplus().intValue()!=0){
				//设置订单状态未入库中
				order.setOrderState(2);
				isFinsh=false;
				break;
			}
			
		}
		if(isFinsh){
			order.setOrderState(3);
		}
		
		
		
		
		
		
	}
	
	
	

}
