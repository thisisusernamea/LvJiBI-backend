package com.yupi.springbootinit.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/queue")
@Profile({"dev","localhost"})
@Slf4j
/**
 * 线程池任务队列测试
 */
public class QueueController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 提交任务到线程池
     * @param name
     */
    @PostMapping("/add")
    public void add (String name){
        CompletableFuture.runAsync(() ->{
            log.info("任务执行中:" + name + ",执行线程:" + Thread.currentThread().getName());
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },threadPoolExecutor);
    }

    /**
     * 获取线程池参数信息
     * @return
     */
    @GetMapping("/get")
    public String get(){
        Map<String,Object> map = new HashMap<>();
        int size = threadPoolExecutor.getQueue().size();
        map.put("当前任务队列长度",size);
        long taskCount = threadPoolExecutor.getTaskCount();
        map.put("线程池已接收的任务总数",taskCount);
        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        map.put("线程池已完成的任务数",completedTaskCount);
        int activeCount = threadPoolExecutor.getActiveCount();
        map.put("正在执行任务的线程数",activeCount);
        return JSONUtil.toJsonStr(map);
    }
}
