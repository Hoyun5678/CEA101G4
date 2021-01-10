package com.souvenir_order_detail.model;

import java.util.List;

public interface SouvenirOrderDetailDAO_interface {
	public void insert(SouvenirOrderDetailVO souvenir_order_detailVO);
    public void delete(String sou_order_id, String sou_id);
    public SouvenirOrderDetailVO findByPrimaryKey(String sou_order_id);
    public void update(SouvenirOrderDetailVO sodVO);
    public List<SouvenirOrderDetailVO> getAll();
    public void insert2 (SouvenirOrderDetailVO sodVO , java.sql.Connection con);
}
