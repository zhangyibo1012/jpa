package cn.orgtec.jpa.controller;

import cn.orgtec.jpa.JpaApplication;
import cn.orgtec.jpa.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * <p>AccountControllerTest.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaApplication.class, AccountControllerTest.class})
public class AccountControllerTest {

    @Autowired
  private   AccountController accountController;
    @Test
    public void add() {
    }

    @Test
    public void findById() {
        Account byId = accountController.findById(1);
        System.out.println(byId.toString());
    }

    @Test
    public void findList() {
    }

    @Test
    public void findIdsByCondition() {
        List<Integer> idsByCondition = accountController.findIdsByCondition("9999");
        System.out.println(idsByCondition);
    }

    @Test
    public void findByBankNum() {
        List<Account> byBankNum = accountController.findByBankNum("9999");
        byBankNum.forEach(a -> System.out.println(a.toString()));

    }

    @Test
    public void update() {
        Account byBankNum = new Account();
        byBankNum.setId(4);
        byBankNum.setBalance(999d);
        Account update = accountController.update(byBankNum);
        Optional.ofNullable(update).ifPresent(System.out::println);
    }

}