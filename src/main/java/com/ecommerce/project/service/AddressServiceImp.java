package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUsers(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddress() {

        List<Address> addressList = addressRepository.findAll();
        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();


    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        return modelMapper.map(address,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddressByUser(User user) {
        List<Address> addressList = user.getAddresses();
        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {



        Address addressFromDB = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        addressFromDB.setCityName(addressDTO.getCityName());
        addressFromDB.setCountry(addressDTO.getCountry());
        addressFromDB.setStreet(addressDTO.getStreet());
        addressFromDB.setPinCode(addressDTO.getPinCode());
        addressFromDB.setBuildingName(addressDTO.getBuildingName());
        addressFromDB.setStateName(addressDTO.getStateName());

        Address updatedAddress = addressRepository.save(addressFromDB);

        User user = addressFromDB.getUsers();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);

        userRepository.save(user);
        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddressById(Long addressId) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        User user = address.getUsers();
        user.getAddresses().removeIf(addresses -> addresses.getAddressId().equals(addressId));
        userRepository.save(user);
        addressRepository.delete(address);
        return "Address deleted successfully with addressID: " + addressId;
    }


}
