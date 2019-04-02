package cn.orgtec.jpa.entity;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

/**
 * <p>GenderEnumConverter.java此类用于  数据库属性类型与 Java 存储的类型做转换 </p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 *  <p> 枚举转换器 </p>
 */
public class GenderEnumConverter implements AttributeConverter<GenderEnum , String> {

    /**
     *  把输入的类型转换为数据库存储的类型
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public String convertToDatabaseColumn(GenderEnum attribute) {
        return attribute.getCode();
    }

    /**
     *  用于把数据库的类型转换成实体中的类型 遍历枚举
     * @param dbData
     * @return
     */
    @Override
    public GenderEnum convertToEntityAttribute(String dbData) {
        return Arrays.stream(GenderEnum.values()).filter(en -> en.getCode().equals(dbData)).findFirst().orElse(null);
    }
}
