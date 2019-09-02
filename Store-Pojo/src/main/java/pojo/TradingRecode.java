package pojo;

import lombok.Data;

/**
 * 交易记录
 */
@Data
public class TradingRecode {

    private Integer tradingRecodeId;

    private String userPhone;

    private String tradingRecodeTime;

    private Double tradingRecodeMoney;

    private String tradingRecodeComment;

    private Integer typeId;

    private Integer statusId;

    private Integer wayId;




}
