# abstract-tree
Custom tree project with vertical and horizontal traversal.


The tree has the following structure if filled with sequential numbers from 1 to 16 (as shown in the Solution.class):

![123](https://user-images.githubusercontent.com/90202470/132716427-9fcdf627-01ae-4d2d-98ac-bc63f2c5acbe.png)

When you delete one parent node, the entire branch of this node is completely deleted. 
So when deleting node "3", nodes "7", "8", "15", "16" will be deleted.

When adding a new node, the algorithm searches for the node closest to the beginning that does not have a child and becomes its child. If two nodes are equal in distance from the beginning, the algorithm chooses to become the child of the left node. 
So if you add the following node at the initial tree structure, it will take the place of the left child node "8".
If a new node is added after deleting the "3" node, it will take its place.
