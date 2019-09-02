package pojo;

import lombok.Data;

@Data
public class Notice {

    private int noticeId;//编号 自增

    private int noticeTypeId;//公告类型

    private String noticeTitle;//标题

    private String noticeTime;//发布时间

    private String noticeContent;//详情

    private int noticeHotCount;//浏览量

    private String noticeUser;//发布人

    private NoticeType noticeType;//公告类型
}
