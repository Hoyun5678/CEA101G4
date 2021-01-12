package com.emp_record.model;

import java.util.ArrayList;
import java.util.List;



public class EmpRecordService {
	private EmpRecordDAO_interface dao;

	public EmpRecordService() {
		dao=new EmpRecordDAO();
	}

	public EmpRecordVO addEmpRe(String emp_id,java.sql.Timestamp emp_record_time,String emp_record_content,Integer emp_record_read) {
		EmpRecordVO empreVO=new EmpRecordVO();
		empreVO.setEmp_id(emp_id);
		empreVO.setEmp_record_time(emp_record_time);
		empreVO.setEmp_record_content(emp_record_content);
		empreVO.setEmp_record_read(emp_record_read);
		dao.insert(empreVO);
		return empreVO;
		
	}
	public EmpRecordVO updateEmpRe(String emp_record_id,String emp_id,java.sql.Timestamp emp_record_time,String emp_record_content,Integer emp_record_read) {
		EmpRecordVO empreVO=new EmpRecordVO();
		empreVO.setEmp_record_id(emp_record_id);
		empreVO.setEmp_id(emp_id);
		empreVO.setEmp_record_time(emp_record_time);
		empreVO.setEmp_record_content(emp_record_content);
		empreVO.setEmp_record_read(emp_record_read);
		dao.update(empreVO);
		return empreVO;
		
	}
	public EmpRecordVO getOneEmpRe(String emp_record_id) {
		return dao.findByPrimaryKey(emp_record_id);
	}
	public void deleteEmpRe(String emp_record_id) {
		dao.delete(emp_record_id);
	}
	
	public List<EmpRecordVO>getAll(){
		return dao.getAll();
	}
	public List<EmpRecordVO>getByEmpId(String emp_id){
		List<EmpRecordVO> list=new ArrayList<EmpRecordVO>();
		return list;
	}
	

}
