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
         FTPFile[] FtpFilelist=ftpServer.getFileList("/��������簲ȫ����");
         
         if(FtpFilelist!=null){
        	 for(FTPFile f:FtpFilelist)
				System.out.println(f.getRawListing());
         }
         
         else 
        	 System.out.println("error none");
         FtpFilelist=ftpServer.getFileList("/��������簲ȫ����");
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
        ftpServer.upload("G://����.txt","����.txt", "/��������簲ȫ����");
        ftpServer.download("/��������簲ȫ����/����.txt","G://���.txt");
         ftpServer.closeFtp();
	}
	

}
