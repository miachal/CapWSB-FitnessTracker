package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    /**
     * Maps a user to a DTO.
     *
     * @param user user to map
     * @return DTO containing user data
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Maps a user DTO to an entity.
     *
     * @param userDto DTO containing user data
     * @return entity
     */
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    /**
     * Maps a user DTO to an updated entity.
     *
     * @param id      id of the user to update
     * @param userDto DTO containing updated user data
     * @return updated entity
     */
    User toUpdatedEntity(long id, UserDto userDto) {
        return new User(id,
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    /**
     * Maps a user to a simplified DTO containing only the user's first, last name and id.
     *
     * @param user user to map
     * @return simplified DTO containing only the user's first, last name and id
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                                 user.getFirstName(),
                                 user.getLastName());
    }

    /**
     * Maps a user to a simplified DTO containing only the user's email and id.
     *
     * @param user user to map
     * @return simplified DTO containing only the user's email and id
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(),
                                user.getEmail());
    }
}
