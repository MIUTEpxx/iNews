package com.example.demo.service;

import com.example.demo.entity.History;
import com.example.demo.entity.HistoryItem;

import java.util.List;

public interface HistoryService {
    public List<HistoryItem> getHistoryListByUserid(Integer userid);
    public History getHistoryByUserid(Integer userid, Integer newsid);
    public boolean insertHistory(Integer userid, Integer newsid, String looktime);
    public boolean updateHistoryByUserid(Integer userid,Integer newsid,String looktime);
}
