package yyh.dbservice.com.Index.db.dao;

import yyh.dbservice.com.Index.db.model.UserInfo;

import java.util.List;

/**
 * Created by oak on 2017/8/2.
 */
public interface IUserDao
{

    /**
     * 获取用户信心
     * @return
     */
    List<UserInfo> queryUserInfos();


}
