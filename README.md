# CS187

My code for the assigned projects of CS187, a course that introduces and develops methods for designing and implementing abstract data types using Java. Project overviews taken from the instructions provided by the professor, as well as the grade I received on each project, are provided below.

Note that the majority of code that I have written would be under the TODO sections of each project, though there are helper methods and classes that are not under the TODOs.

## Getting Started



Grade: 12/12

## RLE Encoding

For this project, you will implement Run Length Encoding, or RLE, an algorithm that encodes ascii data in a compressed format. Given a text file containing an image constructed with ascii characters, RLE will encode the file in compressed form. Given a file with data in RLE compressed form, you will be able to decompress the file and recover the exact image that was originally compressed. You will use arrays as the basic data structures to implement the RLE compression and decompression algorithms.

Grade: 60/60

## Bucket Sort


Grade: 95/95

## Linked Lists

In this project, you will write a linked-based implementation for the List abstract data type, as specified in ListInterface.java. You’ll be building a singly linked list that can act as a drone route. The requirement is all methods you implement must use recursion and you are NOT allowed to use any loop (such as for, while, do-while). You must also stay within Big-O runtime bounds so that the drones are efficient in delivering life essentials.

Grade: 145/150; TODO sections in RecursiveList.java

## Postfix Evaluator

For this assignment, you will implement an evaluator for postfix expressions. Your evaluator will be stack-based, and capable of evaluating correctly formed but otherwise arbitrary arithmetic expressions on integers. You will implement the stack using a linked list.

Grade: 50/50; TODO sections in ArithPostfixEvaluator.java, language folder, and LinkedStack.java

## Scapegoat Tree

In this assignment, you’ll start with a codebase for binary search trees similar to that presented in lecture. You’ll need to implement several additional methods for binary search trees. Then, you’ll subclass the binary search tree to create a “scapegoat tree” — a simple form of a self-balancing binary search tree.

Grade: 111/115; TODO sections in BinarySearchTree.java, and ScapegoatTree.java

## Priority Queue

For this project, you will implement a futuristic automated emergency room (ER) triage system. Your system will receive data in the form of patient and injury pairs over time and automatically assign patients to doctors as the resources become available depending on the severity of the patient’s injury. To accomplish this, you will implement a data structure called a priority queue – more specifically, a heap-based priority queue.

Grade: 65/65; TODO sections in Heap.java

## Graph Coloring

Many real-world problems can be represented by graphs and solved by applying various algorithms to the graph (GPS navigation, utility networks, social networks are just some of the systems that rely on graphs). In this assignment, you will implement a graph structure and a graph coloring algorithm. You will then use the graph and graph-coloring algorithm to solve various problems involving conflict resolution and finding an optimal assignment of resources to accomplish a task or goal.

Grade: 48/48; TODO sections in UndirectedUnweightedGraph.java
