package com.tracking.panel.dao;

import com.tracking.panel.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	public User getActiveUser(String userName) {
		User activeUserInfo = new User();
		Boolean enabled = true;
		List<?> list = entityManager.createQuery("SELECT u FROM User u WHERE email=? AND active=?")
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		if(!list.isEmpty()) {
			activeUserInfo = (User)list.get(0);
		}
		return activeUserInfo;
	}
}