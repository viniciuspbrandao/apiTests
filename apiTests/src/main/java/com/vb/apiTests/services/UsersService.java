package com.vb.apiTests.services;

import com.vb.apiTests.domain.Users;
import com.vb.apiTests.domain.dto.UserDTO;

import java.util.List;

public interface UsersService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO obj);
    Users update(UserDTO obj);
    void delete(Integer id);
}
