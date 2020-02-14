package com.githubparser.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import com.githubparser.repositories.GithubParserRepository;
import com.githubparser.responses.GithubParserResponse;
import com.githubparser.utils.Constants;
import com.githubparser.utils.Validators;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import spark.Request;
import spark.Response;
import spark.Route;

@Api
@Path("/github/parse")
@Produces("application/json")
public class GithubParserController implements Route {
	@Override
	@GET
	@ApiOperation(value = "Parses Github repository URL and returns repository files with total line numbers and bytes grouped by file extension in JSON format.", nickname = "GithubParserController")
	@ApiImplicitParams({ //
			@ApiImplicitParam(required = true, dataType = "string", name = Constants.REPOSITORY_QUERY_PARAM, paramType = "query") //
	}) //
	@ApiResponses(value = { //
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = GithubParserResponse.class), //
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = Constants.INVALID_QUERY_PARAMETER_MSG, response = String.class), //
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error. See returned message for details.", response = String.class) //
	})
	public String handle(@ApiParam(hidden = true) Request request, @ApiParam(hidden = true) Response response)
			throws Exception {
		String repositoryUrl = request.queryParams(Constants.REPOSITORY_QUERY_PARAM);

		if (!Validators.validGithubURL(repositoryUrl, null, false)) {
			response.status(HttpServletResponse.SC_BAD_REQUEST);
			return Constants.INVALID_QUERY_PARAMETER_MSG;
		}

		try {
			response.status(HttpServletResponse.SC_OK);
			return new Gson().toJson(new GithubParserRepository().GetGithubFilesGroupedByExtension(repositoryUrl));
		} catch (Exception e) {
			response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return e.toString();
		}
	}
}