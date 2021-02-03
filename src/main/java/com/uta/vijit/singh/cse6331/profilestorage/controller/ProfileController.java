package com.uta.vijit.singh.cse6331.profilestorage.controller;

import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileSearchQuery;
import com.uta.vijit.singh.cse6331.profilestorage.domain.ProfileServiceResponse;
import com.uta.vijit.singh.cse6331.profilestorage.service.impl.ProfileServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @RequestMapping(value = "${api.profiles}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileServiceResponse> getProfiles() {
        return new ResponseEntity<>(profileService.getProfiles(), HttpStatus.OK);
    }

    @RequestMapping(value = "${api.profiles}", method = RequestMethod.POST, consumes = "text/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileServiceResponse> addProfiles(@RequestBody final String profile_csv) {
        ProfileServiceResponse response = profileService.addProfiles(profile_csv);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "${api.profiles}", method = RequestMethod.PUT, consumes = "text/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileServiceResponse> updateProfiles(@RequestBody final String profile_csv) {
        ProfileServiceResponse response = profileService.updateProfiles(profile_csv);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "${api.profilePictures}", method = RequestMethod.POST, consumes = "text/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileServiceResponse> updateProfilePictures(@RequestBody final Map<String, byte[]> pictures) {
        ProfileServiceResponse response = profileService.updateProfilePicture(pictures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "${api.queryProfiles}", method = RequestMethod.POST, consumes = "text/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileServiceResponse> queryProfiles(@RequestBody final ProfileSearchQuery query) {
        ProfileServiceResponse response = profileService.queryProfiles(query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
