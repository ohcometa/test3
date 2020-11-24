package com.jvision.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //constructor 생성하므로 이것도 넣어줌
public class PostsUpdateRequestDto {
    private  String title;
    private  String content;

    @Builder //get으로 받아온게 아니므로 Builder 시킨다
    public PostsUpdateRequestDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
