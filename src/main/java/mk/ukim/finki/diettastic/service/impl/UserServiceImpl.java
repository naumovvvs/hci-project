package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.Goal;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import mk.ukim.finki.diettastic.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.diettastic.repository.GoalRepository;
import mk.ukim.finki.diettastic.repository.UserRepository;
import mk.ukim.finki.diettastic.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, GoalRepository goalRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
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
    public Optional<User> register(String username, String password, String email, String name, String surname,
                                   String birthday, String file, Integer age, Integer height, Integer weight,
                                   DietType dietType, Role role, List<String> goals) {

        if (file==null || file.isEmpty()) {
            file="/";
        }

        List<Goal> goalObjects = goals.stream().map(x -> new Goal(x.toLowerCase(Locale.ROOT), "/"))
                .collect(Collectors.toList());

        goalObjects.forEach(this.goalRepository::save);

        return Optional.of(this.userRepository.save(new User(username, passwordEncoder.encode(password), email, name,
                surname, birthday, file, age, height.floatValue(), weight.floatValue(), dietType, role, goalObjects)));
    }

    @Override
    public Optional<User> findById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        // If user is not present, the username is valid
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

    @Override
    public void updateUser(User currentUser, Diet diet, Float weight, Float height) {
        if (weight > 0 && weight < 300) {
            currentUser.setWeight(weight);
        }

        if (height > 0 && height < 250) {
            currentUser.setHeight(height);
        }

        if (!currentUser.getDiet().getDietName().equals(diet.getDietName())) {
            currentUser.setDiet(diet);
        }

        this.userRepository.save(currentUser);
    }
}
