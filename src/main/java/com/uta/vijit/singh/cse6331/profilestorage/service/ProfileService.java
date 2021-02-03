package com.uta.vijit.singh.cse6331.profilestorage.service;

import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileSearchQuery;
import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileServiceResponse;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    public ProfileServiceResponse getProfiles();

    public ProfileServiceResponse addProfiles(String profile_csv);

    public ProfileServiceResponse updateProfiles(String profile_csv);

    public ProfileServiceResponse updateProfilePicture(Map<String, byte[]> pictures);

    public ProfileServiceResponse queryProfiles(ProfileSearchQuery query);
}
