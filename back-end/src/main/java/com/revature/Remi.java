package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AuthenticationController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionMappingController;

import io.javalin.Javalin;
import io.javalin.http.ExceptionMapper;
import io.javalin.http.staticfiles.Location;

public class Remi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Javalin app = Javalin.create((config) -> {
				config.enableCorsForAllOrigins();
				
				config.addStaticFiles("static", Location.CLASSPATH);
				
			});
			
			

			Logger logger = LoggerFactory.getLogger(Remi.class);
			app.before(ctx-> { logger.info(ctx.method() + "requeste received"+ ctx.path()+ "endpoint");});
			
			mapControllers(app, new AuthenticationController(), new ClientController());
			
			ExceptionMappingController exceptionController = new ExceptionMappingController();
			exceptionController.mapExceptions(app);

			
			//ExceptionMapper mapper = new ExceptionMapper();
			//mapper.mapExceptions(app);
			
			app.start(8081);
		}
		
		public static void mapControllers(Javalin app, Controller... controllers) {
			
			for (int i = 0; i < controllers.length; i++) {
				controllers[i].mapEndpoints(app);
			}
			
		}
			

}
