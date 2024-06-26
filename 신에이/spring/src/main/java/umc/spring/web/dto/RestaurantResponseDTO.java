package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Getter
    @Builder
    public static class ReviewPreviewDTO {
        String username;
        Integer grade;
        String content;
        LocalDate createdAt;
    }

    @Getter
    @Builder
    public static class ReviewPreviewListDTO {
        List<ReviewPreviewDTO> reviews;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
