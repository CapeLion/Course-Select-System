package db_operate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Vector;

import db_connect.Conn;
import role_model.teacher;

public class teacher_operate {
	public Connection con=new Conn().getConnection();//��ȡ�����ݿ������
	public boolean login_judge(teacher teacher) {
		boolean flag=false;
		try {
			Statement sql=con.createStatement();//������ʵ����Statement����
			ResultSet res;//����ResultSet����
			res=sql.executeQuery("select * from t_info");
			while(res.next())//��ǰ���ݲ������һ�������ѭ��
			{
				
				String u_id=res.getString("gh");//��ȡ������gh��ֵ
				String u_pswd=res.getString("psd");
				if(teacher.get_User_Id().equals(u_id)&&teacher.get_Pswd().equals(u_pswd)) {
					flag=true;
					return flag;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	//��ȡ������Ϣ����
	//�γ̺ţ��γ����ƣ�ѧ�֣��Ͽ�ʱ�䣬�Ͽεص�
	public Vector<String[]> getClassSet(String selected_term,String user_id) {
		Vector<String[]> v_class=new Vector<String[]>();
		try {
			Statement sql=con.createStatement();//������ʵ����Statement����
			ResultSet res;//����ResultSet����
			res=sql.executeQuery("select * from o join c on o.kh=c.kh");
			while(res.next())//��ǰ���ݲ������һ�������ѭ��
			{
				String t_gh=res.getString("gh");
				String t_xq=res.getString("xq");
				//System.out.println(t_xq);
				
				if(t_gh.equals(user_id)&&t_xq.equals(selected_term))
				{
					//System.out.println("check");
					String []temp=new String[5];
					String kh=res.getString("kh");
					temp[0]=kh;
					String km=res.getString("km");
					temp[1]=km;
					String xf=res.getString("xf");
					temp[2]=xf;
					String sksj=res.getString("sksj");
					temp[3]=sksj;
					String skdd=res.getString("skdd");//��ȡ������..��ֵ
					temp[4]=skdd;
					v_class.add(temp);
				}
			}
			Collections.sort(v_class,Collections.reverseOrder());
		}catch(Exception e){
			
		}
		return v_class;
	}
	public Vector<String> getClass_kh(String selected_term,String user_id){
		Vector<String> v_class=new Vector<String>();
		try {
			Statement sql=con.createStatement();//������ʵ����Statement����
			ResultSet res;//����ResultSet����
			res=sql.executeQuery("select * from o join c on o.kh=c.kh");
			while(res.next())//��ǰ���ݲ������һ�������ѭ��
			{
				String t_gh=res.getString("gh");
				String t_xq=res.getString("xq");
				//System.out.println(t_xq);
				
				if(t_gh.equals(user_id)&&t_xq.equals(selected_term))
				{
					//System.out.println("check");
					String temp=new String();
					String kh=res.getString("kh");
					String km=res.getString("km");
					temp=kh+"-"+km;
					v_class.add(temp);
				}
			}
			Collections.sort(v_class,Collections.reverseOrder());
		}catch(Exception e){
			
		}
		return v_class;
	}
	public Vector<String[]> getStudentSet(String selected_term,String user_id,String kh_km)
	{
		Vector<String[]> v_stu=new Vector<String[]>();
		try {
			Statement sql=con.createStatement();//������ʵ����Statement����
			ResultSet res;//����ResultSet����
			res=sql.executeQuery("select  e.xh,e.xq,e.kh,e.gh,s.xm,s.yxh,s.sjhm,c.km,c.yxh,d.mc from e join s on e.xh=s.xh join c on e.kh=c.kh join d on c.yxh=d.yxh ");
			while(res.next())//��ǰ���ݲ������һ�������ѭ��
			{
				String t_gh=res.getString("gh");
				String t_xq=res.getString("xq");
				String t_kh=res.getString("kh");
				System.out.println(t_xq);
				String select_kh=kh_km.substring(0,8);
				System.out.println(user_id);
				if(t_gh.equals(user_id)&&t_xq.equals(selected_term)&&t_kh.equals(select_kh))
				{
					System.out.println("check");
					String [] temp=new String[4];
					String xh=res.getString("xh");
					String xm=res.getString("xm");
					String xy=res.getString("mc");
					String sjhm=res.getString("sjhm");
					temp[0]=xh;
					temp[1]=xm;
					temp[2]=xy;
					temp[3]=sjhm;
					v_stu.add(temp);
				}
			}
			Collections.sort(v_stu,Collections.reverseOrder());
		}catch(Exception e){
			
		}
		return v_stu;
	}

}
