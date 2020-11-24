package com.jvision.admin.web;

import com.jvision.admin.domain.posts.Posts;
import com.jvision.admin.domain.posts.PostsRepository;
import com.jvision.admin.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)



public class PostsApiControllerTest {

    @LocalServerPort
    private int port; //위에서 RANDOM_PORT로 선언했으므로 랜덤으로 받아서 여기에 저장

    @Autowired // @Autowired → 스프링이 관리하는 Bean 주입. 이 클래스로 HTTP GET, POST API 테스트 가능

    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After //단위 테스트가 끝나고 어떤 함수를 실행시킬것인가
    public void tearDown() throws  Exception
    {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception
    {
        String title="title";
        String content="content";
        String author="author";
        //id는 자동생성
        PostsSaveRequestDto requestDto=PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //경로를 잡아주면 그 위치에다가 저장할 수 있게 하는 메소드
        //Long.class는 id에 해당하는 데이터 타입
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //assertThat은 검증하기 위해 쓰는것임
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); //HttpStatus가 OK로 떨어지는것과 같은지
        assertThat(responseEntity.getBody()).isGreaterThan(0L); //Body안에 데이터가 0보다 큰 값인가 (=데이터가 들어있는가)

        List<Posts> all = postsRepository.findAll();
        //1.전체를 전부 레코드가 있으면 가지고 와라 2.Posts에 있는 모든 필드를 전부 가져와서 List 타입으로 저장해라

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        //1. get(0):0번레코드 부터 시작 2.getTitle로 받고 3.title하고 같은지 확인해달라
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);


    }
}
