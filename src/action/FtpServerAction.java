package action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pojo.FileUploadPojo;
import pojo.Ftp;
import pojo.UserPojo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import service.FtpServer;
import service.Function;

public class FtpServerAction extends ActionSupport implements ModelDriven<Ftp>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ftpUrl;
	 private Ftp ftp=new Ftp();
	 private String SUCCESS="success";
	 private String FAILED="failed";
	 private FtpServer ftpServer=new FtpServer();
	 public String FtpLogin(){
		    ftpServer.control(ftp);
	    	ActionContext actionContext = ActionContext.getContext();
		    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		    if(ftpServer.connectFtp()){
		    	ftpServer.closeFtp();
		    	actionContext.getSession().put(ftp.getServer()+":"+ftp.getPort(),ftp);
				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"登录FTP服务器:ftp://"+ftp.getServer()+":"+ftp.getPort(),"登录成功",Function.getInstantDateTime());
		    	return SUCCESS;
		    }
		    else{
				 Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"登录FTP服务器:ftp://"+ftp.getServer()+":"+ftp.getPort(),"登录失败",Function.getInstantDateTime());
		    	 return FAILED;
		    }
			  
	 }
	 public boolean FtpFileUpload(Ftp ftpSiteSeesion,FileUploadPojo fup){
		    ftpServer.control(ftpSiteSeesion);
		    if(ftpServer.connectFtp()){
		    	if(ftpServer.upload(fup.getLocalDirectoryAndFileName(), fup.getFtpFileName(),fup.getFtpDirectory())){
		    		ftpServer.closeFtp();
		    		return true;
		    	}
		    	else{
		    		ftpServer.closeFtp();
		    		return false;
		    	}
		    }
		    else
			   return false;
	 }
	 public boolean FtpFileDownload(Ftp ftpSiteSeesion,String ftpDirectoryAndFileName,String localDirectoryAndFileName){
		    ftpServer.control(ftpSiteSeesion);
		    if(ftpServer.connectFtp()){
		    	if(ftpServer.download(ftpDirectoryAndFileName, localDirectoryAndFileName)){
		    		ftpServer.closeFtp();
		    		return true;
		    	}
		    	else{
		    		ftpServer.closeFtp();
		    		return false;
		    	}
		    }
		    else
			   return false;
	 }
	 public String FtpLoginOut(){
		    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		 if(ActionContext.getContext().getSession().get(ftpUrl)!=null){
			 @SuppressWarnings("rawtypes")
			 Map session=ActionContext.getContext().getSession();
			 session.remove(ftpUrl);
			 session.remove("ftp");
			 Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"登出FTP服务器:ftp://"+ftpUrl,"登出成功",Function.getInstantDateTime());
			 return SUCCESS;
		 }
		 return FAILED;
	 }
	@Override
	public Ftp getModel() {
		// TODO Auto-generated method stub
		return ftp;
	}
	public String getFtpUrl() {
		return ftpUrl;
	}
	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}
}
