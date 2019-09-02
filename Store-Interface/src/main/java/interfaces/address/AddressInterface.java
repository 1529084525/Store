package interfaces.address;

import org.apache.ibatis.annotations.Param;
import pojo.Address;

import java.util.List;

public interface AddressInterface {
    /**
     * 根据addressId查询地址
     *
     * @param addressId
     * @return
     */
    Address selectById(int addressId);

    /**
     * 查询所有地址
     *
     * @return
     */
    List<Address> selectAll();

    /**
     * 添加地址
     *
     * @param address
     * @return
     */
    int insertAddress(Address address);

    /**
     * 根据addressId删除地址信息
     *
     * @param addressId
     * @return
     */
    int deleteById(int addressId);

    /**
     * 根据id修改地址信息
     *
     * @param address
     * @return
     */
    int updateById(Address address);

    /**
     * 根据手机号查询对应的地址
     *
     * @param phone
     * @return 根据默认地址排序
     */
    List<Address> selectAddressByPhone(String phone);

    /**
     * 根据手机号来恢复所有默认地址 (设置0
     *
     * @param phone
     * @return
     */
    int updateDefaultAddressByPhone(String phone);

    /**
     * 根据id查询单条信息
     *
     * @param id
     * @return
     */
    Address selectAddressById(int id);

    /**
     * 根据id修改
     * @param id
     * @return
     */
    int updateDefault(int id,String phone);

}
