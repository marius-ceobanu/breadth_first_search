package com.codecool.breadth_first_search.finder_methods;

import com.codecool.breadth_first_search.model.UserNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendsOfFriendsFinder {
    public static Set<UserNode> getFriendsOfFriends(UserNode user, int distance) {
        Queue<UserNode> q = new LinkedList<>();
        Set<UserNode> allFriends = new HashSet<>();

        q.add(user);

        for(int i = 0; i < distance; i++) {
            Queue<UserNode> queuedUsers = new LinkedList<>();
            queuedUsers.addAll(q);
            q.clear();

            for(UserNode queuedUser : queuedUsers) {
                allFriends.addAll(queuedUser.getFriends());
                q.addAll(queuedUser.getFriends());
            }
        }

        return allFriends;
    }
}
