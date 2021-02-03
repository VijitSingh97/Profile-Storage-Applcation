package com.uta.vijit.singh.cse6331.profilestorage.persistence.impl;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Picture;
import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.persistence.ProfilePersistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

@Service
public class ProfilePersistenceImpl implements ProfilePersistence {

    private ConcurrentHashMap<String, Profile> profilesHashTable;

    public ProfilePersistenceImpl() {
        profilesHashTable = new ConcurrentHashMap<>();
    }

    @Override
    public List<Profile> getProfiles() {
        return new ArrayList<>(profilesHashTable.values());
    }

    @Override
    public List<Profile> addProfiles(List<Profile> profiles) {
        List<Profile> result = new ArrayList<>();
        profiles.forEach((profile) -> {
            this.profilesHashTable.putIfAbsent(profile.getName(), profile);
            result.add(profile);
        });
        return result;
    }

    @Override
    public List<Profile> updateProfiles(List<Profile> profiles) {
        List<Profile> result = new ArrayList<>();
        profiles.forEach((profile) -> {
            this.profilesHashTable.put(profile.getName(), profile);
            result.add(profile);
        });
        return result;
    }

    @Override
    public List<Profile> updateProfilePictures(Map<String, byte[]> profilePictures) {
        List<Profile> result = new ArrayList<>();
        profilePictures.forEach((name, image) -> {
            Profile profile = this.profilesHashTable.get(name);
            if (profile != null) {
                Picture picture = profile.getPicture();
                picture.setPicture(image);
                result.add(profile);
            }
        });
        return result;
    }
}
