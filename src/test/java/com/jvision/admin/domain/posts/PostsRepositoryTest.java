package com.jvision.admin.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //테스트 할 것을 유닛 단위로 연결해주는 어노테이션2
@SpringBootTest

public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup()
    {
        postsRepository.deleteAll();
    }


@Test
public void 게시글저장_불러오기(){
    String title="테스트 게시글";
    String content="테스트 본문";
    //insert into Posts values("", "", "" ~~~~~) <- 원래 sql문
    postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("grizzzzzly55@gmail.com")
            .build()
    );

    //select * from Posts where~~~ <-원래 sql문은 이것, 밑에 메소드들이 대체해준다
   List<Posts> postsList=postsRepository.findAll();
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);//assertThat은 데이터 검증
    assertThat(posts.getContent()).isEqualTo(content);
}

}
//application.properties 파일을 바꿨을때 쿼리 확인 가능하고 게시글저장_불러오기 메소드를 실행하면 아래 문구 확인가능
//Hibernate: drop table posts if exists
//Hibernate: create table posts (id bigint generated by default as identity, author varchar(255), content TEXT not null, title varchar(500) not null, primary key (id))
//Hibernate: insert into posts (id, author, content, title) values (null, ?, ?, ?)

//application.properties 파일에 ~~~hibernate.dialect.MySQL5Dialect 추가하면 MySQL 형식으로 바뀐 쿼리 확인 가능