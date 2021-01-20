package com.codecool.breadth_first_search;

import com.codecool.breadth_first_search.finder_methods.FriendChainCalculator;
import com.codecool.breadth_first_search.finder_methods.FriendsOfFriendsFinder;
import com.codecool.breadth_first_search.model.UserNode;

import java.util.*;

public class BreadthFirstSearch {
    private static List<UserNode> users;
    private static GraphPlotter graphPlotter;
    private final static StringBuilder MENU = new StringBuilder()
        .append("(1) Minimum handshakes\n")
        .append("(2) Friends of Friends\n")
        .append("(3) Shortest routes\n")
        .append("(other key) Quit\n");

    public static void main(String[] args) {
        initSocialGraph();
        graphPlotter = new GraphPlotter(users);

        System.out.println("Done!");

        boolean running = true;

        while(running) {
            System.out.println(MENU);
            Scanner reader = new Scanner(System.in);

            int menuChoice;

            try{
                menuChoice = reader.nextInt();
            } catch (InputMismatchException e) {
                break;
            }

            switch (menuChoice) {
                case 1:
                    minimumConnections();
                    break;
                case 2:
                    friendsOfFriends();
                    break;
                case 3:
                    shortestRoute();
                    break;
                default:
                    running = false;
                    break;

            }
        }
        System.exit(0);
    }

    private static void minimumConnections() {
        List<List<UserNode>> result = getShortestRoute();

        graphPlotter.highlightRoute(result.get(0));
        int minConnections = result.get(0).size()-1;
        System.out.format("\nMinimum connections: %d\n\n", minConnections);
    }

    private static void shortestRoute() {
        List<List<UserNode>> result = getShortestRoute();

        graphPlotter.highlightRoute(result.get(0));
        printRoutes(result);
        int distance = result.get(0).size()-2;
        System.out.format("\nMinimum connections: %d\n\n", distance);
    }

    private static void friendsOfFriends() {
        int mainUser = -1;
        int distance = -1;

        while((mainUser<0||distance<0)||(mainUser>=users.size()||distance>=10)) {
            Scanner reader = new Scanner(System.in);

            try{
                System.out.println("Main user Id");
                mainUser = reader.nextInt();
                System.out.println("Distance");
                distance = reader.nextInt();
                if(distance>10) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Try another combination.");
            }

            UserNode user = users.get(mainUser);
            Set<UserNode> allFriends = FriendsOfFriendsFinder.getFriendsOfFriends(user, distance);
            System.out.format("All friends of %s: %s\n\n", user.toString(), allFriends.toString());
            visualizeFriendCircle(allFriends, user);
        }
    }

    private static List<List<UserNode>> getShortestRoute() {
        int startUserId = -1;
        int endUserId = -1;

        while((startUserId<0 || endUserId<0)||(startUserId>=users.size()||endUserId>=users.size())) {
            Scanner reader = new Scanner(System.in);

            try{
                System.out.println("Start user Id");
                startUserId = reader.nextInt();
                System.out.println("End user Id");
                endUserId = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("The person does not exist. Give another id.");
            }
        }

        UserNode startUser = users.get(startUserId);
        UserNode endUser = users.get(endUserId);

        return FriendChainCalculator.getShortestRoutesBetween(startUser, endUser).get();
    }

    private static void initSocialGraph() {
        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();
    }

    private static void visualizeFriendCircle(Set<UserNode> friendCircle, UserNode user) {
        graphPlotter.highlightNodes(friendCircle, user);
    }

    private static void printRoutes(List<List<UserNode>> routes) {
        for(List<UserNode> nodes : routes) {
            System.out.print("\nRoute with " + nodes.size() + " steps:");
            for(int i = 0, nodesSize = nodes.size(); i < nodesSize; i++) {
                UserNode node = nodes.get(i);
                System.out.print(" " + node.getId());
                if(i < nodesSize - 1) {
                    System.out.print(" ->");
                }
            }
        }
        System.out.println();
    }
}
