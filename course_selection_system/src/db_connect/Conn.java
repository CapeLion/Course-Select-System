package db_connect;
//连接数据库
import java.sql.*;
public class Conn{
	Connection con;
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student?serverTimezone=GMT%2B8","root","123456");
			System.out.println("数据库连接成功！");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		Conn c=new Conn();
		c.getConnection();
	}
}