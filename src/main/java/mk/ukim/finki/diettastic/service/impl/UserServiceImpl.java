package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.diettastic.model.exceptions.UsernameTakenException;
import mk.ukim.finki.diettastic.repository.UserRepository;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        return this.userRepository.findByUsernameAndPassword(username, password).or(()->Optional.of(new User()));
    }

    @Override
    public Optional<User> register(String username, String password, String name, String surname, String email, String avatarURL, String birthday, String role) {
        // we don't check if params are provided, because we require them to be provided
        // required in controller, and required in HTML
        // we check only for avatarURL, because it is not required
        if(avatarURL==null || avatarURL.isEmpty()){
            avatarURL = "";
        }

        // check if username exists first, no duplicate usernames allowed
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameTakenException();
        }

        return Optional.of(this.userRepository.save(new User(username, passwordEncoder.encode(password),
                email, name, surname, birthday, avatarURL)));
    }

    @Override
    public Optional<User> findById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        //if user is not present, the username is valid
        return user.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
