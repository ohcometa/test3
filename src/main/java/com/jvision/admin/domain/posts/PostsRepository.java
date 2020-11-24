package com.jvision.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //원래 sql문으로 전체 DB에 접근하는 파일을 만들어야되는데 그 역할을 PostsRepository가 대신해준다
    //insert문을 자동으로 생성 p9-2
    //h2 DB에 자동으로 생성을 해준다
}
