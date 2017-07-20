package pkuyouth.dao;

import org.apache.ibatis.annotations.*;
import pkuyouth.responsevos.Comment;

import java.util.List;

/**
 * Created by WangJian on 2017/3/4.
 */
@Mapper
public interface CommentMapper {
    @Select("select * from comment where article_id=#{article_id}")
    List<Comment> getArticleComment(@Param("article_id") Integer article_id);
    @Insert("insert into comment (user_id, article_id, user_name, user_pic_url, comment) values (#{user_id}, #{article_id}, #{user_name}, #{user_pic_url}, #{comment});")
    void addComment(@Param("user_id")String user_id, @Param("article_id")Integer article_id, @Param("user_name")String user_name,
                    @Param("user_pic_url")String user_pic_url, @Param("comment")String comment);
    @Delete("delete from comment where user_id=#{user_id} and article_id=#{article_id} and id=#{id}; ")
    void deleteComment(@Param("user_id") String user_id, @Param("article_id")Integer article_id, @Param("id")Integer id);
}
