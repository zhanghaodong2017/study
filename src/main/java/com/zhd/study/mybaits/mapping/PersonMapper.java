package com.zhd.study.mybaits.mapping;

import com.zhd.study.mybaits.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 14:18
 */
@Repository
public interface PersonMapper {

    UserInfo queryUserByGuid(String guid);

    UserInfo queryUserByName(String userName);
}
