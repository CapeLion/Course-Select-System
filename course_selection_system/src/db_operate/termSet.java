package db_operate;
import db_connect.*;
import java.sql.*;
import java.util.*;
//�����ݿ��л�ÿ��ε�ѧ��˳���ɴ���С����
public class termSet {
	public Connection con=new Conn().getConnection();
	public Vector getTermSet() {
		Vector<String> v_term=new Vector<String>(20);
		try {
			Statement sql=con.createStatement();//������ʵ����Statement����
			ResultSet res;//����ResultSet����
			res=sql.executeQuery("select distinct xq from o");
			while(res.next())//��ǰ���ݲ������һ�������ѭ��
			{
				String item=res.getString("xq");//��ȡ������id��ֵ
				v_term.add(item);
			}
			Collections.sort(v_term,Collections.reverseOrder());
		}catch(Exception e){
			
		}
		return v_term;
	}

}
