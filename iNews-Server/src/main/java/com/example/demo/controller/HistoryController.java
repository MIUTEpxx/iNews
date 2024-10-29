package com.example.demo.controller;


import com.example.demo.entity.History;
import com.example.demo.entity.HistoryItem;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/getAll/{userid}")//根据用户id获得其所有历史记录
    public List<HistoryItem> getALLHistoryById(@PathVariable("userid") Integer userid) {
        return historyService.getHistoryListByUserid(userid);
    }

    @PostMapping("/save/{userid}/{newsid}") //保存浏览记录
    public boolean updateHistoryById(@PathVariable("userid") Integer userid, @PathVariable("newsid") Integer newsid) {
        History history = historyService.getHistoryByUserid(userid,newsid);//检查数据库表中是否有记录(是否浏览过该新闻)
        long currentTimestamp = System.currentTimeMillis() / 1000;//获取当前时间戳
        String timestampString = String.valueOf(currentTimestamp);
        if (history == null) {//则新增记录
            return historyService.insertHistory(userid,newsid,timestampString);
        }else{//修改记录的上次浏览时间
            return historyService.updateHistoryByUserid(userid,newsid,timestampString);
        }
    }
}
