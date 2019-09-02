package service.merchandiseDetails;

import customPojo.Merchandise_Details;
import interfaces.merchandiseDetails.MerchandiseDetailsInterface;
import mapper.merchandiseDetails.MerchandiseDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.MerchandiseDetails;

@Component
public class MerchandiseDetailsService implements MerchandiseDetailsInterface {

    @Autowired
    private MerchandiseDetailsMapper merchandiseDetailsMapper;

    @Override
    public int insertInfo(MerchandiseDetails merchandiseDetails) {
        return merchandiseDetailsMapper.insertInfo(merchandiseDetails);
    }

    @Override
    public Merchandise_Details selectMerchandise_DetailsById(String merchandiseId) {
        return merchandiseDetailsMapper.selectMerchandise_DetailsById(merchandiseId);
    }
}
