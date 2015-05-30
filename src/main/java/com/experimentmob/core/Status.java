package com.experimentmob.core;

/**
 * App server status codes
 * 
 */
public enum Status {
	/**
	 * Everything went well. No errors were faced.
	 */
	OK(1000),

	/**
	 * Generic failure. Reason for error is unknown.
	 */
	FAIL(1001),

	/**
	 * Executing operation will put the app server in an invalid or unknown
	 * state.
	 */
	INVALID_STATE(1002),

	/**
	 * Allowing an operation to execute will put the game in invalid or unknown
	 * state.
	 */
	ERR_INVALID_GAME_STATE(1003),

	/**
	 * Internal Error
	 */
	INTERNAL_ERROR(1004),

	/**
	 * Argument passed to method was invalid.
	 */
	INVALID_ARGUMENT(2000),

	/**
	 * Value given for user profile field is invalid.
	 */
	INVALID_PROFILE_PARAM(2001),

	/**
	 * Request for transaction was invalid or cannot be fulfilled right now.
	 */
	TXN_ERROR_INVALID_REQUEST(2002),

	/**
	 * Requested transaction is attempting to do an overdraw on users wallet.
	 */
	TXN_ERROR_OVERDRAW(2003),

	/**
	 * Application session is not valid.
	 */
	INVALID_APP_SESSION(2004),

	/**
	 * Player is invalid.
	 */
	INVALID_PLAYER(2005),

	/**
	 * Operation is not allowed or is not supported in current state.
	 */
	OPERATION_NOT_ALLOWED(3000),

	/**
	 * Game is already dead or finished.
	 */
	ERR_GAME_NOT_ALIVE(3001),

	/**
	 * Error executing desired operation on data store.
	 */
	ERR_STORE_OPERATION(3002),

	/**
	 * Error in calling external API
	 */
	ERR_API_OPERATION(3003),

	/**
	 * Error in fetching an entity from database.
	 */
	ERR_ENTITY_NOT_FOUND(4000),

	/**
	 * Session does not exist in database.
	 */
	ERR_SESSION_NOT_FOUND(4001),

	/**
	 * Game does not exist in database.
	 */
	ERR_GAME_NOT_FOUND(4002),

	/**
	 * User does not exist in database.
	 */
	ERR_USER_NOT_FOUND(4003),

	/**
	 * Challenge state not found in database. This is a serious error.
	 */
	ERR_CHALLENGE_STATE_NOT_FOUND(4004),

	ERR_INVALID_BILLBOARD(4005),

	/**
	 * Error in configuration of app server.
	 */
	ERR_CONFIGURATION(5000),

	/**
	 * Access denied for a challenge operation. Only sending and receiving users
	 * can change a challenge object.
	 */
	CHALLENGE_ACCESS_DENIED(6001),

	/**
	 * Playlist could not be found
	 */
	PES_PLAYLIST_NOT_FOUND(7001),

	/**
	 * Unique id provided is invalid
	 */
	ERR_INVALID_UNIQUE_ID(8001),

	/**
	 * Argument passed to method is invalid
	 */
	ERR_INVALID_ARGUMENT(8002),

	/**
	 * Error in saving transaction
	 */
	ERR_TXN_SAVE_FAILURE(8002),

	/**
	 * Error in reading transaction details
	 */
	ERR_TXN_NOT_FOUND(8003),

	/**
	 * Unrecoverable error
	 */
	ERR_UNRECOVERABLE_ERROR(4005),

	/**
	 * Error in loading template config.
	 */
	ERR_TEMPLATE_CONFIG(4006),

	ERR_INVALID_JSON(4007),

	ERR_BILLBOARD_NOT_FOUND(4008);

	private int code;

	private Status(int intCode) {
		this.code = intCode;
	}

	/**
	 * get the integer status code
	 * 
	 * @return error code
	 */
	public int getCode() {
		return this.code;
	}
}
