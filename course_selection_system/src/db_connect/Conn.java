package db_connect;
//�������ݿ�
import java.sql.*;
public class Conn{
	Connection con;
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���ݿ��������سɹ���");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student?serverTimezone=GMT%2B8","root","123456");
			System.out.println("���ݿ����ӳɹ���");
		
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