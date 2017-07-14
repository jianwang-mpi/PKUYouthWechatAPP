package testboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WangJian on 2017/2/18.
 */
public class Test {
    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\IdeaProjects\\PKUYouthWechatAPP\\article.html")));
        StringBuilder articleBuilder = new StringBuilder();
        String temp = null;
        while((temp = bufferedReader.readLine())!=null){
            articleBuilder.append(temp);
            // articleBuilder.append("\n");
        }
        String article = articleBuilder.toString();
        String regex = "(<p(.*?)>(.*?)</p>)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(article);
        while(matcher.find()){
            String find = matcher.group();
            System.out.println(find);
        }
    }
}
