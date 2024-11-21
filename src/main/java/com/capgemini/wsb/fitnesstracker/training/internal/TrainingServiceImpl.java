package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;
    private final TrainingMapper trainingMapper;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findAllTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> findTrainingsAfter(LocalDate date) {
        return trainingRepository.findByEndTimeAfter(date.atStartOfDay());
    }

    @Override
    public List<Training> findTrainingsByActivityType(ActivityType type) {
        return trainingRepository.findByActivityType(type);
    }

    @Override
    public Training createTraining(Training training) {
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID");
        }

        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Training training) {
        if (training.getId() == null) {
            throw new IllegalArgumentException("Missing training with this ID");
        }
        return trainingRepository.save(training);
    }

}
