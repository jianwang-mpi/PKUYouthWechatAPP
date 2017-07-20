package pkuyouth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pkuyouth.requestobjects.*;
import pkuyouth.responsevos.*;
import pkuyouth.services.ApproveService;
import pkuyouth.services.ArticleService;
import pkuyouth.services.CollectService;

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
        //TODO
        return new SuccessVO(1);
    }
    // 读者查看收藏
    @RequestMapping(value = "/view_collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO viewCollect(@RequestBody ViewCollectObject viewCollectObject){
        ViewCollectVO viewCollectVO = new ViewCollectVO();
        viewCollectVO.setApprove(1);
        viewCollectVO.setCollect(1);
        viewCollectVO.setDesc("除夕是团圆的日子，某种意义上，过去一整年的欢笑、欣慰、苦痛、辛酸都是为了今天。为了今天，腊八的蒜、小年的饭、门上的福、元宵的灯，甚至还有回家的车票……一切都会被早早准备好。街上卖春联的小摊摆出来的时候，或许就是春节最初的起点。");
        viewCollectVO.setId(12345);
        viewCollectVO.setPic_url("http://mmbiz.qpic.cn/mmbiz_jpg/l9iadYXd83Z4mOaohfrSJZ8tczmsMicYHic3FiaT2wHcxetSibEba9ia7cmcAMsZUbEaIjwIH32o6EhtRFEwxbrI5iaicg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
        viewCollectVO.setTitle("年味｜有了这些，才过了年");
        viewCollectVO.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3NzAzMDEyNg==&mid=2650825896&idx=1&sn=6f8f467a98c2f7c20dddb6230f355ace&chksm=84acf329b3db7a3fb12226761cce0cee735f18d6b8c4525fe7a9f6ce526b4bc4ed41c897488f#rd");
        Comment[] comments = new Comment[3];
        for (int i = 0; i < comments.length; i++) {
            comments[i] = new Comment();
            comments[i].setComment("测试test?!~");
            comments[i].setUser_id("123");
            comments[i].setUser_name("王健！~?");
            comments[i].setUser_img_url("http://wx.qlogo.cn/mmopen/icAibcTU33ZRaTroyYeZUAZaLPPUbWpCTlJwDaITibt1ibQRAFGgny9Yevx9iaq9ibFBKiacaU2foASFYlILN7BkkRxRkDVdeXibCk0j/0");
        }
        viewCollectVO.setComments(comments);
        return viewCollectVO;
    }

    // 取消收藏
    @RequestMapping(value = "/cancel_collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO cancelCollect(@RequestBody CancelCollectObject cancelCollectObject){
        return new SuccessVO(1);
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
    public BasicVO cancelApprove(@RequestBody CancelApproveObject cancelApproveObject){
        return new SuccessVO(1);
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
        //TODO
        return new SuccessVO();
    }

    //文章展示
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO show(@RequestBody ShowArticleObject showArticleObject) {
        try {
            ShowArticleVO showArticleVO = articleService.showArticle(showArticleObject.getArticle_id(), showArticleObject.getUser_id());
            return showArticleVO;
        }catch (Exception e){
            BasicVO result = new ErrorVO(3,"文章读取失败");
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
