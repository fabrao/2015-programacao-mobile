package com.github.mobappscourse.mobbank.service.bankops;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.mobappscourse.mobbank.service.bankops.dao.AccountXmlDAO;


@Path("account")
public class AccountResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAll() {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	return dao.getAll();
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(Account account) {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	dao.save(account);
    	return account;
    }
	
    @Path("{account_number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Account getBalance(@PathParam("account_number") long acNumber) {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	return dao.getById(acNumber);
    }

    @Path("{account_number}/withdraw/{value}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)    
    public String withdraw(@PathParam("account_number") long acNumber, @PathParam("value") double value) {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	
    	return dao.withdraw(acNumber, value);
    }

    @Path("{account_number}/deposit/{value}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)    
    public String deposit(@PathParam("account_number") long acNumber, @PathParam("value") double value) {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	
    	return dao.deposit(acNumber, value);
    }    
    
    @Path("{account_number}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)    
    public String closeAccount(@PathParam("account_number") long acNumber) {
    	AccountXmlDAO dao = new AccountXmlDAO();
    	
    	return dao.remove(acNumber);
    }
}
