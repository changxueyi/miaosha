package com.cxy.dao;

import com.cxy.pojo.SequenceInfo;

public interface SequenceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int deleteByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int insert(SequenceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int insertSelective(SequenceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    SequenceInfo selectByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int updateByPrimaryKeySelective(SequenceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbggenerated Sun Mar 08 09:56:25 CST 2020
     */
    int updateByPrimaryKey(SequenceInfo record);
}