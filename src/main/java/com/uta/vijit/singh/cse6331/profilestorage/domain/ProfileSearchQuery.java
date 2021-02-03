package com.uta.vijit.singh.cse6331.profilestorage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileSearchQuery {
    private String attribute;
    private String operation;
    private int threshold;
    private String contains;
}
