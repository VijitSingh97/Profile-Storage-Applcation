package com.uta.vijit.singh.cse6331.profilestorage.service.impl;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Picture;
import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileSearchQuery;
import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileServiceResponse;
import com.uta.vijit.singh.cse6331.profilestorage.persistence.ProfilePersistence;
import com.uta.vijit.singh.cse6331.profilestorage.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfilePersistence profilePersistence;

    @Override
    public ProfileServiceResponse getProfiles() {
        return new ProfileServiceResponse(true, null, profilePersistence.getProfiles());
    }

    @Override
    public ProfileServiceResponse addProfiles(String profilesCSV) {
        List<Profile> profiles = csvToProfileArray(profilesCSV);
        return new ProfileServiceResponse(true, null, profilePersistence.addProfiles(profiles));
    }

    @Override
    public ProfileServiceResponse updateProfiles(String profilesCSV) {
        List<Profile> profiles = csvToProfileArray(profilesCSV);
        return new ProfileServiceResponse(true, null, profilePersistence.updateProfiles(profiles));
    }


    @Override
    public ProfileServiceResponse updateProfilePicture(Map<String, byte[]> pictures) {
        return new ProfileServiceResponse(true, null, profilePersistence.updateProfilePictures(pictures));
    }

    @Override
    public ProfileServiceResponse queryProfiles(ProfileSearchQuery query) {
        Boolean res = true;
        String message = null;
        List<Profile> profilesQueried = profilePersistence.getProfiles();
        for(Profile profile : profilesQueried) {
            if (query.getAttribute() == "name") {
                if (!profile.getName().contains(query.getContains())) {
                    profilesQueried.remove(profile);
                }
            } else if (query.getAttribute() == "grade") {
                int grade = profile.getGrade();
                if (query.getOperation() == "greaterThan") {
                    if (grade <= query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "greaterThanEqualTo") {
                    if (grade < query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "lessThan") {
                    if (grade >= query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "lessThanEqualTo") {
                    if (grade > query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else {
                    res = false;
                    message = "Not a valid operation: " + query.getOperation();
                }
            } else if (query.getAttribute() == "room") {
                int room = profile.getRoom();
                if (query.getOperation() == "greaterThan") {
                    if (room <= query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "greaterThanEqualTo") {
                    if (room < query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "lessThan") {
                    if (room >= query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else if (query.getOperation() == "lessThanEqualTo") {
                    if (room > query.getThreshold()) {
                        profilesQueried.remove(profile);
                    }
                } else {
                    res = false;
                    message = "Not a valid operation: " + query.getOperation();
                }
            } else if (query.getAttribute() == "state") {
                if (!profile.getState().contains(query.getContains())) {
                    profilesQueried.remove(profile);
                }
            } else if (query.getAttribute() == "picture") {
                if (!profile.getPicture().getName().contains(query.getContains())) {
                    profilesQueried.remove(profile);
                }
            } else if (query.getAttribute() == "keywords") {
                if (!String.join("\n",profile.getKeywords()).contains(query.getContains())) {
                    profilesQueried.remove(profile);
                }
            } else {
                res = false;
                message = "Not a valid query: " + query.toString();
            }
        }
        return new ProfileServiceResponse(res, message, profilesQueried);
    }

    private static List<Profile> csvToProfileArray(String profilesCSV) {
        List<Profile> profiles = new ArrayList<>();
        List<String> profileCSVArray = Arrays.asList(profilesCSV.split("\n"));
        profileCSVArray.forEach((profileCSV) -> {
            String[] profileAttr = profileCSV.split(",", -1);
            System.out.println(profileCSV);
            String[] keywordArray;
            if (profileAttr.length == 6) {
                keywordArray = profileAttr[5].split(" ", -1);
            } else {
                keywordArray = new String[]{};
            }
            Profile p = new Profile(profileAttr[0], nullableStringToInt(profileAttr[1]), nullableStringToInt(profileAttr[2]),
                    profileAttr[3], new Picture(profileAttr[4], null), Arrays.asList(keywordArray));
            profiles.add(p);
        });
        return profiles;
    }

    private static int nullableStringToInt(String str) {
        try{
            return Integer.valueOf(str);
        } catch (Exception e) {
            return 0;
        }
    }

}
