package com.authorization.model;

import java.util.List;


public class AuthorizationService {
	private AuthorizationDAO_interface dao;

	public AuthorizationService() {
		dao = new AuthorizationDAO();
	}

	public AuthorizationVO addAuthorization(String func_name) {

		AuthorizationVO authorizationVO = new AuthorizationVO();

		authorizationVO.setFunc_name(func_name);
		dao.insert(authorizationVO);

		return authorizationVO;
	}

	public AuthorizationVO updateAuthorization(String func_name, String func_id) {

		AuthorizationVO authorizationVO = new AuthorizationVO();

		authorizationVO.setFunc_name(func_name);
		authorizationVO.setFunc_id(func_id);
		dao.update(authorizationVO);

		return authorizationVO;
	}

	public void deleteAuthorization(String func_id) {
		dao.delete(func_id);
	}

	public AuthorizationVO getOneAuthorization(String func_id) {
		return dao.findByPrimaryKey(func_id);
	}

	public List<AuthorizationVO> getAll() {
		return dao.getAll();
	}
}
