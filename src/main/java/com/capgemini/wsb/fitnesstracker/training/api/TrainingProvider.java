package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface TrainingProvider {
    Optional<Training> getTraining(Long trainingId);
    List<Training> findAllTrainings();
    List<Training> findAllTrainingsByUserId(Long userId);
    List<Training> findTrainingsAfter(LocalDate date);
    List<Training> findTrainingsByActivityType(ActivityType type);
    Training createTraining(Training training);
    Training updateTraining(Training training);

}
