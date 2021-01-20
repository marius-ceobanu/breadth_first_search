package com.codecool.breadth_first_search.finder_methods;

import com.codecool.breadth_first_search.model.UserNode;

import java.util.*;

public class FriendChainCalculator {
    public static Optional<List<List<UserNode>>> getShortestRoutesBetween(UserNode start, UserNode end) {
        List<List<UserNode>> routes = new ArrayList<>();
        routes.add(getRoutes(start, end));
        return Optional.of(routes);
    }

    public static List getRoutes(UserNode start, UserNode finish) {
        Map<UserNode, Boolean> visited = new HashMap<>();
        Map<UserNode, UserNode> previous = new HashMap<>();
        LinkedList<UserNode> directions = new LinkedList<>();
        Queue<UserNode> q = new LinkedList<>();
        UserNode current = start;

        q.add(current);
        visited.put(current, true);

        while (!q.isEmpty()) {
            current = q.remove();
            if(current.equals(finish)) {
                break;
            } else {
                for(UserNode node : current.getFriends()) {
                    if(!visited.containsKey(node)) {
                        q.add(node);
                        visited.put(node, true);
                        previous.put(node, current);
                    }
                }
            }
        }

        if(!current.equals(finish)) {
            System.out.println("can't reach destination!");
        }

        for(UserNode node = finish; node != null; node = previous.get(node)) {
            directions.add(node);
        }

        Collections.reverse(directions);

        return directions;
    }
}
