package com.example.rapha.alo;

import java.util.HashMap;

public class MorseTree {
    private String alphabet;
    private Node current;
    private Node left;
    private Node right;
    private Node parent;
    private Node[] nodes;
    private HashMap<Character, Node> map;
    public String letterList;

    public MorseTree() {
        alphabet = "_etianmsurwdkgohvf_l_pjbxcyzq__54_3___2__+____16=/_____7___8_90";
        map = new HashMap<>();
        int i;
        nodes = new Node[63];

        for (i = nodes.length - 1; i >= nodes.length / 2; i--) {
            //System.out.println("currentIndex:" + i + " | " + "parentIndex:" + parentIndex2);
            nodes[i] = new Node(i, alphabet.charAt(i), null, null, null);
//            System.out.println("indice: " + i + " | " + " letra: " + nodes[i].getChar());
            map.put(alphabet.charAt(i), nodes[i]);
        }

        for (i = nodes.length / 2 - 1; i >= 0; i--) {

            if (i == 0) {
                nodes[i] = new Node(i, alphabet.charAt(i), nodes[(2 * i) + 1], nodes[(2 * i) + 2], null);
            } else {
                nodes[i] = new Node(i, alphabet.charAt(i), nodes[(2 * i) + 1], nodes[(2 * i) + 2], null);
            }
//            System.out.println("indice: " + i + " | " + " letra: " + nodes[i].getChar());
            map.put(alphabet.charAt(i), nodes[i]);
        }

        for (i = nodes.length - 1; i >= nodes.length / 2; i--) {
            Double parentIndex;
            Double j = (double) i/2;
            parentIndex = Math.ceil(j);
            int parentIndex2 = parentIndex.intValue() - 1;
            nodes[i].setParent(nodes[parentIndex2]);
        }
        for (i = nodes.length / 2 - 1; i >= 0; i--) {
            Double parentIndex;
            Double j = (double) i/2;
            parentIndex = Math.ceil(j);
            int parentIndex2 = parentIndex.intValue() - 1;
            if (i == 0) {
                nodes[i].setParent(null);
            } else {
                nodes[i].setParent(nodes[parentIndex2]);
            }
        }

    }

    public String codigo(char letter) {
        parent = current.getParent();
        String seq = new String();
        map.get(letter);
        while (parent != null) {
            if (current == parent.getLeft()) {
                return null;

            } else if (current == parent.getRight()) {
                //tra�o
                return null;
            }
        }
        return seq;
    }

    public char translate(String code) {
        String[] parts = code.split("");
        {
            current = nodes[0];
            left = current.getLeft();
            right = current.getRight();
            char translation = ' ';

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].matches("-")) {
                    System.out.println("é traço");
                    if (right.getChar() == '_') {
                        try {
                            if (right.getLeft().getChar() != '_' || right.getRight().getChar() != '_') {

                                current = right;
                                left = current.getLeft();
                                right = current.getRight();
                                translation = current.getChar();
                            } else {
                                translation = current.getChar();
                            }
                        } catch (NullPointerException e) {

                            current = right;
                            translation = current.getChar();
                        }
                    } else {
                        current = right;
                        left = current.getLeft();
                        right = current.getRight();
                        translation = current.getChar();

                    }
                } else if (parts[i].matches(".")) {
                    System.out.println("é ponto");
                    if (left.getChar() == '_') {
                        try {
                            if (left.getLeft().getChar() != '_' || left.getRight().getChar() != '_') {
                                current = left;
                                left = current.getLeft();
                                right = current.getRight();
                                translation = current.getChar();
                            } else {
                                translation = current.getChar();
                            }
                        } catch (NullPointerException e) {

                            current = left;
                            translation = current.getChar();
                        }
                    } else {
                        current = left;
                        left = current.getLeft();
                        right = current.getRight();
                        translation = current.getChar();

                    }
                }
            }
            System.out.println(translation);
            return translation;
        }
    }

    public Node[] getNodes(){
        return nodes;
    }
}



