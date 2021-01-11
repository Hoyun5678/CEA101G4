package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtil_CompositeQuery_SouvenirOrderDetail {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("sou_order_amount".equals(columnName) ||"sou_price".equals(columnName))
			aCondition = columnName + "=" + value;
		else if ("sou_order_id".equals(columnName)||"sou_id".equals(columnName) )
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count =" + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("sou_order_id", new String[] { "SO001" });
		map.put("sou_id", new String[] { "SOU001" });
		String finalSQL = "select * from SOUVENIR_ORDER "
				          + jdbcUtil_CompositeQuery_SouvenirOrder.get_WhereCondition(map)
				          + "order by sou_order_id";
		System.out.println("●●finalSQL= " + finalSQL);

	}
}
