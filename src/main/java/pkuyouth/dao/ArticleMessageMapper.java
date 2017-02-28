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
    @Select("select * from articlemessage where id = #{id};")
    ArticleMessage getArticleMessageById(@Param("id") int id);
    @Select("select * from articlemessage;")
    List<ArticleMessage> getArticleMessages();
    @Select("select * from articlemessage where title like CONCAT('%',#{title},'%' );")
    List<ArticleMessage> searchArticlesByTitle(@Param("title") String title);
}
