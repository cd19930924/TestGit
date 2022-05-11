package com.utils;

import java.util.Map;
import java.util.Set;

public class CarerSearchUtils {

	public static String getASearchCondition(String columnName, String value) {
		String aCondition = null;
		if ("SERVICE_TYPE".equals(columnName) && "0".equals(value)) {
			aCondition = "  `SERVICE_TYPE` in (0,2)";
		} else if ("SERVICE_TYPE".equals(columnName) && "1".equals(value)) {
			aCondition = " `SERVICE_TYPE` in (1,2)";
		} else if ("SERVICE_TYPE".equals(columnName) && "2".equals(value)) {
			aCondition = " `SERVICE_TYPE` = '2' ";
		} else if( "county".equals(columnName)) {
			aCondition = "`COUNTY`.`CTY_NO` = '" + value + "'";
		} else if ("district".equals(columnName)) {
			aCondition = "`DISTRICT`.`DIST_NO` = '" + value + "'";
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
					whereCondition.append(" where " + aCondition);
				}
				else {
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("sent to search="+count);
			}
		}
		return whereCondition.toString();
	}
}
