package com.uta.vijit.singh.cse6331.profilestorage.service;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.domain.UploadProfileResponse;

import java.util.List;

public interface ProfileService {
    public UploadProfileResponse addProfiles(String profile_csv);

    public List<Profile> getProfiles();
}
