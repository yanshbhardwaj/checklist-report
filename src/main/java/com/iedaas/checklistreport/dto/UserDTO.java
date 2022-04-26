package com.iedaas.checklistreport.dto;

import java.util.UUID;

public class UserDTO {
    private UUID userUid;
    private String userName;
    private String userImage;

    public UserDTO() {
    }

    public UserDTO(UUID userUid, String userName, String userImage) {
        this.userUid = userUid;
        this.userName = userName;
        this.userImage = userImage;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
