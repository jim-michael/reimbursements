package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.ReceiptDTO;
import com.revature.dto.ReceiptWithStatusDTO;
import com.revature.model.Receipt;
import com.revature.model.User;
import com.revature.util.JDBCUtility;

public class UserDAO {

	public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * From users WHERE ers_username = ? AND ers_password = ?";
			//String sql = "SELECT * FROM httpsession_demo.users WHERE username = ? AND password = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String user = rs.getString("ers_username");
				String pass = rs.getString("ers_password");
				String userRole = rs.getString("user_role");
				
				/*int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				String userRole = rs.getString("user_role");*/
				
				return new User(id, firstName, lastName, user, pass, userRole);
			} else {
				return null;
			}
		}
	}
//==============================================================add new receipt
	
	public void addNewReceipt(Receipt receipt, int id)throws SQLException
	{
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "insert into reimbursement(reimb_submitted,reimb_type,reimb_description,reimb_amount,reimb_author) values(now(),?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,receipt.getType());
			pstmt.setString(2,receipt.getDescription());
			pstmt.setDouble(3,receipt.getAmount());
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
		}
	}
	
	//================================================see all receipts
	public List<ReceiptWithStatusDTO> seeAllReceipts(int id)throws SQLException
	     
	{
		List<ReceiptWithStatusDTO> receipts = new ArrayList();
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "select reimb_type,reimb_id,reimb_submitted,reimb_description,reimb_amount from reimbursement where reimb_author = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) { // A while loop keeps going until it becomes false
				
				// 5. Grab all of the information from the current row that we are on
				
				int idd = rs.getInt("reimb_id");
				String type = rs.getString("reimb_type");
				String description= rs.getString("reimb_description");
				Double amount = rs.getDouble("reimb_amount");
				Timestamp dateSubmitted = rs.getTimestamp("reimb_submitted");
				ReceiptWithStatusDTO receipt = new ReceiptWithStatusDTO(idd,type,amount,description,dateSubmitted);
				receipts.add(receipt);
							
			}
			return receipts;
		}
	}
	
	//================================================see all receipts
		public List<ReceiptWithStatusDTO> adminSeeAllReceipts()throws SQLException
		     
		{
			List<ReceiptWithStatusDTO> receipts = new ArrayList();
			
			try (Connection con = JDBCUtility.getConnection()) {
				String sql = "select reimb_type,reimb_description,reimb_amount,reimb_resolved,reimb_status,reimb_id from reimbursement";
				PreparedStatement pstmt = con.prepareStatement(sql);
				//pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) { // A while loop keeps going until it becomes false
					
					// 5. Grab all of the information from the current row that we are on
					
					//int id = rs.getInt("id");
					String type = rs.getString("reimb_type");
					//String description= rs.getString("reimb_description");
					Double amount = rs.getDouble("reimb_amount");
					String status = rs.getString("reimb_status");
					Timestamp timestamp = rs.getTimestamp("reimb_resolved");
                    int id = rs.getInt("reimb_id");
                    ReceiptWithStatusDTO receipt = new ReceiptWithStatusDTO(id,type,amount,status,timestamp);
					receipts.add(receipt);
								
				}
				return receipts;
			}
		}
		
		//=======================================================  addNewRequestReceip
		
		public void addNewRequestReceipt(int id ,Double amount, String type, String description,String mimeType,InputStream content)throws SQLException
		{   Receipt receipt = null;
			     
			try (Connection con = JDBCUtility.getConnection()) {
				//Receipt receipt;
				String sql = "insert into reimbursement(reimb_submitted,reimb_type,reimb_description,reimb_amount,reimb_author,reimb_receipt) values(now(),?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,type);
				pstmt.setString(2,description);
				pstmt.setDouble(3,amount);
				pstmt.setInt(4, id);
				pstmt.setBinaryStream(5, content);
				pstmt.executeUpdate();
				
				
			}
		}
		
		//======================================================================= 10
		
		public List<ReceiptDTO> getAllreceiptsByRegular(int associateId) throws SQLException {
			try (Connection con = JDBCUtility.getConnection()) {
				List<ReceiptDTO> receiptdto = new ArrayList<>();
				
				String sql = "SELECT reimb_author, reimb_type,reimb_description,reimb_amount,reimb_status FROM reimbursement WHERE reimb_author = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, associateId);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int id = rs.getInt("reimb_author");
					String type = rs.getString("reimb_type");
					String description = rs.getString("reimb_description");
					Double amount = rs.getDouble("reimb_amount");
					String status = rs.getString("reimb_status");
					
					ReceiptDTO receipt = new ReceiptDTO(id,type, description,amount,status);
					
					receiptdto.add(receipt);
				}
				
				return receiptdto;
			}
		}
		//========================================================================11
		public InputStream getImageByUserId(int id) throws SQLException {
			try(Connection con = JDBCUtility.getConnection()) {
				String sql = "SELECT reimb_receipt FROM reimbursement WHERE reimb_id = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					InputStream image = rs.getBinaryStream("reimb_receipt");
					
					return image;
				}
				
				return null;
			}
		}
		
	
		
		//============================================================== 11
		public List<ReceiptWithStatusDTO> getApprovedReceipts(int id) throws SQLException {
			try (Connection con = JDBCUtility.getConnection()) {
				
			
				List<ReceiptWithStatusDTO> list = new ArrayList();
				
				ReceiptWithStatusDTO record;
				String sql = "select reimb_id,reimb_type,reimb_amount,reimb_status,reimb_resolved from reimbursement where reimb_status= ?";
               PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "approved");
				
				ResultSet rs = pstmt.executeQuery();
				
				
				while (rs.next()) {
					
					int idd = rs.getInt("reimb_id");
					String type = rs.getString("reimb_type");
					Double amount = rs.getDouble("reimb_amount");
					String status = rs.getString("reimb_status");
					Timestamp timestamp = rs.getTimestamp("reimb_resolved");
					 record = new ReceiptWithStatusDTO(idd,type,amount,status,timestamp);
					 list.add(record);
					
			}
				return list;
		}
		}
		
		//======================================================== 12 
		public List<ReceiptWithStatusDTO> getDeniedReceipts(int id) throws SQLException {
			try (Connection con = JDBCUtility.getConnection()) {
				
			
				List<ReceiptWithStatusDTO> list = new ArrayList();
				
				ReceiptWithStatusDTO record;
				String sql = "select reimb_id,reimb_type,reimb_amount,reimb_status,reimb_resolved from reimbursement where reimb_status= ?";
               PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "denied");
				
				ResultSet rs = pstmt.executeQuery();
				
				
				while (rs.next()) {
					
					int idd = rs.getInt("reimb_id");
					String type = rs.getString("reimb_type");
					Double amount = rs.getDouble("reimb_amount");
					String status = rs.getString("reimb_status");
					Timestamp timestamp = rs.getTimestamp("reimb_resolved");
					 record = new ReceiptWithStatusDTO(idd,type,amount,status,timestamp);
					 list.add(record);
					
			}
				return list;
		}
		}
		
		
		//=========================================================
		
		//======================================================== 13 
				public List<ReceiptWithStatusDTO> getPendingReceipts(int id) throws SQLException {
					try (Connection con = JDBCUtility.getConnection()) {
						
					
						List<ReceiptWithStatusDTO> list = new ArrayList();
						
						ReceiptWithStatusDTO record;
						String sql = "select reimb_id,reimb_type,reimb_amount,reimb_status,reimb_resolved from reimbursement where reimb_status= ?";
		               PreparedStatement pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, "pending");
						
						ResultSet rs = pstmt.executeQuery();
						
						
						while (rs.next()) {
							
							int idd = rs.getInt("reimb_id");
							String type = rs.getString("reimb_type");
							Double amount = rs.getDouble("reimb_amount");
							String status = rs.getString("reimb_status");
							Timestamp timestamp = rs.getTimestamp("reimb_resolved");
							 record = new ReceiptWithStatusDTO(idd,type,amount,status,timestamp);
							 list.add(record);
							
					}
						return list;
				}
				}
				
				
				//========================================================= change status
				public void changeStatus(int receiptNumber, String status)throws SQLException
				{
					try (Connection con = JDBCUtility.getConnection()) {
						String sql = "update reimbursement set reimb_resolved= now(), reimb_status = ? where reimb_id = ?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1,status);
						pstmt.setInt(2,receiptNumber);
						pstmt.executeUpdate();
					}
				}
				
				
	
}//end
