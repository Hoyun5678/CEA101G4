package com.activity_product.model;

import java.util.List;
import java.util.Set;



public class ActivityProductService {
	private ActivityProductDAO_interface dao;

	public ActivityProductService() {
		
		dao = new ActivityProductDAO();
	}
		
		public List<ActivityProductVO> getAll() {
			return dao.getAll();
		}

		public ActivityProductVO getOneActPro(String act_id) {
			return dao.findByPrimaryKey(act_id);
		}
		 public void update(ActivityProductVO actpVO) {
			 dao.update(actpVO);
		 }
		 public void insert(ActivityProductVO actpVO) {
			 dao.insert(actpVO);
		 }
		 public List<ActivityProductVO> getAllbySellMemId(String sell_mem_id) {
			 return dao.getAllbySellMemId(sell_mem_id);
		 }

//		public Set<ActivityProductVO> getEmpsByDeptno(Integer deptno) {
//			return dao.getEmpsByDeptno(deptno);
//		}

//		public void deleteDept(Integer deptno) {
//			dao.delete(deptno);
//		}

	}

