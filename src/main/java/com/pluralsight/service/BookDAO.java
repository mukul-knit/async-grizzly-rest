package com.pluralsight.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pluralsight.async_rest_grizzly.Books;

import jersey.repackaged.com.google.common.util.concurrent.ListeningExecutorService;
import jersey.repackaged.com.google.common.util.concurrent.MoreExecutors;

public class BookDAO {

	private Map<String, Books> bookCache = new ConcurrentHashMap<String, Books>();
	private ListeningExecutorService executorService;
	
	public BookDAO() {
		Books book1 = new Books();
		book1.setName("Fooled by randomness");
		book1.setPublisher("Pearson");
		
		this.bookCache.put("1", book1);
		
		Books book2 = new Books();
		book2.setName("Fooled by randomness");
		book2.setPublisher("Pearson");
		this.bookCache.put("2", book2);
		
		executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
	}
	
	

	public void addBookToCache(final Books books) {
		this.bookCache.put(UUID.randomUUID().toString(), books);
	}

	public Books getBookFromCache(final String bookId) {
		return this.bookCache.get(bookId);
	}
	
	public Collection<Books> getAllBooks() {
		return this.bookCache.values();
		
	}

	public jersey.repackaged.com.google.common.util.concurrent.ListenableFuture<Collection<Books>> getAllBooksAsync() {
		return  executorService.submit(new Callable<Collection<Books>>() {

			@Override
			public Collection<Books> call() throws Exception {
				// TODO Auto-generated method stub
				return bookCache.values();
			}
		});
	}

}
