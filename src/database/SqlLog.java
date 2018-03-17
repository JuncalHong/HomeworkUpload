package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojo.LogPojo;

public class SqlLog {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private DatabaseConnection dc;
	private ResultSet rs;
	public boolean insert(LogPojo log){
		boolean flag=false;
		String sql="INSERT INTO log(id,username,realname,action,result,time) VALUES (?,?,?,?,?,?)";
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,log.getId());
			this.pstmt.setString(2,log.getUsername());
			this.pstmt.setString(3,log.getRealname());
			this.pstmt.setString(4,log.getAction());
			this.pstmt.setString(5,log.getResult());
			this.pstmt.setString(6,log.getTime());
			if(this.pstmt.executeUpdate()>0){
				flag=true;
			}
			if(null!=this.pstmt)
				this.pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return flag;
	}
	public List<LogPojo> getAllLog(){
		List<LogPojo> LogList=new ArrayList<LogPojo>();
		LogPojo log=null;
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			String sql="SELECT * from log  order by time desc";
			this.pstmt=this.conn.prepareStatement(sql);
		    this.rs=this.pstmt.executeQuery();
		while(rs.next()){
				log=new LogPojo();
				log.setId(rs.getString("id"));
				log.setRealname(rs.getString("realname"));
				log.setUsername(rs.getString("username"));
				log.setAction(rs.getString("action"));
				log.setResult(rs.getString("result"));
				log.setTime(rs.getString("time"));
				LogList.add(log);
	    	}
			if(null!=rs)
				rs.close();
			if(null!=this.pstmt)
				this.pstmt.close();
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					this.conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return LogList;
	}
}
