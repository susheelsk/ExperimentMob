package com.experimentmob.core;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthHelper {

	private static final String PASSWORD_SALT = "asjfhk;ew;ouqy08214-9230riuhfoiszj;lmda;023-48yfwol";
	private static final String FIELD = "authUser";
	private static final String FIELD_SEPARATOR = "|";
	private static final String DB_UNAME_KEY = "username";
	private static final String DB_PASSWD_KEY = "password";
	private static final String DB_SALT_KEY = "salt";
	private static final String DIGEST_ALGO = "SHA-256";
	public static final String IS_USER_SET = "userset";

	private String username;
	private String passwordHash;
	private String passwordSalt;
	private static SecureRandom secureRand;

	public AuthHelper() {
		username = null;
		passwordHash = null;
		passwordSalt = null;

		if (secureRand == null) {
			secureRand = new SecureRandom();
		}
	}

	public AuthHelper(String uname) {
		readAuthData(uname);

		if (secureRand == null) {
			secureRand = new SecureRandom();
		}
	}

	private boolean readAuthData(String uname) {
		String key = FIELD + FIELD_SEPARATOR + username;
		try {
			String keyValue = DatabaseHelper.getInstance().getJedis().get(key);
			if (keyValue == null || keyValue.equals("")) {
				return false;
			}
			JSONObject jobj = new JSONObject(keyValue);
			username = jobj.getString(DB_UNAME_KEY);
			passwordHash = jobj.getString(DB_PASSWD_KEY);
			passwordSalt = jobj.getString(DB_SALT_KEY);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean auth(String uname, String passwd) {
		if (!readAuthData(uname))
			return false;
		if (!passwordHash.equals(getPasswdHash(passwd)))
			return false;
		return true;
	}

	public boolean create(String uname, String passwd) {
		String key = FIELD + FIELD_SEPARATOR + username;
		username = uname;
		passwordSalt = new BigInteger(128, secureRand).toString();
		passwordHash = getPasswdHash(passwd);
		JSONObject jobj = new JSONObject();
		try {
			jobj.put(DB_UNAME_KEY, uname);
			jobj.put(DB_SALT_KEY, passwordSalt);
			jobj.put(DB_PASSWD_KEY, passwordHash);
			DatabaseHelper.getInstance().getJedis().set(key, jobj.toString());
			DatabaseHelper.getInstance().getJedis().set(IS_USER_SET, jobj.toString());
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getPasswdHash(String password) {
		String hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGO);
			String data = PASSWORD_SALT + password + passwordSalt;
			byte[] digestedPassword = digest.digest(data.getBytes());
			hash = new String(Hex.encodeHex(digestedPassword));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	public Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        return map;
    }

}
