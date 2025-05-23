package com.warrr.zipflex.api.notice.dto.in;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCreateRequestDto {
    private String title;
    private String content;
    private LocalDateTime registDate;
}
