package pkuyouth.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pkuyouth.constants.Constants;
import pkuyouth.dao.ArticleMessageMapper;
import pkuyouth.dtos.ArticleMessage;
import pkuyouth.responsevos.ArticleVO;
import pkuyouth.responsevos.SearchArticleVO;
import pkuyouth.services.ArticleService;
import pkuyouth.utils.TimeUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by WangJian on 2017/2/18.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
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
        String parsedTime = TimeUtils.parseTime(searchContent);
        List<ArticleMessage> searchResult = new LinkedList<ArticleMessage>();
        if(parsedTime == null) {
            List<ArticleMessage> searchResultByTitle = articleMessageMapper.searchArticlesByTitle(searchContent);
            List<ArticleMessage> searchResultByContent = articleMessageMapper.searchArticlesByContent(searchContent);
            searchResult.addAll(searchResultByTitle);
            searchResult.addAll(searchResultByContent);
        }else {
            List<ArticleMessage> searchResultByTime = articleMessageMapper.searchArticlesByTime(searchContent);
            searchResult.addAll(searchResultByTime);
        }
        return createSearchArticleVO(searchResult);
    }

    private SearchArticleVO createSearchArticleVO(List<ArticleMessage> articleMessages){
        SearchArticleVO searchArticleVO = new SearchArticleVO();
        searchArticleVO.setArticle_count(articleMessages.size());
        ArticleVO[] articleVOs = new ArticleVO[articleMessages.size()];
        for(int i = 0; i< articleVOs.length; i++){
            articleVOs[i] = new ArticleVO();
            articleVOs[i].setTitle(articleMessages.get(i).getTitle());
            articleVOs[i].setId(articleMessages.get(i).getANo());
            articleVOs[i].setDesc(articleMessages.get(i).getDescription());
            articleVOs[i].setUrl(articleMessages.get(i).getUrl());
            articleVOs[i].setPic_url(articleMessages.get(i).getPicurl());
        }
        searchArticleVO.setArticleVOs(articleVOs);
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
