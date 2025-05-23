package com.warrr.zipflex.api.notice.dto.out;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeInfoResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registDate;

}
