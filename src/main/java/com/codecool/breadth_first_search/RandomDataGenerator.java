package com.codecool.breadth_first_search;

import com.codecool.breadth_first_search.model.UserNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomDataGenerator {
    private static final int CONNECTIVITY_STEP_THRESHOLD = 30;
    private static final int MAX_NUMBER_OF_FRIENDS_AT_START = 3;
    private static final int MAX_DISTANCE_FROM_FIRST = 4;

    private static Random random = new Random(1234);

    private String[] firstNames = {
    "Inez", "Emery", "Virginia", "Charissa", "Tyrone", "Ayanna", "Jena", "Ora",
    "Cooper", "Gareth", "Karleigh", "Aladdin", "Arden", "Pearl", "Mariko", "Hadley",
    "Tanya", "Madeline", "Naomi", "Maggie", "Kerry", "Elmo", "Wylie", "Alec",
    "Axel", "Belle", "Cally", "Theodore", "Emmanuel", "Christopher", "Olympia"};

    private String[] lastNames = {
    "Winifred", "Tanner", "Rajah", "Cedric", "Tyler", "Nicholas", "Abra", "Aurora",
    "Bryar", "Kibo", "Myles", "Hillary", "Lydia", "Dolan", "Lucian", "Prescott",
    "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"
    };

    List<UserNode> generate() {
        List<UserNode> users = new ArrayList<>();
        UserNode firstUser = createNewUser();
        users.add(firstUser);

        generateTree(firstUser, users, MAX_DISTANCE_FROM_FIRST);

        makeFriendShips(users);

        createDiamondRoute(users);

        return users;
    }

    /**
     * Create an extra user which connected to 2 users.
     * <p>
     * As a result between user(58) and user(116) there are multiple same length route
     * @param users social graph of users
     */
    private void createDiamondRoute(List<UserNode> users) {
        UserNode anotherUser = createNewUser();
        users.add(anotherUser);
        anotherUser.addFriend(users.get(0));
        anotherUser.addFriend(users.get(108));
    }

    private void makeFriendShips(List<UserNode> users) {
        for (int i = 0; i < users.size() - CONNECTIVITY_STEP_THRESHOLD; i++) {
            if (shouldConnect(i)) {
                users.get(i).addFriend(users.get(i + CONNECTIVITY_STEP_THRESHOLD));
            }
        }
    }

    private boolean shouldConnect(int indexOfUser) {
        return indexOfUser % 4 == 0;
    }

    /**
     * Generate and connect users in a star shaped tree
     * @param user first user
     * @param allUsers already generated users
     * @param depth length of friendchain from first user
     */
    private void generateTree(UserNode user, List<UserNode> allUsers, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < MAX_NUMBER_OF_FRIENDS_AT_START; i++) {
            UserNode newUser = createNewUser();
            user.addFriend(newUser);
            allUsers.add(newUser);
            generateTree(newUser, allUsers, depth - 1);
        }
    }

    private UserNode createNewUser() {
        return new UserNode(getRandomElement(firstNames), getRandomElement(lastNames));
    }

    private String getRandomElement(String[] array) {
        return array[random.nextInt(array.length)];
    }
}
