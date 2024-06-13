package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class RestaurantResponseDTO {

    @Getter
    @Builder
    public static class joinResultDTO {
        Long restaurantId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class createReviewResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class createMissionResultDTO {
        Long missionId;
        LocalDateTime createdAt;
    }
}
