package com.activity_order.model;

import java.util.List;
import java.util.Set;





public class ActivityOrderService {
	private ActivityOrderDAO_interface dao;

	public ActivityOrderService() {
		dao = new ActivityOrderJDBCDAO();
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


//	public Set<ActivityOrderVO> getEmpsByDeptno(String act_order_id) {
//		return dao.getEmpsByDeptno(act_order_id);
//	}
//
//	public void deleteDept(Integer deptno) {
//		dao.delete(deptno);
//	}
}


