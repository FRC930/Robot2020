package frc.robot.utilities;

import org.junit.Assert;

import static frc.robot.utilities.DeadbandMath.DeadbandZone;
import static frc.robot.utilities.DeadbandMath.ShotChance;


import org.junit.Test;

/**
 * DeadbandMathTest
 */
public class DeadbandMathTest {

    //Values were found using Desmos, plugging in random values to fit the outcome wanted.
    //Deadband: https://www.desmos.com/calculator/zczdd2trpi
    //Target Size/Shot Chance: https://www.desmos.com/calculator/eqrhvubq9t
    
    @Test
    public void testSet1() {
        DeadbandMath deadbandMath = DeadbandMath.getInstance();
        // Values are set and get methods are called separate.
        deadbandMath.setPosition(0, 8.25);
        Assert.assertEquals(DeadbandZone.GREEN, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(10, 8.25);
        Assert.assertEquals(DeadbandZone.GREEN, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(-12, 11);
        Assert.assertEquals(DeadbandZone.GREEN, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(24, 3.7);
        Assert.assertEquals(DeadbandZone.GREEN, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(-22, 8.6);
        Assert.assertEquals(DeadbandZone.GREEN, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
    }

    @Test    
    public void testSet2() {
        DeadbandMath deadbandMath = DeadbandMath.getInstance();
        // Values are set and get methods are called separate.
        deadbandMath.setPosition(-30, 5.7);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(27, 4.5);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(32, 2.3);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(-23, 9.6);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(33, 3.8);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        deadbandMath.setPosition(-34, 2.7);
        Assert.assertEquals(DeadbandZone.YELLOW, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.HIGH, deadbandMath.getShotChance());
        }

    @Test
    public void testSet3() {
        DeadbandMath deadbandMath = DeadbandMath.getInstance();
        // Values are set and get methods are called separate.
        deadbandMath.setPosition(-62, 3.2);
        Assert.assertEquals(DeadbandZone.RED, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.LOW, deadbandMath.getShotChance());
        deadbandMath.setPosition(80, 2.2);
        Assert.assertEquals(DeadbandZone.RED, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.MISS, deadbandMath.getShotChance());
        deadbandMath.setPosition(65, 2.2);
        Assert.assertEquals(DeadbandZone.RED, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.LOW, deadbandMath.getShotChance());
        deadbandMath.setPosition(-86, 4.7);
        Assert.assertEquals(DeadbandZone.RED, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.MISS, deadbandMath.getShotChance());
        }

    @Test
    public void testSet4() {
        DeadbandMath deadbandMath = DeadbandMath.getInstance();
        // Values are set and get methods are called separate.
        deadbandMath.setPosition(90, 3);
        Assert.assertEquals(DeadbandZone.ERROR, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.ERROR, deadbandMath.getShotChance());
        deadbandMath.setPosition(-17, 20);
        Assert.assertEquals(DeadbandZone.ERROR, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.ERROR, deadbandMath.getShotChance());
        deadbandMath.setPosition(20, -10);
        Assert.assertEquals(DeadbandZone.ERROR, deadbandMath.getDeadbandZone());
        Assert.assertEquals(ShotChance.ERROR, deadbandMath.getShotChance());
        deadbandMath.setPosition(-46, 12);
        }

}