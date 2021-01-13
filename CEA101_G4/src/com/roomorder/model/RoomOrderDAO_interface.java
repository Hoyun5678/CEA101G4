package com.roomorder.model;


import java.util.List;

import com.roomorderdetail.model.RoomOrderDetailVO;

public interface RoomOrderDAO_interface {
    public void insert(RoomOrderVO roomOrderVO);
    public void delete(String roomOrderId);
    public RoomOrderVO findByPrimaryKey(String roomOrderId);
    public List<RoomOrderVO> getAll();
    // 萬用複合查詢(傳入參數型態Map)(回傳 List)
    // public List<RoomOrderVO> getAll(Map<String, String[]> map); 
	public List<RoomOrderVO> getByMemId(String memId);
	public List<RoomOrderVO> getBySellMemId(String sellMemId);
	public List<RoomOrderVO> getByMemIdAndRoomOrderId(String memId, String roomOrderId);
	public List<RoomOrderVO> getBySellMemIdAndDate(String sellMemId, String selectedDate);
	
	public RoomOrderVO insertWithDetail (RoomOrderVO roomOrderVO , List<RoomOrderDetailVO> list);
	public void updateOrderStatus(RoomOrderVO roomOrderVO);
	void updatePaymentStatus(RoomOrderVO roomOrderVO);
	

}
