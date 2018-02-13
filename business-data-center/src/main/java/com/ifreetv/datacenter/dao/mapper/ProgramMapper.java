package com.ifreetv.datacenter.dao.mapper;

import com.ifreetv.datacenter.dao.entity.Program;
import com.ifreetv.datacenter.dao.entity.ProgramWithBLOBs;
import com.ifreetv.datacenter.entity.IPTVChannel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

public interface ProgramMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProgramWithBLOBs record);

    int insertSelective(ProgramWithBLOBs record);

    ProgramWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProgramWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProgramWithBLOBs record);

    int updateByPrimaryKey(Program record);

    IPTVChannel selectByDateAndClassifyNameAndChannelName(@Param("parseDate")String parseDate, @Param("classifyName")String classifyName, @Param("channelName")String channelName);

    int insertSelectiveOfIPTVChannel(IPTVChannel iptvChannel);

    int updateByIPTVChannel(IPTVChannel record);
}