package com.revature.controller;

import javax.security.auth.login.FailedLoginException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.dto.ExceptionMessageDTO;
import com.revature.exception.AssignmentImageNotFoundException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.exception.UnauthorizedException;
import com.revature.service.*;

import io.javalin.Javalin;

public class ExceptionMappingController {

	public void mapExceptions(Javalin app) {
		app.exception(UnrecognizedPropertyException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new ExceptionMessageDTO(e));
		});
		
		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.json(new ExceptionMessageDTO(e));
			ctx.status(404);
		});
		
		// For example, in the cases where we want to modify or delete or get a student who does not exist (PUT /students/10000, DELETE /students/10000, 
		// GET /students/10000), that should result in some kind of StudentNotFoundException being thrown in the service layer.
		// When it propagates to the controller layer, that is when we can go ahead and specify a 404 Not found HTTP status code (and an appropriate exception message
		// that is encapsulated via a DTO (ExceptionMessageDTO))
		app.exception(ClientNotFoundException.class, (e, ctx) -> {
			ctx.json(new ExceptionMessageDTO(e));
			ctx.status(404);
		});
		
		/*app.exception(FormatIsNotRight.class, (e, ctx) -> {
			ctx.json(new ExceptionMessageDTO(e));
			ctx.status(404);
		});*/
		
		app.exception(FailedLoginException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new ExceptionMessageDTO(e));
		});
		
		app.exception(UnauthorizedException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.json(new ExceptionMessageDTO(e));
		});
		app.exception(AssignmentImageNotFoundException.class, (e, ctx) -> {
			ctx.status(404);
			ctx.json(new ExceptionMessageDTO(e.getMessage()));
		});
		
	}
	
}

