package pkuyouth.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by WangJian on 2017/3/3.
 */
@Mapper
public interface CollectMapper {
    @Insert("insert into collect (user_id,article_id) values (#{user_id},#{article_id});")
    void collect(@Param("user_id") String user_id, @Param("article_id") int article_id);
}
