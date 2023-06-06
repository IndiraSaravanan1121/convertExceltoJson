package com.utils;

import java.io.File;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class ReadJsonData {
	
	public static JSONObject data;
	
	public JSONObject readJsonFile(String filePath) {
		try {
			File f = new File(filePath);
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			data = new JSONObject(jt);
		} catch (Exception e) {
			System.out.println("File not Found");
		}
		return data;
	}

}
