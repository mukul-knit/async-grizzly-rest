package com.pluralsight.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


import com.pluralsight.async_rest_grizzly.Books;
import com.pluralsight.service.BookDAO;


public class BooksResource {

	private BookDAO bookCache = new BookDAO();

	@GET
	@Path("/{bookId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Books get_book(@PathParam("bookId") final String bookId) {
		return this.bookCache.getBookFromCache(bookId);
	}
	
	@GET
	@Produces({"application/json;qs=2", "application/json;qs=1"})
	public Collection<Books> getBooks() {
		return bookCache.getAllBooks();
		
	}

}
