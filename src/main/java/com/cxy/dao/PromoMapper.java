package com.cxy.dao;

import com.cxy.pojo.Promo;

public interface PromoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int insert(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int insertSelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    Promo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int updateByPrimaryKeySelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int updateByPrimaryKey(Promo record);
}