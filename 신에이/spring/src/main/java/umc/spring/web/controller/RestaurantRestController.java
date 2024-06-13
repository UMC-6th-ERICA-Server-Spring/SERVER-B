package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<RestaurantResponseDTO.joinResultDTO> join(@RequestBody @Valid RestaurantRequestDTO.joinDTO request,
                                                                 @PathVariable Long regionId) {

        Restaurant restaurant = restaurantCommandService.joinRestaurant(request, regionId);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }

    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<RestaurantResponseDTO.createReviewResultDTO> createReview(@RequestBody @Valid RestaurantRequestDTO.createReviewDTO request,
                                                                                 @PathVariable Long restaurantId,
                                                                                 @RequestParam(name = "memberId") Long memberId) {

        Review review = restaurantCommandService.createReview(request, restaurantId, memberId);
        return ApiResponse.onSuccess(RestaurantConverter.toCreateReviewResultDTO(review));
    }

    @PostMapping("/{restaurantId}/missions")
    public ApiResponse<RestaurantResponseDTO.createMissionResultDTO> createMission(@RequestBody @Valid RestaurantRequestDTO.createMissionDTO request,
                                                                                   @PathVariable Long restaurantId) {

        Mission mission = restaurantCommandService.createMission(request, restaurantId);
        return ApiResponse.onSuccess(RestaurantConverter.toCreateMissionResultDTO(mission));
    }
}
