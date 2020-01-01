package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class GameWorld {
    public GameObject player = new GameObject();
    public List<GameObject> asteroids = new LinkedList<GameObject>();
    public List<GameObject> bullets = new LinkedList<GameObject>();
}
