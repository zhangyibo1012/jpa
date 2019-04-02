package cn.orgtec.jpa.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * <p>TimeInterval.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/28 <p>
 * <p>@remark:</p>
 */
public class TimeInterval {
    public static void main(String[] args) {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-03-01 22:36:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.MINUTE);
        System.out.println(betweenDay);
    }
}
