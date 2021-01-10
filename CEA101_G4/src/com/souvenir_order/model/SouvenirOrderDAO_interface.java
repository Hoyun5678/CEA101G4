package com.souvenir_order.model;

import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;

public interface SouvenirOrderDAO_interface {
	public void insert(SouvenirOrderVO soVO);
    public void delete(String sou_order_id);
    public SouvenirOrderVO findByPrimaryKey(String sou_order_id);
    public void update(SouvenirOrderVO soVO);
    public List<SouvenirOrderVO> getAll();
    public List<SouvenirOrderVO> getAll(Map<String, String[]> map); 
}
