package com.experimentmob.apis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.experimentmob.core.Constants;
import com.experimentmob.core.DatabaseHelper;
import com.experimentmob.pojos.AbTestingPojo;
import com.experimentmob.pojos.ExperimentPojo;
import com.google.gson.Gson;

@Path("/apis")
public class OpenApis {

	private static Logger logger = Logger.getLogger(OpenApis.class.getCanonicalName());

	@GET
	@Path("/files/{file}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("file") String filepath) {
		logger.info("Fetching file : " + filepath);
		try {
			java.nio.file.Path path = Paths.get(DatabaseHelper.getInstance().getFilespath(), filepath);
			File file = path.toFile();
			return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
					.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"") // optional
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.serverError().header("Can't get file", "File not present").build();
	}

	@GET
	@Path("{appId}/abtesting")
	@Produces(MediaType.APPLICATION_JSON)
	public String getABTesting(@PathParam("appId") String appId) {
		List<ExperimentPojo> experimentList = new ArrayList<ExperimentPojo>();
		try {
			String experimentsKey = appId + Constants.FIELD_SEPERATOR + Constants.EXPERIMENTS;
			List<String> listExperimentString = DatabaseHelper.getInstance().getJedis().lrange(experimentsKey, 0, -1);
			if (listExperimentString != null && listExperimentString.size() > 0) {
				for (String experimentString : listExperimentString) {
					logger.info("ExperimentString : " + experimentString);
					ExperimentPojo experimentPojo = new Gson().fromJson(experimentString, ExperimentPojo.class);
					experimentList.add(experimentPojo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AbTestingPojo abTestingPojo = new AbTestingPojo(experimentList);
		return new Gson().toJson(abTestingPojo);
	}

	@GET
	@Path("{appId}/partOfCohort")
	@Produces(MediaType.APPLICATION_JSON)
	public String isPartOfCohort(@PathParam("appId") String appId, @QueryParam("userId") String userId, @QueryParam("cohortId") String cohortId) {
		try {
			java.nio.file.Path path = Paths.get(DatabaseHelper.getInstance().getFilespath(), cohortId);
			File file = path.toFile();
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.equals(userId)) { 
		        	//true;
		        	JSONObject jobj = new JSONObject();
					jobj.put("status", "success");
					jobj.put("existsInCohort", true);
					scanner.close();
					return jobj.toString();
		        }
		    }
		    scanner.close();
		    JSONObject jobj = new JSONObject();
			jobj.put("status", "success");
			jobj.put("existsInCohort", false);
			return jobj.toString();
		} catch(Exception e) { 
			e.printStackTrace();
			try {
				JSONObject jobj = new JSONObject();
				jobj.put("status", "success");
				jobj.put("existsInCohort", false);
				return jobj.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

}
