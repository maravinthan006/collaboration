package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {
List<User> getSuggestedUsers(User user);
public void friendRequest(String from, String to);
public List<Friend> pendingRequests(String toUsername);
void updatePendingRequest(String from, String username, char status);
public List<Friend> listOfFriends(String username);
}