package com.jvision.admin.config.auth.dto;

import com.jvision.admin.domain.user.Role;
import com.jvision.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture)
    {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes)
    {
        //14-2 0:39
        if("naver".equals(registrationId))

            return ofNaver("id", attributes);
            //return해서 OfNaver 메소드에 "id"랑 attributes를 매개변수로 넣은 것을 동작시킴



        return ofGoogle(userNameAttributeName, attributes);
    }



    //14-2 2:04 별 설명은 없고 그냥 response로 받아온다 그정도만 설명하심
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes)
    {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        //attribute 속성중에 "response"에 해당하는 값을 참조하게 함
        return OAuthAttributes.builder()
                .name((String)response.get("name"))
                .email((String)response.get("email"))
                .picture((String)response.get("profile_image"))
                //response가 갖고 있는 name과 email, profile image를
                //OAuthAttributes의 name email picture에 집어넣게 함 (build의 역할)

                .attributes(response) //attributes는 response를 설정함
                .nameAttributeKey(userNameAttributeName)
                //userNameAttributeName는 id값을 의미.
                //그 id값을 설정을 해서 OAuthAttributes를 빌드해주는 것 (8:08)
                .build();
    }
    //만약 Kakao의 로그인 연동을 쓰고 싶다. 그러면 조건문 하나하고 OfKakao 같은 메소드 하나 필요
    //여기까지 작성하고 이 OauthAttributes 창에서 Application 실행하면 오류뜨는데,
    //기능은 정상적으로 다 작동한다
    //웃기게 다른 창. 예를 들어 index.mustache 창에서 Application 을 실행하면 그 오류가 안뜬다.




    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes)
    {
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }



    public User toEntity()
    {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .build();
    }

}

