package db_operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import db_connect.Conn;
import role_model.Admin;

public class admin_operate {
	public Connection con=new Conn().getConnection();//获取与数据库的连接
	public boolean truncateTable(String tableName) {
		boolean successDelete = false;
		String sql = "truncate table " + tableName;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, tableName);
			successDelete = pstmt.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return successDelete;
	}
	public boolean login_judge(Admin admin) {
		boolean flag=false;
		try {
			Statement sql=con.createStatement();//声明并实例化Statement对象
			ResultSet res;//声明ResultSet对象
			res=sql.executeQuery("select * from admin_info");
			while(res.next())//当前数据不是最后一条则进入循环
			{
				
				String u_id=res.getString("id");//获取列名是id的值
				String u_pswd=res.getString("psd");
				if(admin.get_User_Id().equals(u_id)&&admin.get_Pswd().equals(u_pswd)) {
					admin.setName(u_id);
					flag=true;
					return flag;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Vector<String[]> getInfo(String tableName) {
		// TODO Auto-generated method stub
		Vector<String[]> ret = new Vector<String[]>();
		String sql = "select * from "+tableName;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1,tableName);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			while (rs.next()) {
				String [] tmp = new String[columnNum];
				for(int i=0;i<columnNum;i++) {
					tmp[i] = rs.getString(i+1);
				}
				ret.add(tmp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
	public boolean insertINFO (String tableName,String [] info) {
		String sql = "insert into " + tableName +" values(";
		int len = info.length;
		for(int i=0;i<len;i++) {
			sql += "\'"+info[i] + "\'";
			if(i!=len-1)sql += ",";
		}
		sql += ")";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, tableName);
			pstmt.execute();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateOneRow(String tableName,String primaryKey,String [] info) {
		boolean updateSuccess = true;
		String sql = "delete from " + tableName + " where " +primaryKey + " = " + info[0];
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, tableName);
			pstmt.execute();
			updateSuccess &= insertINFO(tableName, info);
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateSuccess;
	}
	public boolean deleteOneRow(String tableName,String primaryKey,String id) {
		boolean deleteSuccess = true;
		String sql = "delete from " + tableName + " where " +primaryKey + " = " + id;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, tableName);
			pstmt.execute();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return deleteSuccess;
	}
	public boolean updateAlltable(String tableName,Vector<String[]> data) {
		truncateTable(tableName);
		for(int i=0;i<data.size();i++) {
			if(!insertINFO(tableName, data.get(i)))return false;
		}
		return true;
	}
	public Vector<String []> getGrade(){
		Vector<String[]> ret = new Vector<String[]>();
		String sql = "select e.xh,s.xm,e.xq,e.kh,c.km,e.gh,t.xm,e.zpcj from c,e,s,t where e.kh = c.kh and e.xh = s.xh and t.gh = e.gh";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String [] tmp = new String[8];
				for(int i=0;i<8;i++) {
					tmp[i] = rs.getString(i+1);
				}
				ret.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public boolean updateGrade(String xq,String xh,String kh,String gh,int cj) {
		boolean updateSuccess = true;
		String sql = "update e set zpcj = ? where xq = ? and xh = ? and kh = ? and gh = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,cj);
			pstmt.setString(2, xq);
			pstmt.setString(3,xh);
			pstmt.setString(4,kh);
			pstmt.setString(5,gh);
			pstmt.execute();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return updateSuccess;
	}
	public boolean deleteGrade(String xq,String xh,String kh,String gh) {
		boolean updateSuccess = true;
		String sql = "delete from e where xq = ? and xh = ? and kh = ? and gh = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, xq);
			pstmt.setString(2,xh);
			pstmt.setString(3,kh);
			pstmt.setString(4,gh);
			pstmt.execute();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return updateSuccess;
	}
}
