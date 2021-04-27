package org.healthcare.remedies.homeremediesonline.dao.impl;
 
import org.healthcare.remedies.homeremediesonline.dao.AccountDAO;
import org.healthcare.remedies.homeremediesonline.entity.Account;
import org.healthcare.remedies.homeremediesonline.entity.SignUp;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
 
// Transactional for Hibernate
@Transactional
public class AccountDAOImpl implements AccountDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public Account findAccount(String userName ) {

        Session session = sessionFactory.getCurrentSession();
     
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("userName", userName));
    
        Account uniqueResult = (Account) crit.uniqueResult();
		return uniqueResult;
    }
    
    @Override
    public PaginationResult<SignUp> listUserInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + SignUp.class.getName()//
                + "(ord.fname, ord.lname, ord.email, ord.contact, "
                + " ord.passwd, ord.cpasswd, ord.createDate) " + " from "
                + SignUp.class.getName() + " ord "//
                + " order by ord.createDate desc";
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
 
        return new PaginationResult<SignUp>(query, page, maxResult, maxNavigationPage);
    }
 
}