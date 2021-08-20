package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.Goal;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.repository.DietRepository;
import mk.ukim.finki.diettastic.repository.UserRepository;
import mk.ukim.finki.diettastic.service.DietService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;
    private final UserRepository userRepository;

    public DietServiceImpl(DietRepository dietRepository, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Diet> getAllDiets() {
        return this.dietRepository.findAll();
    }

    @Override
    public Optional<Diet> getDietByName(String dietName) {
        return this.dietRepository.findByDietName(dietName);
    }

    @Override
    public void changeDiet(String dietName) {
        User user =  userRepository.findByUsername("test").get();
        Diet diet = dietRepository.findByDietName(dietName).get();

        user.setDiet(diet);

        this.userRepository.save(user);
    }

    @Override
    public Diet calculateDietForUser(String username) {
        User user = this.userRepository.findByUsername(username).get();

        List<String> goals = user.getPersonalGoals().stream().map(Goal::getGoalName).collect(Collectors.toList());

        if(goals.contains("Be healthier")) {
            return this.dietRepository.findByDietName("Dukan diet").get();
        } else if(goals.contains("Lose weight")) {
            return this.dietRepository.findByDietName("Low-carb diet").get();
        } else {
            return this.dietRepository.findByDietName("Atkins diet").get();
        }
    }
}
