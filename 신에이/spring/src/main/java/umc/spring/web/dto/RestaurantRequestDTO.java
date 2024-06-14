package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public class RestaurantRequestDTO {

    @Getter
    public static class joinDTO {
        @NotBlank
        String name;
        @NotNull
        Long foodCategoryId;
        @Min(0)
        @Max(23)
        Integer openHour;
        @Min(0)
        @Max(59)
        Integer openMinute;
        @Min(0)
        @Max(23)
        Integer closeHour;
        @Min(0)
        @Max(59)
        Integer closeMinute;
    }

    @Getter
    public static class createReviewDTO {
        @NotNull
        @Max(5)
        @Min(1)
        Integer grade;
        @Size(max = 200)
        String content;
        @Size(max = 30)
        String image1;
        @Size(max = 30)
        String image2;
        @Size(max = 30)
        String image3;
    }

    @Getter
    public static class createMissionDTO {
        @NotNull
        LocalDate deadLine;

        @NotNull
        @Min(0)
        Integer reward;

        @NotBlank
        @Size(max = 50)
        String content;
    }
}
