package com.jvision.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //원래 sql문으로 전체 DB에 접근하는 파일을 만들어야되는데 그 역할을 PostsRepository가 대신해준다
    //insert문을 자동으로 생성 p9-1
    //h2 DB에 자동으로 생성을 해준다
    @Query("SELECT p from Posts p ORDER BY p.id DESC")
    //p9-3 23:55
    //자동으로 생성 안해주는 것들은 시퀄문(sql 문)으로 쿼리를 직접 만드는 수 밖에 없다.
    //이 쿼리도 그래서 만든것
    //SELECT : 조회, p : Posts라는 이름을 p라는 이름으로 네이밍. 즉 니모닉(Mnemonic)화 시킨것
    //ORDER BY : 순서를 나타내 줌, p.id : p 객체의 id 속성, DESC : DESCENDing -> 내림차순

    List<Posts> findAllDesc(); //Posts에 해당되는 것을 List에 넣고, findAllDesc라는 함수를 발생시켜라
}
