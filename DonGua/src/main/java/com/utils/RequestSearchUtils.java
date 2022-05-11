package com.utils;

import java.util.Map;
import java.util.Set;

public class RequestSearchUtils {

	public static String getASearchCondition(String columnName, String value) {
		String aCondition = null;
		//TODO add district
		if ("county".equals(columnName)) {
			aCondition = "PATIENT_ADDR" + " like CONCAT((SELECT `CTY_NAME` FROM `COUNTY` WHERE `CTY_NO` = '" + value + "'),'%')"; 
		} else if ("district".equals(columnName)) {
			aCondition = "PATIENT_ADDR" + " like CONCAT('%',(SELECT `DIST_NAME` FROM `DISTRICT` WHERE `DIST_NO` = '" + value + "'),'%')";
		} else if ("SERVICE_TYPE".equals(columnName)) {
			aCondition = columnName + "=" + value;
		} else if ("start_dateTime".equals(columnName)) {
			aCondition = "START_TIME" + ">" + "'" + value + "'";
		} else if ("end_dateTime".equals(columnName)) {
			aCondition = "END_TIME" + "<" + "'" + value + "'";
		} 
		return aCondition + " ";
	}

	public static String getWhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = getASearchCondition(key, value.trim());

				if (count == 1) {
					whereCondition.append(" and " + aCondition);
				} else {
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("send to search=" + count);
			}
		}
		return whereCondition.toString();
	}

}
