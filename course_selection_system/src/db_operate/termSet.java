package db_operate;
import db_connect.*;
import java.sql.*;
import java.util.*;
//从数据库中获得开课的学期顺序，由大至小排序
public class termSet {
	public Connection con=new Conn().getConnection();
	public Vector getTermSet() {
		Vector<String> v_term=new Vector<String>(20);
		try {
			Statement sql=con.createStatement();//声明并实例化Statement对象
			ResultSet res;//声明ResultSet对象
			res=sql.executeQuery("select distinct xq from o");
			while(res.next())//当前数据不是最后一条则进入循环
			{
				String item=res.getString("xq");//获取列名是id的值
				v_term.add(item);
			}
			Collections.sort(v_term,Collections.reverseOrder());
		}catch(Exception e){
			
		}
		return v_term;
	}

}
