package com.wxf.service;

import com.wxf.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 熔断
 */
@Component
public class UserServiceFailBack  implements  UserService{

    @Override
    public MyPageImpl<User> list(@RequestBody UserVo userVo) {
        System.out.println(" 对不起，熔断了。。。。");
        return  null;
    }

    @Override
    public Boolean del(int id) {
        return false;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public List<Depart> listDeparts() {
        return new ArrayList<Depart>();
    }

    @Override
    public List<Favourite> listFavourites() {
        return new ArrayList<Favourite>();
    }
}
