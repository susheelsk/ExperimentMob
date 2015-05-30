package com.experimentmob.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.json.JSONException;
import org.json.JSONObject;

import com.experimentmob.core.Model.FlashMessageType;

import freemarker.template.TemplateException;

public abstract class AbstractController
{
    private SessionHelper session;
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;
    private static final String COOKIE_NAME = "iqapp_cookie";
    protected static TemplateEngine template;
    private Model model;
    private JSONObject requestVars;
    private AuthHelper auth;

    /**
     * @param key key
     * @param value value
     * @throws AbTestingException if there is error in setting variable
     * @throws IOException 
     */
    public void sessionSet(String key, String value) throws AbTestingException, IOException {
        session.set(key, value);
    }

    /**
     * @param key key
     * @return value at that key
     * @throws AbTestingException if key does not exist
     */
    public String sessionGet(String key) {
        try {
            return session.getVal(key);
        } catch (AbTestingException e) {
            return null;
        }
    }

    protected void start() throws AbTestingException {
        getCookie();
        if (template == null) {
            template = TemplateEngine.getInstance();
        }
        model = new Model();
        model.setPage("loggedIn", isLoggedIn());
        model.setPage("user", auth.getUser());
        
        model.setPage("root_path", httpRequest.getContextPath());
        String flashError = sessionGet("flash.error");
        if(flashError != null) {
            model.addFlash(FlashMessageType.ERROR, flashError);
        }
        flashError = sessionGet("flash.warn");
        if(flashError != null) {
            model.addFlash(FlashMessageType.WARNING, flashError);
        }
        flashError = sessionGet("flash.success");
        if(flashError != null) {
            model.addFlash(FlashMessageType.SUCCESS, flashError);
        }
        clearFlashes();
        
        getRequestVars();
        Map<String, String> lastReqVars = new HashMap<String, String>();
        @SuppressWarnings("unchecked")
        Iterator<String> keys = requestVars.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if(key == null) continue;
            try {
                lastReqVars.put(key, requestVars.getString(key));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        model.setData("request", lastReqVars);
        clearRequestVars();
        try {
			String pingMessage = DatabaseHelper.getInstance().getJedis().ping();
			if(!pingMessage.equals("PONG")) {
				setFlash(FlashMessageType.ERROR, "Can't connect with redis");
				throw new IOException("Can't connect with redis");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    protected Model model() {
        return model;
    }

    protected void setPageTitle(String title) {
        model.setPage("title", title);
    }
    
    protected void setTabId(int id) {
        model.setPage("tab_id", id);
    }
    
    protected void setRequestVar(String key, String val) {
        try {
            requestVars.put(key, val);
            sessionSet("__request_vars", requestVars.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AbTestingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected Map<String, Object> getCurrentUser(){ 
    	if(auth != null) {
    		return auth.getUser();
    	}
    	return null;
    }
    
    private void getRequestVars() {
        String reqVarStr = sessionGet("__request_vars");
        if(reqVarStr == null) {
            requestVars = new JSONObject();
            return;
        }
        try {
            requestVars = new JSONObject(reqVarStr);
        } catch (JSONException e) {
            requestVars = new JSONObject();
        }
    }
    
    protected void clearRequestVars() {
        try {
            requestVars = new JSONObject();
            session.del("__request_vars");
        } catch (AbTestingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void setFlash(FlashMessageType type, String msg) {
        try {
            switch(type) {
                case ERROR:
                    sessionSet("flash.error", msg);
                    break;
                case SUCCESS:
                    sessionSet("flash.success", msg);
                    break;
                case WARNING:
                    sessionSet("flash.warn", msg);
                    break;
                default:
                    break;
                
            }
        } catch (AbTestingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void clearFlashes() {
        try {
            session.del("flash.error");
            session.del("flash.warn");
            session.del("flash.success");
        } catch (AbTestingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void clearRedirectLogin() {
    	try {
			session.del("redirectUrl");
		} catch (AbTestingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected String render(String tpl) throws IOException, TemplateException {
    	return template.render(tpl, model);
    }

    private boolean isLoggedIn() {
        try {
            String currentUser = session.getVal("loggedInUser");
            if (currentUser == null || currentUser.trim().equals("")) {
                auth = new AuthHelper(); 
                return false;
            } else {
                auth = new AuthHelper(currentUser); 
            }
        } catch (AbTestingException e) {
            auth = new AuthHelper(); 
            return false;
        }
        return true;
    }
    
    protected void requireLogin() {
        if (!isLoggedIn()) {
            try {
                String redirectUrl = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
                session.set("redirectUrl", redirectUrl);
                httpResponse.sendRedirect(getUrl("/auth/login"));
                return;
            } catch (IOException e) {
                throw new IllegalStateException("Could not redirect. User is not logged in!!");
            } catch (AbTestingException e) {
                try {
                    httpResponse.sendRedirect(getUrl("/auth/login"));
                    return;
                } catch (IOException e1) {
                    throw new IllegalStateException("Could not redirect. User is not logged in!!");
                }
            }
        }
    }
    
    protected void requireNoLogin() {
        if (isLoggedIn()) {
            try {
                httpResponse.sendRedirect(getUrl("/admin/home"));
            } catch (IOException e) {
                throw new IllegalStateException("Could not redirect. User is not logged in!!");
            }
        }
    }

    protected void logout() {
        try {
			session.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    protected void redirect(String url) {
        try {
        	url = httpResponse.encodeURL(url);
            httpResponse.sendRedirect(url);
        } catch (IOException e) {
            throw new IllegalStateException("Could not redirect. User is not logged in!!");
        }
    }

    protected String getUrl(String path) {
        String scheme = httpRequest.getScheme();
        String serverName = httpRequest.getServerName();
        int serverPort = httpRequest.getServerPort();
        if ((scheme.toLowerCase().equals("http") && serverPort != 80)
            || (scheme.toLowerCase().equals("https") && serverPort != 443)) {
            serverName = serverName + ":" + serverPort;
        }
        return scheme + "://" + serverName + httpRequest.getContextPath() + path;
    }

    private void getCookie() {
        Logger requestLogger = Logger.getLogger(getClass().getSimpleName());
        requestLogger.info("Checking cookies");
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cookie : cookies) {
            requestLogger.info("Cookie name is: " + cookie.getName());
            if (cookie.getName().equals(COOKIE_NAME)) {
                requestLogger.info("Cookie found");
                try {
                    requestLogger.info("Getting session with id#" + cookie.getValue());
                    session = SessionHelper.get(cookie.getValue());
                    return;
                } catch (Exception e) {
                    requestLogger.info("Error in fetching session: " + e.getMessage());
                    try {
						DatabaseHelper.getInstance().reInitJedis();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    break;
                }
            }
        }
        requestLogger.info("Creating new session");
        try {
            session = SessionHelper.start();
            Cookie newCookie = new Cookie(COOKIE_NAME, session.getId());
            newCookie.setPath(httpRequest.getContextPath());
            httpResponse.addCookie(newCookie);
        } catch (AbTestingException e1) {
            requestLogger.info("Error in fetching session: " + e1.getReason());
            e1.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}