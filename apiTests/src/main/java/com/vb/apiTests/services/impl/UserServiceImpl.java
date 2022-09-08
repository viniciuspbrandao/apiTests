package com.vb.apiTests.services.impl;

import com.vb.apiTests.domain.Users;
import com.vb.apiTests.domain.dto.UserDTO;
import com.vb.apiTests.repositories.UserRepository;
import com.vb.apiTests.services.UsersService;
import com.vb.apiTests.services.exceptions.DataIntegratyViolationException;
import com.vb.apiTests.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Object Not Found."));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    @Override
    public Users update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj,Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO obj){
        Optional<Users> user = repository.findByEmail(obj.getEmail());
                if(user.isPresent() && !user.get().getId().equals(obj.getId())){
                    throw new DataIntegratyViolationException("Email already registered in the system.");
                }
    }
}
