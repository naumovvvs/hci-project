package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.repository.DietRepository;
import mk.ukim.finki.diettastic.service.DietService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;

    public DietServiceImpl(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    @Override
    public List<Diet> getAllDiets() {
        return this.dietRepository.findAll();
    }

    @Override
    public Optional<Diet> getDietByName(String dietName) {
        return this.dietRepository.findByDietName(dietName);
    }
}
