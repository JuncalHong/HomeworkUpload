package service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.net.ftp.FTPFile;

import pojo.Ftp;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         FtpServer ftpServer=new FtpServer();
         Ftp ftos=new Ftp();
         ftos.setServer("192.168.1.110");
         ftos.setPort(21);
         ftos.setUsername("anonymous");
         ftos.setPassword("");
         ftpServer.control(ftos);
         ftpServer.connectFtp(); 
         FTPFile[] FtpFilelist=ftpServer.getFileList("/计算机网络安全技术");
         
         if(FtpFilelist!=null){
        	 for(FTPFile f:FtpFilelist)
				System.out.println(f.getRawListing());
         }
         
         else 
        	 System.out.println("error none");
         FtpFilelist=ftpServer.getFileList("/计算机网络安全技术");
         if(FtpFilelist!=null){
        	 for(FTPFile f:FtpFilelist)
				try {
					System.out.println(new String(f.toString().getBytes(),"gbk"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }
         else 
        	 System.out.println("error none");
        ftpServer.upload("G://切切.txt","中文.txt", "/计算机网络安全技术");
        ftpServer.download("/计算机网络安全技术/中文.txt","G://你好.txt");
         ftpServer.closeFtp();
	}
	

}
