package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.service.RestaurantService.RestaurantQueryService;
import umc.spring.validation.annotation.ExistRestaurants;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;
    private final RestaurantQueryService restaurantQueryService;

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

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들을 조회하는 API, 페이징 포함. query string으로 page 번호 입력")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "restaurant의 Id, path variable입니다."),
            @Parameter(name = "page", description = "page번호, 0번이 1페이지")
    })
    public ApiResponse<RestaurantResponseDTO.ReviewPreviewListDTO> getReviewList(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                                 @RequestParam(name = "page") Integer page) {
        RestaurantResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO = restaurantQueryService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(reviewPreviewListDTO);
    }

}
