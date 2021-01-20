# Six handshakes

## Story

There is a theory, actually [an already proven theory](https://www.theguardian.com/technology/2008/aug/03/internet.email),
which states that all people around the globe are six or fewer social connections away from each other.
This means that a maximum 6 long chain of friends can be found between any two people.

This idea is called [six degrees of separation](https://en.wikipedia.org/wiki/Six_degrees_of_separation)
or the **6-handshakes rule**. It was originally set out by Frigyes Karinthy, Hungarian writer,
in 1929 and appears in many forms in popular culture.

Now it's your turn to simulate this theory and find answers for some interesting questions on a smaller social graph of connected friends. See below:

![breadth-first-search.png](/media/algorithms/breadth-first-search-graph.png)

## What are you going to learn?

- How to implement a graph
- How to go through all the nodes of a graph
- How to find the shortest path between two nodes in a graph
- Learn about the queue abstract data type

## Tasks

1. There is a function which calculates the minimum handshakes between two given person
    - There is a menu point named `Minimum handshakes` which I can choose at start
    - After choosing the Minimum handshakes menu the system asks 2 names on by one.
- the application re-asking the names while I type two proper values which are registered on the social network
- if I enter a value which doesn't exist in the graph the system signals it with a message:
`The person does not exist. Give another name.`
    - The handshakes between two direct friends is equal to 1
    - The distance or number of handshakes between two given person is (n+1) where n is the number of person between them.
For example on the previously showed graph:
- handshakes between _Axel Lucian_ and _Alice Aurora_ is 2 because they have a common friend named _Cally Tanner_

2. There is a function which list a given person's friends-of-friends at a given distance.
    - There is a menu point named `Friends of friends` which I can choose at start
    - After choosing the Friends of friends menu the system asks one name and the distance
- the application re-asking the name while I type a proper value which is registered on the social network
- if I enter a value which doesn't exist in the graph the system signals it with a message:
`The person does not exist. Give another name.`
- the distance can be only positive integer
    - The list should not contain duplicates
    - Results a list of people who are at maximum given distance from the requested person.
For example on the previously showed graph:
- friends of friends with distance 2 about _Emery Myles_ is: _[Olympia Lydia, Emmanual Winfried, Cally Tanner, Maggie Dolan]_

3. There is a function which list the shortest paths between two given person.
    - There is a menu point named `Shortest routes` which I can choose at start
    - After choosing the Shortest routes menu the system asks 2 names one by one.
- the application re-asking the names while I type two proper values which are registered on the social network
- if I enter a value which doesn't exist in the graph the system signals it with a message:
`The person does not exist. Give another name.`
    - If their distance is `dist` then each path is a list that contains `(dist + 1)` users displaying how the "friend chain" goes.
For example:
- if `A` and `B` are not friends but they have `E` and `F` as common friends
- their distance is `2`
- and `shortestPaths(A,   B)` should return
`[ [A, E, B], [A, F, B] ]`
Or take the given image of the graph:
- shortest path between _Christopher Myles_ and _Chrisopher Hillary_ is: _[Christopher Myles, Naomi Lydia, Jena Lucian, Chrisopher Hillary]_

## General requirements

- After choosing a finder menu and enter valid input data the system visualizes the results.

## Hints

- You have the following data structure:
  ```
  **UserNode**
  id: The unique ID of the user
  firstName
  lastName
  friends: A list or array of IDs that this user is friends with
  ```
  Feel free to add new properties or methods to the `UserNode`
- The user data is stored in plain Java objects, you won't need a database.
  There is a `RandomDataGenerator` class for generating sample data
  for the exercise.
- You can use the provided visualizer class to help debug your code


## Starting your project



## Background materials

- [Graphs](/pages/data-structures/graphs.md)
- [Graph representation](https://www.hackerearth.com/practice/algorithms/graphs/graph-representation/tutorial/)
- [Adjacency list](https://en.wikipedia.org/wiki/Adjacency_list)
- [Adjacency matrix](https://en.wikipedia.org/wiki/Adjacency_matrix)
- [Queue](<https://en.wikipedia.org/wiki/Queue_(abstract_data_type)>)
