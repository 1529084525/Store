package service.carousel;

import interfaces.carousel.CarouselInterface;
import mapper.carousel.CarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Carousel;

import java.util.List;

@Component
public class CarouselService implements CarouselInterface {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> selectAll() {
        return carouselMapper.selectAll();
    }

    @Override
    public Carousel selectById(int carouselId) {
        return carouselMapper.selectById(carouselId);
    }

    @Override
    public int insertCarousel(Carousel carousel) {
        return carouselMapper.insertCarousel(carousel);
    }

    @Override
    public int deleteById(int carouselId) {
        return carouselMapper.deleteById(carouselId);
    }

    @Override
    public int updateById(Carousel carousel) {
        return carouselMapper.updateById(carousel);
    }

    @Override
    public List<Carousel> selectAllCarouselStatus() {
        return carouselMapper.selectAllCarouselStatus();
    }
}
