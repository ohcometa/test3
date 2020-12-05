package com.jvision.admin.domain.user;

//12-2

//유저의 권한 정도를 보관하는 곳
//enum 클래스 사용 이유 : 값이 변하지 않고 열거해 두었다가 그중에 하나를 선택할수 있게 하므로

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //final과 같은 종단맴버 변수 같은 경우는 초기화하기 위해 이 어노테이션을 씀
public enum Role {
    GUEST("ROLE_GUEST","손님"), //스프링 프레임워크에서는 게스트나 유저같은거 앞에 ROLE_ 을 붙이기로 약속했음
    USER("ROLE_USER", "일반 사용자");

    private  final String key;
    private  final String title;

}
