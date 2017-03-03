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
    public void addCollect(CollectObject collectObject){
        collectMapper.collect(collectObject.getUser_id(),Integer.parseInt(collectObject.getArticle_id()));
    }
}
