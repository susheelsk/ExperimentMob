package com.experimentmob.core;

/**
 * Custom exception class for Song Wave Applicationh Server
 * @author ashwinidhekane
 *
 */
public class AbTestingException extends Exception
{
    private static final long serialVersionUID = -9214135353003476187L;
    private Status status;
    private String reason;
    
    /**
     * Generic constructor
     * @param code Status code of error
     * @param excReason descriptive reason which caused this error
     */
    public AbTestingException(Status code, String excReason) {
        this.status = code;
        this.reason = excReason;
    }
    
    /**
     * get the status code for this exception
     * @return status code
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * get the reason for this exception
     * @return descriptive reason
     */
    public String getReason() {
        return this.reason;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[IQApp Exception#" + this.status.getCode() + "] " + this.status.toString() + ": " + super.toString();
    }
}
