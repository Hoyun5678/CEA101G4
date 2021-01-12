package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Activity_Period {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("word".equals(columnName)) {
			// 用於varchar

			aCondition = "ACT_NAME" + " like '%" + value + "%' " + "or ACT_DES" + " like '%" + value + "%' "
					+ "or ACT_ADD" + " like '%" + value + "%' " + "or ACT_TYPE_NAME" + " like '%"+ value + "%' ";
		} else if ("datefilter1".equals(columnName)) {

			aCondition = " ACT_PERIOD_START <" + " to_timestamp('" + value + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')"
					+"and ACT_PERIOD_START>to_timestamp('"+value+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss')";
//			"to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		}else if("price".equals(columnName)) {
			aCondition= "ACT_CUR_PRICE <"+value;
		}
		return aCondition;
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}
}
