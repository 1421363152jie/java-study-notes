package cn.mj.controller;

import cn.mj.model.Menu;
import cn.mj.query.MenuQuery;
import cn.mj.service.MenuService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class MenuAction extends BaseAction{



	private MenuService menuService;
	
	
     private MenuQuery query=new  MenuQuery();
   
       private Menu menu=new Menu();
  


	public MenuService getMenuService() {
		return menuService;
	}


	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	public MenuQuery getQuery() {
		return query;
	}


	public void setQuery(MenuQuery query) {
		this.query = query;
	}


	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public String menu_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
         
         
		Page page = menuService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}

	
}
