package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.Member;
import com.j1.pojo.vo.MemberVo;
import com.j1.service.MemberService;
import com.j1.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/13.
 */
@RestController
@Slf4j
public class MemberInfoIndexController {

    @Autowired
    MemberService memberService;

    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsUtils esUtils;
    @Autowired
    EsAttribute esAttribute;
    private static final String orderIndex = "member_info_index";


    @RequestMapping("/memberInfoIndex")
    public String produceVo(@RequestParam(value = "userName") String userName) {
        try {
            //创建之前先判断索引是否存在,
            if (esUtils.existsIndex(orderIndex)) {
                elasticsearchTemplate.deleteIndex(orderIndex);
            }
            if (!esUtils.existsIndex(orderIndex)) {
                elasticsearchTemplate.createIndex(MemberVo.class);
                elasticsearchTemplate.putMapping(MemberVo.class);

            }

            Member member = new Member();

            List<Member> memberList = memberService.queryMemberPage(member);
            //同步数据至es指定索引中
            if (memberList.size() > 0) {
                // esUtils.insertIntoMmeberByBulk(orderIndex, esAttribute.getIndexType(), memberList, "memberId");

            }

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex(orderIndex);
            e.printStackTrace();
            log.error("创建索引失败", e.getMessage());
        }
        return userName;
    }


    @RequestMapping("/memberInfoIndexTime/{beginTime}/{endTime}")
    public String produceVoTime(@PathVariable String beginTime, @PathVariable String endTime) {
        try {

            /**
             *

             Member member=new Member();
             member.setRegTimeBg(beginTime);
             member.setRegTimeEd(endTime);
             List<Member> memberList = memberService.queryMemberPage(member);
             //同步数据至es指定索引中
             if (memberList.size() > 0) {
             esUtils.insertIntoMmeberByBulk(orderIndex, esAttribute.getIndexType(), memberList, "memberId");

             } */

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex(orderIndex);
            e.printStackTrace();
            log.error("创建索引失败", e.getMessage());
        }
        return "ok";
    }


    /**
     * search
     *
     * @param request
     * @param member
     * @return
     */
    @RequestMapping(value = "/member/list/{keyword}")
    @ResponseBody
    public String memberList(HttpServletRequest request,
                             HttpServletResponse response, Member member, @PathVariable String keyword) {


        //封装查询条件

        return memberService.searchMemberInfo(keyword);

    }

}
