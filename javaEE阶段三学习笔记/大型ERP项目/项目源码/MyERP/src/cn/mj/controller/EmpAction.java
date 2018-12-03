package cn.mj.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import cn.mj.model.Dep;
import cn.mj.model.Emp;
import cn.mj.model.Menu;
import cn.mj.model.Role;
import cn.mj.query.EmpQuery;
import cn.mj.service.DepService;
import cn.mj.service.EmpService;
import cn.mj.service.RoleService;
import cn.mj.utils.MD5Utils;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class EmpAction extends BaseAction{
	
	private EmpService empService;
	
	
	private DepService depService;
	

	private EmpQuery query=new EmpQuery();
	
    private Emp emp=new Emp();
    
    
	private String captha;
	
	private RoleService roleService;
	
	
	private String roleIds;
	
	
	
	
	





	public String getRoleIds() {
		return roleIds;
	}





	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}





	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}





	public String getCaptha() {
		return captha;
	}





	public void setCaptha(String captha) {
		this.captha = captha;
	}





	public Emp getEmp() {
		return emp;
	}





	public void setEmp(Emp emp) {
		this.emp = emp;
	}





	public void setDepService(DepService depService) {
		this.depService = depService;
	}





	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}





	
	 public EmpQuery getQuery() {
		return query;
	}





	public void setQuery(EmpQuery query) {
		this.query = query;
	}




/**
 * 员工信息展示
 * @return
 */
	public String emp_list() {
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.listObj();
		context.put("dlist", list);
		 Integer pageNo = query.getPageNo();
		 if(pageNo==0){
	       	query.setPageNo(1);
	      }
		Page page = empService.queryObjBycondition(query, exclude);  
		context.put("page", page);

		return SUCCESS;

	}
	/**
	 * 登陆页面
	 * @return
	 */
	public String emp_tologin(){
		return SUCCESS;
		
	}
	
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
        String  rightCap= (String) session.getAttribute("piccode");	
		if(!StringUtils.equalsIgnoreCase(rightCap, captha)){
			context.put("tip", "capthaError");
		    return LOGIN;
		}
        //获得用户名
		String username = emp.getUsername();
		String pword=emp.getPassword();
		String password = MD5Utils.md5(pword);
		Emp emp2 = empService.getEmpByUnameAndPword(username, password);
		if(emp2==null){
			context.put("tip", "unameandpwrodError");
		    return LOGIN;
		}
		Map<String, Object> session2 = context.getSession();
		session2.put("user", emp2);
		//获得登陆用户的角色
		Set<Role> roles = emp2.getRoles();
		//获得角色下的菜单
		List<Map<String, Object>> mlist=new ArrayList<Map<String, Object>>();
        for (Role role : roles) {
        	//获得角色下的菜单
        	Set<Menu> menus = role.getMenus();
        	createTreeData(mlist, menus);
		}		
		JSONArray ja=JSONArray.fromObject(mlist);
		context.getSession().put("zNodes", ja);
		return MAIN;
	}
	
	/**
	 * 根据角色展示权限的菜单
	 * @param mlist
	 * @param menus
	 */
	public void createTreeData( List<Map<String, Object>> mlist,
			Set<Menu> menus) {
		 for (Menu menu : menus) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 获得id
			Integer id = menu.getMenuId();
			// 获得父类的id
			Integer pId = menu.getParentMenuId();
			// 获得菜单的名称
			String name = menu.getName();
			//获得链接地址
			 String url = menu.getUrl();
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				map.put("url", url);
			   map.put("target", "main");
				mlist.add(map);
	
		}


	}
	
	/**
	 * 注销登陆
	 */
	
	 public String emp_logout(){
		 ActionContext context = ActionContext.getContext();
	     Map<String, Object> session2 = context.getSession();
	     session2.remove("user");
		return MAIN;
	 }
	
	/**
	 * 员工的添加页面
	 */
	public String emp_input() {
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.listObj();
		context.put("dlist", list);

		return SUCCESS;

	}
	/**
	 * 员工添加
	 * @throws IOException
	 */
	public void ajax_emp_input() throws IOException{
	    String md5 = MD5Utils.md5(emp.getPassword());
	    emp.setPassword(md5);
		empService.sava(emp);
		response.getWriter().write("success");
	}
	
	/**
	 * 用户名的重复检验
	 * @throws IOException 
	 */
	public void ajax_emp_validUsername() throws IOException{
		String result="yes";
		Emp emp2 = empService.getEmpByUname(emp.getUsername());
	    if(emp2!=null){
	    	result="no";
	    }
	   response.getWriter().write(result);
	}
	/**
	 * 修改用户展示
	 */
	public String emp_update() {
		emp= empService.getObj(emp.getEmpId());	
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.listObj();
		context.put("dlist", list);

		return SUCCESS;

	}
	/**
	 * 修改用户
	 * @throws IOException 
	 */
	public void ajax_emp_update() throws IOException{
		empService.updateEmp(emp);
		
		response.getWriter().write("success");
	}
	
	/**
	 * 删除员工
	 */
	public String emp_delete(){
		empService.delete(emp.getEmpId());
		return LIST;
		
	}
	
	/**
	 * 分配角色页面展示
	 */
	
	public String emp_listpop(){
		ActionContext context = ActionContext.getContext();
       List<Role> roles = empService.getEmpByRoles(emp.getEmpId());
   	    context.put("roles", roles);
		
		return SUCCESS;
		
	}
	/**
	 * 分配角色
	 * @throws IOException 
	 */
	 public void ajax_emp_grantrole() throws IOException{
		 empService.updateEmpRoles(emp.getEmpId(), roleIds);
		 
		response.getWriter().write("success"); 
		 
	 }
	
	   /**
	    * 生成验证图片
	    * @throws Exception
	    */
	    public void ajax_emp_getImage() throws Exception{
			 System.out.println("#######################生成数字和字母的验证码#######################");  
		        BufferedImage img = new BufferedImage(68, 22,  
		  
		        BufferedImage.TYPE_INT_RGB);  
		  
		        // 得到该图片的绘图对象    
		  
		        Graphics g = img.getGraphics();  
		  
		        Random r = new Random();  
		  
		        Color c = new Color(200, 150, 255);  
		  
		        g.setColor(c);  
		  
		        // 填充整个图片的颜色    
		  
		        g.fillRect(0, 0, 68, 22);  
		  
		        // 向图片中输出数字和字母    
		  
		        StringBuffer sb = new StringBuffer();  
		        
		  
		        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
		  
		        int index, len = ch.length;  
		  
		        for (int i = 0; i < 4; i++) {  
		  
		            index = r.nextInt(len);  
		  
		            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
		  
		            (255)));  
		  
		            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
		            // 输出的  字体和大小                      
		  
		            g.drawString("" + ch[index], (i * 15) + 3, 18);  
		            //写什么数字，在图片 的什么位置画    
		  
		            sb.append(ch[index]);  
		  
		        }  
		        //把验证码的值放入session中
		        request.getSession().setAttribute("piccode", sb.toString());  
		        //把验证码的图片写回浏览器
		        ImageIO.write(img, "JPG", response.getOutputStream());  
		}
	 
	
	

}
