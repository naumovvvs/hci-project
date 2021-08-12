package mk.ukim.finki.diettastic.service;

import mk.ukim.finki.diettastic.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> login(String username, String password);
    Optional<User> register(String username, String password, String name, String surname, String email,
                            String avatarURL, String birthday, String role);
    Optional<User> findById(Long userId);
    boolean checkIfUsernameExists(String username);
    Optional<User> findByUsername(String username);

    @Override
    default UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
