package com.emp_auth.model;

import java.util.List;



public class EmpAuthService {
	private EmpAuthDAO_interface dao;
	public EmpAuthService() {
		dao = new EmpAuthDAO();
	}

	public EmpAuthVO addEmpAuth(String emp_id, String func_id) {

		EmpAuthVO empauthVO = new EmpAuthVO();
		empauthVO.setEmp_id(emp_id);
		empauthVO.setFunc_id(func_id);
		dao.insert(empauthVO);

		return empauthVO;
	}
	public void deleteEmpAuth(String emp_id, String func_id) {
		dao.delete(emp_id, func_id);
	}

	public EmpAuthVO getOneEmpAuth(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}

	public List<EmpAuthVO> getAll() {
		return dao.getAll();
	}
}
