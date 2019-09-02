package service.transactionWay;

import mapper.transactionWay.TransactionWayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class TransactionWayService implements TransactionWayMapper {

    @Autowired
    private TransactionWayMapper transactionWayMapper;
}
