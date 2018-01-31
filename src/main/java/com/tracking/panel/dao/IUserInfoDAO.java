package com.tracking.panel.dao;

import com.tracking.panel.domain.User;


public interface IUserInfoDAO {
	User getActiveUser(String userName);
}