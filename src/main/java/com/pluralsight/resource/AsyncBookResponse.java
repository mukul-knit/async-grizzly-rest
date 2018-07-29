package com.pluralsight.resource;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import com.pluralsight.async_rest_grizzly.Books;
import com.pluralsight.service.BookDAO;

import jersey.repackaged.com.google.common.util.concurrent.FutureCallback;
import jersey.repackaged.com.google.common.util.concurrent.Futures;
import jersey.repackaged.com.google.common.util.concurrent.ListenableFuture;

@Path("/asyncbooks")
public class AsyncBookResponse {
	
	BookDAO bookCache = new BookDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getAllBooksAsync(@Suspended final AsyncResponse resp) {
		ListenableFuture<Collection<Books>> futureObj = this.bookCache.getAllBooksAsync();
		Futures.addCallback(futureObj, new FutureCallback<Collection<Books>>() {

			@Override
			public void onFailure(Throwable arg0) {
				resp.resume(arg0);
				
			}

			@Override
			public void onSuccess(Collection<Books> books) {
				resp.resume(books);
			}
		});
	}
	
	@GET
	@Path("/usingCompletableFuture/books")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getAllBooksAsyncCompletableFuture(@Suspended final AsyncResponse resp) {
		CompletableFuture<Collection<Books>> futureObj = CompletableFuture.supplyAsync(new Supplier<Collection<Books>>() {

			@Override
			public Collection<Books> get() {
				return bookCache.getAllBooks();
			}
		});
		try {
			resp.resume(futureObj.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.resume(e);
		}
		
	}

}
