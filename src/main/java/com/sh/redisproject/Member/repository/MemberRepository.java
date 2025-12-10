package com.sh.redisproject.Member.repository;

import com.sh.redisproject.common.entity.Member;
import com.sh.redisproject.common.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findById(Long memberId);
    @Query("select m.memberStatus from Member m where m.id = :memberId")
    MemberStatus getMemberStatusById(@Param("memberId") Long memberId);

}
