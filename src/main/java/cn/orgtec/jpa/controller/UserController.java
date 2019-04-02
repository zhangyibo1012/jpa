package cn.orgtec.jpa.controller;

import cn.orgtec.jpa.entity.User;
import cn.orgtec.jpa.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>UserController.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/27 <p>
 * <p>@remark:</p>
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public int update(@RequestBody User user) {
        return userService.update(user);
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public int delete(@RequestParam int id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public User findById(@RequestParam int id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "findList", method = RequestMethod.POST)
    public List<User> findList() {
        return userService.findList();
    }

    @GetMapping(value = "/getById")
    public User getById(Integer id) {
        return userService.getUserWithAccount(id);
    }
    @GetMapping(value = "/getList")
    public List<User> getById() {
        return userService.getUserWithAccountList();
    }

}
