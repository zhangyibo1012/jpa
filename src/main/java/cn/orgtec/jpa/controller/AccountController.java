package cn.orgtec.jpa.controller;

import cn.orgtec.jpa.entity.Account;
import cn.orgtec.jpa.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuhaixing
 * @date 2018/5/2 9:55
 */
@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(@RequestBody Account account) {
        return accountService.add(account);
    }

    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public Account findById(@RequestParam int id) {
        return accountService.findById(id);
    }

    @RequestMapping(value = "findByCondition", method = RequestMethod.POST)
    public List<Account> findList(@RequestBody Account account) {
        return accountService.findByCondition(account);
    }


    @GetMapping(value = "findIdsByCondition")
    public List<Integer> findIdsByCondition(@RequestParam String bankNum){
        return accountService.findIdsByCondition(bankNum);
    }

    @GetMapping(value = "findByBankNum")
    public List<Account> findByBankNum(@RequestParam String banNum){
        return accountService.findByBankNum(banNum);
    }

    /**
     *  用form传数据时,把version也传\过来;
     *
     *   从DB中把version查出来,赋给实体对象;再更新;
     * @param account
     * @return
     *
     * {
     *   "id": "1",
     *   "bankNumber": "9999",
     *   "version":"999"
     * }
     *
     */
    @PutMapping(value = "update")
    public Account update(@RequestBody  Account account){
        return accountService.updataAccount(account);
    }

    @PutMapping(value = "updateAcc")
    public Integer updateAcc(@RequestBody  Account account){
        System.out.println(account.toString());
        return accountService.updateAc(account);
    }

}
