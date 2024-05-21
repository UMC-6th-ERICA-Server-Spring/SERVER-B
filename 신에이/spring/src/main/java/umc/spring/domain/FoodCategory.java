package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String category;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<MemberPrefer> preferList = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<Restaurant> restaurantList = new ArrayList<>();


}
