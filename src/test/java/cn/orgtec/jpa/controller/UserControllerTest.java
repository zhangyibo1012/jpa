package cn.orgtec.jpa.controller;

import cn.orgtec.jpa.JpaApplication;
import cn.orgtec.jpa.entity.GenderEnum;
import cn.orgtec.jpa.entity.User;
import cn.orgtec.jpa.service.impl.JobExecutionLogServiceImplTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;


/**
 * <p>UserControllerTest.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaApplication.class, UserControllerTest.class})
public class UserControllerTest {



    @Autowired
    private UserController userController;

    @Test
    public void getUserWithAccount(){
        User byId = userController.getById(1);
        System.out.println(byId);
    }

    @Test
    public void getById(){
        List<User> users = userController.getById();
        Optional.ofNullable(users).ifPresent(System.out::println);

    }

    @Test
    public void add() {
        User user = new User();
        user.setName("小徐2");
        user.setAge(30);
        user.setGender(GenderEnum.MAN);
//        GenderEnum类型已经转换  数据库存储 1
        userController.add(user);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
//        GenderEnum类型已经转换
        User user = userController.findById(1);
//        User{id=1, name='小徐', age=25, gender=MAN, version=0}
        System.out.println(user);

    }

    @Test
    public void findList() {
    }
}