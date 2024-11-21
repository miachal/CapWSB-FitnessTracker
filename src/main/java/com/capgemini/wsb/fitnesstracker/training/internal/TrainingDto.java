package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;

import java.util.Date;

record TrainingDto(@Nullable Long Id, User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {}
