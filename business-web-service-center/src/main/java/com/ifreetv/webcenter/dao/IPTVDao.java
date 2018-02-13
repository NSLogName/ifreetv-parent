package com.ifreetv.webcenter.dao;

import com.ifreetv.webcenter.dao.base.BaseDao;
import com.ifreetv.webcenter.dao.mapper.ProgramMapper;
import com.ifreetv.webcenter.entity.ChannelContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*******************************
 * @Title IPTVDao
 * @package com.ifreetv.webcenter.dao
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:24
 * @version
 ********************************/
@Repository
public class IPTVDao extends BaseDao{
    @Autowired
    ProgramMapper programMapper;

    public List<String> selectClassifyList(String parseDate) {
        return programMapper.selectClassifyList(parseDate);
    }

    public List<String>  selectChannelList(String parseDate, String classifyName) {
        return programMapper.selectChannelList(parseDate, classifyName);
    }

    public ChannelContent  selcetChannelContent(String parseDate, String channelName) {
        return programMapper.selectChannelContent(parseDate, channelName);
    }
}
