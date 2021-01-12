package com.activity_type.model;

import java.util.List;
import java.util.Set;

import com.activity_product.model.ActivityProductVO;



public class ActivityTypeService {
	private ActivityTypeDAO_interface dao;

	public ActivityTypeService() {
		dao = new ActivityTypeDAO();
	}

	public List<ActivityTypeVO> getAll() {
		return dao.getAll();
	}

	public ActivityTypeVO getOneActType(String act_type_id) {
		return dao.findByPrimaryKey(act_type_id);
	}
	public Set<ActivityProductVO> getActivityByActType(String act_type_id){
		return dao.getActivityByActType(act_type_id);
	}
	public void update(ActivityTypeVO acttypeVO) {
		dao.update(acttypeVO);
	}
	public void insert(String act_type_name) {
		ActivityTypeVO acttypeVO=new ActivityTypeVO();
		acttypeVO.setAct_type_name(act_type_name);
		dao.insert(acttypeVO);
	}

	

//	public void deleteActType(String act_type_id) {
//		dao.delete(act_type_id);
//	}
}
