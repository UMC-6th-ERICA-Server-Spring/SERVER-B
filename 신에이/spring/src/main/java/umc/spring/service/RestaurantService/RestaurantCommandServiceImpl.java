package umc.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.repository.*;
import umc.spring.web.dto.RestaurantRequestDTO;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final FoodCategoryRepository foodCategoryRepository;

    private final RegionRepository regionRepository;

    private final RestaurantRepository restaurantRepository;

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Restaurant joinRestaurant(RestaurantRequestDTO.joinDTO request, Long regionId) {

        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);

        newRestaurant.setFoodCategory(foodCategoryRepository.findById(request.getFoodCategoryId()).
                orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)));
        newRestaurant.setRegion(regionRepository.findById(regionId).
                orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)));

        return restaurantRepository.save(newRestaurant);
    }

    @Override
    @Transactional
    public Review createReview(RestaurantRequestDTO.createReviewDTO request, Long restaurantId, Long memberId) {

        Review newReview = RestaurantConverter.toReview(request);

        newReview.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)));
        newReview.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND)));

        return reviewRepository.save(newReview);
    }


}
