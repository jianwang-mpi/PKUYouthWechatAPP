package pkuyouth.services;

import pkuyouth.responsevos.SearchArticleVO;
import pkuyouth.responsevos.ShowArticleVO;

/**
 * Created by WangJian on 2017/2/18.
 */
public interface ArticleService {
    SearchArticleVO replaceArticle();
    SearchArticleVO searchArticle(String searchContent);
    ShowArticleVO showArticle(String articleId, String userId);
}
