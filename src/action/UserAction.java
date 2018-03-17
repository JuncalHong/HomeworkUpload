package action;

import pojo.UserPojo;
import service.Function;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import database.SqlUser;

public class UserAction extends ActionSupport implements ModelDriven<UserPojo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserPojo userPojo=new UserPojo();
	private SqlUser sqlUser=new SqlUser();
	private String SUCCESS="success";
	private String FAILED="failed";
	private String opassword;
	private String npassword;
	private String rnpassword;
	public String getOpassword() {
		return opassword;
	}
	public void setOpassword(String opassword) {
		this.opassword = opassword;
	}
	public String getNpassword() {
		return npassword;
	}
	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}
	public String getRnpassword() {
		return rnpassword;
	}
	public void setRnpassword(String rnpassword) {
		this.rnpassword = rnpassword;
	}

	public String UserUpdateRealnameManage(){
		UserPojo newUser=new UserPojo();
		newUser=sqlUser.getUserInforByUserName(userPojo.getUsername());
		if(newUser!=null){
			newUser.setRealname(userPojo.getRealname());
			if(sqlUser.update(newUser))
				return SUCCESS;
			else
				return FAILED;
		}
		   
		else
			return FAILED;
	}
	public String UserUpdateInforUser(){
		 if(null==opassword||"".equals(opassword.replaceAll(" ", "")))
				return FAILED;
		 if(null==npassword||"".equals(npassword.replaceAll(" ", "")))
				return FAILED;
		 if(null==rnpassword||"".equals(rnpassword.replaceAll(" ", "")))
				return FAILED;
		 if(!npassword.equals(rnpassword))
			    return FAILED;
		 UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		 UserPojo newUser=new UserPojo();
		 newUser=sqlUser.getUserInforByUserName(loginUser.getUsername());
		 if(newUser==null||!newUser.getPassword().equals(opassword))
			 return FAILED;
		 newUser.setRealname(userPojo.getRealname());
		 newUser.setPassword(npassword);
		if(sqlUser.update(newUser)){
			 Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"修改个人用户信息","修改成功",Function.getInstantDateTime());
			 ActionContext.getContext().getSession().put("user",newUser);
			 return SUCCESS;
		}	
		else
			return FAILED;
		 
	}
	public String UserNewUser(){
	    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		if(userPojo==null)
			return FAILED;
		if(null==userPojo.getUsername()||"".equals(userPojo.getUsername().replaceAll(" ", "")))
			return FAILED;
		if(null==userPojo.getRealname()||"".equals(userPojo.getRealname().replaceAll(" ", "")))
			return FAILED;
		if(null==userPojo.getPassword()||"".equals(userPojo.getPassword().replaceAll(" ", "")))
			return FAILED;
		userPojo.setState(1);
		userPojo.setType(0);
		userPojo.setRegtime(Function.getInstantDateTime());
		if(sqlUser.insert(userPojo)){
			return SUCCESS;
		}
				return FAILED;
	}
	public String UserDelete(){
	    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		UserPojo newUser=new UserPojo();
		newUser=sqlUser.getUserInforByUserName(userPojo.getUsername());
		if(newUser!=null){
			if(newUser.getType()!=1){
			if(sqlUser.delete(newUser))
				return SUCCESS;
			else
				return FAILED;
			}
			else
				return FAILED;
		}
		   
		else
			return FAILED;
	}
	public String UserUnable(){
	    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		UserPojo newUser=new UserPojo();
		newUser=sqlUser.getUserInforByUserName(userPojo.getUsername());
		if(newUser!=null){
			if(newUser.getType()!=1){
				newUser.setState(0);
			if(sqlUser.update(newUser))
				return SUCCESS;
			else
				return FAILED;
			}
			else
				return FAILED;
		}
		   
		else
			return FAILED;
	}
	public String UserEnable(){
	    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
	    if(loginUser.getType()!=1)
	    	return FAILED;
		UserPojo newUser=new UserPojo();
		newUser=sqlUser.getUserInforByUserName(userPojo.getUsername());
		if(newUser!=null){
			if(newUser.getType()!=1){
				newUser.setState(1);
			if(sqlUser.update(newUser))
				return SUCCESS;
			else
				return FAILED;
			}
			else
				return FAILED;
		}
		   
		else
			return FAILED;
	}
	@Override
	public UserPojo getModel() {
		// TODO Auto-generated method stub
		return userPojo;
	}
	public UserPojo getUserPojo() {
		return userPojo;
	}
	public void setUserPojo(UserPojo userPojo) {
		this.userPojo = userPojo;
	}

}
