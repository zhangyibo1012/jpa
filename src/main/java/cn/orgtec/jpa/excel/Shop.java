package cn.orgtec.jpa.excel;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.Date;

/**
 * <p>Shop.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/20 <p>
 * <p>@remark:</p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet(name = "商户列表", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class Shop {

    @ExcelField(name = "是否VIP商户")
    private boolean vip;

    @ExcelField(name = "商户名称", align = HorizontalAlignment.CENTER)
    private String shopName;

    @ExcelField(name = "分店数量")
    private short branchNum;

    @ExcelField(name = "商户ID")
    private int shopId;

    @ExcelField(name = "浏览人数")
    private long visitNum;

    @ExcelField(name = "当月营业额")
    private float turnover;

    @ExcelField(name = "历史营业额")
    private double totalTurnover;

    @ExcelField(name = "开店时间")
    private Date addTime;
}
