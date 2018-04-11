package com.ooad.InClassComp.service;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.ooad.InClassComp") 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserTest {

    @MockBean
    private UserDAO service;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByEmailTest() throws Exception {
        User user  = new User("userName", "password", 1, "user@mail.com", Boolean.TRUE);
        entityManager.persist(user);
    }
}