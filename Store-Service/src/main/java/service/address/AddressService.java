package service.address;

import interfaces.address.AddressInterface;
import mapper.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Address;

import java.util.List;

@Component
public class AddressService implements AddressInterface {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address selectById(int addressId) {
        return addressMapper.selectById(addressId);
    }

    @Override
    public List<Address> selectAll() {
        return addressMapper.selectAll();
    }

    @Override
    public int insertAddress(Address address) {
        if (address.getDefaultAddress().equals("1"))
            addressMapper.updateDefaultAddressByPhone(address.getUserPhone());
        return addressMapper.insertAddress(address);
    }

    @Override
    public int deleteById(int addressId) {
        return addressMapper.deleteById(addressId);
    }

    @Override
    public int updateById(Address address) {
        return addressMapper.updateById(address);
    }

    @Override
    public List<Address> selectAddressByPhone(String phone) {
        return addressMapper.selectAddressByPhone(phone);
    }

    @Override
    public int updateDefaultAddressByPhone(String phone) {
        return addressMapper.updateDefaultAddressByPhone(phone);
    }

    @Override
    public Address selectAddressById(int id) {
        return addressMapper.selectAddressById(id);
    }

    @Override
    public int updateDefault(int id, String phone) {
        addressMapper.updateDefaultAddressByPhone(phone);
        return addressMapper.updateDefault(id);
    }
}
