package com.kali.DemoRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;


public class MysqlRepository {
	
	Connection con=null;
	public MysqlRepository() {
		String url="jdbc:mysql://localhost:3306/jdbc";
		String username="root";
		String password="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens(){
		
		List<Alien> aliens= new ArrayList<Alien>();
		String sql="select * from alien";
		try {
			Statement statement=con.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				Alien alien=new Alien();
				alien.setId(resultSet.getInt(1));
				alien.setName(resultSet.getString(2));
				alien.setPoints(resultSet.getInt(3));
				aliens.add(alien);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return aliens;
	}
	public Alien getAlien(int id) {
		Alien a=new Alien();
		String sql="select * from alien where id = " +id;
		try {
			Statement statement=con.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			if(resultSet.next()) {
				a.setId(resultSet.getInt(1));
				a.setName(resultSet.getString(2));
				a.setPoints(resultSet.getInt(3));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return a;
	}
	public void create(Alien a) {
		String sql="insert into alien values(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,a.getId());
			ps.setString(2,a.getName());
			ps.setInt(3,a.getPoints());
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	} 
	public void update(Alien a) {
		String sql="update alien set name=?,points=? where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1,a.getName());
			ps.setInt(2,a.getPoints());
			ps.setInt(3,a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		
		String sql="delete from alien where id = "+id;
		System.out.println("Deleted record Id  : "+id);
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			//ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
