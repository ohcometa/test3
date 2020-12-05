package com.jvision.admin.web;

import com.jvision.admin.service.posts.PostsService;
import com.jvision.admin.web.dto.PostsResponseDto;
import com.jvision.admin.web.dto.PostsSaveRequestDto;
import com.jvision.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") //등록용으로 만듬 //서버에서 전달하기로한 이 경로를 url에 등록 시켜야된다. 인터넷 주소창에서 localhost:포트 뒤에 붙는다.
    public long save(@RequestBody PostsSaveRequestDto requestDto)
    {
        return postsService.save(requestDto);
    }
    //requestDto가 하나 작성이되고 작성이 된 값들을 postsService로 넘겨주게 된다 (p9-1 26:54)

    @PutMapping("/api/v1/posts/{id}") //정보를 수정할때 쓰는 어노테이션 post 매핑이나 get 매핑과는 다르게 put 매핑은 업데이트 할때 쓰인다
    public long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) //정보를 수정하니까 update, PathVariable은 Path중 일부인 ID를 받기위해 쓴것,
    {
        return postsService.update(id, requestDto); //서비스(postService)에 데이터를 넘겨라
    }

    @GetMapping("/api/v1/posts/{id}") //검색(조회)를 위함
    public PostsResponseDto findById(@PathVariable Long id) //입력한 경로 중에서 id라는 필드만 때서({id}), id(이건 또다른 id인듯?)라는 변수에 저장해 넘겨받아라
    {
        return postsService.findById(id); // 서비스에 id 껴서 넘겨라
    }

    @DeleteMapping("api/v1/posts/{id}") //p11-3 8분경
    public Long delete(@PathVariable Long id) //실제 key값인 id를 받아오려면 @PathVariable 필요
    {
        postsService.delete(id); //이 delete 메소드는 postsService에 response하고 그걸 통해서 jpa repository에 전송할수 있도록 줘야함
                                 //어떤 값을 삭제할지에 대한 키값이 반드시 전송이 같이 되야함
        return id;
    }
}
// p11-1 14:30 update() : 클라이언트 수정 요청, findById():삭제 요청 메소드