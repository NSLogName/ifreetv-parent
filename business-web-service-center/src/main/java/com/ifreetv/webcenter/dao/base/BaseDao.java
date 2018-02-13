package com.ifreetv.webcenter.dao.base;

import java.util.List;

/*******************************
 * @Title BaseDao
 * @package com.ifreetv.webcenter.dao.base
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:23
 * @version
 ********************************/
public class BaseDao {
    /**
     * 将list转为In的查询方式
     *
     * @param ids
     * @return
     */
    public String listToInQuery(List<Integer> ids) {
        StringBuffer sb = new StringBuffer();
        for (Integer id : ids) {
            sb.append(id).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
