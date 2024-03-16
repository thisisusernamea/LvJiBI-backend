package com.yupi.springbootinit.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelUtilsTest {

    @Resource
    ExcelUtils excelUtils;

    @Test
    void excelToTable() throws FileNotFoundException {
        //System.out.println("result = " + excelUtils.excelToTable());
    }
}