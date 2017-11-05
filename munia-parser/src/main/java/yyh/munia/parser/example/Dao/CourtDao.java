package yyh.munia.parser.example.Dao;

import org.springframework.stereotype.Repository;
import yyh.munia.parser.parser.anno.FileIdentify;
import yyh.munia.parser.parser.inf.IDBWrite;
import yyh.munia.parser.parser.model.EfileType;

/**
 * Created by oak on 2017/11/5.
 */
@Repository("CourtDao")
@FileIdentify(EfileType = EfileType.COURT_INFO,suffix = {"xls","xlxs"})
public interface CourtDao extends IDBWrite
{
}
