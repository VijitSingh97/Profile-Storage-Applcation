package com.uta.vijit.singh.cse6331.profilestorage.domain;

import lombok.Data;
import lombok.NonNull;
@Data
public class UploadProfileResponse {
    @NonNull private final Boolean success;
    @NonNull private final String message;
}
