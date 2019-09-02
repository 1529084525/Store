package mapper.carousel;

import org.apache.ibatis.annotations.Mapper;
import pojo.Carousel;

import java.util.List;

@Mapper
public interface CarouselMapper {

    /**
     * 查询所有轮播信息
     *
     * @return
     */
    List<Carousel> selectAll();

    /**
     * 根据carouselId查询信息
     *
     * @param carouselId
     * @return
     */
    Carousel selectById(int carouselId);

    /**
     * 添加轮播信息
     *
     * @param carousel
     * @return
     */
    int insertCarousel(Carousel carousel);

    /**
     * 根据carouselId删除信息
     *
     * @param carouselId
     * @return
     */
    int deleteById(int carouselId);

    /**
     * 根据carouselId修改信息
     *
     * @param carousel
     * @return
     */
    int updateById(Carousel carousel);




    //根据状态查询所有
    List<Carousel> selectAllCarouselStatus();


}
