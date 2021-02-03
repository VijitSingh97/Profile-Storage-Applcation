package com.uta.vijit.singh.cse6331.profilestorage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public final class ProfileServiceResponse {
    private Boolean success;
    private String message;
    private List<Profile> profiles;
}
