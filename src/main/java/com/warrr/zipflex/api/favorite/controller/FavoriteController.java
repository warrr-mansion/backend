package com.warrr.zipflex.api.favorite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;
import com.warrr.zipflex.api.favorite.service.FavoriteService;
import com.warrr.zipflex.api.favorite.vo.in.FavoriteRequestVo;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Favorite - Generic")
@SecurityRequirement(name = "JWT")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Operation(summary = "관심 법정동 / 매물 등록", description = """
                    - `code`: 관심 지역 코드 `regionCode` 또는 매물 ID `houseInfoId`\n\n
                    - (숫자도 문자열 값으로 전달해야 합니다 : **10 -> "10"**)\n\n
                    - `favoriteType`: `REGION`, `HOUSE`""")
    @PostMapping
    public BaseResponse<Void> regist(@RequestBody @Valid FavoriteRequestVo requestVo,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        favoriteService.addFavoriteRegion(requestVo, authUserDetail);
        return new BaseResponse<>();
    }

    @Operation(summary = "관심 법정동 / 매물 해제", description = """
                    - `code`: 관심 지역 코드 `regionCode` 또는 매물 ID `houseInfoId`\n\n
                    - (숫자도 문자열 값으로 전달해야 합니다 : **10 -> "10"**)\n\n
                    - `favoriteType`: `REGION`, `HOUSE`""")
    @DeleteMapping
    public BaseResponse<Void> delete(@RequestBody @Valid FavoriteRequestVo requestVo,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        favoriteService.removeFavoriteRegion(requestVo, authUserDetail);
        return new BaseResponse<>();
    }

    @Operation(summary = "관심 법정동 / 매물 목록 조회 (페이지네이션)", description = """
                    - `favoriteType`: `REGION`, `HOUSE`""")
    @GetMapping("/{favoriteType}")
    public BaseResponse<CursorPage<?>> getFavorites(@PathVariable FavoriteType favoriteType,
                    @RequestParam(defaultValue = "1") int pageNo,
                    @RequestParam(defaultValue = "10") int pageSize,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        return new BaseResponse<>(favoriteService.getFavoritesByPage(
                        FavoritePageRequestDto.toDto(favoriteType, pageNo, pageSize),
                        authUserDetail));
    }

}
