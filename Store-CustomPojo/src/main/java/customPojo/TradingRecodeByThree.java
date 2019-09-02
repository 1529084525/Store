package customPojo;

import lombok.Data;

/**
 * 用来连接查询多表的交易记录
 */
@Data
public class TradingRecodeByThree {
    private Integer tradingRecodeId;
    private String userPhone;
    private String tradingRecodeTime;
    private String tradingRecodeComment;
    private double tradingRecodeMoney;
    private String transactionStatusName;
    private Integer transactionStatusId;
    private String transactionTypeName;
    private String transactionWayName;
}
