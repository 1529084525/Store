package service.loveClothes;

import interfaces.loveClothes.LoveClothesInterface;
import mapper.loveClothes.LoveClothesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoveClothesServices implements LoveClothesInterface {
    @Autowired
    private LoveClothesMapper loveClothesMapper;

    @Override
    public int insertLove(String userPhone, String merchandiseId) {
        return loveClothesMapper.insertLove(userPhone, merchandiseId);
    }

    @Override
    public int deleteLoveById(String userPhone, String merchandiseId) {
        return loveClothesMapper.deleteLoveById(userPhone,merchandiseId);
    }

    @Override
    public Object selectLove(String userPhone, String merchandiseId) {
        return loveClothesMapper.selectLove(userPhone,merchandiseId);
    }
}
