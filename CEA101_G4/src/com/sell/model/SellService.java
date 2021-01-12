package com.sell.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public class SellService {
	
	private SellDAO_interface dao;
	
	public SellService() {
		dao = new SellDAO();
	}
	
	public SellVO addSell(String sellMemAccount, String sellMemPwd, String sellMemName, Date sellMemBirth
			, String sellMemTel, String sellRoomName, String sellMemAddress, Double sellLatitude, Double sellLongitud
			, String sellMemMail, String sellMemIdNumber/*, Integer sellAccStatus*/, Integer sellGender/*, Timestamp sellJointime*/) {
		
		SellVO sellVO = new SellVO();
		
//		sellVO.setSellMemId(sellMemId);
		sellVO.setSellMemAccount(sellMemAccount);
		sellVO.setSellMemPwd(sellMemPwd);
		sellVO.setSellMemName(sellMemName);
		sellVO.setSellMemBirth(sellMemBirth);
		sellVO.setSellMemTel(sellMemTel);
		sellVO.setSellRoomName(sellRoomName);
		sellVO.setSellMemAddress(sellMemAddress);
		sellVO.setSellLatitude(sellLatitude);
		sellVO.setSellLongitud(sellLongitud);
		sellVO.setSellMemMail(sellMemMail);
		sellVO.setSellMemIdNumber(sellMemIdNumber);
		sellVO.setSellAccStatus(0);
		sellVO.setSellGender(sellGender);
		sellVO.setSellJointime(new Timestamp(System.currentTimeMillis()));
		
		dao.insert(sellVO);
		return sellVO;
	}
	
	public SellVO updateSell(String sellMemId, String sellMemAccount, String sellMemPwd, String sellMemName, Date sellMemBirth
			, String sellMemTel, String sellRoomName, String sellMemAddress, Double sellLatitude, Double sellLongitud
			, String sellMemMail, String sellMemIdNumber, Integer sellAccStatus, Integer sellGender) {
		
		SellVO sellVO = new SellVO();
		
		sellVO.setSellMemId(sellMemId);
		sellVO.setSellMemAccount(sellMemAccount);
		sellVO.setSellMemPwd(sellMemPwd);
		sellVO.setSellMemName(sellMemName);
		sellVO.setSellMemBirth(sellMemBirth);
		sellVO.setSellMemTel(sellMemTel);
		sellVO.setSellRoomName(sellRoomName);
		sellVO.setSellMemAddress(sellMemAddress);
		sellVO.setSellLatitude(sellLatitude);
		sellVO.setSellLongitud(sellLongitud);
		sellVO.setSellMemMail(sellMemMail);
		sellVO.setSellMemIdNumber(sellMemIdNumber);
		sellVO.setSellAccStatus(sellAccStatus);
		sellVO.setSellGender(sellGender);
		
		dao.update(sellVO);
		return sellVO;
	}
	
	public SellVO updateSell(String sellMemId, String sellMemName, String sellMemTel
			, String sellRoomName, String sellMemAddress, Double sellLatitude
			, Double sellLongitud, String sellMemMail) {
		
		SellVO sellVO = new SellVO();
		
		sellVO.setSellMemId(sellMemId);
		sellVO.setSellMemName(sellMemName);
		sellVO.setSellMemTel(sellMemTel);
		sellVO.setSellRoomName(sellRoomName);
		sellVO.setSellMemAddress(sellMemAddress);
		sellVO.setSellLatitude(sellLatitude);
		sellVO.setSellLongitud(sellLongitud);
		sellVO.setSellMemMail(sellMemMail);
		
		dao.updateInfo(sellVO);
		return sellVO;
	}
	
	public SellVO updateSell(String sellMemId, String sellMemPwd) {
		SellVO sellVO = new SellVO();
		sellVO.setSellMemId(sellMemId);
		sellVO.setSellMemPwd(sellMemPwd);
		dao.updatePwd(sellVO);
		return sellVO;
	}
	
	public void deleteSell(String sellMemId) {
		dao.delete(sellMemId);
	}
	
	public SellVO getOneSell(String sellMemId) {
		return dao.findByPrimaryKey(sellMemId);
	}
	
	public SellVO getSellByCol(String colName, String compareValue) {
		return dao.findByCol(colName, compareValue);
	}
	
	public List<SellVO> getAll() {
		return dao.getAll();
	}
	
	public List<SellVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	

}
