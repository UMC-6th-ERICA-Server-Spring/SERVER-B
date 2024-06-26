package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantConverter {

    public static RestaurantResponseDTO.joinResultDTO toJoinResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.joinResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.joinDTO request) {
        LocalTime openTime = LocalTime.of(request.getOpenHour(), request.getOpenMinute());
        LocalTime closeTime = LocalTime.of(request.getCloseHour(), request.getCloseMinute());
        return Restaurant.builder()
                .name(request.getName())
                .missionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .openTime(openTime)
                .closeTime(closeTime)
                .build();
    }

    public static RestaurantResponseDTO.createReviewResultDTO toCreateReviewResultDTO(Review review) {
        return RestaurantResponseDTO.createReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(RestaurantRequestDTO.createReviewDTO request) {
        return Review.builder()
                .grade(request.getGrade())
                .content(request.getContent())
                .image1(request.getImage1())
                .image2(request.getImage2())
                .image3(request.getImage3())
                .build();
    }

    public static RestaurantResponseDTO.createMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return RestaurantResponseDTO.createMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(RestaurantRequestDTO.createMissionDTO request) {
        return Mission.builder()
                .deadline(request.getDeadLine())
                .reward(request.getReward())
                .content(request.getContent())
                .build();
    }
}
