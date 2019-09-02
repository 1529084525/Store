package interfaces.brand;

import org.apache.ibatis.annotations.Param;
import pojo.Brand;

import java.util.List;

public interface BrandInterface {
    /**
     * 查询所有品牌
     *
     * @return
     */
    List<Brand> selectAll();

    /**
     * 根据brandId查询品牌信息
     *
     * @param brandId
     * @return
     */
    Brand selectById(int brandId);

    /**
     * 添加品牌信息
     *
     * @param brand
     * @return
     */
    int insertBrand(Brand brand);

    /**
     * 根据brandId删除品牌信息
     *
     * @param brandId
     * @return
     */
    int deleteById(int brandId);

    /**
     * 修改品牌信息
     *
     * @param brand
     * @return
     */
    int updateById(Brand brand);

    /**
     * 倒序查询
     * @return
     */
    List<Brand> selectAllByDesc();

}
