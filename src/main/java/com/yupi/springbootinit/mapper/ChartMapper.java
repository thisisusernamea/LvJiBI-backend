package com.yupi.springbootinit.mapper;

import com.yupi.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 常俊杰
* @description 针对表【chart(图标信息表)】的数据库操作Mapper
* @createDate 2024-03-12 16:12:28
* @Entity com.yupi.springbootinit.model.entity.Chart
*/
public interface ChartMapper extends BaseMapper<Chart> {
    /**
     * 根据 id 创建 excel原始数据对应的数据库表
     * @param chartId
     * @param columnList
     */
    void createTableByChartId(@Param("chartId")String chartId, @Param("columnList")List<String> columnList);

    /**
     * 将excel原始数据添加到对应的数据库表
     * @param chartId
     * @param dataList
     * @return
     */
    /*int insertExcelDataToTable(@Param("chartId")String chartId, @Param("dataList")List<String> dataList);*/
}




