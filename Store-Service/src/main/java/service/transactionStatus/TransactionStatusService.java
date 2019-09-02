package service.transactionStatus;

import interfaces.transactionStatus.TransactionStatusInterface;
import mapper.transactionStatus.TransactionStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TransactionStatusService implements TransactionStatusInterface {

    @Autowired
    private TransactionStatusMapper transactionStatusMapper;


}
