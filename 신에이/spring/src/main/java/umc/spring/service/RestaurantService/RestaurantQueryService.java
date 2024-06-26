package umc.spring.service.RestaurantService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findRestaurantById(Long id);
    RestaurantResponseDTO.ReviewPreviewListDTO getReviewList(Long restaurantId, Integer page);
}
