package com.edu.web.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class User implements Serializable {

    private static final Long serialVersionUID = 16L;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
