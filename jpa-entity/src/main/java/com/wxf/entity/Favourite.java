package com.wxf.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "hg_favourite")
public class Favourite implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    String name;

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
