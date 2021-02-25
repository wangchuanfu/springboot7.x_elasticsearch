package com.j1.dao;

import com.j1.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchuanfu on 20/8/13.
 */
@Mapper
@Repository
public interface MemberMapper {
    List<Member> queryMemberPage(Member member);

}
