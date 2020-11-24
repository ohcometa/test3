package com.jvision.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //@RunWith는 SpringBoot와 JUnit을 중간에 결합해주는 역할을한다.
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest는 자동으로 Tomcat을 띄울 수 있도록 해준다. RANDOM_PORT만 쓰면 SpringBootTest.WebEnvironment.RANDOM_PORT이 자동완성 되는듯
public class IndexControllerTest {

    @Autowired //@SpringBootTest를 쓸때 @Autowired와 @Test는 세트로 같이 쓴다.
    private TestRestTemplate testRestTemplate;

    @Test
    public  void  메인페이지_로딩() //테스트 할 내용은 "스프링 부트로 시작하는 웹 서비스"라는 문구가 제대로 들어있는가
    {
        String body = this.testRestTemplate.getForObject("/", String.class);

        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
