package com.chainsys.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
	/**
	 * precondition id,name,price must be valid
	 * @throws Exception 
	 * 
	*/
	public void addMovie(Movie movie) throws Exception
	{
		try {
			Connection connection=ConnectionUtil.getConnection();
			String sqladd="insert into movies(id,name,price)VALUES(movie_id_seq.NEXTVAL,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sqladd);
			preparedStatement.setString(1, movie.name);
			preparedStatement.setInt(2, movie.price);
			int row=preparedStatement.executeUpdate();
			System.out.println("Äffected Row: "+row);
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to Insert Movie");
		}
	}
	
	public void updateMovie(Movie movie) throws Exception
	{
		try {
			Connection connection=ConnectionUtil.getConnection();
			String sqlupdate="update movies set name=? where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlupdate);
			preparedStatement.setString(1, movie.name);
			preparedStatement.setInt(2, movie.id);
			int row=preparedStatement.executeUpdate();
			System.out.println("Affected row: "+row);
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Update Failed");
		}
	}
	
	public void deleteMovie(Movie movie) throws Exception
	{
		try {
			Connection connection=ConnectionUtil.getConnection();
			String sqldelete="delete from movies where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqldelete);
			preparedStatement.setInt(1, movie.id);
			int row=preparedStatement.executeUpdate();
			System.out.println("Affected row: "+row);
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Delete Failed");
		}
	}
	
	public ArrayList<Movie> findAll() throws Exception
	{
		ArrayList<Movie> movielist=new ArrayList<Movie>();
		try {
			Connection connection=ConnectionUtil.getConnection();
			String sqlall="select id,name,price from movies";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlall);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				Movie viewmovie=new Movie();
				viewmovie.id=result.getInt("id");
				viewmovie.name=result.getString("name");
				viewmovie.price=result.getInt("price");
				movielist.add(viewmovie);
			}
			ConnectionUtil.close(connection, preparedStatement, result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to view all");
		}
		return movielist;
	}
	
	public Movie findById(Movie movie) throws Exception
	{
		Movie moviebyid=null;
		try {
			Connection connection=ConnectionUtil.getConnection();
			String sqlbyid="select id,name,price from movies where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlbyid);
			preparedStatement.setInt(1, movie.id);
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				moviebyid=new Movie();
				moviebyid.id=result.getInt("id");
				moviebyid.name=result.getString("name");
				moviebyid.price=result.getInt("price");
			}
			ConnectionUtil.close(connection, preparedStatement, result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Find Particular id Failed");
		}
		return moviebyid;
	}
}
