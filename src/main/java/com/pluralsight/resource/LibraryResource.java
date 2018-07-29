package com.pluralsight.resource;

import javax.ws.rs.Path;

@Path("/library")
public class LibraryResource {
	
	@Path("/books")
	public BooksResource getAllBooks() {
		return new BooksResource();
	}

}
