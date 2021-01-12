package com.foodspot.model;

import java.util.ArrayList;
import java.util.List;


import com.foodspot.model.FoodSpotDAO;
import com.foodspot.model.FoodSpotDAO_interface;
import com.foodspot.model.FoodSpotVO;
import com.roomorderdetail.model.RoomOrderDetailVO;

public class FoodSpotService {
		private FoodSpotDAO_interface dao;
		public FoodSpotService() {
			dao = new FoodSpotDAO();
		}
		public FoodSpotVO addFoodSpot(String sell_mem_id, String fas_spot_name, String fas_add, String fas_des, byte[] fas_photo, Double fas_latitude, Double fas_longitud) {
			
			FoodSpotVO fsVO = new FoodSpotVO();
			
			fsVO.setSell_mem_id(sell_mem_id);
			fsVO.setFas_spot_name(fas_spot_name);
			fsVO.setFas_add(fas_add);
			fsVO.setFas_des(fas_des);
			fsVO.setFas_photo(fas_photo);
			fsVO.setFas_latitude(fas_latitude);
			fsVO.setFas_longitud(fas_longitud);
			dao.insert(fsVO);
			
			return fsVO;
		}
		public FoodSpotVO updateFoodSpot(String fas_id, String sell_mem_id, String fas_spot_name, String fas_add,String fas_des, byte[] fas_photo, Double fas_latitude,Double fas_longitud) {

			FoodSpotVO fsVO = new FoodSpotVO();
			
			fsVO.setFas_id(fas_id);
			fsVO.setSell_mem_id(sell_mem_id);
			fsVO.setFas_spot_name(fas_spot_name);
			fsVO.setFas_add(fas_add);
			fsVO.setFas_des(fas_des);
			fsVO.setFas_photo(fas_photo);
			fsVO.setFas_latitude(fas_latitude);
			fsVO.setFas_longitud(fas_longitud);
			
			dao.update(fsVO);

			return fsVO;
		}
		
		public void deleteFoodSpot(String fas_id) {
			dao.delete(fas_id);
		}

		public FoodSpotVO getOneFoodSpot(String fas_id) {
			return dao.findByPrimaryKey(fas_id);
		}
		
		public List <FoodSpotVO> getOneBySell(String sell_mem_id) {
			return dao.findBySell(sell_mem_id);
		}

		public List<FoodSpotVO> getAll() {
			List<FoodSpotVO> list = new ArrayList<>();
			list = dao.getAll();
			if(list.isEmpty()) {
				list = new ArrayList<>();
			}
			return list;
		}
//		public FoodSpotVO getFasPhoto(String fas_id) {
//			return dao.findByPrimaryKey(fas_id);
//			
//		}
		
}
