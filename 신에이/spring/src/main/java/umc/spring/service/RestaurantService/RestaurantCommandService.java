package umc.spring.service.RestaurantService;

import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {

    public Restaurant joinRestaurant(RestaurantRequestDTO.joinDTO request, Long regionId);
    public Review createReview(RestaurantRequestDTO.createReviewDTO request, Long restaurantId, Long memberId);
    public Mission createMission(RestaurantRequestDTO.createMissionDTO request, Long restaurantId);
}
