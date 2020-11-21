package com.j1.service;

import com.j1.pojo.Member;

import java.util.List;

/**
 * Created by wangchuanfu on 20/8/13.
 */
public interface MemberService {
    List<Member>   queryMemberPage(Member member);

    String searchMemberInfo(String keyword);
}
