package service.brand;

import interfaces.brand.BrandInterface;
import mapper.brand.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Brand;

import java.util.List;

@Component
public class BrandService implements BrandInterface {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand selectById(int brandId) {
        return brandMapper.selectById(brandId);
    }

    @Override
    public int insertBrand(Brand brand) {
        return brandMapper.insertBrand(brand);
    }

    @Override
    public int deleteById(int brandId) {
        return brandMapper.deleteById(brandId);
    }

    @Override
    public int updateById(Brand brand) {
        return brandMapper.updateById(brand);
    }

    @Override
    public List<Brand> selectAllByDesc() {
        return brandMapper.selectAllByDesc();
    }
}
