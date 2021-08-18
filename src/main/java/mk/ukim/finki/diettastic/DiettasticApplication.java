package mk.ukim.finki.diettastic;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.Goal;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import mk.ukim.finki.diettastic.repository.DietRepository;
import mk.ukim.finki.diettastic.repository.GoalRepository;
import mk.ukim.finki.diettastic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DiettasticApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(DiettasticApplication.class, args);

        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
        GoalRepository goalRepository = configurableApplicationContext.getBean(GoalRepository.class);
        DietRepository dietRepository = configurableApplicationContext.getBean(DietRepository.class);

        Diet diet = new Diet("Keto Diet", "Description of Keto Diet", 69);
        dietRepository.save(diet);

        List<Goal> goals = new ArrayList<>();
        Goal goal = new Goal("Be healthier", "/");
        goals.add(goal);

        goalRepository.save(goal);

        User user = new User("test", "test", "test@mail.com", "John",
                "Smith", "2021-01-01", "/", 21, 172.0F, 80.0F,
                DietType.NORMAL, Role.ROLE_BASIC, goals);

        User nutritionist = new User("testN", "test", "testN@mail.com", "Alex",
                "James", "2021-02-03", "/", 25, 180.0F, 69.0F,
                DietType.NORMAL, Role.ROLE_NUTRITIONIST, goals);


        user.setDiet(diet);
        user.setNutritionist(nutritionist);

        userRepository.save(nutritionist);
        userRepository.save(user);


        //SpringApplication.run(DiettasticApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
