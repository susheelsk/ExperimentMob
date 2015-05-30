package com.experimentmob.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;

public class DatabaseHelper {

	private static String PROPERTIES_HOSTNAME = "redis-hostname";
	private static String PROPERTIES_PORT = "redis-port";
	private static String FILES_PATH = "filespath";

	private static DatabaseHelper dbHelper;
	private static Logger logger = Logger.getLogger(DatabaseHelper.class.getCanonicalName());
	private Jedis jedis;
	private String filespath;
	private Properties prop;

	private DatabaseHelper() throws IOException {
		prop = new Properties();
		FileInputStream inputFileInputStream = new FileInputStream("config.properties");
		prop.load(inputFileInputStream);
		if (Util.isNullOrEmpty(prop.getProperty(PROPERTIES_HOSTNAME), prop.getProperty(PROPERTIES_PORT), prop.getProperty(FILES_PATH))) {
			throw new IOException("Please set the properties of hostname, port and filespath in config.properties");
		}
		filespath = prop.getProperty(FILES_PATH);
		File filePath = new File(filespath);
		if(!filePath.canWrite()) {
			throw new IOException("Can't write into filepath. Please ensure that the path given in the config.properties file is writable");
		}
		logger.info("Connecting to jedis");
		jedis = new Jedis(prop.getProperty(PROPERTIES_HOSTNAME, "localhost"), Integer.parseInt(prop.getProperty(PROPERTIES_PORT, "6379")));
		jedis.connect();
		logger.info(("Server is running: " + jedis.ping()));
		setShutdownHook();
	}
	
	private static void setShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				logger.info("Service has shut down");
				dbHelper.jedis.disconnect();
			}
		}));
	}

	public Jedis getJedis() {
		return jedis;
	}

	public String getFilespath() {
		return filespath;
	}
	
	public void reInitJedis() {
		jedis = new Jedis(prop.getProperty(PROPERTIES_HOSTNAME, "localhost"), Integer.parseInt(prop.getProperty(PROPERTIES_PORT, "6379")));
		logger.info(("Server is running: " + jedis.ping()));
	}

	public static DatabaseHelper getInstance() throws IOException {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper();
		}
		return dbHelper;
	}

}
