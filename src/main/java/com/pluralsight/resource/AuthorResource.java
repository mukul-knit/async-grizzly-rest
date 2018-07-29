package com.pluralsight.resource;

import java.util.Collection;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pluralsight.async_rest_grizzly.Author;
import com.pluralsight.service.AuthorDAO;

@Path("/authors")
public class AuthorResource {
	
	private AuthorDAO authorDao = new AuthorDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Author> getALLLibraryAuthors() {
		return this.authorDao.getAllAuthors();
	}
	
	@GET
	@Path("/{authorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Author getAuhtor(@BeanParam Author author) {
		return(this.authorDao.getAuthorFromCache(author.getId()));
	}

}
