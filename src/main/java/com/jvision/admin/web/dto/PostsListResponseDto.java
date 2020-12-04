package com.jvision.admin.web.dto;

import com.jvision.admin.domain.posts.Posts;

//9-2
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;

    public PostsListResponseDto(Posts entity)
    {
        this.id = entity.getId(); //getID는 Posts 클래스에서 롬복으로 자동으로 만들어진 메소드인듯
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        //각각 변수에다가 DB에서 잡아온 id title author를 집어넣어서
        //PostsListResponseDto라는 한 덩어리 객체로 변환
    }
}
