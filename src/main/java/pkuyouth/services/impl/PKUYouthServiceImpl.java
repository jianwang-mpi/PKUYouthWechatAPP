package pkuyouth.services.impl;

import org.springframework.stereotype.Service;
import pkuyouth.dao.ArticleNumberMapper;
import pkuyouth.responsevos.SearchArticleVO;
import pkuyouth.services.PKUYouthService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WangJian on 2017/2/18.
 */
@Service
public class PKUYouthServiceImpl implements PKUYouthService {
    @Resource
    ArticleNumberMapper articleNumberMapper;

    @Override
    public SearchArticleVO replaceArticle() {
        int articleNumber = articleNumberMapper.getArticleNumber();
        System.out.println(articleNumber);

        return null;
    }
    @Deprecated
    private Set<Integer> selectNumber(int totalNumber, int selectNumber) {
        Set<Integer> resultSet = new HashSet<Integer>(selectNumber);
        for (int i = 0; i < selectNumber; i++) {
            int num = (int)(Math.random()*totalNumber);
            if(resultSet.contains(num)){
                i--;
                continue;
            }else{
                resultSet.add(num);
            }
        }
        return resultSet;
    }
}
