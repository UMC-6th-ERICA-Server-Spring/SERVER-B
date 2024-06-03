package umc.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Restaurant;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.RestaurantRepository;
import umc.spring.web.dto.RestaurantRequestDTO;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final FoodCategoryRepository foodCategoryRepository;

    private final RegionRepository regionRepository;

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant joinRestaurant(RestaurantRequestDTO.joinDTO request, Long regionId) {

        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);

        newRestaurant.setFoodCategory(foodCategoryRepository.findById(request.getFoodCategoryId()).
                orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)));
        newRestaurant.setRegion(regionRepository.findById(regionId).
                orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)));

        return restaurantRepository.save(newRestaurant);
    }
}
