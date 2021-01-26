package com.uta.vijit.singh.cse6331.profilestorage.service.impl;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.domain.UploadProfileResponse;
import com.uta.vijit.singh.cse6331.profilestorage.persistence.ProfilePersistence;
import com.uta.vijit.singh.cse6331.profilestorage.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfilePersistence profilePersistence;

    @Override
    public UploadProfileResponse addProfiles(String profile_csv) {
        List<Profile> profiles = csvToProfileArray(profile_csv);
        return buildAddProfileResponse(profilePersistence.addProfiles(profiles));
    }

    @Override
    public List<Profile> getProfiles() {
        return profilePersistence.getProfiles();
    }

    private static List<Profile> csvToProfileArray(String csv_profiles) {
        List<Profile> profiles = new ArrayList<>();
        List<String> csv_profile = Arrays.asList(csv_profiles.split("\n"));
        csv_profile.forEach((p) -> {
            String[] profileAttr = p.split(",");
            profiles.add(new Profile(profileAttr[0], nullableStringToInt(profileAttr[1]), profileAttr[2],
                    profileAttr[3], profileAttr[4], Arrays.asList(profileAttr[5].split(" "))));
        });
        return profiles;
    }

    private static int nullableStringToInt(String str) {
        if (str.isEmpty()) {
            return 0;
        } else return Integer.parseInt(str);
    }

    private static UploadProfileResponse buildAddProfileResponse(int added) {
        // negative upload count is an error
        if (added < 0) {
            return new UploadProfileResponse(false, "Error uploading profiles, please confirm you are sending a valid list of profiles.");
        // one profile being added has different grammar
        } else if (added == 1) {
            return new UploadProfileResponse(true, "1 profile updated.");
        // confirm with user how many profiles they uploaded in response
        } else {
            return new UploadProfileResponse(true, added + " profiles updated.");
        }

    }

}