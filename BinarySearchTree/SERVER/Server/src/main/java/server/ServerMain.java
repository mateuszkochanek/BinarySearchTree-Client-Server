package server;

import binarytree.BinaryTree;

public class ServerMain 
{
    public static void main( String[] args )
    {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        binaryTree.insert(5);
        binaryTree.insert(2);
        binaryTree.insert(1);
        binaryTree.insert(3);
        binaryTree.insert(4);
        binaryTree.insert(9);
        binaryTree.insert(6);

    }
}
