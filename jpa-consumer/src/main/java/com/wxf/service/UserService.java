package com.wxf.service;

import com.wxf.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// ,fallback = UserServiceFailBack.class
@Repository
@FeignClient(value = "jpa-service")
public interface UserService {

    @RequestMapping("/user/list")
    MyPageImpl<User> list(@RequestBody UserVo userVo);

    @RequestMapping("/user/del")
    Boolean del(@RequestParam(value = "id") int id);

    @RequestMapping("/user/add")
    boolean add(@RequestBody User user);

    @RequestMapping("/user/departs")
    List<Depart> listDeparts();

    @RequestMapping("/user/favourites")
    List<Favourite> listFavourites();
}
