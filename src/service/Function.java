package service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.net.ftp.FTPFile;

import database.SqlLog;
import pojo.FileType;
import pojo.Ftp;
import pojo.LogPojo;

public class Function {
	FtpServer ftpServer=new FtpServer();
	
	public List<FileType> getFtpList(String path,String sessionName,Ftp FtpSite) throws UnsupportedEncodingException{
	   // Ftp FtpSite=new FtpServerAction().getFtpSession(sessionName);
	    List<FileType> fileTypeList=new ArrayList<FileType>();
	    if(FtpSite!=null){
			FileType fileType;
			ftpServer.control(FtpSite);
			ftpServer.connectFtp();
			FTPFile[] FtpFilelist = null;
			try {
				FtpFilelist = ftpServer.getFileList(new String(path.getBytes("ISO-8859-1"),"utf-8"));
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	         if(FtpFilelist!=null){
	        	 int i=1;
	        	 for(FTPFile f:FtpFilelist)
					try {
						
						//System.out.println(new String(f.getName().getBytes(),"gbk"));
						fileType=new FileType();
						fileType.setId(i);
						fileType.setFileName(new String(f.getName().getBytes(),"gbk"));
						fileType.setFileSize(f.getSize());
						fileType.setFileType(f.getType());
						fileType.setFilePath(new String(path.getBytes("ISO-8859-1"),"utf-8")+fileType.getFileName()+"/");
						fileTypeList.add(fileType);
						i++;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	         }
	         ftpServer.closeFtp();
	    }
	    
		return fileTypeList;
	}
	public String decodeBianma(String string) throws UnsupportedEncodingException{
		return new String(string.getBytes("ISO-8859-1"),"utf-8");
	} 
	public String getParentPath(String string) throws UnsupportedEncodingException{
		String OldParentPath=new String(string.getBytes("ISO-8859-1"),"utf-8");
		if("/".equals(OldParentPath))
			return OldParentPath;
		OldParentPath=OldParentPath.substring(0,OldParentPath.lastIndexOf("/"));
		return OldParentPath.substring(0,OldParentPath.lastIndexOf("/"))+"/";
	} 
	public static String getUUIDString(){
  	     return UUID.randomUUID().toString();
   }
	public static long getFileSize(String filePath){
		File f= new File(filePath);  
		if (f.exists() && f.isFile()){
			return f.length();
		}
		else
			return -1;
	}
	public static String getInstantDate(){
	     Date date=new Date();
  	     DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
  	     return format.format(date);
	}
	public static String getInstantDateTime(){
	     Date date=new Date();
 	     DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	     return format.format(date);
	}
	public static void SaveLog(String id,String username,String realname,String action,String result,String time){
		LogPojo log=new LogPojo();
		SqlLog sqlLog=new SqlLog();
		log.setId(id);
		log.setUsername(username);
		log.setRealname(realname);
		log.setAction(action);
		log.setResult(result);
		log.setTime(time);
		sqlLog.insert(log);
	}

}
