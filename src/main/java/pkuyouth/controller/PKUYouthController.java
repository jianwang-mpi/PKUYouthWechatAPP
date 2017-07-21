package pkuyouth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pkuyouth.requestobjects.*;
import pkuyouth.responsevos.*;
import pkuyouth.services.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by WangJian on 2017/1/29.
 */
@RestController
@RequestMapping(value = "/pkuyouth")
public class PKUYouthController {
    private static Logger logger = LoggerFactory.getLogger(PKUYouthController.class);

    @Resource
    ArticleService articleService;
    @Resource
    CollectService collectService;
    @Resource
    ApproveService approveService;
    @Resource
    private CommentService commentService;
    @Resource
    private SuggestionService suggestionService;

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO login(@RequestBody LoginCode loginCode) {
        return new AccessTokenVO("adsfasdfa");
    }

    //评论
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public
    @ResponseBody
    BasicVO comment(@RequestBody CommentObject commentObject) {
        try {
            commentService.comment(commentObject.getUser_id(), Integer.valueOf(commentObject.getArticle_id()), commentObject.getUser_name(), commentObject.getUser_pic_url(), commentObject.getComment());
            return new SuccessVO(1);
        }catch (Exception e){
            return new ErrorVO(6, "评论失败");
        }
    }

    //删除评论
    @RequestMapping(value = "/cancel_comment", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO cancelComment(@RequestBody CancelCommentObject cancelCommentObject) {
        try {
            commentService.deleteComment(cancelCommentObject.getUser_id(), Integer.valueOf(cancelCommentObject.getArticle_id()), cancelCommentObject.getComment_id());
            BasicVO result = new SuccessVO(1);
            return result;
        } catch (Exception e) {
            return new ErrorVO(5, "删除评论出错");
        }
    }

    // 读者查看收藏
    @RequestMapping(value = "/view_collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO viewCollect(@RequestBody ViewCollectObject viewCollectObject) {
        try{
            BasicVO basicVO = collectService.showCollect(viewCollectObject.getUser_id());
            return basicVO;
        }catch (Exception e){
            return new ErrorVO(7,"查看收藏错误");
        }
    }

    // 取消收藏
    @RequestMapping(value = "/cancel_collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO cancelCollect(@RequestBody CancelCollectObject cancelCollectObject) {
        try{
            collectService.cancelCollect(cancelCollectObject.getUser_id(), Integer.valueOf(cancelCollectObject.getArticle_id()));
            return new SuccessVO(1);
        }catch (Exception e){
            return new ErrorVO(8,"取消收藏失败");
        }
    }

    //收藏
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO collect(@RequestBody CollectObject collectObject) {
        BasicVO collectVO;
        //TODO
        try {
            collectService.collect(collectObject);
            collectVO = new SuccessVO();
        } catch (Exception e) {
            collectVO = new ErrorVO(3, "收藏失败");
            e.printStackTrace();
            logger.error(new Date().toString() + "收藏失败", e);
        }
        return collectVO;
    }

    // 取消赞
    @RequestMapping(value = "/cancel_approve", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO cancelApprove(@RequestBody CancelApproveObject cancelApproveObject) {
        try{
            approveService.cancelApprove(cancelApproveObject.getUser_id(), Integer.valueOf(cancelApproveObject.getArticle_id()));
            return new SuccessVO(1);
        }catch (Exception e){
            return new ErrorVO(9, "取消赞失败");
        }
    }

    //点赞
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO approve(@RequestBody ApproveObject approveObject) {
        BasicVO approveVO;
        //TODO
        try {
            approveService.manageApprove(approveObject);
            approveVO = new SuccessVO();
        } catch (Exception e) {
            approveVO = new ErrorVO(4, "赞赏失败");
            e.printStackTrace();
            logger.error(new Date().toString() + "赞赏失败", e);
        }
        return approveVO;
    }

    //意见反馈
    @RequestMapping(value = "/suggestion", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO suggestion(@RequestBody SuggestionObject suggestionObject) {
        try{
            suggestionService.suggest(suggestionObject.getUser_id(), suggestionObject.getUser_name(), suggestionObject.getSuggestion());
            return new SuccessVO(1);
        }catch (Exception e){
            return new ErrorVO(10, "意见反馈失败");
        }
    }

    //文章展示
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO show(@RequestBody ShowArticleObject showArticleObject) {
        try {
            ShowArticleVO showArticleVO = articleService.showArticle(showArticleObject.getArticle_id(), showArticleObject.getUser_id());
            return showArticleVO;
        } catch (Exception e) {
            BasicVO result = new ErrorVO(3, "文章读取失败");
            return result;
        }
    }

    //搜索
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO search(@RequestBody SearchObject searchObject) {
        String content = searchObject.getContent();
        BasicVO searchArticleVO;
        try {
            searchArticleVO = articleService.searchArticle(content);
        } catch (Exception e) {
            searchArticleVO = new ErrorVO(2, "搜索文章发生错误");
            e.printStackTrace();
            logger.error(new Date().toString() + "搜索文章发生错误", e);
        }

        return searchArticleVO;
    }

    //换一批
    @RequestMapping(value = "/replace", method = RequestMethod.GET)
    @ResponseBody
    public BasicVO replace() {
        BasicVO replaceArticleVO;
        try {
            replaceArticleVO = articleService.replaceArticle();
        } catch (Exception e) {
            replaceArticleVO = new ErrorVO(1, "替换文章发生错误");
            e.printStackTrace();
            logger.error(new Date().toString() + "替换文章发生错误", e);
        }
        return replaceArticleVO;
    }
    /*
    @Resource
    SearchArticleService searchArticleService;

    //测试
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> test() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("success", "1");

        System.out.println(searchArticleService.getUser(1).getName());
        return result;
    }
    */
}
