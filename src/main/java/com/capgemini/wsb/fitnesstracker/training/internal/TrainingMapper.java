package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    Training toNewEntity(NewTrainingDto newTrainingDto, User user) {
        return new Training(
                user,
                newTrainingDto.startTime(),
                newTrainingDto.endTime(),
                newTrainingDto.activityType(),
                newTrainingDto.distance(),
                newTrainingDto.averageSpeed()
        );
    }

    Training toUpdateEntity(NewTrainingDto newTrainingDto, Training training) {
        Optional.ofNullable(newTrainingDto.startTime()).ifPresent(training::setStartTime);
        Optional.ofNullable(newTrainingDto.endTime()).ifPresent(training::setEndTime);
        Optional.ofNullable(newTrainingDto.activityType()).ifPresent(training::setActivityType);
        Optional.of(newTrainingDto.distance()).ifPresent(training::setDistance);
        Optional.of(newTrainingDto.averageSpeed()).ifPresent(training::setAverageSpeed);
        return training;
    }
}
