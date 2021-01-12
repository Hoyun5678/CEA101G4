package com.souvenir_photo.model;

import java.util.ArrayList;
import java.util.List;


public class SouvenirPhotoService {
	private SouvenirPhotoDAO_interface dao;

	public SouvenirPhotoService() {
		dao = new SouvenirPhotoDAO();
	}

	public SouvenirPhotoVO addSouPhoto(String sou_id, byte[] sou_photo) {
		SouvenirPhotoVO souphVO = new SouvenirPhotoVO();
		souphVO.setSou_id(sou_id);
		souphVO.setSou_photo(sou_photo);
//		souphVO.setSou_photo_content(sou_photo_content);
		

		dao.insert(souphVO);
		return souphVO;

	}

	public SouvenirPhotoVO updateSouPhoto(String sou_photo_id, String sou_id, byte[] sou_photo ,String sou_photo_content) {
		SouvenirPhotoVO souphVO = new SouvenirPhotoVO();
		souphVO.setSou_photo_id(sou_photo_id);
		souphVO.setSou_id(sou_id);
		souphVO.setSou_photo(sou_photo);
//		souphVO.setSou_photo_content(sou_photo_content);
		
		dao.update(souphVO);
		return souphVO;

	}

	public SouvenirPhotoVO getOneSouPhoto(String sou_photo_id) {
		return dao.findByPrimaryKey(sou_photo_id);
	}

	public void deleteSouPhoto(String sou_photo_id) {
		dao.delete(sou_photo_id);
	}

	public List<SouvenirPhotoVO> getAll() {
		List<SouvenirPhotoVO> list = new ArrayList<>();
		list = dao.getAll();
		if(list.isEmpty()) {
			list = new ArrayList<>();
		}
		return list;
	}
	
	public List<SouvenirPhotoVO> getBySouId(String sou_id) {
		return dao.getBySouId(sou_id);
	}
	

}
