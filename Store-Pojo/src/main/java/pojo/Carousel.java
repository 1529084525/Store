package pojo;

import lombok.Data;

/**
 * 轮播图
 */

@Data
public class Carousel {

    private Integer carouselId;//轮播图id

    private String carouselImage;//轮播图片

    private String carouselUrl;//轮播图地址

    private int carouselStatus;//轮播状态

    private int carouselIndex;//轮播图下标
}
