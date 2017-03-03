package pkuyouth.services.impl;

import org.springframework.stereotype.Service;
import pkuyouth.dao.ApproveMapper;
import pkuyouth.requestobjects.ApproveObject;
import pkuyouth.services.ApproveService;

import javax.annotation.Resource;

/**
 * Created by WangJian on 2017/3/3.
 */
@Service
public class ApproveServiceImpl implements ApproveService {
    @Resource
    ApproveMapper approveMapper;

    @Override
    public void manageApprove(ApproveObject approveObject) {
        String user_id = approveObject.getUser_id();
        Integer article_id = Integer.parseInt(approveObject.getArticle_id());
        if (approveMapper.findApprove(user_id, article_id) <= 0) {
            approveMapper.approve(user_id, article_id);
        } else {
            approveMapper.deleteApprove(user_id, article_id);
        }
    }
}
