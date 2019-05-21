package com.ppwei.myhr.mapper;

import com.ppwei.myhr.bean.Hr;
import com.ppwei.myhr.bean.MsgContent;
import com.ppwei.myhr.bean.SysMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by sang on 2018/2/2.
 */
public interface SysMsgMapper {

    int sendMsg(MsgContent msg);

    int addMsg2AllHr(@Param("hrs") List<Hr> hrs, @Param("mid") Long mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size, @Param("hrid") Long hrid);

    int markRead(@Param("flag") Long flag, @Param("hrid") Long hrid);
}
