package com.githubparser;

import static spark.Spark.*;
import com.githubparser.controllers.*;
import com.githubparser.utils.*;
import io.swagger.annotations.*;

@SwaggerDefinition(info = @Info(description = "Github Parser API", //
version = Constants.APP_VERSION, //
title = "API to parse Github repositories files", //
contact = @Contact(name = "Marcio Oliveira", email = "deoliveira.marcioroberto@gmail.com", url = "https://github.com/oliveiramarcio") ) , //
schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS }, //
consumes = { "application/json" }, //
produces = { "application/json" }, //
tags = { @Tag(name = "swagger") })
public class App {
    public static void main(String[] args) {
        try {
            port(HerokuUtils.getHerokuAssignedPort());
            staticFiles.location("/public");
            before(new CorsFilter());
			new OptionsController();

            get("/", (request, response) -> {
                response.redirect("index.html");
                return null;
            });

            get("/" + Constants.APP_VERSION + "/swagger.json", (request, response) -> {
				return SwaggerParser.getSwaggerJson(Constants.APP_PACKAGE);
            });

            get("/github/parse", new GithubParserController());
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
    }
}