package com.wxf.controller;

import com.wxf.entity.*;
import com.wxf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public MyPageImpl<User> list( UserVo userVo){
        System.out.print(" 消费者参数是  " + userVo);
        MyPageImpl<User> page = userService.list(userVo);

        System.out.println ("  消费者 ========== 已经获取数据了。。。。。。。。。。。。");
        page.getContent().iterator().forEachRemaining(x->{System.out.println("消费者  x is " + x);});

        return page;
    }

    @RequestMapping("del")
    public boolean del(@RequestParam(value = "id") int id){
        return userService.del(id);

    }

    @RequestMapping("add")
    public boolean add(@RequestBody User user){
        return userService.add(user);
    }

    /**
     * 获取所有的部门
     * @return
     */
    @RequestMapping("departs")
    public List<Depart> getDeparts(){
        return  userService.listDeparts();
    }

    @RequestMapping("favourites")
    public List<Favourite> getFavourites(){
        return  userService.listFavourites();
    }

}
