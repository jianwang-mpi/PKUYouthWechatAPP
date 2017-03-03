package pkuyouth.services;

import org.springframework.stereotype.Service;
import pkuyouth.requestobjects.ApproveObject;

/**
 * Created by WangJian on 2017/3/3.
 */
public interface ApproveService {
    void manageApprove(ApproveObject approveObject);
}
