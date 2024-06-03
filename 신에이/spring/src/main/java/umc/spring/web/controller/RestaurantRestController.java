package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Restaurant;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<RestaurantResponseDTO.joinResultDTO> join(@RequestBody RestaurantRequestDTO.joinDTO request,
                                                                 @PathVariable(name = "regionId") Long regionId) {

        Restaurant restaurant = restaurantCommandService.joinRestaurant(request, regionId);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }
}
