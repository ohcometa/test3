package com.jvision.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //@RestController와 다른점은 그냥 @Controller는 View를 반환할때 쓰는 것이다. 데이터도 반환할수있긴한데 주로 View 반환할때 그냥 컨트롤러 씀
public class indexController {

    @GetMapping("/") //url 뒤에 붙는 슬래시를 의미
    //아무것도 주지 않았을때(="/")(=루트라고 들어오면) 자동으로 index 파일을 리턴 받아서 index.mustache 라는 파일을 templates 아래에서 불러다가 결과를 나타내준다.
    public String index()
    {
        return "index";
    }
    @GetMapping("/posts/save")
    //url 맨뒤에 /posts/save가 붙는다면. (ex.http://www.naver.com/posts/save)
    public String postsSave()
    {
        return  "posts-save";
    }

}
