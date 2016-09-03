package net.migry.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonUtil {
	@SuppressWarnings("unchecked")
	public static void parseJSON(Map<String, Object> map, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("result", map);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void parseJSONList(List<Map<String, Object>> list, HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		JSONObject json = new JSONObject();
		
		for(Map<String, Object> map : list) {
			JSONObject obj = new JSONObject();
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            obj.put(key, value);
	        }

			arr.add(obj);
		}
		
		json.put("result", arr);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
