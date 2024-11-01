package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * Data transfer object representing a user's first and last name.
 */
record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {

}
