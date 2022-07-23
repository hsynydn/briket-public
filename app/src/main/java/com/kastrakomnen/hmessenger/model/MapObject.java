package com.kastrakomnen.hmessenger.model;

/**
 *  All Patterns are MapObjects
 *      Patterns invades map
 *      Patterns may include coin boxes (visible), when destroyed coin earned
 *      When Patterns make a row those row destroyed
 *
 *  Bomb Object
 *      Invades 1 Square
 *      Whenever stop auto moving, destroy all neighbours in 1 square
 *      When to generate?
 *          Maybe auto generated
 *          Maybe time generated
 *          Player can buy with coins collected in game
 *
 *  HBomb
 *      Invades 2 Square Horizontal
 *      Whenever stop auto moving, destroy the row
 *      When to generate?
 *          Maybe auto generated
 *          Maybe time generated
 *          Player can buy with coins collected in game
 *
 *  StoneBricks
 *      Invades 1 Square
 *      Whenever stop automoving, cannot be destroyed
 *
 *  CoinBricks
 *      Invades 1 Square
 *      Generally not alone, embedded in patterns
 *      Player can collect coins by exploding this bricks
 */

public interface MapObject {

}
