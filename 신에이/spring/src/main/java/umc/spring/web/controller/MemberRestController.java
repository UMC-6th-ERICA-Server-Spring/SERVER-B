package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{memberId}/missions")
    public ApiResponse<MemberResponseDTO.startMissionResultDTO> startMission(@RequestBody @Valid MemberRequestDTO.getMissionDTO request,
                                                                             @PathVariable Long memberId) {
        MemberMission memberMission = memberCommandService.startMission(request, memberId);
        return ApiResponse.onSuccess(MemberConverter.toGetMissionResultDTO(memberMission));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "사용자가 작성한 리뷰 목록 조회", description = "사용자가 작성한 리뷰들을 조회하는 API. query string으로 page 번호 입력")
    @Parameters({
            @Parameter(name = "memberId", description = "memberId, path variable"),
            @Parameter(name = "page", description = "page, query string")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreviewListDTO> getReviewList(@PathVariable(name = "memberId") Long memberId,
                                                                             @RequestParam(name = "page") Integer page) {
        MemberResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO = memberQueryService.getReviewPreviewListDTO(memberId, page);
        return ApiResponse.onSuccess(reviewPreviewListDTO);
    }

}
