package cn.orgtec.jpa.excel;

import com.xuxueli.poi.excel.ExcelImportUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>TestExcel.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/20 <p>
 * <p>@remark:</p>
 */
@Slf4j
public class TestExcel {

    public static void main(String[] args) {

//        伪造数据
        List<Shop> list = new ArrayList<>(100);
        IntStream.rangeClosed(1 , 100).forEach(i -> {
            Shop shop = new Shop(true, "商户"+i, (short) i, 1000+i, 10000+i, (float) (1000+i), (double) (10000+i), new Date());
            list.add(shop);
        });

        String filePath = "C:\\Users\\Administrator\\Downloads\\shop1-sheet.xls";

//        ExcelExportUtil.exportToFile(filePath , list);
//        log.info("Object 转换 Excel 成功");


        List<Object> objects = ExcelImportUtil.importExcel(filePath, Shop.class);
        log.info("Excel 转换为 Java 对象");
        System.out.println(objects);


    }
}
