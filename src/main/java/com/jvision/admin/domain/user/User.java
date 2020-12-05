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

    //12-3 1:07

    @Builder //왜필요? : save에 해당하는 부분에서 무엇을 갖고 있어야 되는지에 따라서 변수에 값을 할당해야 되기 때문에
    public  User(String name, String email, String picture, Role role) //User 생성자 메소드
            //나중에 저장할때(등록할때) 이 생성자에 의해서 변수 값이 할당이 될 것임
    {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) //User 수정 메소드
            //이메일과 권한은 한번 정해지면 개인이 수정하게 할 일은 없으니 클라이언트가 바꿀수 있게 할 name, picture만 작성
    {
        this.name = name;
        this.picture = picture;
        return this; //key 값은 키 값대로 지금 객체라서 같이 넘기지 않고 별도로 넘긴다 하네요 (12-3 5:03)
    }

    public String getRoleKey() //권한 Key 반환 메소드
    {
        return this.role.getKey(); //ROLE_GUSET와 ROLE_USER 둘중에 하나의 값을 전송하게 될 것 (5:30)
    }


}
