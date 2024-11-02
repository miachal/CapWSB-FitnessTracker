package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    User toUpdateEntity(UserDto userDto, User user) {
        Optional.ofNullable(userDto.firstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDto.lastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDto.birthdate()).ifPresent(user::setBirthdate);
        Optional.ofNullable(userDto.email()).ifPresent(user::setEmail);
        return user;
    }
}
