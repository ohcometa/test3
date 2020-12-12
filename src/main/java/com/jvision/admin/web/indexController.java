package com.jvision.admin.web;

import com.jvision.admin.config.auth.dto.SessionUser;
import com.jvision.admin.service.posts.PostsService;
import com.jvision.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller //@RestController와 다른점은 그냥 @Controller는 View를 반환할때 쓰는 것이다. 데이터도 반환할수있긴한데 주로 View 반환할때 그냥 컨트롤러 씀
public class indexController {

//    @GetMapping("/") //url 뒤에 붙는 슬래시를 의미
//    //아무것도 주지 않았을때(="/")(=루트라고 들어오면) 자동으로 index 파일을 리턴 받아서 index.mustache 라는 파일을 templates 아래에서 불러다가 결과를 나타내준다.
//    public String index()
//    {
//        return "index";
//    }

//9-3에서 수정됨
    private final PostsService postsService; //@RequireArgsConstructor 덕분에 자동으로 생성자 생성됨
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("posts", postsService.findAllDesc());
        //13-3 6:34 추가
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        //httpSession은 자바 제공 라이브러리. httpSession에서 만든 user값을 갖고 오라는것.
        //httpSession를 사용해 user를 만든 부분은 CustomOAuth2UserService에서
        //httpSession.setAttribute("user", new SessionUser(user)); << 이부분에서 만들었다
        //그 후 그 값을 SessionUser로 형변환함
        if(user!=null) //user가 없을경우에 대한 코드
            model.addAttribute("userName", user.getName());
        //userName은 index.mustache에서 구글로그인버튼 만들때 지정한 이름
        // 여기까지 추가함
        return "index";
    }

    @GetMapping("/posts/save")
    //url 맨뒤에 /posts/save가 붙는다면. (ex.http://www.naver.com/posts/save)
    public String postsSave()
    {
        return  "posts-save";
    }

    @GetMapping("/posts/update/{id}") //p11-1 23:10
    public String postsUpdate(@PathVariable Long id, Model model) //sub-templatate에서 저장되는건 무조건 model 타입으로 저장되야
    {
        PostsResponseDto dto = postsService.findById(id); //id를 찾아가지고 response로 보내라
        model.addAttribute("posts", dto); //dto형태로 데이터를 저장하겠다
        return  "posts-update";
    }



}
