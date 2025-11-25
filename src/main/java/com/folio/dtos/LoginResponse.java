package com.folio.dtos;

public class LoginResponse {
    private boolean success;
    private String message;
    private String username;
    private Long id;

    public LoginResponse(boolean success, String message, String username, Long id) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.id = id;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
