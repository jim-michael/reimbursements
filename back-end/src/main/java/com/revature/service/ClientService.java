package com.revature.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.UserDAO;
import com.revature.dto.ReceiptDTO;
import com.revature.dto.ReceiptWithStatusDTO;
import com.revature.exception.AssignmentImageNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.exception.UnauthorizedException;
import com.revature.model.Receipt;
import com.revature.model.User;
import com.revature.exception.ClientNotFoundException;

//import com.revature.dao.CustomerDAO;
//import com.revature.dto.CustomerDTO;
//import com.revature.model.Customers;

public class ClientService {
	UserDAO userdao;
	
	Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	public ClientService()
	{
		userdao = new UserDAO();
	}
	public ClientService(UserDAO userDao)
	{
		this.userdao = userDao;
	}
	
	public void addNewClient(String username,String password,String firsname,String lastname,String email,String role,
			String amount, String type, String description,String status, String authorid, String resolverid) throws SQLException
	{    
		//try 
		//{
	//	customer = customerdao.addCustomer(dto);
		//if(customer == null) 
			// logger.warn("addNewCustomer can not occur becasue account or customer is empty");
			 
			 
		//}
		//} catch(SQLException e) 
		//{
		//	e.getMessage();
		//	e.getClass().getName();
		//}
		//System.out.println(customer);
		//return "yes";
	}
	//================================   new receipt
	
	public void addNewReceipt(Receipt receipt , int id) throws SQLException {
		{
			userdao.addNewReceipt(receipt,id);
		}
	}
	//=============================================see all receipts
	
	public List<ReceiptWithStatusDTO> seeAllReceipts(int id)throws SQLException
	{
		List<ReceiptWithStatusDTO> l = userdao.seeAllReceipts(id);
		return l;
	}
	
	//=============================================see all receipts
	
		public List<ReceiptWithStatusDTO> AdminSeeAllReceipts()throws SQLException
		{
			List<ReceiptWithStatusDTO> l = userdao.adminSeeAllReceipts();
			return l;
		}
		
		//===============================================addNewRequestReceipt
		
		public void addNewRequestReceipt (int id, String amount,String type, String description,String mimeType,InputStream content )throws SQLException,NumberFormatException,InvalidParameterException   
		{
			Set<String> allowedFileTypes = new HashSet<>();
			allowedFileTypes.add("image/jpeg");
			allowedFileTypes.add("image/png");
			allowedFileTypes.add("image/gif");
			
			if (!allowedFileTypes.contains(mimeType)) {
				throw new InvalidParameterException("When adding an assignment image, only PNG, JPEG, or GIF are allowed");
			}
			try {
			double myamount = Double.parseDouble(amount);
			 userdao.addNewRequestReceipt(id,myamount,type,description,mimeType,content);
		
			} catch(NumberFormatException e) {
			   throw new InvalidParameterException("amount is not number"); 
			}
			
		}
		
		//=================================================================== 10
		public InputStream getImageByUserId(User user, String idd) throws SQLException, UnauthorizedException, AssignmentImageNotFoundException,InvalidParameterException {
			
			try {
				System.out.println("you are in serviec begin");
				int receiptImageId = Integer.parseInt(idd);
				
				// Check if they are an associate
				if (user.getUserRole().equals("regular")) {
					// Grab all of the assignments that belong to the associate
					//int receiptImageId = user.getId();
					List<ReceiptDTO> assignmentsThatBelongToAssociate = this.userdao.getAllreceiptsByRegular(user.getId());
					
					Set<Integer> assignmentIdsEncountered = new HashSet<>();
					for (ReceiptDTO a : assignmentsThatBelongToAssociate) {
						assignmentIdsEncountered.add(a.getId());
					}
					
					// Check to see if the image they are trying to grab for a particular assignment is actually their own assignment
					if (!assignmentIdsEncountered.contains( receiptImageId)) {
						throw new UnauthorizedException("You cannot access the images of  that do not belong to yourself");
					}
				}
				
				// Grab the image from the DAO
				InputStream image = this.userdao.getImageByUserId(receiptImageId);
				
				if (image == null) {
					throw new AssignmentImageNotFoundException("Image was not found for id " + receiptImageId);
				}
				System.out.println("you are in serviec end");
				return image;
				
			} catch(NumberFormatException e) {
				throw new InvalidParameterException(" id supplied must be an int");
			}
			
		}
		
	
		//=============================================================== 11
		
		public List<ReceiptWithStatusDTO> getApprovedReceipts(int id)throws SQLException,ClientNotFoundException
		{
			List<ReceiptWithStatusDTO> list;
			list = this.userdao.getApprovedReceipts(id);
			if(list == null)
				throw new ClientNotFoundException(" no record found");
			return list;
		}
		
		//++++++++++++++++++++++++++++=================================
		
		//=============================================================== 12
		
				public List<ReceiptWithStatusDTO> getDeniedReceipts(int id)throws SQLException,ClientNotFoundException
				{
					List<ReceiptWithStatusDTO> list;
					list = this.userdao.getDeniedReceipts(id);
					if(list == null)
						throw new ClientNotFoundException(" no record found");
					return list;
				}
				
				//++++++++++++++++++++++++++++=================================13
				
				public List<ReceiptWithStatusDTO> getPendingReceipts(int id)throws SQLException,ClientNotFoundException
				{
					List<ReceiptWithStatusDTO> list;
					list = this.userdao.getPendingReceipts(id);
					if(list == null)
						throw new ClientNotFoundException(" no record found");
					return list;
				}
				
				//++++++++++++++++++++++++++++=================================14
				
	//++++++++++++++++++++++++++++=================================chage status
				
				public void changeStatus(String receiptNumber, String status)throws SQLException,InvalidParameterException
				{
					try {
					int receiptId = Integer.parseInt(receiptNumber);
					 this.userdao.changeStatus(receiptId,status);
					
				} catch(NumberFormatException e) {
					throw new InvalidParameterException(" id supplied must be an int");
				}
					
				}
				
				//++++++++++++++++++++++++++++=================================14



}//end
