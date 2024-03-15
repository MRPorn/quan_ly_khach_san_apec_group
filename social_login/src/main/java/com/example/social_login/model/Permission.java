package com.example.social_login.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER_READ("user::read"),

    ADMIN_READ("admin::read"),
    ADMIN_CREATE("admin::create"),
    ADMIN_UPDATE("admin::update")
    ;
    private final String permission;

}
