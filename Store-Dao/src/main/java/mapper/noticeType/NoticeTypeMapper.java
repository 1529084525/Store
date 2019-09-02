package mapper.noticeType;

import pojo.NoticeType;

import java.util.List;

public interface NoticeTypeMapper {

    /**
     * 查询所有公告类型
     * @return
     */
    List<NoticeType> selectAll();
}
