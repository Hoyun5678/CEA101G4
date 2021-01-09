package com.foodspot.model;

import java.util.List;

import com.foodspot.model.FoodSpotVO;

public interface FoodSpotDAO_interface {
	public void insert(FoodSpotVO fsVO);
	public void update(FoodSpotVO fsVO);
	public void delete(String fas_id);
	public FoodSpotVO findByPrimaryKey(String fas_id);
	public List<FoodSpotVO> findBySell(String sell_mem_id);
	public List<FoodSpotVO> getAll();
	
	
//    public FoodSpotVO getFasPhoto(String fas_id);
}
