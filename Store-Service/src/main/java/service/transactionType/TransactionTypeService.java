package service.transactionType;


import interfaces.transactionType.TransactionTypeInterface;
import mapper.transactionType.TransactionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TransactionTypeService implements TransactionTypeInterface {

    @Autowired
    private TransactionTypeMapper transactionTypeMapper;
}
