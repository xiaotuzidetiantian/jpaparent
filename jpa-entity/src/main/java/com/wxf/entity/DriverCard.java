package com.wxf.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="hg_drivercard")
public class DriverCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    //驾驶证类型
    String cardtype;

    // 过期时间
    Date expiredate;


}
