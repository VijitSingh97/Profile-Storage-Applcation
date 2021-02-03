package com.uta.vijit.singh.cse6331.profilestorage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Picture {
    private String name;
    private byte[] picture;
}
