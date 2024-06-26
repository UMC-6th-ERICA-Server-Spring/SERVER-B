package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class startMissionResultDTO {
        Long memberMissionId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class ReviewPreviewDTO {
        String username;
        String restaurantName;
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
