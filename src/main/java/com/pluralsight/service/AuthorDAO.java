package com.pluralsight.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.pluralsight.async_rest_grizzly.Author;

public class AuthorDAO {
	
private Map<String, Author> authorCache = new ConcurrentHashMap<String, Author>();
	
	public AuthorDAO() {
		Author a1 = new Author("paulo coelho", 55);
		a1.setCity("Madeira");
		a1.setId("1");
		
		this.authorCache.put("1", a1);
		
		
		Author a2 = new Author("AynRand", 65);
		a2.setCity("Berlin");
		a2.setId("2");
		
		this.authorCache.put("2", a2);
	}
	
	

	public void addAuthorToCache(final Author author) {
		this.authorCache.put(UUID.randomUUID().toString(), author);
	}

	public Author getAuthorFromCache(final String authorId) {
		return this.authorCache.get(authorId);
	}
	
	public Collection<Author> getAllAuthors() {
		return this.authorCache.values();
		
	}


}
