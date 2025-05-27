package com.warrr.zipflex.api.favorite.vo.in;

import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FavoriteRequestVo {

    @NotNull(message = "FavoriteType은 필수 입력 항목입니다.")
    private FavoriteType favoriteType;
    
    private String code;
    
}
