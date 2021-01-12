package com.souvenir_type.model;

import java.util.List;



public class SouvenirTypeService {
	private SouvenirTypeDAO_interface dao;


	public SouvenirTypeService() {
		dao = new SouvenirTypeJDBCDAO();
	}

	public SouvenirTypeVO addSouType(String sou_type_name) {
		SouvenirTypeVO soutVO = new SouvenirTypeVO();
		soutVO.setSou_type_name(sou_type_name);
		
		


		dao.insert(soutVO);
		return soutVO;

	}

	public SouvenirTypeVO updateSouType(String sou_type_id, String sou_type_name) {
		SouvenirTypeVO soutVO = new SouvenirTypeVO();
		
		soutVO.setSou_type_id(sou_type_id);
		soutVO.setSou_type_name(sou_type_name);
		dao.update(soutVO);
		return soutVO;

	}

	public SouvenirTypeVO getOneSouType(String sou_type_id) {
		return dao.findByPrimaryKey(sou_type_id);
	}

	public void deleteSouType(String sou_type_id) {
		dao.delete(sou_type_id);
	}

	public List<SouvenirTypeVO> getAll() {
		return dao.getAll();
	}

}

