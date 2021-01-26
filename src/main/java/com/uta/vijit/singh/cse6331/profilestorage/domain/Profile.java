package com.uta.vijit.singh.cse6331.profilestorage.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Profile {
    private String name;
    private int salary;
    private String room;
    private String telnum;
    private String picture;
    private List<String> keywords;
}
