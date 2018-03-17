package action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import pojo.FileUploadPojo;
import pojo.Ftp;
import pojo.UserPojo;
import service.FtpServer;
import service.Function;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileAction extends ActionSupport implements Action{
	private String filePath;
	 private File myfile; //上传的文件
	 private String myfileFileName; //文件名称
	 private String myfileContentType; //文件类型
	 private String SUCCESS="success";
	 private String FAILED="failed";
	 private FtpServer ftpServer=new FtpServer();
	 private FtpServerAction ftpServerAction=new FtpServerAction();
	 private InputStream fileInput; 
	 private String filename="1.txt"; 
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public File getMyfile() {
		
		return myfile;
	}
	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}
	public String getMyfileFileName() {
		return myfileFileName;
	}
	public void setMyfileFileName(String myfileFileName) {
		this.myfileFileName = myfileFileName;
	}
	public String getMyfileContentType() {
		return myfileContentType;
	}
	public void setMyfileContentType(String myfileContentType) {
		this.myfileContentType = myfileContentType;
	}
	public InputStream getFileInput() {
		return fileInput;
	}
	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	 public String FileUpload(){
	      //  String realpath = ServletActionContext.getServletContext().getRealPath("/")+"upload";
		    String realpath = ServletActionContext.getServletContext().getRealPath("/")+"upload";
		    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		    Ftp ftpSite=(Ftp)ActionContext.getContext().getSession().get("ftp");
	        if (myfile != null) {
	        	String uuidString=Function.getUUIDString();
	        	String Filename=uuidString+myfileFileName.substring(myfileFileName.lastIndexOf("."), myfileFileName.length());
	        	File f=new File(realpath);
	            File savefile = new File(new File(realpath), Filename);
	            if (!f .exists()  && !f .isDirectory())
	                f.mkdirs();
	            File Temp=new File(f.getAbsolutePath()+"/"+Filename);
	            try {
					 FileUtils.copyFile(myfile, savefile);
					 FileUploadPojo fup=new FileUploadPojo();
					 fup.setFtpFileName(myfileFileName);
					 fup.setLocalDirectoryAndFileName(f.getAbsolutePath()+"/"+Filename);
					 fup.setFtpDirectory(filePath);		
					 
					 if(ftpServerAction.FtpFileUpload(ftpSite, fup)){
						 if(Temp.exists()){
							 Temp.delete();
						 }
						 
						 Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"上传文件:"+myfileFileName+"至FTP服务器:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"上传成功",Function.getInstantDateTime());
						 return SUCCESS;
					 }
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//return StaticParam.getUploadImageFailed();
					 if(Temp.exists()){
						 Temp.delete();
					 }
				}
				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"上传文件:"+myfileFileName+"至FTP服务器:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"上传失败",Function.getInstantDateTime());
	            return FAILED;
	            //ActionContext.getContext().put("message", "文件上传成功");
	        }
	        else{
				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"上传空文件至FTP服务器:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"上传失败",Function.getInstantDateTime());
	        	return FAILED;
	        }
	 }
//	 public String FileDownloadFromFtp() throws UnsupportedEncodingException{
//		 String realpath = ServletActionContext.getServletContext().getRealPath("/")+"download";
//		    System.out.println(realpath);
//		 	File f=new File(realpath);
//         if (!f .exists()  && !f .isDirectory())
//             f.mkdirs();
//	        if (filePath != null) {
//	        	filePath=new String(filePath.getBytes("ISO-8859-1"),"utf-8");
//	        	filePath=filePath.substring(0, filePath.lastIndexOf("/"));
//	   		    String uuidString=Function.getUUIDString();
//	     	    localFilenameUUID=uuidString+filePath.substring(filePath.lastIndexOf("."), filePath.length());
//	     	    String localFilename=filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
//	     	    String localFilePath=ServletActionContext.getServletContext().getRealPath("/")+"download"+"/"+localFilenameUUID;
//	        	if(ftpServerAction.FtpFileDownload((Ftp)ActionContext.getContext().getSession().get("ftp"), filePath, localFilePath)){
//	        		File downloadLocalFile=new File(localFilePath);
//	        		long fileSize=Function.getFileSize(localFilePath);
//	        		if(fileSize==-1){
//	        			return FAILED;
//	        		}
//	        		else if(fileSize==0){
//	        			if(downloadLocalFile.exists()&&downloadLocalFile.isFile()){
//	        				downloadLocalFile.delete();
//	        			}
//	        			return FAILED;
//	        		}		
//	        		return SUCCESS;
//	        	}
//	            return FAILED;
//	            //ActionContext.getContext().put("message", "文件上传成功");
//	        }
//	        else
//	        	return FAILED;
//	 }
//	 public String FileDownloadFromDownload() throws UnsupportedEncodingException{
//	      //  String realpath = ServletActionContext.getServletContext().getRealPath("/")+"upload";
//	        		HttpServletRequest request = ServletActionContext.getRequest();  
//	                String Agent = request.getHeader("User-Agent");  
//	                if (null != Agent) {  
//	                    Agent = Agent.toLowerCase();  
//	                    if (Agent.indexOf("firefox") != -1) {  
//	                    	this.filename = new String(this.filename.getBytes(),"iso8859-1");  
//	                    } else if (Agent.indexOf("msie") != -1) {  
//	                    	this.filename = new String(this.filename.getBytes(),"utf-8");   
//	                    } else {  
//	                    	this.filename = new String(this.filename.getBytes(),"utf-8");   
//	                    }  
//	                }
//	                //System.out.println("%%%%"+fileName);
//	        		fileInput=ServletActionContext.getServletContext().getResourceAsStream("download"+"/"+localFilenameUUID);
//	        		return SUCCESS;
//	    
//	 }
	 public String FileDownload() throws UnsupportedEncodingException{
	      //  String realpath = ServletActionContext.getServletContext().getRealPath("/")+"upload";
		    String realpath = ServletActionContext.getServletContext().getRealPath("/")+"download";
		    //System.out.println(realpath);
		    UserPojo loginUser=(UserPojo)ActionContext.getContext().getSession().get("user");
		    Ftp ftpSite=(Ftp)ActionContext.getContext().getSession().get("ftp");
		 	File f=new File(realpath);
            if (!f .exists()  && !f .isDirectory())
                f.mkdirs();
	        if (filePath != null) {
	        	filePath=new String(filePath.getBytes("ISO-8859-1"),"utf-8");
	    	
	        	filePath=filePath.substring(0, filePath.lastIndexOf("/"));
	   		    String uuidString=Function.getUUIDString();
	     	    String localFilenameUUID=uuidString+filePath.substring(filePath.lastIndexOf("."), filePath.length());
	     	    String localFilename=filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
	     	    String localFilePath=ServletActionContext.getServletContext().getRealPath("/")+"download"+"/"+localFilenameUUID;
	     	    if(ftpServerAction.FtpFileDownload(ftpSite, filePath, localFilePath)){
	        		File downloadLocalFile=new File(localFilePath);
	        		long fileSize=Function.getFileSize(localFilePath);
	        		if(fileSize==-1){
	    				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"下载文件:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"下载失败,下载文件夹",Function.getInstantDateTime());
	        			return FAILED;
	        		}
	        		else if(fileSize==0){
	        			if(downloadLocalFile.exists()&&downloadLocalFile.isFile())
        				     downloadLocalFile.delete();
	    				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"下载文件:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"下载失败,权限不足或者下载的文件长度为0",Function.getInstantDateTime());
	        			return FAILED;
	        		}	
	        		HttpServletRequest request = ServletActionContext.getRequest();  
	                String Agent = request.getHeader("User-Agent");  
	                this.filename=localFilename;
	                if (null != Agent) {  
	                    Agent = Agent.toLowerCase();  
	                    if (Agent.indexOf("firefox") != -1) {  
	                    	this.filename = new String(this.filename.getBytes(),"iso8859-1");  
	                    } else if (Agent.indexOf("msie") != -1) {  
	                    	this.filename = new String(this.filename.getBytes(),"utf-8");   
	                    }
	                    else if (Agent.indexOf("chrome") != -1) {  
	                    	this.filename = new String(this.filename.getBytes(),"iso8859-1");   
	                    } 
	                    else if (Agent.indexOf("safari") != -1) {  
	                    	this.filename = new String(this.filename.getBytes(),"iso8859-1");   
	                    } else {  
	                    	this.filename = new String(this.filename.getBytes(),"utf-8");   
	                    }  
	                }
	                //System.out.println("%%%%"+fileName);
	        		fileInput=ServletActionContext.getServletContext().getResourceAsStream("download"+"/"+localFilenameUUID);
    				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"下载文件:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"下载成功",Function.getInstantDateTime());
	        		return SUCCESS;
	        	}
				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"下载文件:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+filePath,"下载失败",Function.getInstantDateTime());
	            return FAILED;
	            //ActionContext.getContext().put("message", "文件上传成功");
	        }
	        else{
				Function.SaveLog(Function.getUUIDString(),loginUser.getUsername(),loginUser.getRealname(),"下载文件:ftp://"+ftpSite.getServer()+":"+ftpSite.getPort()+"; 空下载文件路径","下载失败",Function.getInstantDateTime());
	        	return FAILED;
	        }
	 }
	 public String FileDownloadClear(){
		 String realpath = ServletActionContext.getServletContext().getRealPath("/")+"download";
		 File fileFather=new File(realpath);
		 File[] fileList=fileFather.listFiles();
		 if(fileList!=null){
		 for(File file:fileList){
			 if(file.exists())
				 file.delete();
		 }
		 }
	    return SUCCESS;
	 }
	       
}
