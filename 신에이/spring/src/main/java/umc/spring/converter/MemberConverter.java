package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request) {

        Gender gender = null;

        switch (request.getGender()) {

            case 1:
                gender = Gender.MALE;
                break;

            case 2:
                gender = Gender.FEMALE;
                break;
        }

        LocalDate birth = LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay());

        return Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .name(request.getName())
                .username(request.getUsername())
                .gender(gender)
                .birth(birth)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .locationAgree(request.getLocationAgree())
                .marketingAgree(request.getMarketingAgree())
                .notice1(request.getNotice1())
                .notice2(request.getNotice2())
                .notice3(request.getNotice3())
                .inquiryList(new ArrayList<>())
                .memberPreferList(new ArrayList<>())
                .missionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.startMissionResultDTO toGetMissionResultDTO(MemberMission memberMission) {
        return MemberResponseDTO.startMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.IN_PROGRESS)
                .build();
    }

    public static MemberResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return MemberResponseDTO.ReviewPreviewDTO.builder()
                .restaurantName(review.getRestaurant().getName())
                .username(review.getMember().getUsername())
                .grade(review.getGrade())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        List<MemberResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewPage.stream()
                .map(MemberConverter::toReviewPreviewDTO).collect(Collectors.toList());
        return MemberResponseDTO.ReviewPreviewListDTO.builder()
                .reviews(reviewPreviewDTOList)
                .listSize(reviewPreviewDTOList.size())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalElements(reviewPage.getTotalElements())
                .totalPage(reviewPage.getTotalPages())
                .build();
    }
}
