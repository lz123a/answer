package com.yt.repository;

import com.yt.domain.Od;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OdRepository extends JpaRepository<Od,Integer> {
    public List<Od> findByStudent_NameAndState(String Student_name,int State);
    public List<Od> findByStudent_NameAndStateLessThan(String student_name,int state);
//    public List<Od> findByStudent_NameAndStateOrStudent_NameOrState(String Student_name,int State1,int state2);

}
