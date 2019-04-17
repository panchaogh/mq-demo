package com.pcgrw.sbrabbit.dao;

import com.pcgrw.sbrabbit.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}