package com.sh.redisproject.common.entity;

import com.sh.redisproject.common.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "MEMBER_SSN")
    private String ssn;

    private String phone;

    private String address;

    private char accountAuth;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @PreUpdate
    protected void onUpdatedAt(){
        //탈퇴한 회원의 경우만 데이터 삭제 날짜 기입
        if(this.memberStatus == MemberStatus.DELETE){
            this.deletedAt = LocalDateTime.now();
        }
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(Long id, String name, String email, String password, String ssn, String phone, String address, char accountAuth, LocalDateTime deletedAt, MemberStatus memberStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.accountAuth = accountAuth;
        this.deletedAt = deletedAt;
        this.memberStatus = memberStatus;
    }

    public Member() {
    }
}