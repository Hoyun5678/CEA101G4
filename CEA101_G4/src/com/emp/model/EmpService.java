package com.emp.model;

import java.util.List;


public class EmpService {
	private EmpDAO_interface dao;
	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(String emp_account, String emp_pwd, String emp_name, Integer emp_acc_status, Integer emp_gender) {

		EmpVO empVO = new EmpVO();
		empVO.setEmp_account(emp_account);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_acc_status(emp_acc_status);
		empVO.setEmp_gender(emp_gender);
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(String emp_id, String emp_account, String emp_pwd, String emp_name, Integer emp_acc_status, Integer emp_gender) {

		EmpVO empVO = new EmpVO();

		empVO.setEmp_id(emp_id);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_acc_status(emp_acc_status);
		empVO.setEmp_gender(emp_gender);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(String emp_id) {
		dao.delete(emp_id);
	}

	public EmpVO getOneEmp(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	public EmpVO getOneEmpByAccount(String emp_account) {
		return dao.findByAccount(emp_account);
	}
}
