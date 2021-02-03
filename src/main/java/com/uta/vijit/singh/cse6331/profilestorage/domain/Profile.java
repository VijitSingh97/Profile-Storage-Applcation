package com.uta.vijit.singh.cse6331.profilestorage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Profile {
    private String name;
    private Integer grade;
    private Integer room;
    private String state;
    private Picture picture;
    private List<String> keywords;
}
