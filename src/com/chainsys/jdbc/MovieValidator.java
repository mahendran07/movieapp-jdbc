package com.chainsys.jdbc;

public class MovieValidator {
	public void validateInsertMovie(Movie movie) throws Exception
	{
		if(movie.name==null || movie.name.equalsIgnoreCase("null") || movie.name.isEmpty())
		{
			throw new Exception("Invalid Movie Name");
		}
		if(movie.price<=0)
		{
			throw new Exception("Price Must be greater than zero");
		}
	}
	
	public void validateUpdateMovie(Movie movie) throws Exception
	{
		if(movie.id<=0)
		{
			throw new Exception("Invalid Movie id");
		}
		if(movie.name==null || movie.name.equalsIgnoreCase("null") || movie.name.isEmpty())
		{
			throw new Exception("Invalid Movie Name");
		}
	}
}
