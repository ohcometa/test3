package com.jvision.admin.service.posts;

import com.jvision.admin.domain.posts.Posts;
import com.jvision.admin.domain.posts.PostsRepository;
import com.jvision.admin.web.dto.PostsResponseDto;
import com.jvision.admin.web.dto.PostsSaveRequestDto;
import com.jvision.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service

public class PostsService {
    public final PostsRepository postsRepository;

    @Transactional
    //트랜잭션 생성 -> 해당 값을 DTO에 저장할수 있게 해줌
    public Long save(PostsSaveRequestDto requestDto)
    {
        return postsRepository.save(requestDto.toEntity()).getId(); //내가 만든 toEntity로 저장할것임을 뜻함
    }

    //p7-2
    //Repository에 저장될 수 있도록 갱신되거나 검색할 수 있도록 만듬
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto)
    {
        Posts posts = postsRepository.findById(id) //이미 저장된 테이블로 부터 값을 검색하기 위해
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id=" + id)); //예외 처리
        posts.update(requestDto.getTitle(), requestDto.getContent()); //예외 아닐때
        return id; //새로운 id로 리턴됨
    }

    @Transactional
    public PostsResponseDto findById(Long id)
    {
        Posts entity = postsRepository.findById(id) //여기서는 객체이름을 entity라고 하기로 해서 entity로 적음
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        return new PostsResponseDto(entity);
    }

}
