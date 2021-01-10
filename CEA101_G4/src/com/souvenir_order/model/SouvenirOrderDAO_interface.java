package com.souvenir_order.model;

import java.util.List;
import java.util.Map;
import com.souvenir_order_detail.model.SouvenirOrderDetailVO;

public interface SouvenirOrderDAO_interface {
	public void insert(SouvenirOrderVO soVO);
    public void delete(String sou_order_id);
    public SouvenirOrderVO findByPrimaryKey(String sou_order_id);
    public void update(SouvenirOrderVO soVO);
    public List<SouvenirOrderVO> getAll();
    public List<SouvenirOrderVO> getAll(Map<String, String[]> map);
    public void insertWithDetail( SouvenirOrderVO soVO , List<SouvenirOrderDetailVO> list);
}
