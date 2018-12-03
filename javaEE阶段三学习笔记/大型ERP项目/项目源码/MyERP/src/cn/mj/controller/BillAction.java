package cn.mj.controller;

import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.mj.model.BillBean;
import cn.mj.model.Supplier;
import cn.mj.query.BillQuery;
import cn.mj.service.OrderModelService;
import cn.mj.service.SupplierService;

import com.opensymphony.xwork2.ActionContext;

public class BillAction extends BaseAction{

  private OrderModelService orderModelService;
  
  
  private BillQuery query =new BillQuery();

  
  private SupplierService supplierService;
  
  


public BillQuery getQuery() {
	return query;
}




public void setSupplierService(SupplierService supplierService) {
	this.supplierService = supplierService;
}




public void setOrderModelService(OrderModelService orderModelService) {
	this.orderModelService = orderModelService;
}




public void setQuery(BillQuery query) {
	this.query = query;
}






	public String bill_bill(){
		ActionContext context = ActionContext.getContext();
		List<Supplier> suppliers = supplierService.listObj();
		List<BillBean> list = orderModelService.selectBillByCondition(query);
		context.put("blist", list);
		context.put("suppliers", suppliers);
		 Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("graph");
		root.addAttribute("caption", "订单商品数量");
		root.addAttribute("xAxisName", "商品名称");
		root.addAttribute("yAxisName", "数量");
		
		for (BillBean ba : list) {
		   String color="";
		   Random r=new Random();
		   for (int i = 0; i < 5; i++) {
			color=color+r.nextInt(10);
			   
		}
			
			root.addElement("set").addAttribute("name", ba.getPname())
					.addAttribute("value", ba.getCount() + "").addAttribute("color", color);

		}
		String xml = doc.asXML();
		xml=xml.replace("\"", "'");
		context.put("fcxml", xml);
		
		return SUCCESS;
	
	
  }
}
