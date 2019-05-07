package com.biblioP7.dao;

import com.biblioP7.beans.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {

}
