package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao=new MemberDAO();
	}

	public MemberVO addMem(String  mem_account,String mem_pwd,String mem_name,java.sql.Date mem_birth,String mem_tel,String mem_address,
			String mem_mail,String mem_id_number,Integer mem_acc_status,Integer mem_gender) {
		MemberVO memVO=new MemberVO();
		memVO.setMem_account(mem_account);
		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(mem_name);
		memVO.setMem_birth(mem_birth);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_address(mem_address);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_id_number(mem_id_number);
		memVO.setMem_acc_status(mem_acc_status);
		memVO.setMem_gender(mem_gender);
		dao.insert(memVO);
		return memVO;
		
	}
	public MemberVO updateMemNormal(String mem_id,String mem_name,String mem_tel,String mem_address) {
		MemberService memSvc=new MemberService();
		MemberVO memVO=memSvc.getOneMem(mem_id);
		memVO.setMem_id(memVO.getMem_id());
		memVO.setMem_account(memVO.getMem_account());
		memVO.setMem_pwd(memVO.getMem_pwd());
		memVO.setMem_name(mem_name);
		memVO.setMem_birth(memVO.getMem_birth());
		memVO.setMem_tel(mem_tel);
		memVO.setMem_address(mem_address);
		memVO.setMem_mail(memVO.getMem_mail());
		memVO.setMem_id_number(memVO.getMem_id_number());
		memVO.setMem_acc_status(memVO.getMem_acc_status());
		memVO.setMem_gender(memVO.getMem_gender());
		memVO.setMem_jointime(memVO.getMem_jointime());
		dao.update(memVO);
		return memVO;
	}
	
	public MemberVO updateMem(String mem_id,String  mem_account,String mem_pwd,String mem_name,java.sql.Date mem_birth,String mem_tel,String mem_address,
			String mem_mail,String mem_id_number,Integer mem_acc_status,Integer mem_gender,java.sql.Timestamp mem_jointime) {
		MemberVO memVO=new MemberVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_account(mem_account);
		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(mem_name);
		memVO.setMem_birth(mem_birth);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_address(mem_address);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_id_number(mem_id_number);
		memVO.setMem_acc_status(mem_acc_status);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_jointime(mem_jointime);
		dao.update(memVO);
		return memVO;
		
	}
	public MemberVO getOneMem(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
	}
	public MemberVO getOneMemByAccount(String mem_account) {
		return dao.findbyaccount(mem_account);
	}
	
	public List<MemberVO>getAll(){
		return dao.getAll();
	}
	public void upDateStatus(String mem_id, Integer i) {
		MemberVO memVO=dao.findByPrimaryKey(mem_id);
		memVO.setMem_acc_status(i);
		dao.upDateStatus(memVO);
	}
	public void upDateMemPwd(String mem_account, String mem_pwd) {
		MemberVO memVO=dao.findbyaccount(mem_account);
		
		memVO.setMem_id(memVO.getMem_id());
		memVO.setMem_account(memVO.getMem_account());
		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(memVO.getMem_name());
		memVO.setMem_birth(memVO.getMem_birth());
		memVO.setMem_tel(memVO.getMem_tel());
		memVO.setMem_address(memVO.getMem_address());
		memVO.setMem_mail(memVO.getMem_mail());
		memVO.setMem_id_number(memVO.getMem_id_number());
		memVO.setMem_acc_status(memVO.getMem_acc_status());
		memVO.setMem_gender(memVO.getMem_gender());
		memVO.setMem_jointime(memVO.getMem_jointime());
		dao.update(memVO);
		
	}
	

}
