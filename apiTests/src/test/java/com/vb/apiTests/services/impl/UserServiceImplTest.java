package com.vb.apiTests.services.impl;

import com.vb.apiTests.domain.Users;
import com.vb.apiTests.domain.dto.UserDTO;
import com.vb.apiTests.repositories.UserRepository;
import com.vb.apiTests.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    private static final Integer ID = 1;
    private static final String NAME = "Vinicius";
    private static final String EMAIL = "vinicius@mail.com";
    private static final String PASSWORD = "123456";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUsers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);

        Users response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Object Not Found."));

        try {
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object Not Found.", ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {

    }

    private void startUsers(){
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}