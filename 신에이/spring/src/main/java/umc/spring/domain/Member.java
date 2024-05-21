package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String loginId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(length = 20)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private Gender gender;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false, length = 30)
    private String address;

    @Column(nullable = false, length = 30)
    private String specAddress;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @ColumnDefault("'ACTIVE'")
    private Status status;

    private LocalDateTime inactiveDate;

    @Column(nullable = false)
    private Boolean locationAgree;

    @Column(nullable = false)
    private Boolean marketingAgree;

    @Column(nullable = false)
    private Boolean notice1;

    @Column(nullable = false)
    private Boolean notice2;

    @Column(nullable = false)
    private Boolean notice3;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> preferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();



}
