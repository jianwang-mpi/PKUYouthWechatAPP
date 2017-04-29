package pkuyouth.services.impl;

import org.springframework.stereotype.Service;
import pkuyouth.dao.CollectMapper;
import pkuyouth.requestobjects.CollectObject;
import pkuyouth.services.CollectService;

import javax.annotation.Resource;

/**
 * Created by WangJian on 2017/3/3.
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    CollectMapper collectMapper;
    public void collect(CollectObject collectObject){
        String user_id = collectObject.getUser_id();
        Integer article_id = Integer.parseInt(collectObject.getArticle_id());
        if(collectMapper.findCollect(user_id,article_id)<=0) {
            collectMapper.addCollect(user_id,article_id);
        }else{
            collectMapper.deleteCollect(user_id,article_id);
        }
    }
}
