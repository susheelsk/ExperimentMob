package com.experimentmob.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Util {
	
	public static boolean isValid(String regex,String input) {
		final Pattern pattern = Pattern.compile(regex);
	    if (!pattern.matcher(input).matches()) {
	        return false;
	    }else {
	    	return true;
	    }
	}
	
	public static String uploadFileAndGetPath(InputStream inputStream,String baseUrl) throws IOException {
		String fileName = UUID.randomUUID().toString();
		Path path = Paths.get(DatabaseHelper.getInstance().getFilespath(), fileName);
		Files.copy(inputStream, path);
		String fileUrl = baseUrl + "/files/"+fileName;
		return fileUrl;
	}
	
	public static String uploadFileAndGetFileName(String fileContents,String baseUrl) throws IOException {
		String fileName = UUID.randomUUID().toString();
		Path path = Paths.get(DatabaseHelper.getInstance().getFilespath(), fileName);
		FileUtils.writeStringToFile(path.toFile(), fileContents);
		return fileName;
	}
	
	public static boolean isCsvValid(String csvContents) {
		String[] lines = csvContents.split(System.getProperty("line.separator"));
		for (String line : lines) {
			if (line.contains(",")) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNullOrEmpty(String... inputs) {
		for(String input : inputs) {
			if(input==null || input.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInteger(String input) {
	    try { 
	        Integer.parseInt(input); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static boolean isNumber(String input) {
		try { 
	        Double.parseDouble(input); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
