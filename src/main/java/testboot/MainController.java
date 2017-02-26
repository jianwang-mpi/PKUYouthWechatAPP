package testboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WangJian on 2017/2/18.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    String test(){
        return "hello";
    }
}
