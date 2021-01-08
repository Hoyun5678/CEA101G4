
package com.activity_period.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.activity_period.model.ActivityPeriodVO;



public class ActivityPeriodService {
	private ActivityPeriodDAO_interface dao;

	public ActivityPeriodService() {
		dao = new ActivityPeriodJDBCDAO();
	}

	public List<ActivityPeriodVO> getAll() {
		return dao.getAll();
	}
	public List<ActivityPeriodVO> getAllActPerByActId(String act_id) {
		return dao.getAllActPerByActId(act_id);
	}

	public ActivityPeriodVO getOneActPeriod(String act_period_id) {
		return dao.findByPrimaryKey(act_period_id);
	}
	public List<ActivityPeriodVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public void insert(String act_id,Timestamp act_sign_start,
			Timestamp act_sign_end,Timestamp act_period_start,Timestamp act_period_end,Integer act_up_limit,
			Integer act_low_limit,Double act_cur_price,Integer act_period_status,Integer act_sign_sum) {
		ActivityPeriodVO actperVO = new ActivityPeriodVO();
		actperVO.setAct_id(act_id);
		actperVO.setAct_sign_start(act_sign_start);
		actperVO.setAct_sign_end(act_sign_end);
		actperVO.setAct_period_start(act_period_start);
		actperVO.setAct_period_end(act_period_end);
		actperVO.setAct_up_limit(act_up_limit);
		actperVO.setAct_low_limit(act_low_limit);
		actperVO.setAct_cur_price(act_cur_price);
		actperVO.setAct_period_status(act_period_status);
		actperVO.setAct_sign_sum(act_sign_sum);
		dao.insert(actperVO);
	}
	
	public void upDateActPerStatus(String act_period_id,Integer act_period_status) {
		dao.upDateActPerStatus(act_period_id, act_period_status);
		
	}
	

//	public void deleteActType(String act_type_id) {
//		dao.delete(act_type_id);
//	}
}
