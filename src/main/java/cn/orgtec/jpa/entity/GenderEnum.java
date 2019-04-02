package cn.orgtec.jpa.entity;

import lombok.Getter;

/**
 * <p>GenderEnum.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Getter
public enum GenderEnum {
    MAN("1","男"),WOMAN("2","女");
    GenderEnum(String code,String value){
        this.code = code;
        this.value = value;
    }
    private String code;
    private String value;
    private String description;
}
