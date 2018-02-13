package com.ifreetv.webcenter.dao.mapper;

import com.ifreetv.webcenter.dao.entity.Program;
import com.ifreetv.webcenter.dao.entity.ProgramWithBLOBs;
import com.ifreetv.webcenter.dao.mapper.i.IMapper;
import com.ifreetv.webcenter.entity.ChannelContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramMapper extends IMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(ProgramWithBLOBs record);

    int insertSelective(ProgramWithBLOBs record);

    ProgramWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProgramWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProgramWithBLOBs record);

    int updateByPrimaryKey(Program record);

    List<String> selectClassifyList(@Param("parseDate") String parseDate);

    List<String> selectChannelList(@Param("parseDate") String parseDate, @Param("classifyName") String classifyName);

    ChannelContent selectChannelContent(@Param("parseDate") String parseDate, @Param("channelName") String channelName);
}