package cn.mj.interceptor;

import java.util.Map;

import cn.mj.controller.BaseAction;
import cn.mj.model.Emp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
   
	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
	   String result=null;
	   //获得的context
		ActionContext context = ai.getInvocationContext();
		//根据context获得session
		Map<String, Object> session = context.getSession();
		//从session中取到登陆的用户
		Emp emp = (Emp) session.get("user");
		//判断用户是否为空
		if(emp!=null){
			//不为空往下执行
			result = ai.invoke();
		}else{
			//返回登陆页面
			result=BaseAction.LOGIN;	
		}
		return result;
	}

	

}
