package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import pojo.Ftp;

public class FtpServer {
	private FTPClient ftpClient = null;
	private int code=0;
	private static String LOCAL_CHARSET = "GBK";
	// 
	private static String SERVER_CHARSET = "ISO-8859-1";
	public FtpServer(){
		
	}
	private Ftp ftp=new  Ftp();
	public  boolean  connectFtp(){
	        	if (ftpClient != null && ftpClient.isConnected()) {
			//System.out.println("FTP already open:" );

			   return true;
			  }
			  try {
			   ftpClient = new FTPClient();
               ftpClient.connect(ftp.getServer(), ftp.getPort());
			   ftpClient.login(ftp.getUsername(), ftp.getPassword());
			   // 
			   int reply = ftpClient.getReplyCode();
			   if (FTPReply.isPositiveCompletion(reply)) {
			   }
			   else 
				   return false;
			   
//			   if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(
//					   "OPTS UTF8", "ON"))) {
//					   LOCAL_CHARSET = "UTF-8";
//				}
			   ftpClient.setControlEncoding( LOCAL_CHARSET );
			 //  System.out.println("open FTP success:" + ftp.getServer()+";port:"+ftp.getPort() + ";name:"+ ftp.getUsername() + ";pwd:" + ftp.getPassword());
			   
			                // or ascii
			   return true;
			  } catch (Exception ex) {
			  // ex.printStackTrace();
			   return false;
			  }
	}
	public boolean closeFtp(){
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public FTPFile[] getFileList(String ftpPath)  {
		  FTPFile[] list = null;
		try {
			ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),SERVER_CHARSET));
			list = ftpClient.listFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return list;
    
	}
	public boolean upload(String localDirectoryAndFileName, String ftpFileName,String ftpDirectory) {
			  if (!ftpClient.isConnected()) {
			   return false;
			  }
			  boolean flag = false;
			  if (ftpClient != null) {
			   File srcFile = new File(localDirectoryAndFileName);
			   FileInputStream fis = null;
			   try {
				   ftpClient.changeWorkingDirectory(new String(ftpDirectory.getBytes(),
						   SERVER_CHARSET));
			    fis = new FileInputStream(srcFile);
			    ftpClient.setBufferSize(1024);
			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // .binally
			   // ftpClient.setControlEncoding("UTF-8");
			 
			
			   // ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			    //
			    flag = ftpClient.storeFile(new String(ftpFileName.getBytes(),
			    		SERVER_CHARSET), fis);
			   } catch (Exception e) {
			    e.printStackTrace();
			    return false;
			   } finally {
			    IOUtils.closeQuietly(fis);
			   }
			  }
			 
//			  System.out.println("success put file " + localDirectoryAndFileName
//			    + " to " + ftpDirectory + ftpFileName);
			  return flag;
			 }
	 public boolean changeDir(String ftpPath) {
		  if (!ftpClient.isConnected()) {
		   return false;
		  }
		  try {
		 
		   // ��·���е�б��ͳһ
		   char[] chars = ftpPath.toCharArray();
		   StringBuffer sbStr = new StringBuffer(256);
		   for (int i = 0; i < chars.length; i++) {
		 
		    if ('\\' == chars[i]) {
		     sbStr.append('/');
		    } else {
		     sbStr.append(chars[i]);
		    }
		   }
		   ftpPath = sbStr.toString();
		   // System.out.println("ftpPath"+ftpPath);
		 
		   if (ftpPath.indexOf('/') == -1) {
		    // ֻ��һ��Ŀ¼
		    // System.out.println("change"+ftpPath);
		    ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),
		      "iso-8859-1"));
		   } else {
		    // ���Ŀ¼ѭ������
		    String[] paths = ftpPath.split("/");
		    // String pathTemp = "";
		    for (int i = 0; i < paths.length; i++) {
		     // System.out.println("change "+paths[i]);
		     ftpClient.changeWorkingDirectory(new String(paths[i]
		       .getBytes(), SERVER_CHARSET));
		    }
		   }
		 
		   return true;
		  } catch (Exception e) {
		   e.printStackTrace();
		   return false;
		  }
		 }
	 public boolean download(String ftpDirectoryAndFileName,String localDirectoryAndFileName) {
			  if (!ftpClient.isConnected()) {
			   return false;
			  }
			  ftpClient.enterLocalPassiveMode(); // Use passive mode as default
			           // because most of us are behind
			           // firewalls these days.
			 
			  try {
			   // ��·���е�б��ͳһ
			   char[] chars = ftpDirectoryAndFileName.toCharArray();
			   StringBuffer sbStr = new StringBuffer(256);
			   for (int i = 0; i < chars.length; i++) {
			 
			    if ('\\' == chars[i]) {
			     sbStr.append('/');
			    } else {
			     sbStr.append(chars[i]);
			    }
			   }
			   ftpDirectoryAndFileName = sbStr.toString();
			   String filePath = ftpDirectoryAndFileName.substring(0,
			     ftpDirectoryAndFileName.lastIndexOf("/"));
			   String fileName = ftpDirectoryAndFileName
			     .substring(ftpDirectoryAndFileName.lastIndexOf("/") + 1);
			   // System.out.println("filePath | "+filePath);
			   // System.out.println("fileName | "+fileName);
			   this.changeDir(filePath);
			   FileOutputStream fos= new  FileOutputStream(localDirectoryAndFileName);
			   
			   ftpClient.retrieveFile( new String(fileName.getBytes(),SERVER_CHARSET),fos); // download
			                 // file
			  // System.out.print(ftpClient.getReplyString()); // check result
			   fos.close();
			  
			  } catch (IOException e) {
				  return false;
			   //e.printStackTrace();
			  }
			  
//			  System.out.println("Success get file" + ftpDirectoryAndFileName
//			    + " from " + localDirectoryAndFileName);
			  return true;
			 }
			 
	public void control(Ftp ftp){
		this.ftp=ftp;;	

	}
	
}
