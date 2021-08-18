package mk.ukim.finki.diettastic.service;

import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> login(String username, String password);
    Optional<User> register(String username, String password, String email, String name, String surname,
                            String birthday, String file, Integer age, Integer height, Integer weight, DietType dietType,
                            Role role, List<String> goals);
    Optional<User> findById(Long userId);
    boolean checkIfUsernameExists(String username);
    Optional<User> findByUsername(String username);

    @Override
    default UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
