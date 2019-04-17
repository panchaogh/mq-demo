package com.pcgrw.sbrabbit.dao;

import com.pcgrw.sbrabbit.entity.BrokerMessageLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);

    /**
     * 查询状态为初始状态并且超时的消息
     * @return
     */
    List<BrokerMessageLog> selectInitStatusAndTimeoutMessage();

    /**
     * 根据消息Id更新重试次数
     * @param messageId
     * @return
     */
    int updateTryCountByMessageId(String messageId);
}