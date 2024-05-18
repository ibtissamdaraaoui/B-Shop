package ma.xproce.bshop.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import ma.xproce.bshop.dao.entities.URole;
import ma.xproce.bshop.dao.entities.UserM;
import ma.xproce.bshop.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private static List<UserM> users = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        if (userRepository.count() == 0) {  // Check if the admin user already exists
            UserM user = new UserM();
            user.setRole(URole.ADMIN);
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("abc"));
            userRepository.save(user);
        }
    }

    public void register(UserM user) {
        user.setRole(URole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserM findByLogin(String login) {
        return userRepository.findByUsername(login).get();
        /*return users.stream().filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElse(null);*/
    }


}