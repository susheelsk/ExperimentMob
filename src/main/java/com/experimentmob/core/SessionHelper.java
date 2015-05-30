package com.experimentmob.core;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionHelper {
	
	private String     sessionId;
    private long       createdOn;
    private JSONObject data;
    
    private static final String FIELD_SEPARATOR   = "|";
    private static final String FIELD_PREFIX      = "session";
    
    private static final String DB_TIME_FIELD     = "createdOn";
    private static final String DB_DATA_FIELD     = "data";
    
    private SessionHelper() throws AbTestingException, IOException {
        sessionId = UUID.randomUUID().toString();
        Date dt = new Date();
        createdOn = dt.getTime();
        data = new JSONObject();
        save();
    }
    
    private SessionHelper(String id) throws IOException, AbTestingException {
        sessionId = id;
        String key = FIELD_PREFIX + FIELD_SEPARATOR + sessionId;
        String keyValue = DatabaseHelper.getInstance().getJedis().get(key);
        
        if(keyValue!=null && !keyValue.trim().equals("")) {
            try {
                JSONObject jobj = new JSONObject(keyValue);
                createdOn = jobj.getLong(DB_TIME_FIELD);
                data      = jobj.getJSONObject(DB_DATA_FIELD);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new AbTestingException(Status.ERR_STORE_OPERATION, e.getMessage());
            }
        } else {
            throw new AbTestingException(Status.ERR_ENTITY_NOT_FOUND, "Could not find web session for given id. Store result is " + keyValue);
        }
    }
    
    /**
     * start a new session
     * @return new session
     * @throws AbTestingException if there is error in starting
     * @throws IOException 
     */
    public static SessionHelper start() throws AbTestingException, IOException {
        return new SessionHelper();
    }
    
    /**
     * @param id session id
     * @return web session
     * @throws AbTestingException if session not found 
     * @throws IOException 
     */
    public static SessionHelper get(String id) throws AbTestingException, IOException {
        return new SessionHelper(id);
    }
    
    /**
     * @return session id
     */
    public String getId() {
        return sessionId;
    }
    
    /**
     * destroy this session
     * @throws IOException 
     */
    public void destroy() throws IOException {
        String key = FIELD_PREFIX + FIELD_SEPARATOR + sessionId;
        DatabaseHelper.getInstance().getJedis().del(key);
        sessionId = null;
        createdOn = 0;
    }
    
    /**
     * @param key key to set
     * @param value value at key
     * @throws AbTestingException  if there is error in storing the value
     * @throws IOException 
     */
    public void set(String key, String value) throws AbTestingException, IOException {
        try {
            data.put(key, value);
            save();
        } catch (JSONException e) {
            throw new AbTestingException(Status.FAIL, e.getMessage());
        }
    }
    
    /**
     * @param key key
     * @return value stored at key
     * @throws AbTestingException if key does not exist
     */
    public String getVal(String key) throws AbTestingException {
        try {
            return data.getString(key);
        } catch (JSONException e) {
            throw new AbTestingException(Status.FAIL, e.getMessage());
        }
    }
    
    private void save() throws AbTestingException, IOException {
        String key = FIELD_PREFIX + FIELD_SEPARATOR + sessionId;
        JSONObject jobj = new JSONObject();
        try {
            jobj.put(DB_TIME_FIELD, createdOn);
            jobj.put(DB_DATA_FIELD, data);
            DatabaseHelper.getInstance().getJedis().set(key, jobj.toString());
            DatabaseHelper.getInstance().getJedis().expire(key, 2*24*60*60); //expire session after 2 days
        } catch (Exception e) {
            throw new AbTestingException(Status.ERR_STORE_OPERATION, "Could not save session information. " + e.getMessage());
        }
    }

    /**
     * @param key key to delete
     * @throws AbTestingException 
     * @throws IOException 
     */
    public void del(String key) throws AbTestingException, IOException {
        data.remove(key);
        save();
    }

}
