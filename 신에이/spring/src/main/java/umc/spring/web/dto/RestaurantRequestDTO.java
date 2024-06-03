package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.FoodCategory;

public class RestaurantRequestDTO {

    @Getter
    public static class joinDTO {
        String name;
        Long foodCategoryId;
    }
}
