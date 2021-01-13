package com.sell.model;

import java.util.List;
import java.util.Map;

import com.sell.model.SellVO;

public interface SellDAO_interface {
    public SellVO insert(SellVO sellVO);
    public void update(SellVO sellVO);
    public void updateInfo(SellVO sellVO);
    public void updatePwd(SellVO sellVO);
    public void delete(String sellMemId);
    public SellVO findByPrimaryKey(String sellMemId);
    public List<SellVO> getAll();
    // 萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<SellVO> getAll(Map<String, String[]> map);
    public SellVO findByCol(String colName, String compareValue);

}
