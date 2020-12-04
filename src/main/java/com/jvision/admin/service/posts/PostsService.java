package com.jvision.admin.service.posts;

import com.jvision.admin.domain.posts.Posts;
import com.jvision.admin.domain.posts.PostsRepository;
import com.jvision.admin.web.dto.PostsListResponseDto;
import com.jvision.admin.web.dto.PostsResponseDto;
import com.jvision.admin.web.dto.PostsSaveRequestDto;
import com.jvision.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<PostsListResponseDto> findAllDesc() //PostsRepository의 쿼리가 실행이 되면 posts란 요소들을 갖고 있는 리스트 형태로 리턴을 해라
    {
        return postsRepository.findAllDesc().stream()
                //그리고 나서 postsRepository 인터페이스에 있는 findAllDesc를 stream 타입으로 바꿔서
                .map(PostsListResponseDto::new) //map 타입으로 Posts~~Dto에 새로운 새로운 객체 생성 (명령어가 람다식이라 이해할 수 어렵다한다)
                .collect(Collectors.toList());
        //생성된 객체를 자바가 기본 제공하는 Collectors의 toList 함수를 이용해 list로 모아서 반환
    }


}
