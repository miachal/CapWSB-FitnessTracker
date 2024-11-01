package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user user to create
     * @return created user
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Finds a user by ID.
     *
     * @param userId ID of the user to find
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Finds a user by email.
     *
     * @param email email of the user to find
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds all users.
     *
     * @return list of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user.
     *
     * @param userId ID of the user to delete
     */
    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Finds all users with an email similar to the given fragment.
     * This method is case-insensitive.
     *
     * @param emailFragment email fragment to search for
     * @return list of users with similar email
     */
    @Override
    public List<User> findAllUsersByEmailCorrelation(String emailFragment) {
        return userRepository.findAll().stream()
                .filter(user -> isEmailSimilar(user.getEmail(), emailFragment))
                .toList();
    }

    /**
     * Finds all users older than a given age.
     *
     * @param age age to compare
     * @return list of users older than the given age
     */
    @Override
    public List<User> findAllUsersOlderThan(int age) {
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(LocalDate.now().minusYears(age)))
                .toList();
    }

    /**
     * Updates a user.
     *
     * @param user user to update
     * @return updated user
     */
    @Override
    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User has no DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Checks if two emails are similar.
     * This method is case-insensitive.
     *
     * @param email1 first email
     * @param email2 second email
     * @return true if emails are similar, false otherwise
     */
    private boolean isEmailSimilar(String email1, String email2) {
        email1 = email1.toLowerCase();
        email2 = email2.toLowerCase();
        return email1.contains(email2) || email2.contains(email1);
    }
}