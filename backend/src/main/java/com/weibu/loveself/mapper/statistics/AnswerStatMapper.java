package com.weibu.loveself.mapper.statistics;

import com.weibu.loveself.entity.Organization;
import com.weibu.loveself.entity.Sponsor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Marker;

import java.util.List;

/**
 * 答案统计Mapper相关接口
 */
public interface AnswerStatMapper extends Marker {

    /** 查询指定套题的答题结果中关联的所有代表 */
    @Select(value = "select * from sponsor\n" +
            "        where id in (\n" +
            "            select distinct(id_sponsor) from user_relation\n" +
            "                where id_user in (\n" +
            "                    select id_user from user_answer where id_question=#{idQuestion}\n" +
            "                )\n" +
            "        )")
    List<Sponsor> selectSponsorsAnswerSpecifiedQuestion(@Param("idQuestion") Long idQuestion);

    /** 将指定套题的答题相关代表信息批量插入结果表，结果表先删除再创建、然后批量插入数据 */
    int insertSponsorsAnswerSpecifiedQuestion(@Param("tableName") String tableName,
                                              @Param("dataList")List<Organization> dataList);
}
