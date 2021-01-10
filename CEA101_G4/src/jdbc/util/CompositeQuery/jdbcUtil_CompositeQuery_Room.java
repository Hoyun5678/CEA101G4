/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Room {
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		String aCondition = null;
		if ("ROOM_PRICE".equals(columnName) || "ROOM_COLLECT".equals(columnName) 
				|| "ROOM_STATUS".equals(columnName))
			aCondition = columnName + "=" + value;
		else if ("SELL_MEM_ADDRESS".equals(columnName) || "SELL_MEM_ID".equals(columnName) 
				|| "ROOM_NAME".equals(columnName) || "ROOM_DES".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("ROOM_CAPACITY".equals(columnName))                          // 用於Oracle的date
			aCondition =  columnName + ">=" + value;

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		if(map.size() == 4) {
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
					count++;
					String aCondition = get_aCondition_For_Oracle(key, value.trim());
	
					if (count == 1)
						whereCondition.append(" where " + aCondition);
					else if(count == 2)
						whereCondition.append(" and ( " + aCondition);
					else if (count == 5)
						whereCondition.append(" ) " + aCondition);
					else
						whereCondition.append(" or " + aCondition);
	
	//				System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			whereCondition.append(" ) ");
		} else {
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
					count++;
					String aCondition = get_aCondition_For_Oracle(key, value.trim());
	
					if (count == 1)
						whereCondition.append(" where " + aCondition);
					else
						whereCondition.append(" or " + aCondition);
				}
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("SELL_MEM_ID", new String[] { "SELL001" });
		map.put("SELL_MEM_ACCOUNT", new String[] { "selltest001" });
//		map.put("SELL_MEM_PWD", new String[] { "testpassword" });
//		map.put("SELL_MEM_NAME", new String[] { "1981-02-17" });
//		map.put("SELL_MEM_BIRTH", new String[] { "5000.5" });
//		map.put("SELL_MEM_TEL", new String[] { "0.0" });
//		map.put("SELL_ROOM_NAME", new String[] { "10" });
//		map.put("SELL_MEM_ADDRESS", new String[] { "getXXX" });
//		map.put("SELL_LATITUDE", new String[] { "getXXX" });
//		map.put("SELL_LONGITUD", new String[] { "getXXX" });
//		map.put("SELL_MEM_MAIL", new String[] { "getXXX" });
//		map.put("SELL_MEM_ID_NUMBER", new String[] { "getXXX" });
//		map.put("SELL_ACC_STATUS", new String[] { "getXXX" });
//		map.put("SELL_GENDER", new String[] { "getXXX" });
//		map.put("SELL_JOINTIME", new String[] { "getXXX" });

		String finalSQL = "SELECT * FROM SELLER_MEMBER "
		          + jdbcUtil_CompositeQuery_Room.get_WhereCondition(map)
		          + "ORDER BY SELL_MEM_ID";
//		System.out.println("●●finalSQL = " + finalSQL);

	}
}
