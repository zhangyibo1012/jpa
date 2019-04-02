package cn.orgtec.jpa.entity;

import lombok.Data;

/**
 * <p>BroadcastDetail.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark:</p>
 */
@Data
public class BroadcastDetail {

//    private BroadcastDTO broadcastDTO;

    private Behavior behavior;

}

@Data
class Comment{
    private  Integer count;
    private Integer did;
}

@Data
class Behavior {
    private Comment comment;
    private Favor favor;
    private Hate hate;
    private Reward reward;

}

@Data
class Favor extends Comment{

}

@Data
class Hate extends Comment{

}

@Data
class Reward {
    private Integer amount;
    private Integer did;
    private Integer nop;
}




