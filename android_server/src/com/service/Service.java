package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBManager;
import com.info.Trainer_info;

public class Service {

    public Boolean login(String username, String password) {

        // ��ȡSql��ѯ���
        String logSql = "select * from tb_user where username ='" + username
                + "' and password ='" + password + "'";

        // ��ȡDB����
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // ����DB����
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                 sql.closeDB();
                 return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

    public Boolean register( String username, String password) {
    
    	
        // ��ȡSql��ѯ���
        String regSql = "insert into tb_user(username,password) values('" + username + "','" + password+ "') ";

        // ��ȡDB����
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
       
    }
    
    public List<Trainer_info> get_trainers() {
		List<Trainer_info> trainer_list=new ArrayList<>();
		  String trainer_Sql = "select * from tb_trainer ";
	       
	        // ��ȡDB����
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();
	        try {
	            ResultSet rs = sql.executeQuery(trainer_Sql );
	            while(rs.next()) {
	            	trainer_list.add(new Trainer_info(rs.getString("name"),rs.getString("motto"),rs.getString("introduce")));	
	    			
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        sql.closeDB();
		return trainer_list;
	}
    
    
 public ResultSet coach_sql() {
        
        // ��ȡSql��ѯ���
        String trainer_Sql = "select * from tb_trainer ";
       
        // ��ȡDB����
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        ResultSet rs= sql.executeQuery( trainer_Sql );
        sql.closeDB();
        return rs;
       
    }
}
