package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Restaurant;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
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
        return Restaurant.builder()
                .name(request.getName())
                .missionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .build();
    }
}
