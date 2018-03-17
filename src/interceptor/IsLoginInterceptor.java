package interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pojo.UserPojo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class IsLoginInterceptor extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String LOGINTYPE_UNLOGIN="unlogin";
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=ServletActionContext.getRequest().getSession();
		UserPojo newuser=(UserPojo)session.getAttribute("user");
		if(newuser!=null&&newuser.getState()==1)	
				return arg0.invoke();
		else 
			return LOGINTYPE_UNLOGIN;
	}

}
