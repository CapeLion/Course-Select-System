package role_model;

import java.util.Vector;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import db_operate.admin_operate;

public class Admin {
	private String user_id;
	private String pswd;
	private String name = "ppp";
	private boolean flag = false;
	public Admin(String id,String pwd) {
		// TODO Auto-generated constructor stub
		user_id = id;
		pswd = pwd;
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public boolean trylog () {
		flag = new admin_operate().login_judge(this);
		return flag;
	}
	public Vector<String[]> getData(String tableName){
		return new admin_operate().getInfo(tableName);
	}
	public Vector<String[]> get_grade_data(){
		return new admin_operate().getGrade();
	}
	public boolean change(String tableName,Vector<String[]> info) {
		return new admin_operate().updateAlltable(tableName,info);
	}
	public boolean updateRow(String tableName,String priKey,String [] info) {
		return (new admin_operate().updateOneRow(tableName, priKey, info));
	}
	public boolean deleteRow(String tableName,String priKey,String id) {
		return (new admin_operate().deleteOneRow(tableName,priKey,id));
	}
	public boolean insertInfo(String tableName,String[] info) {
		boolean flag = new admin_operate().insertINFO(tableName, info);
		return flag;
	}
 	public String get_User_Id()
	{
		return user_id;
	}
	public String get_Pswd()
	{
		return pswd;
	}
	public void set_User_Id(String User_Id)
	{
		user_id=User_Id;
	}
	public void set_Pswd(String Pswd)
	{
		pswd=Pswd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
