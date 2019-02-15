package com.chainsys.jdbc.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.jdbc.Movie;
import com.chainsys.jdbc.MovieDAO;
import com.chainsys.jdbc.MovieValidator;

public class TestMovieDAO {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		MovieDAO movieDAO=new MovieDAO();
		Movie movie=new Movie();
		System.out.println("1. Insert Movie");
		System.out.println("2. Update Movie");
		System.out.println("3. Delete Movie");
		System.out.println("4. Find Particular Movie");
		System.out.println("5. View All Movie");
		System.out.println("Choose Operations");
		int operation=scanner.nextInt();
		MovieValidator movieValidator;
		switch(operation)
		{
		case 1: System.out.println("Enter Movie name");
				movie.name=scanner.next();
				System.out.println("Enter Movie price");
				movie.price=scanner.nextInt();
				movieValidator=new MovieValidator();
				try {
					movieValidator.validateInsertMovie(movie);
					movieDAO.addMovie(movie);
				} catch (Exception e) {
					e.printStackTrace();
				}
				movieDAO.findAll();
				break;
		case 2: System.out.println("Enter id");
				movie.id=scanner.nextInt();
				System.out.println("Enter name");
				movie.name=scanner.next();
				movieValidator=new MovieValidator();
			try {
				movieValidator.validateUpdateMovie(movie);
				movieDAO.updateMovie(movie);
			} catch (Exception e) {
				e.printStackTrace();
			}
				movieDAO.findAll();
				break;
		case 3: System.out.println("Enter id");
				movie.id=scanner.nextInt();
				movieDAO.deleteMovie(movie);
				movieDAO.findAll();
				break;
		case 4: System.out.println("Enter id");
				movie.id=scanner.nextInt();
				Movie moviebyid=movieDAO.findById(movie);
				if(moviebyid==null)
				{
					System.out.println("No Record");
				}
				else
				{
					System.out.println("Movie id: "+moviebyid.id);
					System.out.println("Movie name: "+moviebyid.name);
					System.out.println("Movie price: "+moviebyid.price);
				}
				break;	
		case 5: ArrayList<Movie> movielist=new ArrayList<Movie>();
				movielist=movieDAO.findAll();
				if(movielist.size()==0)
				{
					System.out.println("No Data");
				}
				else
				{
					for(Movie temp:movielist)
					{
						System.out.println("Movie id: "+temp.id);
						System.out.println("Movie name: "+temp.name);
						System.out.println("Movie price: "+temp.price);
					}
				}
				break;	
		default: System.out.println("Choose only 1 to 5");
				 break;
		}
		scanner.close();
	}

}
