package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pojo.UserPojo;

public class SqlUser {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private DatabaseConnection dc;
	private ResultSet rs;
	public UserPojo login(UserPojo user){
		String sql="SELECT * from userinformation WHERE username=? and password=? and state=1";
		UserPojo loginUser=null;
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,user.getUsername());
			this.pstmt.setString(2,user.getPassword());
			ResultSet rs=this.pstmt.executeQuery();	
			if(rs.next()){
				loginUser=new UserPojo();
				loginUser.setUsername(rs.getString("username"));
				loginUser.setPassword(rs.getString("password"));
				loginUser.setRealname(rs.getString("realname"));
				loginUser.setRegtime(rs.getString("regtime"));
				loginUser.setType(rs.getInt("type"));
				loginUser.setState(rs.getInt("state"));
				if(loginUser.getType()==1){
					loginUser.setTypeString("管理员");
				}
				if(loginUser.getState()==0)
					loginUser.setStateString("禁用");
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
		return loginUser;
	}
	public UserPojo getUserInforByUserName(String username){
		UserPojo user=null;
		String sql="SELECT * from userinformation WHERE username=?";
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,username);
			ResultSet rs=this.pstmt.executeQuery();
			if(rs.next()){
				user=new UserPojo();
				user=new UserPojo();
				user=new UserPojo();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setRegtime(rs.getString("regtime"));
				user.setType(rs.getInt("type"));
				user.setState(rs.getInt("state"));
				if(user.getType()==1){
					user.setTypeString("管理员");
				}
				if(user.getState()==0)
					user.setStateString("禁用");
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
		
		return user;
	}
	public List<UserPojo> getAllUser(){
		List<UserPojo> UserList=new ArrayList<UserPojo>();
		UserPojo user=null;
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			String sql="SELECT * from userinformation  order by type desc,state asc";
			this.pstmt=this.conn.prepareStatement(sql);
		    ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
				user=new UserPojo();
				user=new UserPojo();
				user.setUsername(rs.getString("username"));
				//user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setRegtime(rs.getString("regtime"));
				user.setType(rs.getInt("type"));
				user.setState(rs.getInt("state"));
				if(user.getType()==1){
					user.setTypeString("管理员");
				}
				if(user.getState()==0)
					user.setStateString("禁用");
				UserList.add(user);
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
		
		return UserList;
	}
	public boolean update(UserPojo user){
		boolean flag=false;
		String sql="update userinformation set password=?,type=?,realname=?,state=?,regtime=? where username = ?";
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,user.getPassword());
			this.pstmt.setInt(2,user.getType());
			this.pstmt.setString(3,user.getRealname());
			this.pstmt.setInt(4,user.getState());
	
			this.pstmt.setString(5,user.getRegtime());
			this.pstmt.setString(6,user.getUsername());
			if(this.pstmt.executeUpdate()>0){
				flag=true;
			}
			if(null!=this.pstmt)
				this.pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean insert(UserPojo user){
		boolean flag=false;
		String sql="INSERT INTO userinformation(username,password,type,realname,state,regtime) VALUES (?,?,?,?,?,?)";
		try {
			dc=new DatabaseConnection();
			this.conn=dc.connection();
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,user.getUsername());
			this.pstmt.setString(2,user.getPassword());
			this.pstmt.setInt(3,user.getType());
			this.pstmt.setString(4,user.getRealname());
			this.pstmt.setInt(5,user.getState());
			this.pstmt.setString(6,user.getRegtime());
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
	 public boolean delete(UserPojo user){
			// TODO Auto-generated method stub
	    	boolean flag=false;
			String sql="DELETE from userinformation where username=?";
			try {
				dc=new DatabaseConnection();
				this.conn=dc.connection();
				this.pstmt=this.conn.prepareStatement(sql);
				this.pstmt.setString(1,user.getUsername());
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
}
