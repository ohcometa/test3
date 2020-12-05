//12-2 20분 경

package com.jvision.admin.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //테이블을 만들때 꼭 필요한 어노테이션
@NoArgsConstructor //final 같은 종단변수 아닐때는 required 아닌 노아그스 ㄱㄱ
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //식별할 수 있는, 자동 증가되는 값
    private Long id;

    @Column(nullable = false) //테이블의 필드면 @Column을 쓴다. nullable은 비워도 되니? 의 뜻
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) //enum 타입으로 JPA DB와 데이터 읽기쓰기할거임. 근데 int 아닌 String 타입으로 저장할거임
    @Column(nullable = false)
    private Role role;


}
