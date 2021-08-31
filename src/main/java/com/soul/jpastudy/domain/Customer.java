package com.soul.jpastudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * 客户的实体类
 *      配置映射关系
 *          1. 实体类和表的映射关系
 *          2. 实体类中属性和表中字段的映射关系
 *  @Entity:声明实体类
 *  @Table: 配置实体类和表的映射关系
 *      name: 配置数据库表的名称
 */

@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id // 声明主键的配置
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @Column(name = "cust_id")   // 映射数据库表中的字段
    private Long custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_address")
    private String custAddress;

}
