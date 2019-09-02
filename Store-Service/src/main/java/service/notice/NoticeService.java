package service.notice;

import interfaces.notice.NoticeInterface;
import mapper.notice.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Notice;

import java.util.List;

@Component
public class NoticeService implements NoticeInterface {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int insertNotice(Notice notice) {
         return noticeMapper.insertNotice(notice);
    }

    @Override
    public List<Notice> selectAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public Notice selectInfoById(int noticeId) {
        return noticeMapper.selectInfoById(noticeId);
    }

    @Override
    public int selectCount() {
        return noticeMapper.selectCount();
    }

    @Override
    public List<Notice> selectPage(Integer start, Integer count) {
        start=(start-1)*20;//-1*要展示的数量
        return noticeMapper.selectPage(start,count);
    }

    @Override
    public Notice selectNew() {
        return noticeMapper.selectNew();
    }

    @Override
    public int updateHotCount(int noticeId) {
        return noticeMapper.updateHotCount(noticeId);
    }


}
