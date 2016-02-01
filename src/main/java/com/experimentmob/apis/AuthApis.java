package com.experimentmob.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.experimentmob.core.AbTestingException;
import com.experimentmob.core.AbstractController;
import com.experimentmob.core.AuthHelper;
import com.experimentmob.core.Constants;
import com.experimentmob.core.DatabaseHelper;
import com.experimentmob.core.Model;
import com.experimentmob.core.Model.FlashMessageType;
import com.experimentmob.pojos.AppPojo;

@Path("/auth")
public class AuthApis extends AbstractController {
	/**
	 * @return response html
	 */

	private static Logger logger = Logger.getLogger(AuthApis.class.getCanonicalName());

	@GET
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	public String login() {
		try {
			start();
			requireNoLogin();
			logger.info("Opening login page");
			setPageTitle("Login");
			List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
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
			return render("/login");
		} catch (Exception e) {
			e.printStackTrace();
			redirect(getUrl("/admin/404"));
			return null;
		}
	}

	/**
	 * @param userName
	 *            username
	 * @param password
	 *            password
	 */
	@POST
	@Path("/confirm")
	@Produces(MediaType.TEXT_HTML)
	public void takeLogin(@FormParam("uname") String userName, @FormParam("passwd") String password, @FormParam("appId") String appId) {
		try {
			start();
			requireNoLogin();
			logger.info("Loggin into appId : "+appId);
			AuthHelper auth = new AuthHelper();
			try {
				if (auth.auth(userName, password)) {
					sessionSet("loggedInUser", userName);
//					String redir = sessionGet("redirectUrl");
//					if (redir != null) {
//						redirect(getUrl(redir));
//						return;
//					}
					List<String> listApps = DatabaseHelper.getInstance().getJedis().lrange(Constants.APP_LIST, 0, -1);
					if (listApps == null || listApps.size() < 1) {
						redirect(getUrl("/admin/addapp"));
						return;
					}
					redirect(getUrl("/admin/"+appId+"/home"));
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			setFlash(FlashMessageType.ERROR, "Username or password is invalid. Please try again!!");
			redirect(getUrl("/auth/login"));
			return;
		} catch (AbTestingException e) {
			setFlash(FlashMessageType.ERROR, "Unexpected error in logging in. Please try again!!");
			redirect(getUrl("/auth/login"));
			e.printStackTrace();
		}
	}

	/**
	 * @return response html
	 */
	@GET
	@Path("/signup")
	@Produces(MediaType.TEXT_HTML)
	public String signup() {
		try {
			start();
			requireLogin();
			setPageTitle("Add user");
			return render("/signup");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("{appId}/adduser")
	@Produces(MediaType.TEXT_HTML)
	public String addUser(@PathParam("appId") String appId) {
		try {
			start();
			requireLogin();
			Model model = model();
			model.setData("appId", appId);
			setPageTitle("Create admin");
			return render("/signup");
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect(getUrl("/admin/404"));
		return null;
	}

	/**
	 * @return response html
	 */
	@GET
	@Path("/install")
	@Produces(MediaType.TEXT_HTML)
	public String install() {
		try {
			start();
			requireNoLogin();
			setPageTitle("Create admin");
			if (!DatabaseHelper.getInstance().getJedis().exists(AuthHelper.IS_USER_SET)) {
				return render("/signup");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect(getUrl("/admin/404"));
		return null;
	}

	/**
	 * @param uname
	 *            username
	 * @param passwd
	 *            password
	 * @param confirmPasswd
	 *            confirm password
	 * @return response html
	 */
	@POST
	@Path("/confirmsignup")
	@Produces(MediaType.TEXT_HTML)
	public String takeSignup(@FormParam("uname") String uname, @FormParam("passwd") String passwd, @FormParam("confirm") String confirmPasswd) {
		try {
			start();
			requireLogin();
			if (uname.trim().equals("") || passwd.trim().equals("") || confirmPasswd.equals("")) {
				setFlash(FlashMessageType.ERROR, "User is being a monkey");
				redirect(getUrl("/auth/signup"));
				return null;
			}

			if (!passwd.equals(confirmPasswd)) {
				setPageTitle("Register");
				setTabId(2);
				setFlash(FlashMessageType.ERROR, "Passwords do not match. Please try again!!");
				redirect(getUrl("/auth/signup"));
				return null;
			} else {
				AuthHelper auth = new AuthHelper();
				if (!auth.create(uname, passwd)) {
					setPageTitle("Register");
					setTabId(2);
					setFlash(FlashMessageType.ERROR, "Username already taken!!");
					redirect(getUrl("/auth/login"));
					return null;
				}
			}
			super.logout();
			setFlash(FlashMessageType.SUCCESS, "Registration successful. Please login!!");
			redirect(getUrl("/auth/signup"));
		} catch (AbTestingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @return response html
	 */
	@Override
	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_HTML)
	public void logout() {
		try {
			start();
			requireLogin();
			super.logout();
		} catch (AbTestingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redirect(getUrl("/auth/login"));
	}
}
