package com.jvision.admin.web.dto;

import com.jvision.admin.domain.posts.Posts;
import lombok.Getter;

@Getter //롬복에 Getter 사용해서 받는걸 쉽게
public class PostsResponseDto {  //조회하는 클래스

    private  Long id;
    private  String title;
    private  String content;
    private  String author;

    public PostsResponseDto(Posts entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}

