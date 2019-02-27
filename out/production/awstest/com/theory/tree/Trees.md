# Trees

```
        7                ^            
      /  \               |
     /    \              | 
    4      10            | H = 3     ln(n+1) 
  /  \    /  \           | n = 9   
 1    5   9   15         |      
             /  \        |       
             12  18      ^         
```

## Properties BT

The maximum number of nodes at level ‘l’ = 2l-1.

Maximum number of nodes = 2h – 1.
Here h is height of a tree. Height is considered 
as is maximum number of nodes on root to leaf path

Minimum possible height =  ceil(Log2(n+1))   

In Binary tree, number of leaf nodes is always one 
more than nodes with two children.

Time Complexity of Tree Traversal: O(n)


### Complexity BT
- Insert O(ln(n)) worst O(n)
- Search O(ln(n)) worst O(n)
- Delete O(ln(n)) worst O(n)

## BST

## Properties
Search :  O(h)
Insertion : O(h)
Deletion : O(h)
Extra Space : O(n) for pointers

h: Height of BST
n: Number of nodes in BST

If Binary Search Tree is Height Balanced, 
then h = O(Log n) 

Self-Balancing BSTs such as AVL Tree, Red-Black
Tree and Splay Tree make sure that height of BST 
remains O(Log n)

### Complexity BST (dynamically balanced)
insertion O(Log n)
deletion O(Log n)
## Heap

### Properties
Get Minimum in Min Heap: O(1) [Or Get Max in Max Heap]
Extract Minimum Min Heap: O(Log n) [Or Extract Max in Max Heap]
Decrease Key in Min Heap: O(Log n)  [Or Extract Max in Max Heap]
Insert: O(Log n) 
Delete: O(Log n)

## Types
- AVL
- BST
- HEAP