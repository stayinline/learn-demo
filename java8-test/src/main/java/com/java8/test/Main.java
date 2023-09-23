package com.java8.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/hemaoling/Desktop/副本整机生态及安全.xlsx"));

        //创建工作簿
        Workbook workbook = new XSSFWorkbook(fis);
        //获取第一个工作表
        Sheet sheet = workbook.getSheetAt(1);

        Map<String, Double> workerHours = new HashMap<>();

        //使用迭代器，遍历行
        Iterator<Row> iterator = sheet.iterator();

        // 跳过标题行
        if (iterator.hasNext()) {
            iterator.next();
        }

        List<Temp> list = new ArrayList<>();

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Cell employeeCell = currentRow.getCell(0);
            Cell hoursCell = currentRow.getCell(10);

            String empID = employeeCell.getStringCellValue();

            DataFormatter dataFormatter = new DataFormatter();
            String cellStringValue = dataFormatter.formatCellValue(hoursCell);
            double hours = Double.parseDouble(cellStringValue);

            // 使用HashMap存储和计算员工编号和总时长
            workerHours.put(empID, workerHours.getOrDefault(empID, 0.0) + hours);
        }

        workerHours.forEach((k, v) -> {
            list.add(new Temp(k, v));
        });

        list.stream().sorted(Comparator.comparing(Temp::getTime))
                .forEach(x -> {
                    System.out.println("姓名：" + x.getName() + "， 调休总时长：" + x.getTime());
                });

        workbook.close();
        fis.close();
    }

    @Data
    public static class Temp {
        private String name;
        private double time;

        public Temp(String name, double time) {
            this.name = name;
            this.time = time;
        }
    }
}
