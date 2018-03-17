package action;

import pojo.UserPojo;
import service.ComputerControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ComputerAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SUCCESS="success";
	private String FAILED="failed";
	public String ComputerClose(){
		UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		ComputerControl.closeWindow();
		return SUCCESS;
	}
	public String ComputerRestart(){
		UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		ComputerControl.restartWindow();;
		return SUCCESS;
	}
	public String computerLogoff(){
		UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		ComputerControl.Logoff();
		return SUCCESS;
	}
}
