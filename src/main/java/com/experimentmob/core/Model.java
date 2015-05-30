package com.experimentmob.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Model extends HashMap<String, Object>
{
    /**
     * @author ashwinidhekane
     *
     */
    public enum FlashMessageType {
        /**
         * Success
         */
        SUCCESS,
        
        /**
         * Warning
         */
        WARNING,
        
        /**
         * Error
         */
        ERROR;
        
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        };
    }
    
    private static final long serialVersionUID = -99606862835703111L;

    /**
     * default constructor
     */
    public Model() {
        this.put("page", new HashMap<String, Object>());
        setPage("tab_id", 1);
        this.put("data", new HashMap<String, Object>());
        
        Map<String, List<String>> flashMessages = new HashMap<String, List<String>>();
        flashMessages.put(FlashMessageType.SUCCESS.toString(), new ArrayList<String>());
        flashMessages.put(FlashMessageType.WARNING.toString(), new ArrayList<String>());
        flashMessages.put(FlashMessageType.ERROR.toString(), new ArrayList<String>());
        this.put("flash", flashMessages);
    }
    
    /**
     * @param key system to add it to
     * @param pageData page data
     */
    @SuppressWarnings("unchecked")
    public void setPage(String key, Object data) {
        Map<String, Object> old = (Map<String, Object>) get("page");
        old.put(key, data);
        this.put("page", old);
    }
    
    /**
     * @param key system to add it to
     * @param pageData page data
     */
    @SuppressWarnings("unchecked")
    public void setData(String key, Object data) {
        Map<String, Object> old = (Map<String, Object>) get("data");
        old.put(key, data);
        this.put("data", old);
    }
    
    /**
     * @param type type of message
     * @param message message to display
     */
    public void flash(FlashMessageType type, String message) {
        List<String> flashes = new ArrayList<String>();
        flashes.add(message);
        setFlash(type, flashes);
    }
    
    /**
     * @param type type of message
     * @param message message to display
     */
    @SuppressWarnings("unchecked")
    public void addFlash(FlashMessageType type, String message) {
        Map<String, List<String>> flashMessages = (Map<String, List<String>>) this.get("flash");
        flashMessages.get(type.toString()).add(message);
    }
    
    @SuppressWarnings("unchecked")
    private void setFlash(FlashMessageType type, List<String> messages) {
        Map<String, List<String>> flashMessages = (Map<String, List<String>>) this.get("flash");
        flashMessages.put(type.toString(), messages);
    }
    
    /**
     * Clear all flash messages
     */
    public void clearFlash() {
        Map<String, List<String>> flashMessages = new HashMap<String, List<String>>();
        flashMessages.put(FlashMessageType.SUCCESS.toString(), new ArrayList<String>());
        flashMessages.put(FlashMessageType.WARNING.toString(), new ArrayList<String>());
        flashMessages.put(FlashMessageType.ERROR.toString(), new ArrayList<String>());
        this.put("flash", flashMessages);
    }
}
