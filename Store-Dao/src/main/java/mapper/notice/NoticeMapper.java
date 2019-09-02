package mapper.notice;

import pojo.Notice;

import java.util.List;

public interface NoticeMapper {
    /**
     * 添加公告
     * @param notice
     * @return
     */
    int insertNotice(Notice notice);

    /**
     * 查询所有公告
     * @return
     */
    List<Notice> selectAll();

    /**
     * 根据noticeId查询公告详情
     * @param noticeId
     * @return
     */
    Notice selectInfoById(int noticeId);

    /**
     * 查询总条数
     * @return
     */
    int selectCount();

    /**
     * 分页查询
     * @param start
     * @param count
     * @return
     */
    List<Notice> selectPage(Integer start,Integer count);

    /**
     * 查询最新的一条公告
     * @return
     */
    Notice selectNew();

    /**
     * 根据noticeId修改浏览量
     * @param noticeId
     * @return
     */
    int updateHotCount(int noticeId);
}
