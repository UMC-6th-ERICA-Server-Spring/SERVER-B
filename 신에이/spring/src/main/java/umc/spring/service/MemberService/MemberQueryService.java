package umc.spring.service.MemberService;

import umc.spring.web.dto.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.ReviewPreviewListDTO getReviewPreviewListDTO(Long memberId, Integer page);
}
