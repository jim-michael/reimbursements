package com.revature.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import com.revature.dao.UserDAO;
import com.revature.model.User;

public class UserService {
	
	private UserDAO userDao;
	
	public UserService() {
		this.userDao = new UserDAO();
	}
	// it is added by me
	public UserService(UserDAO userDao) {
		this.userDao= userDao;
	}
//======================================================================================
	 public static String getMd5(String input)
	    {
	        try {
	  
	            // Static getInstance method is called with hashing MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	  
	            // digest() method is called to calculate message digest
	            //  of an input digest() return array of byte
	            byte[] messageDigest = md.digest(input.getBytes());
	  
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	  
	            // Convert message digest into hex value
	            String hashtext = no.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } 
	  
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	        
	
	    }
	
//=================================================================================	
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, FailedLoginException {
		
		 String x = getMd5(password);
		 password= x;
		// System.out.println("password" + password);
		
		User user = this.userDao.getUserByUsernameAndPassword(username, password);
		
		if (user == null) {
			throw new FailedLoginException("Incorrect username and/or password");
		}
		//System.out.println("you are in user service " + user.getFirstName());
		return user;
	}
	
}
