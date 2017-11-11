package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.UserRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserRelationMapper extends IBaseMybatisMapper<UserRelation> {

    @Select(value = "select user_relation.*\n" +
            "        from user_relation\n" +
            "            right join sponsor\n" +
            "            on user_relation.id_sponsor = sponsor.id\n" +
            "            right join `user`\n" +
            "            on user_relation.id_user = user.id\n" +
            "        where user.openid = #{openid}\n" +
            "        and sponsor.mobile = #{mobile}")
    UserRelation selectUserRelation(@Param("openid") String openid,
                                    @Param("mobile") String mobile);
}
