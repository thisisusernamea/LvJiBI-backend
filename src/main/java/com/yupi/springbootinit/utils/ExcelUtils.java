package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yupi.springbootinit.mapper.ChartMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel 工具类
 */
@Slf4j
@Service
public class ExcelUtils {

    /**
     * 将excel数据转换为cvs文本
     * @param multipartFile
     * @return
     */
    public static String excelToCvs(MultipartFile multipartFile){
        //读取excel数据
        List<Map<Integer,String>> excelList = null;
        try {
            excelList = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("表格处理错误",e);
        }
        if(CollUtil.isEmpty(excelList)){
            return "";
        }
        /*
        excel数据提取压缩 -> 将 excelList 转换为 cvs文本
         */
        StringBuilder stringBuilder = new StringBuilder();
        //读取表头
        LinkedHashMap<Integer,String> headerMap = (LinkedHashMap) excelList.get(0);
        //过滤null数据,并将表头数据转换cvs文本
        List<String> headerList = headerMap.values().stream().filter(header -> ObjectUtils.isNotEmpty(header)).collect(Collectors.toList());
        stringBuilder.append(StringUtils.join(headerList,",")).append("\n");
        //读取除表头外的真实数据
        for (int i = 1; i < excelList.size(); i++) {
            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap)excelList.get(i);
            List<String> dataList = dataMap.values().stream().filter(data -> ObjectUtils.isNotEmpty(data)).collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList,",")).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 获取 excel 表头,作为excel表对应数据库表的字段
     */
    public static List<String> getExcelHeader(MultipartFile multipartFile){
        List<Map<Integer,String>> excelList = null;
        try {
            excelList = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("表格处理错误",e);
        }
        if(CollUtil.isEmpty(excelList)){
            return null;
        }
        //读取表头
        LinkedHashMap<Integer,String> headerMap = (LinkedHashMap) excelList.get(0);
        List<String> headerList = headerMap.values().stream().filter(header -> ObjectUtils.isNotEmpty(header)).collect(Collectors.toList());
        return headerList;
    }

}
