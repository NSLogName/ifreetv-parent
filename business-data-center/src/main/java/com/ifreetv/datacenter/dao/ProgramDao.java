package com.ifreetv.datacenter.dao;

import com.ifreetv.datacenter.dao.mapper.ProgramMapper;
import com.ifreetv.datacenter.entity.IPTVChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*******************************
 * @Title ProgramDao
 * @package com.ifreetv.datacenter.dao
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 15:29
 * @version
 ********************************/
@Repository
public class ProgramDao {
    @Autowired
    ProgramMapper programMapper;

    public IPTVChannel selectByDateAndClassifyNameAndChannelName(String parseDate, String classifyName, String channelName) {
        return programMapper.selectByDateAndClassifyNameAndChannelName(parseDate, classifyName, channelName);
    }

    public boolean insertSelectiveOfIPTVChannel(IPTVChannel iptvChannel) {
        return programMapper.insertSelectiveOfIPTVChannel(iptvChannel) > 0;
    }

    public boolean updateByIPTVChannel(IPTVChannel iptvChannel) {
        return programMapper.updateByIPTVChannel(iptvChannel) > 0;
    }

}
