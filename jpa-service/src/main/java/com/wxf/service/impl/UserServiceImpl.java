package com.wxf.service.impl;

import com.netflix.discovery.converters.Auto;
import com.wxf.entity.*;
import com.wxf.repository.DpartRepository;
import com.wxf.repository.FavRepository;
import com.wxf.repository.UserRepository;
import com.wxf.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DpartRepository dpartRepository;

    @Autowired
    FavRepository favRepository;


    @Override
    public MyPageImpl<User> list(UserVo userVo) {

        Specification specification = new Specification<UserVo>() {
            //  动态的生成查询条件
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //保存查询条件
                List<Predicate> list = new ArrayList<Predicate>();

                //根据username 生成查询条件
                if(!StringUtils.isEmpty(userVo.getUsername())){
                    //
                    Predicate predicate = criteriaBuilder.like(root.get("username"), "%" + userVo.getUsername() + "%");
                    //查询条件保存到集合当中
                    list.add(predicate);
                }
                if(!StringUtils.isEmpty(userVo.getName())){
                    //
                    Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + userVo.getName() + "%");
                    //查询条件保存到集合当中
                    list.add(predicate);
                }
                if(userVo.getSex()!=0){
                    Predicate predicate =criteriaBuilder.equal(root.get("sex"),userVo.getSex());
                    list.add(predicate);
                }
                // 获取条件的数组
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);

                // 把前述的所有的条件组合生成一个条件
                Predicate predicateAll = criteriaBuilder.and(predicates);
                return predicateAll;
            }
        };

        // 生成分页的对象  符合国人习惯
        Pageable pageable = PageRequest.of(userVo.getPage()-1,userVo.getPageSize(), Sort.Direction.DESC,"uid");

        //  Page<User> page = userRepository.findAll(pageable);
        // return new MyPageImpl(page);

        //使用动态sql 查询的结果
        Page page = userRepository.findAll(specification, pageable);

        return new MyPageImpl(page);


    }

    @Override
    public boolean del(int id) {

        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    //@Transactional
    public boolean add(User user) {
        User user1 = userRepository.saveAndFlush(user);
        try {
            if (user1.getUid()>0)
                return true;
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Depart> listDeparts() {
        return dpartRepository.findAll();
    }

    @Override
    public List<Favourite> listFavourites() {
        return favRepository.findAll();
    }
}
