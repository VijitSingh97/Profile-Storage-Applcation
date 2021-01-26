package com.uta.vijit.singh.cse6331.profilestorage.persistence.impl;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.persistence.ProfilePersistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProfilePersistenceImpl implements ProfilePersistence {

    private ConcurrentHashMap<String, Profile> profiles;

    public ProfilePersistenceImpl() {
        profiles = new ConcurrentHashMap<>();
    }

    @Override
    public int addProfiles(List<Profile> profiles) {
        AtomicInteger uploaded = new AtomicInteger();
        profiles.forEach((p) -> {
            this.profiles.put(p.getName(), p);
            uploaded.getAndIncrement();
        });
        return uploaded.get();
    }

    @Override
    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles.values());
    }
}
