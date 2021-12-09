package com.revature.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.tika.Tika;

import com.revature.dto.ExceptionMessageDTO;
import com.revature.dto.LoginDTO;
import com.revature.model.Receipt;
import com.revature.dto.*;

//import org.eclipse.jetty.security.authentication.AuthorizationService;

import com.revature.model.User;
import com.revature.service.*;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
public class ClientController implements Controller {
	
private AuthorizationService authorizationService;
private ClientService clientservice;

//private AuthorizationService authorizationService = new AuthorizationService();
	
	public ClientController() {
		this.authorizationService = new AuthorizationService();
		this.clientservice = new ClientService();
		
	}
	
	// This will be a "protected" endpoint that can only be accessed when you are logged in as either admin or regular
	private Handler getStudentById = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
		//this.authorizationService.authorizeRegularAndAdmin(user); 
		// Any other logic goes below
		String id = ctx.pathParam("studentId");
		
		
		
		
	};
	
	// This will be a protected endpoint that can only be accessed when you are logged in as an admin
	private Handler addStudent = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeAdmin(user); // This will authorize only admins
		
		// Any other logic goes below
		
		
		
		
		
		
	};
	
	//================================= addreceipt
	private Handler addNewReceipt = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		System.out.println("your role is " + user.getUserRole());
		this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
	
		// Any other logic goes below
		//String id = ctx.pathParam("studentId");
		Receipt receipt = ctx.bodyAsClass(Receipt.class);
		this.clientservice.addNewReceipt(receipt,user.getId());
	};
	
	
	//=================================================================== see all receipts
	
	//================================= addreceipt
		private Handler seeAllReceipts = (ctx) -> {
			System.out.println("password of the user in see all receipts");
			List<ReceiptWithStatusDTO> receipts;
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			//System.out.println("your role is " + user.getFirstName());
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
			System.out.println("your role is " + user.getUserRole());
			// Any other logic goes below
			//String id = ctx.pathParam("studentId");
			//Receipt receipt = ctx.bodyAsClass(Receipt.class);
			receipts = this.clientservice.seeAllReceipts(user.getId());
			ctx.json(receipts);
		};
	
		
		private Handler adminSeeAllReceipts = (ctx) -> {
			List<ReceiptWithStatusDTO> receipts;
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			this.authorizationService.authorizeAdmin(user); // This will authorize only admins
		
			// Any other logic goes below
			//String id = ctx.pathParam("studentId");
			//Receipt receipt = ctx.bodyAsClass(Receipt.class);
			receipts = this.clientservice.AdminSeeAllReceipts();
			ctx.json(receipts);
		};
		
		//============================================= save image
		
		private Handler addNewRequestReceipt = (ctx) -> {
			// Protect endpoint
			
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			//System.out.println("your role is " + user.getUserRole());
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
		
			
			String amount = ctx.formParam("amount");
			String type = ctx.formParam("type");
			String description = ctx.formParam("description");
			
			/*
			 * Extracting file from HTTP Request
			 */
			UploadedFile file = ctx.uploadedFile("receipt_image");
			
			if (file == null) {
				ctx.status(400);
				ctx.json(new ExceptionMessageDTO("Must have an image to upload"));
				return;
			}
			
			InputStream content = file.getContent(); // This is the most important. It is the actual content of the file

			Tika tika = new Tika();
			
			// We want to disallow users from uploading files that are not jpeg, gif, or png
			// So, in the controller layer, figure out the MIME type of the file
			// jpeg = image/jpeg
			// gif = image/gif
			// png = image/png
			String mimeType = tika.detect(content);
			
			// Service layer invocation
			 this.clientservice.addNewRequestReceipt(user.getId(),amount,type,description, mimeType, content);
			ctx.json("receipt was created");
			ctx.status(201);
		};
		
		//=====================================================  9
		
		private Handler getImageByUserId = (ctx) -> {
			// protect endpoint
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			//this.authorizationService.authorizeAdmin(user); // This will authorize only admins
			//this.authorizationService.authorizeRegularAndAdmin(user);
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
			
			String userId = ctx.pathParam("id");
			
			InputStream image = this.clientservice.getImageByUserId(user,userId);
			
			Tika tika = new Tika();
			String mimeType = tika.detect(image);
			
			ctx.contentType(mimeType); // specifying to the client what the type of the content actually is
			ctx.result(image); // Sending the image back to the client
		};
		
		
		//===================================================== 11
		
		
		private Handler getApprovedReceipts= (ctx) -> {
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
		
			// Any other logic goes below
			//String id = ctx.pathParam("studentId");
			
			List<ReceiptWithStatusDTO> list;
			list = this.clientservice.getApprovedReceipts(user.getId());
			ctx.json(list);
			
			
			
			
		};
		
		//=======================================================    12
		
		private Handler getDeniedReceipts= (ctx) -> {
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
		
			// Any other logic goes below
			//String id = ctx.pathParam("studentId");
			
			List<ReceiptWithStatusDTO> list;
			list = this.clientservice.getDeniedReceipts(user.getId());
			ctx.json(list);
			
			
			
			
		};
		
		//====================================================== 13 
		private Handler getPendingReceipts= (ctx) -> {
			User user = (User) ctx.req.getSession().getAttribute("currentuser");
			this.authorizationService.authorizeRegularAndAdmin(user); // This will authorize either admins or regular users
		
			// Any other logic goes below
			//String id = ctx.pathParam("studentId");
			
			List<ReceiptWithStatusDTO> list;
			list = this.clientservice.getPendingReceipts(user.getId());
			ctx.json(list);
			
			
			
			
		};
	//======================================================================
		
		//====================================================== 13  change status
				private Handler changeStatus= (ctx) -> {
					User user = (User) ctx.req.getSession().getAttribute("currentuser");
					this.authorizationService.authorizeAdmin(user);
					// Any other logic goes below
					//String id = ctx.pathParam("studentId");
					String receiptNumber = ctx.pathParam("id");
					String status = ctx.pathParam("status");
					
					 this.clientservice.changeStatus(receiptNumber,status);		
					
				};
			//======================================================================	
				
		

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/client/getimage/{id}",getImageByUserId);//only admin
		app.post("/client/addform",addNewRequestReceipt);
		app.post("/client/newreceipt", addNewReceipt);
		app.get("client/receipts", seeAllReceipts);
		app.get("admin/allreceipts", adminSeeAllReceipts);
		app.get("/students/{studentId}", getStudentById);
		app.post("/students", addStudent);
		app.get("/client/approved/receipts", getApprovedReceipts);
		app.get("/client/denied/receipts", getDeniedReceipts);
		app.get("/client/pending/receipts", getPendingReceipts);
		app.put("/client/receipt/{id}/{status}", changeStatus);
	
}
	
	
}	

