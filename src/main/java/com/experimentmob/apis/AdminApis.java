package com.experimentmob.apis;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.experimentmob.core.AbstractController;
import com.experimentmob.core.Constants;
import com.experimentmob.core.DatabaseHelper;
import com.experimentmob.core.Model;
import com.experimentmob.core.Util;
import com.experimentmob.core.Model.FlashMessageType;
import com.experimentmob.pojos.AppPojo;
import com.experimentmob.pojos.CohortPojo;
import com.experimentmob.pojos.ExperimentPojo;
import com.experimentmob.pojos.FieldsPojo;
import com.google.gson.Gson;
import com.sun.jersey.multipart.FormDataParam;

@Path("/admin")
public class AdminApis extends AbstractController {

	private static Logger logger = Logger.getLogger(AuthApis.class.getCanonicalName());

	@GET
	@Path("/404")
	@Produces(MediaType.TEXT_HTML)
	public String notFound() {
		try {
			start();
			logger.info("Page not found");
			return render("/404");
		} catch (Exception e) {
			e.printStackTrace();
			return "<html><head><title>OpenA/B Testing: 404</title></head><body><h1>Page Not Found</h1><p>Sorry, but the page you requested has not been found</p></body></html>";
		}
	}
	
	@GET
	@Path("/home")
	@Produces(MediaType.TEXT_HTML)
	public String homeSelectApp() {
		try {
			start();
			requireLogin();
			List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
			if (listApps == null || listApps.size() < 1) {
				redirect(getUrl("/admin/addapp"));
				return null;
			}
			List<AppPojo> listAppPojo = new ArrayList<AppPojo>();
			if (listApps != null && listApps.size() > 0) {
				for (String app : listApps) {
					JSONObject appObject = new JSONObject(app);
					AppPojo appPojo = new AppPojo(appObject.getString("name"), appObject.getString("id"));
					listAppPojo.add(appPojo);
				}
				Model model = model();
				model.setData("appList", listAppPojo);
			}
			return render("/home");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/home")
	@Produces(MediaType.TEXT_HTML)
	public String home(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			logger.info("Home Page for appId : " + appId);
			String defaultFieldsKey = appId + Constants.FIELD_SEPERATOR + Constants.DEFAULT_FIELDS;
			List<String> defFieldList = DatabaseHelper.getInstance().getJedis().lrange(defaultFieldsKey, 0, -1);
			if (defFieldList == null || defFieldList.size() < 1) {
				redirect(getUrl("/admin/" + appId + "/defaultfields"));
				return null;
			}
			String experimentsKey = appId + Constants.FIELD_SEPERATOR + Constants.EXPERIMENTS;
			List<String> listExperimentString = DatabaseHelper.getInstance().getJedis().lrange(experimentsKey, 0, -1);
			List<ExperimentPojo> experimentList = new ArrayList<ExperimentPojo>();
			Model model = model();
			model.setData("appId", appId);
			if (listExperimentString != null && listExperimentString.size() > 0) {
				for (String experimentString : listExperimentString) {
					logger.info("ExperimentString : "+experimentString);
					ExperimentPojo experimentPojo = new Gson().fromJson(experimentString, ExperimentPojo.class);
					experimentList.add(experimentPojo);
				}
				model.setData("exprList", experimentList);
			}

			return render("/listexperiments");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/defaultfields")
	@Produces(MediaType.TEXT_HTML)
	public String defaultFields(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			logger.info("Default Page for appId : " + appId);
			String key = appId + Constants.FIELD_SEPERATOR + Constants.DEFAULT_FIELDS;
			List<String> defFieldList = DatabaseHelper.getInstance().getJedis().lrange(key, 0, -1);
			List<FieldsPojo> fieldsList = new ArrayList<FieldsPojo>();
			Model model = model();
			model.setData("appId", appId);
			if (defFieldList != null && defFieldList.size() > 0) {
				for (String defFieldString : defFieldList) {
					FieldsPojo fieldsPojo = new Gson().fromJson(defFieldString, FieldsPojo.class);
					fieldsList.add(fieldsPojo);
				}
				model.setData("fieldList", fieldsList);
			}
			return render("/defaultfields");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/apps")
	@Produces(MediaType.TEXT_HTML)
	public String apps(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			logger.info("Default Page for appId : " + appId);
			List<String> appList = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
			List<AppPojo> fieldsList = new ArrayList<AppPojo>();
			Model model = model();
			model.setData("appId", appId);
			if (appList != null && appList.size() > 0) {
				for (String appString : appList) {
					AppPojo fieldsPojo = new Gson().fromJson(appString, AppPojo.class);
					fieldsList.add(fieldsPojo);
				}
				model.setData("appList", fieldsList);
			}
			return render("/apps");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/addexperiment")
	@Produces(MediaType.TEXT_HTML)
	public String addExperiment(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			String defaultFieldsKey = appId + Constants.FIELD_SEPERATOR + Constants.DEFAULT_FIELDS;
			String cohortsKey = appId + Constants.FIELD_SEPERATOR + Constants.COHORTS;
			List<String> defFieldList = DatabaseHelper.getInstance().getJedis().lrange(defaultFieldsKey, 0, -1);
			List<String> cohortStringList = DatabaseHelper.getInstance().getJedis().lrange(cohortsKey, 0, -1);
			if (defFieldList == null || defFieldList.size() < 1) {
				redirect(getUrl("/admin/" + appId + "/defaultfields"));
				return null;
			}
			Model model = model();
			model.setData("appId", appId);
			if (defFieldList != null && defFieldList.size() > 0) {
				List<FieldsPojo> fieldsList = new ArrayList<FieldsPojo>();
				for (String defFieldString : defFieldList) {
					FieldsPojo fieldsPojo = new Gson().fromJson(defFieldString, FieldsPojo.class);
					fieldsList.add(fieldsPojo);
				}
				model.setData("fieldList", fieldsList);
				model.setData("fieldJSONArray", new Gson().toJson(fieldsList));
			}
			if (cohortStringList != null && cohortStringList.size() > 0) {
				List<CohortPojo> cohortList = new ArrayList<CohortPojo>();
				for (String defFieldString : cohortStringList) {
					CohortPojo fieldsPojo = new Gson().fromJson(defFieldString, CohortPojo.class);
					cohortList.add(fieldsPojo);
				}
				model.setData("cohortList", cohortList);
				model.setData("cohortJSONArray", new Gson().toJson(cohortList));
			}
			return render("/addexperiment");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("/adddialog")
	@Produces(MediaType.TEXT_HTML)
	public String addDialog() {
		try {
			start();
			requireLogin();
			return render("/adddialog");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/cohorts")
	@Produces(MediaType.TEXT_HTML)
	public String addCohort(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			logger.info("Default Page for appId : " + appId);
			String cohortsKey = appId + Constants.FIELD_SEPERATOR + Constants.COHORTS;
			List<String> cohortStringList = DatabaseHelper.getInstance().getJedis().lrange(cohortsKey, 0, -1);
			List<CohortPojo> cohortList = new ArrayList<CohortPojo>();
			Model model = model();
			model.setData("appId", appId);
			if (cohortStringList != null && cohortStringList.size() > 0) {
				for (String appString : cohortStringList) {
					CohortPojo fieldsPojo = new Gson().fromJson(appString, CohortPojo.class);
					cohortList.add(fieldsPojo);
				}
				model.setData("cohortList", cohortList);
			}
			return render("/cohort");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("{appId}/history")
	@Produces(MediaType.TEXT_HTML)
	public String history(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			logger.info("History Page for appId : " + appId);
			
			String experimentsKey = appId + Constants.FIELD_SEPERATOR + Constants.HISTORY;
			List<String> listExperimentString = DatabaseHelper.getInstance().getJedis().lrange(experimentsKey, 0, 19);
			List<ExperimentPojo> experimentList = new ArrayList<ExperimentPojo>();
			Model model = model();
			model.setData("appId", appId);
			if (listExperimentString != null && listExperimentString.size() > 0) {
				for (String experimentString : listExperimentString) {
					logger.info("ExperimentString : "+experimentString);
					ExperimentPojo experimentPojo = new Gson().fromJson(experimentString, ExperimentPojo.class);
					experimentList.add(experimentPojo);
				}
				model.setData("exprList", experimentList);
			}

			return render("/history");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("/addapp")
	@Produces(MediaType.TEXT_HTML)
	public String addApp() {
		try {
			start();
			requireLogin();
			return render("/addapp");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@GET
	@Path("/help")
	@Produces(MediaType.TEXT_HTML)
	public String help() {
		try {
			start();
			return render("/help");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}
	
	@GET
	@Path("{appId}/help")
	@Produces(MediaType.TEXT_HTML)
	public String helpWithAppId(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			Model model = model();
			model.setData("appId", appId);
			return render("/help");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	@POST
	@Path("/upload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public String uploadFile(@FormDataParam("file") InputStream file) {
		try {
			start();
			requireLogin();
			String fileString = Util.uploadFileAndGetPath(file, getUrl("/apis"));
			if (fileString != null && fileString.length() > 1) {
				JSONObject jobj = new JSONObject();
				jobj.put("status", "success");
				jobj.put("data", fileString);
				logger.info("File upload : " + jobj.toString());
				return jobj.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
		try {
			JSONObject jobj = new JSONObject();
			jobj.put("status", "failed");
			jobj.put("payLoadJsonString", "Something went wrong trying to upload");
			return jobj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("{appId}/removeCohort")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void removeCohort(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("description") String description,
			@FormDataParam("file") String file) {
		try {
			start();
			requireLogin();
			String key = appId + Constants.FIELD_SEPERATOR + Constants.COHORTS;
			List<String> cohortStringList = DatabaseHelper.getInstance().getJedis().lrange(key, 0, -1);
			CohortPojo cohortPojo = new CohortPojo(name, description, file);
			if (cohortStringList != null && cohortStringList.size() > 0) {
				String cohortPojoString = new Gson().toJson(cohortPojo);
				logger.info("Removing cohortPojo : " + cohortPojoString);
				long delStatus = DatabaseHelper.getInstance().getJedis().lrem(key.getBytes(), 0, cohortPojoString.getBytes());
				if (delStatus > 0) {
					setFlash(FlashMessageType.SUCCESS, "Removed cohort");
				} else {
					setFlash(FlashMessageType.ERROR, "Failed to remove the cohort");
				}
			}
			redirect(getUrl("/admin/" + appId + "/cohorts"));
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

	@POST
	@Path("{appId}/createCohort")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void createCohort(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("description") String description,
			@FormDataParam("file") InputStream file) {
		try {
			start();
			requireLogin();
			if (Util.isNullOrEmpty(name, description)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/cohorts"));
				return;
			}
			String key = appId + Constants.FIELD_SEPERATOR + Constants.COHORTS;
			List<String> listCohortString = DatabaseHelper.getInstance().getJedis().lrange(key, 0, -1);
			if (listCohortString != null) {
				for (String app : listCohortString) {
					JSONObject appObject = new JSONObject(app);
					if (appObject.getString("name").equals(name)) {
						setFlash(FlashMessageType.ERROR, "Cohort with same name exists");
						redirect(getUrl("/admin/" + appId + "/cohorts"));
						return;
					}
				}
			}
			String csvContents = IOUtils.toString(file, "UTF-8");
			if(!Util.isCsvValid(csvContents)) {
				setFlash(FlashMessageType.ERROR, "There is some problem with the Cohort CSV");
				redirect(getUrl("/admin/" + appId + "/cohorts"));
				return;
			}
			String fileString = Util.uploadFileAndGetFileName(csvContents, getUrl("/apis"));
			if (fileString != null && !fileString.equals("")) {
				CohortPojo cohortPojo = new CohortPojo(name, description, fileString);
				DatabaseHelper.getInstance().getJedis().lpush(key.getBytes(), new Gson().toJson(cohortPojo).getBytes());
				setFlash(FlashMessageType.SUCCESS, "Cohort created");
				redirect(getUrl("/admin/" + appId + "/cohorts"));
			} else {
				setFlash(FlashMessageType.ERROR, "Error uploading file. Try again");
				redirect(getUrl("/admin/" + appId + "/cohorts"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			setFlash(FlashMessageType.ERROR, "Error uploading file. Try again");
			redirect(getUrl("/admin/" + appId + "/cohorts"));
		}
	}

	@POST
	@Path("{appId}/removedefaultfields")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void removeDefaultFields(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("type") String type,
			@FormDataParam("value") String value) {
		try {
			start();
			requireLogin();
			logger.info("Name : " + name + ", type : " + type + ", value : " + value);
			String key = appId + Constants.FIELD_SEPERATOR + Constants.DEFAULT_FIELDS;
			List<String> defFieldList = DatabaseHelper.getInstance().getJedis().lrange(key, 0, -1);
			JSONObject jobj = new JSONObject();
			jobj.put("name", name);
			jobj.put("type", type);
			if (type.equals("STRING")) {
				jobj.put("value", value);
			} else if (type.equals("BOOLEAN")) {
				jobj.put("value", Boolean.parseBoolean(value));
			} else if (type.equals("INTEGER")) {
				jobj.put("value", Integer.parseInt(value));
			} else if (type.equals("NUMBER")) {
				jobj.put("value", Double.parseDouble(value));
			} else if (type.equals("FILE")) {
				jobj.put("value", value);
			}
			if (defFieldList != null && defFieldList.size() > 0) {
				String fieldPojoString = jobj.toString();
				logger.info("Removing fieldPojo : " + fieldPojoString);
				long delStatus = DatabaseHelper.getInstance().getJedis().lrem(key.getBytes(), 0, fieldPojoString.getBytes());
				if (delStatus > 0) {
					setFlash(FlashMessageType.SUCCESS, "Removed field");
				} else {
					setFlash(FlashMessageType.ERROR, "Failed to remove the field");
				}
			}
			redirect(getUrl("/admin/" + appId + "/defaultfields"));
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

	@POST
	@Path("{appId}/setdefaultfields")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void setDefaultFields(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("type") String type,
			@FormDataParam("value") String value, @FormDataParam("filestream") InputStream fileInputStream) {
		try {
			start();
			requireLogin();
			String key = appId + Constants.FIELD_SEPERATOR + Constants.DEFAULT_FIELDS;
			List<String> defFieldList = DatabaseHelper.getInstance().getJedis().lrange(key, 0, -1);
			if (defFieldList != null && defFieldList.size() > 0) {
				for (String defFieldString : defFieldList) {
					JSONObject appObject = new JSONObject(defFieldString);
					if (appObject.getString("name").equals(name)) {
						setFlash(FlashMessageType.ERROR, "Field with same name exists");
						redirect(getUrl("/admin/" + appId + "/defaultfields"));
						return;
					}
				}
			}
			// below code validates the values being set
			JSONObject jobj = new JSONObject();
			jobj.put("name", name);
			jobj.put("type", type);
			logger.info("Name : " + name + ", type : " + type + ", value : " + value);
			boolean isNameValid = Util.isValid("^[a-zA-Z_][a-zA-Z_0-9]*$", name);
			if (!isNameValid) {
				setFlash(FlashMessageType.ERROR, "Field name is not valid. Please name these fields appropriately");
				redirect(getUrl("/admin/" + appId + "/defaultfields"));
				return;
			}
			if (type.equals("FILE") && Util.isNullOrEmpty(name, type)) {
				logger.info("Came here");
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/defaultfields"));
				return;
			} else if (!type.equals("FILE") && Util.isNullOrEmpty(name, value, type)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/defaultfields"));
				return;
			}
			if (type.equals("STRING")) {
				// nothing to validate
				jobj.put("value", value);
			} else if (type.equals("BOOLEAN")) {
				if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
					value = value.toLowerCase();
					jobj.put("value", Boolean.parseBoolean(value));
				} else {
					setFlash(FlashMessageType.ERROR, "Incorrect input data. Please ensure the value is either true or false");
					redirect(getUrl("/admin/" + appId + "/defaultfields"));
					return;
				}
			} else if (type.equals("INTEGER")) {
				if (!Util.isInteger(value)) {
					setFlash(FlashMessageType.ERROR, "Incorrect input data. Please ensure the value is an integer");
					redirect(getUrl("/admin/" + appId + "/defaultfields"));
					return;
				}
				jobj.put("value", Integer.parseInt(value));
			} else if (type.equals("NUMBER")) {
				if (!Util.isNumber(value)) {
					setFlash(FlashMessageType.ERROR, "Incorrect input data. Please ensure the value is a number");
					redirect(getUrl("/admin/" + appId + "/defaultfields"));
					return;
				}
				jobj.put("value", Double.parseDouble(value));
			} else if (type.equals("FILE")) {
				String fileUrl = Util.uploadFileAndGetPath(fileInputStream, getUrl("/apis"));
				jobj.put("value", fileUrl);
			}
			DatabaseHelper.getInstance().getJedis().lpush(key.getBytes(), jobj.toString().getBytes());
			redirect(getUrl("/admin/" + appId + "/defaultfields"));
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

	@POST
	@Path("/addappapi")
	@Produces(MediaType.TEXT_HTML)
	public void addApp(@FormParam("name") String name, @FormParam("id") String id) {
		try {
			start();
			requireLogin();
			JSONObject jobj = new JSONObject();
			if (Util.isNullOrEmpty(name, id)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/addapp"));
				return;
			}
			jobj.put("name", name);
			jobj.put("id", id);
			List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
			if (listApps != null) {
				for (String app : listApps) {
					JSONObject appObject = new JSONObject(app);
					if (appObject.getString("name").equals(name)) {
						setFlash(FlashMessageType.ERROR, "App with same name exists");
						redirect(getUrl("/admin/addapp"));
						return;
					} else if (appObject.getString("id").equals(id)) {
						setFlash(FlashMessageType.ERROR, "App with same id exists");
						redirect(getUrl("/admin/addapp"));
						return;
					}
				}
			}
			DatabaseHelper.getInstance().getJedis().lpush(Constants.APP_LIST.getBytes(), jobj.toString().getBytes());
			setFlash(FlashMessageType.SUCCESS, "App has been added");
			redirect(getUrl("/admin/" + id + "/home"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("{appId}/removeApp")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void removeApp(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("id") String id) {
		try {
			start();
			requireLogin();
			JSONObject jobj = new JSONObject();
			jobj.put("name", name);
			jobj.put("id", id);
			List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
			if (listApps != null) {
				String fieldPojoString = jobj.toString();
				logger.info("Removing fieldPojo : " + fieldPojoString);
				long delStatus = DatabaseHelper.getInstance().getJedis().lrem(Constants.APP_LIST.getBytes(), 0, fieldPojoString.getBytes());
				if (delStatus > 0) {
					setFlash(FlashMessageType.SUCCESS, "Removed App");
					if (listApps.size() == 1) {
						logout();
						redirect(getUrl("/auth/login"));
						return;
					}
				} else {
					setFlash(FlashMessageType.ERROR, "Failed to remove the app");
				}
				redirect(getUrl("/admin/" + appId + "/apps"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

	@POST
	@Path("{appId}/createApp")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void createApp(@PathParam("appId") String appId, @FormDataParam("name") String name, @FormDataParam("id") String id) {
		try {
			start();
			requireLogin();
			logger.info("Creating app with id : "+appId + ", name : "+name);
			if (Util.isNullOrEmpty(name, id)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/apps"));
				return;
			}
			JSONObject jobj = new JSONObject();
			jobj.put("name", name);
			jobj.put("id", id);
			List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
			if (listApps != null) {
				for (String app : listApps) {
					JSONObject appObject = new JSONObject(app);
					if (appObject.getString("name").equals(name)) {
						setFlash(FlashMessageType.ERROR, "App with same name exists");
						redirect(getUrl("/admin/" + appId + "/apps"));
						return;
					} else if (appObject.getString("id").equals(id)) {
						setFlash(FlashMessageType.ERROR, "App with same id exists");
						redirect(getUrl("/admin/" + appId + "/apps"));
						return;
					}
				}
			}
			DatabaseHelper.getInstance().getJedis().lpush(Constants.APP_LIST.getBytes(), jobj.toString().getBytes());
			setFlash(FlashMessageType.SUCCESS, "App has been added");
			redirect(getUrl("/admin/" + appId + "/apps"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("{appId}/removeExperiment")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void removeExperiment(@PathParam("appId") String appId, @FormDataParam("exprId") String exprId) {
		try {
			start();
			requireLogin();
			if (Util.isNullOrEmpty(exprId)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/home"));
				return;
			}
			String experimentsKey = appId + Constants.FIELD_SEPERATOR + Constants.EXPERIMENTS;
			List<String> listExperimentString = DatabaseHelper.getInstance().getJedis().lrange(experimentsKey, 0, -1);
			String experimentRemoveString = null;
			for (String experimentString : listExperimentString) {
				ExperimentPojo experimentPojo = new Gson().fromJson(experimentString, ExperimentPojo.class);
				if (experimentPojo.getId().equals(exprId)) {
					experimentRemoveString = experimentString;
				}
			}
			if (experimentRemoveString != null && experimentRemoveString.length() > 1) {
				long delStatus = DatabaseHelper.getInstance().getJedis().lrem(experimentsKey.getBytes(), 0, experimentRemoveString.getBytes());
				
				ExperimentPojo remExperimentPojo = new Gson().fromJson(experimentRemoveString, ExperimentPojo.class);
				remExperimentPojo.setFinishedDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				String historyKey = appId + Constants.FIELD_SEPERATOR + Constants.HISTORY;
				DatabaseHelper.getInstance().getJedis().lpush(historyKey.getBytes(), new Gson().toJson(remExperimentPojo).getBytes());
				
				if(delStatus > 0) {
					setFlash(FlashMessageType.SUCCESS, "Experiment Removed");
				}else {
					setFlash(FlashMessageType.ERROR, "Problem removing experiment.Try again");
				}
				redirect(getUrl("/admin/" + appId + "/home"));
				return;
			} else {
				setFlash(FlashMessageType.ERROR, "Problem removing experiment.Try again");
				redirect(getUrl("/admin/" + appId + "/home"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

	@POST
	@Path("{appId}/createExperiment")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void createExperiment(@PathParam("appId") String appId, @FormDataParam("exprData") String exprData) {
		try {
			start();
			requireLogin();
			logger.info("Experiment Data : "+exprData);
			if (Util.isNullOrEmpty(exprData)) {
				setFlash(FlashMessageType.ERROR, "Incorrect input data. Please check again");
				redirect(getUrl("/admin/" + appId + "/addexperiment"));
				return;
			}
			
			String experimentsKey = appId + Constants.FIELD_SEPERATOR + Constants.EXPERIMENTS;
			DatabaseHelper.getInstance().getJedis().lpush(experimentsKey.getBytes(), exprData.getBytes());
			setFlash(FlashMessageType.SUCCESS, "Experiment Published");
			redirect(getUrl("/admin/" + appId + "/home"));
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
		}
	}

}
