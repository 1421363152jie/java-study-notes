package cn.mj.controller;

import java.io.IOException;

import cn.mj.model.Dep;
import cn.mj.query.DepQuery;
import cn.mj.service.DepService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class DepAction extends BaseAction{



	private DepService depService;
	
	
     private DepQuery query=new  DepQuery();
   
      private Dep dep=new Dep();
  


	public DepService getDepService() {
		return depService;
	}


	public void setDepService(DepService depService) {
		this.depService = depService;
	}


	public DepQuery getQuery() {
		return query;
	}


	public void setQuery(DepQuery query) {
		this.query = query;
	}


	public Dep getDep() {
		return dep;
	}


	public void setDep(Dep dep) {
		this.dep = dep;
	}

   /**
    * 部门信息的展示
    * @return
    */
	public String dep_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
		Page page = depService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}
	/**
	 * 部门添加页面展示
	 * @return
	 */
	public String dep_input(){
		
		return SUCCESS;
	}
	
	/**
	 * 部门添加
	 * @throws IOException 
	 * 
	 */
	public void ajax_dep_input() throws IOException{
		depService.sava(dep);
		response.getWriter().write("success");
		
	}
	/**
	 * 部门修改页面展示
	 */
	
	public String dep_update(){
		dep=depService.getObj(dep.getDepId());
		return SUCCESS;
	}
	
	/**
	 * 部门修改
	 */
	public void ajax_dep_update() throws IOException{
		depService.update(dep);
		response.getWriter().write("success");	
	}
	/**
	 * 删除部门
	 */
	public String dep_delete(){
		depService.delete(dep.getDepId());
		return LIST;
		
	}
	
	
	
}
