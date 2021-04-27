package org.healthcare.remedies.homeremediesonline.dao;
 
import org.healthcare.remedies.homeremediesonline.entity.Account;
import org.healthcare.remedies.homeremediesonline.entity.SignUp;
import org.healthcare.remedies.homeremediesonline.model.OrderInfo;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
 
public interface AccountDAO {
 
    
    public Account findAccount(String userName );

	public PaginationResult<SignUp> listUserInfo(int page, int mAX_RESULT, int mAX_NAVIGATION_PAGE);

    
    
}