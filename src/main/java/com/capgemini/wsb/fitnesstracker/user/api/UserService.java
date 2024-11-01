package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user user to create
     * @return created user
     */

    User createUser(User user);
    /**
     * Deletes a user.
     *
     * @param userId ID of the user to delete
     */

    void deleteUser(Long userId);
    /**
     * Updates a user.
     *
     * @param user user to update
     * @return updated user
     */

    User updateUser(User user);
}
