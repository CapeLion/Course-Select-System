package db_operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import db_connect.Conn;
import role_model.*;

public class student_operate {
	public Connection con=new Conn().getConnection();//获取与数据库的连接
	public boolean login_judge(student stu) {
		boolean flag=false;
		try
		{
			Statement sql=con.createStatement();//声明并实例化Statement对象
			ResultSet res;//声明ResultSet对象
			res=sql.executeQuery("select * from s_info");
			while(res.next())//当前数据不是最后一条则进入循环
			{
				
				String u_id=res.getString("xh");//获取列名是id的值
				String u_pswd=res.getString("psd");
				System.out.println("用户名："+u_id+"+"+u_pswd);
				if(stu.get_User_Id().equals(u_id)&&stu.get_Pswd().equals(u_pswd))
				{
					flag=true;
					return flag;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public Vector<String[]> getChosenClass(String xh) {
		Vector<String []> ret = new Vector<String[]>();
		String sql = "select c.kh,c.km,t.xm,o.sksj,o.skdd from c,o,t,e where e.xh = ? and c.kh = e.kh and e.gh = t.gh and e.gh = o.gh and o.kh = e.kh";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,xh);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String [] tmp = new String[5];
				for(int i=0;i<5;i++) {
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
	public List<Course> getCourseList(String kehao,String keming){
		List<Course> retList = new ArrayList<Course>();
		StringBuffer sqlString = new StringBuffer("SELECT * FROM O LEFT OUTER JOIN C ON O.kh=C.kh WHERE O.kh like "+kehao+" and C.km like "+keming);
		System.out.println(sqlString);
		try
		{
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next())
			{
				Course c = new Course();
				c.setKh(executeQuery.getString("kh"));
				c.setKm(executeQuery.getString("km"));
				c.setGh(executeQuery.getString("gh"));
				c.setSksj(executeQuery.getString("sksj"));
				c.setSkdd(executeQuery.getString("skdd"));
				retList.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean addSelectedCourse(String xh,String kh,String gh){
		String sql = "insert into E(xh,kh,gh,xq) "
				+ "values(?,?,?,2020)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, xh);
			preparedStatement.setString(2, kh);
			preparedStatement.setString(3, gh);
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String xh,String kh,String gh){
		String sql = "delete from E where xh=? and kh=? and gh=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, xh);
			preparedStatement.setString(2, kh);
			preparedStatement.setString(3, gh);
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
