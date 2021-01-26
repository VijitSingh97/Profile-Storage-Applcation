package com.uta.vijit.singh.cse6331.profilestorage.persistence;


import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;

import java.util.List;

public interface ProfilePersistence {
    public int addProfiles(List<Profile> profiles);

    public List<Profile> getProfiles();
}
