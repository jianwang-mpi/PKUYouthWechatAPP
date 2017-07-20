package pkuyouth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pkuyouth.responsevos.Comment;

import java.util.List;

/**
 * Created by WangJian on 2017/3/4.
 */
@Mapper
public interface CommentMapper {
    @Select("select * from comment where article_id=#{article_id}")
    List<Comment> getArticleComment(@Param("article_id") Integer article_id);
}
