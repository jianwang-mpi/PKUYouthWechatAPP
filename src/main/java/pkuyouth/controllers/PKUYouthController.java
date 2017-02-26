package pkuyouth.controllers;

import pkuyouth.requestobjects.*;
import pkuyouth.responsevos.*;
import pkuyouth.services.PKUYouthService;
import pkuyouth.services.SearchArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangJian on 2017/1/29.
 */
@RestController
@RequestMapping(value = "/pkuyouth")
public class PKUYouthController {
    @Resource
    PKUYouthService pkuYouthService;

    //评论
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public
    @ResponseBody
    BasicVO comment(@RequestBody CommentObject commentObject) {
        //TODO
        return new SuccessVO(1);
    }

    //收藏
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO collect(@RequestBody CollectObject collectObject) {
        //TODO
        return new SuccessVO();
    }

    //点赞
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    @ResponseBody
    public BasicVO approve(@RequestBody ApproveObject approveObject) {
        //TODO
        return new SuccessVO();
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
        //TODO
        ShowArticleVO showArticleVO = new ShowArticleVO();
        showArticleVO.setApprove(1);
        showArticleVO.setCollect(1);
        showArticleVO.setDesc("除夕是团圆的日子，某种意义上，过去一整年的欢笑、欣慰、苦痛、辛酸都是为了今天。为了今天，腊八的蒜、小年的饭、门上的福、元宵的灯，甚至还有回家的车票……一切都会被早早准备好。街上卖春联的小摊摆出来的时候，或许就是春节最初的起点。");
        showArticleVO.setId(12345);
        showArticleVO.setPic_url("http://mmbiz.qpic.cn/mmbiz_jpg/l9iadYXd83Z4mOaohfrSJZ8tczmsMicYHic3FiaT2wHcxetSibEba9ia7cmcAMsZUbEaIjwIH32o6EhtRFEwxbrI5iaicg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
        showArticleVO.setTitle("年味｜有了这些，才过了年");
        showArticleVO.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3NzAzMDEyNg==&mid=2650825896&idx=1&sn=6f8f467a98c2f7c20dddb6230f355ace&chksm=84acf329b3db7a3fb12226761cce0cee735f18d6b8c4525fe7a9f6ce526b4bc4ed41c897488f#rd");
        Comment[] comments = new Comment[3];
        for (int i = 0; i < comments.length; i++) {
            comments[i] = new Comment();
            comments[i].setComment("测试test?!~");
            comments[i].setUser_id("123");
            comments[i].setUser_name("王健！~?");
            comments[i].setUser_img_url("http://wx.qlogo.cn/mmopen/icAibcTU33ZRaTroyYeZUAZaLPPUbWpCTlJwDaITibt1ibQRAFGgny9Yevx9iaq9ibFBKiacaU2foASFYlILN7BkkRxRkDVdeXibCk0j/0");
        }
        showArticleVO.setComments(comments);
        return showArticleVO;
    }

    //搜索
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public BasicVO search(@RequestParam String content) {
        SearchArticleVO searchArticleVO = new SearchArticleVO();
        searchArticleVO.setArticle_count(3);
        Article[] articles = new Article[3];
        for (int i = 0; i < articles.length; i++) {
            articles[i] = new Article();
            articles[i].setId(12345);
            articles[i].setDesc("除夕是团圆的日子，某种意义上，过去一整年的欢笑、欣慰、苦痛、辛酸都是为了今天。为了今天，腊八的蒜、小年的饭、门上的福、元宵的灯，甚至还有回家的车票……一切都会被早早准备好。街上卖春联的小摊摆出来的时候，或许就是春节最初的起点。");
            articles[i].setTitle("年味｜有了这些，才过了年");
            articles[i].setPic_url("http://mmbiz.qpic.cn/mmbiz_jpg/l9iadYXd83Z4mOaohfrSJZ8tczmsMicYHic3FiaT2wHcxetSibEba9ia7cmcAMsZUbEaIjwIH32o6EhtRFEwxbrI5iaicg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
            articles[i].setUrl("http://mp.weixin.qq.com/s?__biz=MzA3NzAzMDEyNg==&mid=2650825896&idx=1&sn=6f8f467a98c2f7c20dddb6230f355ace&chksm=84acf329b3db7a3fb12226761cce0cee735f18d6b8c4525fe7a9f6ce526b4bc4ed41c897488f#rd");
        }
        searchArticleVO.setArticles(articles);
        return searchArticleVO;
    }

    //换一批
    @RequestMapping(value = "/replace", method = RequestMethod.GET)
    @ResponseBody
    public BasicVO replace() {
        SearchArticleVO searchArticleVO = pkuYouthService.replaceArticle();
        return searchArticleVO;
    }
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
}
