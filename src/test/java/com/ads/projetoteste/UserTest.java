package com.ads.projetoteste;

import com.ads.projetoteste.domain.User;
import com.ads.projetoteste.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    public void testCreateUser() {
        User user = new User("Thaina Weingartner", "thainawchagas@gmail.com");
        User savedUser = userRepository.save(user);

        Assert.notNull(savedUser);
    }

    @Test
    @Rollback(false)
    public void testFindUserByName() {
        String name = "Thaina Weingartner";
        User user = userRepository.findByName(name);

        Assert.hasText(user.getName(), name);
    }

    @Test
    @Rollback(false)
    public void testFindUserByNameNotExist() {
        String name = "Kael Chagas";
        User user = userRepository.findByName(name);

        Assert.isNull(user);
    }

    @Test
    @Rollback(false)
    public void testUpdateUser() {
        String name = "Thaina Weingartner";
        User user = new User(name, "thainaweingartner@gmail.com");
        userRepository.save(user);

        User createdUser = userRepository.findByName(name);
        Integer userId = createdUser.getId();

        User newUser = new User("Thaina Weingartner Chagas", "thainawchagas@gmail.com");
        userRepository.update(userId, newUser);

        Optional<User> updatedUser = userRepository.findById(userId);

        assertThat(updatedUser).isEqualTo(newUser);
    }

    @Test
    public void testListUsers() {
        List<User> user = (List<User>) userRepository.findAll();

        assertThat(user).size().isGreaterThan(0);
    }
}
