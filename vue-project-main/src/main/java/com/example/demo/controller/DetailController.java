package com.example.demo.controller;


import com.example.demo.entity.FullNewsInfo;
import com.example.demo.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/{id}")//ResponseEntity 是 Spring 框架中用于构建 HTTP 响应的工具类。它表示整个 HTTP 响应，包括状态码、头部信息和正文内容
    public ResponseEntity<FullNewsInfo> getFullNewsInfoById(@PathVariable Integer id) {
        FullNewsInfo fullNewsInfo = detailService.getFullNewsInfoById(id);
        if(fullNewsInfo == null) {return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(fullNewsInfo);//前端或者客户端就可以根据返回的 ResponseEntity 对象来判断请求是否成功
    }

}
