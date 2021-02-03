package com.uta.vijit.singh.cse6331.profilestorage.persistence;


import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;

import java.util.List;
import java.util.Map;

public interface ProfilePersistence {
    public List<Profile> getProfiles();

    public List<Profile> addProfiles(List<Profile> profiles);

    public List<Profile> updateProfiles(List<Profile> profiles);

    public List<Profile> updateProfilePictures(Map<String, byte[]> profilePictures);
}
