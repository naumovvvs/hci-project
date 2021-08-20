package mk.ukim.finki.diettastic.service;

import mk.ukim.finki.diettastic.model.Diet;

import java.util.List;
import java.util.Optional;

public interface DietService {
    List<Diet> getAllDiets();
    Optional<Diet> getDietByName(String dietName);
    void changeDiet(String dietName);
    Diet calculateDietForUser(String username);
}
