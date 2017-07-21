package pkuyouth.services;

import pkuyouth.requestobjects.CollectObject;
import pkuyouth.responsevos.SearchArticleVO;

/**
 * Created by WangJian on 2017/3/3.
 */
public interface CollectService {
    void collect(CollectObject collectObject);
    SearchArticleVO showCollect(String userId);
    void cancelCollect(String userId, Integer articleId);
}
