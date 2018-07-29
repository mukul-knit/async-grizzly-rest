package com.pluralsight.resources;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.pluralsight.async_rest_grizzly.Author;

public class BookResourceTest extends JerseyTest {
	
	protected Application configure() {
		 return new ResourceConfig().packages("com.pluralsight.resource");
	}
	
	
	@Test
	public void testGetALLLibraryAuthors() {
		Collection<Author> authors = target("authors").request().get(new GenericType<Collection<Author>>() {});
		assertNotNull(authors);
	}
}
