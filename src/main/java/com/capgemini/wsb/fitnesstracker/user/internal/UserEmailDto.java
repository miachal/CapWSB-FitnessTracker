package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * Data transfer object representing a user's email.
 */
record UserEmailDto(@Nullable Long Id, String email) {
}
