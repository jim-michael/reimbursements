package com.revature.unittest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.controller.*;
import javax.security.auth.login.FailedLoginException;
import com.revature.exception.*;
import com.revature.model.Receipt;
import com.revature.model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import com.revature.dao.UserDAO;
import com.revature.dto.ReceiptDTO;
import com.revature.dto.ReceiptWithStatusDTO;
import com.revature.service.ClientService;
import com.revature.service.UserService;
import static org.mockito.ArgumentMatchers.eq;

public class UnitTest {
	@Test
	public void getUserByUsernameAndPasswordFailed() {
		UserDAO mocksuserdao = mock(UserDAO.class);
		User user = new User(1,"gamal","gamal","gamal","gamal","admin");
		UserService us =  new UserService(mocksuserdao);
		//when(mocksuserdao.getUserByUsernameAndPassword("gamal","gamal")).thenReturn(user);
		//User actual = us.getUserByUsernameAndPassword("gamal","gamal");
		//assertEquals(user,actual);
		Assertions.assertThrows(FailedLoginException.class,() ->
		{ us.getUserByUsernameAndPassword("gamal","gamal"); });
	}
		
	@Test
	public void getUserByUsernameAndPasswordpassed() throws SQLException,FailedLoginException {
		UserDAO mocksuserdao = mock(UserDAO.class);
		User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		UserService us =  new UserService(mocksuserdao);
		when(mocksuserdao.getUserByUsernameAndPassword("gamal","e9f99cff925f61d8c62fd0bc3b5edc54")).thenReturn(user);
		User actual = us.getUserByUsernameAndPassword("gamal","gamal");
		//System.out.println(actual);
		assertEquals(user,actual);
		//Assertions.assertThrows(FailedLoginException.class,() ->
		//{ us.getUserByUsernameAndPassword("gamal","gamal"); });
	}
	
	
	/*@Test
	public void getUserByUsernameAndPasswordpassedFailedLogin() throws SQLException,FailedLoginException {
		UserDAO mocksuserdao = mock(UserDAO.class);
		User user = new User(1,"gamal","gamal","gamal","gamal","admin");
		UserService us =  new UserService(mocksuserdao);
		when(mocksuserdao.getUserByUsernameAndPassword("gamal","gamal")).thenThrow(FailedLoginException.class);
		User actual = us.getUserByUsernameAndPassword("gamal","ahmedl");
		//System.out.println(actual);
		//assertEquals(user,actual);
		Assertions.assertThrows(FailedLoginException.class,() ->
		{ us.getUserByUsernameAndPassword("gamal","gamal"); });
	}*/
	
	
	@Test
	public void seeAllReceipts()throws SQLException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		Date date = new Date();
		Timestamp x = new Timestamp(date.getTime());
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"one",3.0,"status",x);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"one",44.0,"status",x);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.seeAllReceipts(5)).thenReturn(receipts);
		 actual = us.seeAllReceipts(5);
		//System.out.println(actual);
		assertEquals(receipts,actual);
	}
	

	@Test
	public void seeAllReceiptsfailed()throws SQLException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<Receipt> receipts = new ArrayList();
		List<Receipt> actual;
		Receipt one = new Receipt("one","two",3.0);
		Receipt two = new Receipt("one","two",44.0);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		//when(mocksuserdao.seeAllReceipts(5)).thenReturn(receipts);
		when( mocksuserdao.seeAllReceipts(eq(1))).thenThrow(SQLException.class);

		 //actual = us.seeAllReceipts(5);
		//System.out.println(actual);
		//assertEquals(receipts,actual);
		 Assertions.assertThrows(SQLException.class,() ->
			{ us.seeAllReceipts(1); });
	}
	

	@Test
	public void adminseeAllReceiptspositive()throws SQLException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		Date date = new Date();
		Timestamp x = new Timestamp(date.getTime());
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"one",3.0,"status",x);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"one",44.0,"status",x);
		
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.adminSeeAllReceipts()).thenReturn(receipts);
		 actual = us.AdminSeeAllReceipts();
		//System.out.println(actual);
		assertEquals(receipts,actual);
	}
	
	@Test
	public void adminseeAllReceiptsfailed()throws SQLException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<Receipt> receipts = new ArrayList();
		List<Receipt> actual;
		Receipt one = new Receipt("one","two",3.0);
		Receipt two = new Receipt("one","two",44.0);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		//when(mocksuserdao.seeAllReceipts(5)).thenReturn(receipts);
		when( mocksuserdao.adminSeeAllReceipts()).thenThrow(SQLException.class);

		 //actual = us.seeAllReceipts(5);
		//System.out.println(actual);
		//assertEquals(receipts,actual);
		 Assertions.assertThrows(SQLException.class,() ->
			{ us.AdminSeeAllReceipts(); });
	}
	
	@Test
	public void getApprovedReceipts()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getApprovedReceipts(1)).thenReturn(receipts);
		 actual = us.getApprovedReceipts(1);
		//System.out.println(actual);
		assertEquals(receipts,actual);
	}
	
	@Test
	public void getDeniedReceipts()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getDeniedReceipts(1)).thenReturn(receipts);
		 actual = us.getDeniedReceipts(1);
		//System.out.println(actual);
		assertEquals(receipts,actual);
	}
	
	@Test
	public void getPendingReceipts()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getPendingReceipts(1)).thenReturn(receipts);
		 actual = us.getPendingReceipts(1);
		//System.out.println(actual);
		assertEquals(receipts,actual);
	}
	@Test
	public void getPendingReceiptsfailed()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getPendingReceipts(eq(1))).thenThrow(SQLException.class);
		 //actual = us.getPendingReceipts(1);
		//System.out.println(actual);
		//assertEquals(receipts,actual);
		Assertions.assertThrows(SQLException.class,() ->
		{ us.getPendingReceipts(1); });
	}
	
	@Test
	public void getdeniedReceiptsfailed()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getDeniedReceipts(1)).thenThrow(SQLException.class);
		 //actual = us.getPendingReceipts(1);
		//System.out.println(actual);
		//assertEquals(receipts,actual);
		Assertions.assertThrows(SQLException.class,() ->
		{ us.getDeniedReceipts(1); });
	}

	@Test
	public void getApprovedReceiptsfailed()throws SQLException,ClientNotFoundException {
		UserDAO mocksuserdao = mock(UserDAO.class) ;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		//User user = new User(1,"gamal","gamal","gamal","e9f99cff925f61d8c62fd0bc3b5edc54","admin");
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		List<ReceiptWithStatusDTO> actual;
		ReceiptWithStatusDTO one = new ReceiptWithStatusDTO(1,"gamal",23.5,"status",timestamp);
		ReceiptWithStatusDTO two = new ReceiptWithStatusDTO(1,"gamal",24.8,"status",timestamp);
		receipts.add(one);
		receipts.add(two);
		ClientService us =  new ClientService(mocksuserdao);
		when(mocksuserdao.getApprovedReceipts(1)).thenThrow(SQLException.class);
		 //actual = us.getPendingReceipts(1);
		//System.out.println(actual);
		//assertEquals(receipts,actual);
		Assertions.assertThrows(SQLException.class,() ->
		{ us.getApprovedReceipts(1); });
	}

}// end
