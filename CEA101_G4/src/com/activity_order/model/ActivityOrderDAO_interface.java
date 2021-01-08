package com.activity_order.model;

import java.util.List;
import java.util.Set;


public interface ActivityOrderDAO_interface {
	public void insert(ActivityOrderVO actordVO);
    public void update(ActivityOrderVO actordVO);
    public ActivityOrderVO findByPrimaryKey(String act_order_id);
//    public void delete(String mem_id,String act_period_id);
    public List<ActivityOrderVO> getAll();
    public List<ActivityOrderVO> getActivityByMemid(String mem_id);

}
