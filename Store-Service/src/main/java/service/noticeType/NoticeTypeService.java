package service.noticeType;

import interfaces.noticeType.NoticeTypeInterface;
import mapper.noticeType.NoticeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.NoticeType;

import java.util.List;

@Component
public class NoticeTypeService implements NoticeTypeInterface {
    @Autowired
    private NoticeTypeMapper noticeTypeMapper;

    @Override
    public List<NoticeType> selectAll() {
        return noticeTypeMapper.selectAll();
    }
}
