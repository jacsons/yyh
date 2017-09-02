package yyh.munia.bgService.db;

import org.springframework.stereotype.Repository;
import yyh.munia.bgService.model.UserInfo;

import java.util.List;

/**
 * Created by oak on 2017/8/28.
 */
@Repository("IUserDao")
public interface IUserDao
{
    List<UserInfo> queryUserInfos();






}
