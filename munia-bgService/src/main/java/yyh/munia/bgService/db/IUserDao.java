package yyh.munia.bgService.db;

import yyh.munia.bgService.model.UserInfo;

import java.util.List;

/**
 * Created by oak on 2017/8/28.
 */
public interface IUserDao
{

    List<UserInfo> queryUserInfos();

}
