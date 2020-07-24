package com.wxf.repository;

import com.wxf.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavRepository extends JpaRepository<Favourite,Integer> {

}
