package pkuyouth.dao;

import pkuyouth.dtos.ArticleMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by WangJian on 2017/2/18.
 */
@Mapper
public interface ArticleMessageMapper {
    ArticleMessage getArticleMessageById(int id);
}
