package com.glodon.aihome.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewsTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(NewsTest.class.getClassLoader().getResourceAsStream("news/all.txt")));
        System.out.println(bf.readLine().split("\\*\\*\\*")[0]);
    }

}
