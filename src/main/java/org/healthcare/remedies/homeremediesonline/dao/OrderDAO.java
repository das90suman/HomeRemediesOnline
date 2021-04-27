package org.healthcare.remedies.homeremediesonline.dao;
 
import java.util.List;
 
import org.healthcare.remedies.homeremediesonline.model.CartInfo;
import org.healthcare.remedies.homeremediesonline.model.OrderDetailInfo;
import org.healthcare.remedies.homeremediesonline.model.OrderInfo;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
 
public interface OrderDAO {
 
    public void saveOrder(CartInfo cartInfo);
 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage,String userName);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
 
}