package com.common.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

public class JSONParse {
	public static String jsonParse(BufferedReader reader) throws IOException {
		String input = null;
        StringBuilder requestBody = new StringBuilder();
        while((input = reader.readLine()) != null) {
            requestBody.append(URLDecoder.decode(input,"UTF-8"));
        }
        return requestBody.toString();
	}
}
