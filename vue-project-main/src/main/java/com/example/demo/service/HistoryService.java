package com.example.demo.service;

import com.example.demo.entity.History;
import com.example.demo.entity.HistoryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryService {
    public List<HistoryItem> getALLHistoryByUserid(Integer userid);
    public History getOneHistoryByUserid(Integer userid, Integer newsid);
    public boolean insertFavorite(Integer userid, Integer newsid,String looktime);
    public boolean updateHistoryByUserid(Integer userid,Integer newsid,String looktime);
}
