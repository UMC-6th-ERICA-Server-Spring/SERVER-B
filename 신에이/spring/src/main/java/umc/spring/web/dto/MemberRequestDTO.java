package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        @NotBlank
        String loginId;
        @NotBlank
        String password;
        @NotBlank
        String name;
        String username;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @NotBlank
        String address;
        @NotBlank
        String specAddress;
        @NotNull
        Boolean locationAgree;
        @NotNull
        Boolean marketingAgree;
        @NotNull
        Boolean notice1;
        @NotNull
        Boolean notice2;
        @NotNull
        Boolean notice3;
        @ExistCategories
        List<Long> preferCategory;
    }

    @Getter
    public static class getMissionDTO {
        @NotNull
        Long missionId;
    }
}
