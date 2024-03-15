package com.yupi.springbootinit.model.vo;

import lombok.Data;

@Data
public class BIResponse {
    /**
     * 图表信息（一段js代码）
     */
    private String genChart;

    /**
     * 数据分析结论
     */
    private String genResult;

    /**
     * 生成的图表id
     */
    private long chartId;
}
