package pkuyouth.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pkuyouth.constants.Constants;
import pkuyouth.dao.ArticleMessageMapper;
import pkuyouth.dtos.ArticleMessage;
import pkuyouth.responsevos.Article;
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
    private static Logger logger = LoggerFactory.getLogger(PKUYouthServiceImpl.class);
    @Resource
    ArticleMessageMapper articleMessageMapper;

    @Override
    public SearchArticleVO replaceArticle() {

        List<ArticleMessage> articleMessageList = articleMessageMapper.getArticleMessages();
        List<ArticleMessage> selectResult = new ArrayList<ArticleMessage>(Constants.replaceArticleNumber);
        int interval = articleMessageList.size() / Constants.replaceArticleNumber;
        for (int i = 0; i < Constants.replaceArticleNumber; i++) {
            int margin = (int) (Math.random() * interval);
            int index = margin + interval * i;
            selectResult.add(articleMessageList.get(index));
        }
        return createSearchArticleVO(selectResult);
    }

    @Override
    public SearchArticleVO searchArticle(String searchContent) {
        List<ArticleMessage> searchResult = articleMessageMapper.searchArticlesByTitle(searchContent);
        return createSearchArticleVO(searchResult);
    }

    private SearchArticleVO createSearchArticleVO(List<ArticleMessage> articleMessages){
        SearchArticleVO searchArticleVO = new SearchArticleVO();
        searchArticleVO.setArticle_count(articleMessages.size());
        Article [] articles = new Article[articleMessages.size()];
        for(int i = 0;i<articles.length;i++){
            articles[i] = new Article();
            articles[i].setTitle(articleMessages.get(i).getTitle());
            articles[i].setId(articleMessages.get(i).getId());
            articles[i].setDesc(articleMessages.get(i).getDescription());
            articles[i].setUrl(articleMessages.get(i).getUrl());
            articles[i].setPic_url(articleMessages.get(i).getPicurl());
        }
        searchArticleVO.setArticles(articles);
        return searchArticleVO;
    }
    @Deprecated
    private Set<Integer> selectNumber(int totalNumber, int selectNumber) {
        Set<Integer> resultSet = new HashSet<Integer>(selectNumber);
        for (int i = 0; i < selectNumber; i++) {
            int num = (int) (Math.random() * totalNumber);
            if (resultSet.contains(num)) {
                i--;
                continue;
            } else {
                resultSet.add(num);
            }
        }
        return resultSet;
    }
}
