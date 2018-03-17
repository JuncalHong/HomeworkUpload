package action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pojo.UserPojo;
import service.Function;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import database.SqlUser;

public class UserLoginAction extends ActionSupport implements ModelDriven<UserPojo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3499303788647703647L;
	private String SUCCESS="success";
	private String FAILED="failed";
	private SqlUser sqlUser=new SqlUser();
	UserPojo userPojo=new UserPojo();
	public String UserLogin(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		UserPojo newuser=(UserPojo)session.getAttribute("user");
		if(newuser!=null){
			if(sqlUser.login(newuser)!=null){
				return SUCCESS;
			}
			return FAILED;
		}
		else{
		   if(userPojo==null||userPojo.getUsername()==null||"".equals(userPojo.getUsername().replaceAll(" ", ""))||userPojo.getPassword()==null||"".equals(userPojo.getPassword().replaceAll(" ", ""))){
			  return FAILED;
		   }
		   else {
			   newuser=sqlUser.login(userPojo);
			   if(newuser!=null){
					Function.SaveLog(Function.getUUIDString(), newuser.getUsername(), newuser.getRealname(),"登录系统","登录成功",Function.getInstantDateTime());

				    ActionContext.getContext().getSession().put("user",newuser);
					return SUCCESS;
					
				}
			   return FAILED;
		   }	   
		}		
	}
	public String UserLoginOut(){
		 UserPojo  loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		 if( loginUser!=null)
		 Function.SaveLog(Function.getUUIDString(), loginUser.getUsername(), loginUser.getRealname(),"登出系统","登出成功",Function.getInstantDateTime());
		 ServletActionContext.getRequest().getSession().invalidate();
		return FAILED;
	}
	public String UserAuth(){
		UserPojo newuser=(UserPojo)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(newuser!=null&&newuser.getState()==1)
			return "AuthPass";
		else
			return "AuthFailed";
	}
	@Override
	public UserPojo getModel() {
		// TODO Auto-generated method stub
		return this.userPojo;
	}
}
