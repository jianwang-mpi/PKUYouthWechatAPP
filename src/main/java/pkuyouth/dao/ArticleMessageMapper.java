package pkuyouth.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pkuyouth.dtos.ArticleMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by WangJian on 2017/2/18.
 */
@Mapper
public interface ArticleMessageMapper {
    @Select("select * from ArticleMessage where id = #{id};")
    ArticleMessage getArticleMessageById(@Param("id") int id);
    @Select("select * from ArticleMessage;")
    List<ArticleMessage> getArticleMessages();
    @Select("select * from ArticleMessage where title like CONCAT('%',#{title},'%' );")
    List<ArticleMessage> searchArticlesByTitle(@Param("title") String title);
    @Select("select * from ArticleMessage where ANo in (select ANo from articlefulltext where Text like CONCAT('%',#{content},'%' ));")
    List<ArticleMessage> searchArticlesByContent(@Param("content") String content);
    @Select("select * from ArticleMessage where time = #{time}")
    List<ArticleMessage> searchArticlesByTime(@Param("time") String time);
}
