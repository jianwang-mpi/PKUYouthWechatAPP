package pkuyouth.services;

import pkuyouth.responsevos.SearchArticleVO;

/**
 * Created by WangJian on 2017/2/18.
 */
public interface PKUYouthService {
    SearchArticleVO replaceArticle();
    SearchArticleVO searchArticle(String searchContent);
}
