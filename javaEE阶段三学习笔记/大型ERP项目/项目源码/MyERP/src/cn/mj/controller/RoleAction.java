package cn.mj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import cn.mj.model.Menu;
import cn.mj.model.Role;
import cn.mj.query.RoleQuery;
import cn.mj.service.MenuService;
import cn.mj.service.RoleService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction{



	private RoleService roleService;
	
	
     private RoleQuery query=new  RoleQuery();
   
       private Role role=new Role();
  
    private MenuService menuService;
    
    private String permId;
    
    
    
       

	public String getPermId() {
		return permId;
	}


	public void setPermId(String permId) {
		this.permId = permId;
	}


	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	public RoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public RoleQuery getQuery() {
		return query;
	}


	public void setQuery(RoleQuery query) {
		this.query = query;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

   /**
    * 角色页面展示
    * @return
    */
	public String role_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
   
		Page page = roleService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}
	/**
	 * 添加页面的展示
	 * @return
	 */
	public String role_input(){
		return SUCCESS;
		
	}
	/**
	 * 添加 
	 * @throws IOException 
	 */
   public void ajax_role_input() throws IOException{
	   roleService.sava(role);
	   response.getWriter().write("success");
   }
	/**
	 * 修改页面展示
	 * @return
	 */
   public String role_update(){
	   role=roleService.getObj(role.getRoleId());
	return SUCCESS;
	   
   }
   /**
    * 修改
 * @throws IOException 
    */
   public void ajax_role_update() throws IOException{
	   roleService.update(role);
	   response.getWriter().write("success");
   }
   
   /**
    * 删除
    */
   public String role_delete(){
	   roleService.delete(role.getRoleId());
	return LIST;
	   
   }
   
   /**
    * 分配权限
    */
   public String role_listperm(){
		ActionContext context = ActionContext.getContext();
	   //获得角色
	   Role role = roleService.getObj(query.getRoleId());
	   //获得角色下的菜单
	   Set<Menu> menus = role.getMenus();
	   //获得系统菜单(顶层菜单)
	   Menu rootMenu = menuService.getObj(1);
	   List<Map<String, Object>> mlist=new ArrayList<Map<String, Object>>();
	    createTreeData(rootMenu, mlist, menus);
	   JSONArray ja=JSONArray.fromObject(mlist);
	   context.put("zNodes", ja);
	return SUCCESS;
	   
   }
   
	public void createTreeData(Menu menu, List<Map<String, Object>> mlist,
			Set<Menu> rootMenus) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断菜单不为空
		if (menu != null) {
			// 获得id
			Integer id = menu.getMenuId();
			// 获得父类的id
			Integer pId = menu.getParentMenuId();
			// 获得菜单的名称
			String name = menu.getName();
			// 判断不是系统菜单就添加
			if (id.intValue() != 1) {
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				// 遍历当前角色下的菜单是否与id相等
				for (Menu m : rootMenus) {
					if (m.getMenuId().intValue() == menu.getMenuId().intValue()) {
						map.put("checked", true);
						map.put("open", true);
						break;
					}
				}
				mlist.add(map);
			}

			// 获得子类菜单
			Set<Menu> ms = menu.getMenus();
			for (Menu me : ms) {
				if (ms != null && ms.size() > 0) {
					createTreeData(me, mlist, rootMenus);
				}

			}

		}

	}
   /**
    * 分配权限
 * @throws IOException 
    */
   
	public void ajax_role_grantPerm() throws IOException{
		
		roleService.updateGrentRole(role.getRoleId(), permId);
		response.getWriter().write("success");	
	}
	
	
	
   
}
