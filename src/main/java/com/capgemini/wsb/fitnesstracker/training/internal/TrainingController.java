package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

    /**
     * Pobiera listę wszystkich treningów.
     *
     * @return lista obiektów DTO reprezentujących wszystkie treningi
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                          .stream()
                          .map(trainingMapper::toDto)
                          .toList();
    }

    /**
     * Pobiera listę wszystkich treningów użytkownika o określonym ID.
     *
     * @param userId ID użytkownika, którego treningi mają zostać pobrane
     * @return lista obiektów DTO reprezentujących treningi użytkownika
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getAllTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findAllTrainingsByUserId(userId).stream().map(trainingMapper::toDto).toList();
    }

    /**
     * Pobiera listę treningów zakończonych po określonej dacie.
     *
     * @param afterTime data, po której zakończone treningi mają zostać pobrane
     * @return lista obiektów DTO reprezentujących treningi zakończone po podanej dacie
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsFinishedAfter(@PathVariable LocalDate afterTime) {
        return trainingService.findTrainingsAfter(afterTime).stream().map(trainingMapper::toDto).toList();
    }

    /**
     * Pobiera listę treningów na podstawie ich rodzaju aktywności.
     *
     * @param activityType typ aktywności, dla której treningi mają zostać pobrane
     * @return lista obiektów DTO reprezentujących treningi określonego rodzaju aktywności
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType).stream().map(trainingMapper::toDto).toList();
    }

    /**
     * Tworzy nowy trening.
     *
     * @param newTrainingDto obiekt DTO zawierający dane do utworzenia nowego treningu
     * @return obiekt reprezentujący nowo utworzony trening
     * @throws IllegalArgumentException jeśli użytkownik o podanym ID nie istnieje
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody NewTrainingDto newTrainingDto) {
        User user = userProvider.getUser(newTrainingDto.userId()).orElseThrow(() -> new IllegalArgumentException("User with ID: " + newTrainingDto.userId() + " not found"));
        return trainingService.createTraining(trainingMapper.toNewEntity(newTrainingDto, user));
    }

    /**
     * Aktualizuje istniejący trening.
     *
     * @param trainingId ID treningu, który ma zostać zaktualizowany
     * @param newTrainingDto obiekt DTO zawierający dane do aktualizacji treningu
     * @return zaktualizowany obiekt treningu
     * @throws IllegalArgumentException jeśli trening o podanym ID nie istnieje
     */
    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable Long trainingId, @RequestBody NewTrainingDto newTrainingDto) {
        Training training = trainingService.getTraining(trainingId).orElseThrow(() -> new IllegalArgumentException("Training with ID: " + trainingId + " not found"));
        Training updatedTraining = trainingMapper.toUpdateEntity(newTrainingDto, training);
        return trainingService.updateTraining(updatedTraining);
    }

}