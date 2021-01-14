package com.activity_order.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.activity_period.model.ActivityPeriodService;
import com.activity_product.model.ActivityProductService;
import com.roomorder.model.RoomOrderVO;





public class ActivityOrderService {
	private ActivityOrderDAO_interface dao;

	public ActivityOrderService() {
		dao = new ActivityOrderDAO();
	}
	
	
    public List<String> getCheckInBySellMemId(String sell_mem_id) {
    	   	
    	return dao.getCheckInBySellMemId(sell_mem_id);
    }
    	
	public List<ActivityOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActivityOrderVO> getActOrderByMemId(String mem_id) {
		return dao.getActivityByMemid(mem_id);
	}
	
	public List<ActivityOrderVO> getActOrderByActPerId(String act_period_id) {
		return dao.getActOrderByActPerId(act_period_id);
	}
	public ActivityOrderVO getOneOrder(String act_order_id) {
		return dao.findByPrimaryKey(act_order_id);
	}
	public void changeActOrderStatus(ActivityOrderVO actordVO) {
		dao.update(actordVO);
	}
	
	public void insertActivityOrder(ActivityOrderVO actordVO) {
		dao.insert(actordVO);
	}
	public List<ActivityOrderVO> getActOrdrBySellMemIdAndDate(String sell_mem_id, String act_start_date) {
		return dao.getActOrdBySellMemIdAndDate(sell_mem_id, act_start_date);
	}


//	public Set<ActivityOrderVO> getEmpsByDeptno(String act_order_id) {
//		return dao.getEmpsByDeptno(act_order_id);
//	}
//
//	public void deleteDept(Integer deptno) {
//		dao.delete(deptno);
//	}
}


