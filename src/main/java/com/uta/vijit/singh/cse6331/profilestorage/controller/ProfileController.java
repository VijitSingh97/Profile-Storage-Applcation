package com.uta.vijit.singh.cse6331.profilestorage.api.controller;

import com.uta.vijit.singh.cse6331.profilestorage.domain.Profile;
import com.uta.vijit.singh.cse6331.profilestorage.domain.UploadProfileResponse;
import com.uta.vijit.singh.cse6331.profilestorage.service.impl.ProfileServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api}")
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @RequestMapping(method = RequestMethod.POST, consumes = "text/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UploadProfileResponse> updateProfiles(@RequestBody final String profile_csv) {
        UploadProfileResponse response = profileService.addProfiles(profile_csv);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> getProfiles() {
        return new ResponseEntity<>(profileService.getProfiles(), HttpStatus.OK);
    }
}
