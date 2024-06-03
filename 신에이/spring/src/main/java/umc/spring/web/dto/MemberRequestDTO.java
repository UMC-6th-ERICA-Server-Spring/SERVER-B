package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        String loginId;
        String password;
        String name;
        String username;
        Integer gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;
        Boolean locationAgree;
        Boolean marketingAgree;
        Boolean notice1;
        Boolean notice2;
        Boolean notice3;
        @ExistCategories
        List<Long> preferCategory;
    }
}
