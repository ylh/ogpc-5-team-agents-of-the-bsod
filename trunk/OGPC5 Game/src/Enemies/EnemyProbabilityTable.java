/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

/**
 *
 * @author Nekel_Seyew
 */
public class EnemyProbabilityTable {
    private double[] probabilities;
    
    public EnemyProbabilityTable(){
        probabilities=new double[12];
    }
    
    /**
     * gives the probability of the enemy being spawned.
     * @param enemy the int value of the enemy as given by a static variable in Enemy
     * @return will return as a number in between 0 and 100
     */
    public double getProbability(int enemy){
        return probabilities[enemy];
    }
    
    /**
     * Sets the probability. If the passed in value is in between 0 and 1, will be multiplied by 100
     * @param enemy the int value of the enemy as given by a static variable in Enemy
     * @param probability 
     */
    public void setProbability(int enemy, double probability){
        if(probability>0 && probability<1){
            probabilities[enemy]=probability*100;
        }else{
            probabilities[enemy]=probability;
        }
    }
}
