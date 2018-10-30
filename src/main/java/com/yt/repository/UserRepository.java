package com.yt.repository;

import com.yt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUidAndPswordAndIdentity(String uid,String psword,int identity);
}
