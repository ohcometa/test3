package com.jvision.admin.domain.user;
//12-2 26:30

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //<테이블 이름, 키 값의 타입>

    Optional<User> findByEmail(String email); //Optional이란 래퍼(Wrapper) 클래스가 있다 한다.
    //기능은 널값을 처리하면서 값을 받아올수 있드록 한다. 27:54
    //널값 받으면 에러가 날 수 밖에 없다
    //널값을 받는 경우가 생겨서 혹시 에러가 나면 그것까지 처리를 해달라
    //<User> : User 테이블에 있는 것을 Optional로 좀 해달라
    //오류 체크좀 해달라

    //findByEmail로 email 값을 String으로 받으면 자동으로 입력값 없을때 오류체크좀 해달라
    //이메일을 통해서 가져온 '생성된 사용자' 인식을 할 수 있도록
    //이미 생성된 사용자인지, 처음 가입하는 사용자인지 판단할수 있다.
}
