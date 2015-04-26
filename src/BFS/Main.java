package BFS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tbking
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Main {
    String goal="";
    Scanner scan=new Scanner(System.in);
    Queue<String> agenda = new LinkedList<>();    // Use of Queue Implemented using LinkedList for Storing All the Nodes in BFS.
    Map<String,Integer> stateDepth = new HashMap<>(); // HashMap is used to ignore repeated nodes
    Map<String,String> stateHistory = new HashMap<>(); // relates each position to its predecessor

    public static void main(String args[]){

        Main e = new Main();              // New Instance of the EightPuzzle
        System.out.println("Enter the initial state");
        String str=e.scan.next();                                 // Input the Board State as a String with 0 as the Blank Space
        System.out.println("Enter the goal state");
        e.goal=e.scan.next();                                 // Input the Board State as a String with 0 as the Blank Space
        e.add(str, null);                                                   // Add the Initial State
        while(!e.agenda.isEmpty()){
            String currentState = e.agenda.remove();
            e.up(currentState);                                       // Move the blank space up and add new state to queue
            e.down(currentState);                                     // Move the blank space down
            e.left(currentState);                                     // Move left
            e.right(currentState);                          // Move right and remove the current node from Queue
        }

        System.out.println("Solution doesn't exist");
    }

    //Add method to add the new string to the Map and Queue
    void add(String newState, String oldState){
        if(!stateDepth.containsKey(newState)){
            int newValue = oldState == null ? 0 : stateDepth.get(oldState) + 1;
            stateDepth.put(newState, newValue);
            agenda.add(newState);
            stateHistory.put(newState, oldState);
        }
    }

    /* Each of the Methods below Takes the Current State of Board as String. Then the operation to move the blank space is done if possible.
      After that the new string is added to the map and queue.If it is the Goal State then the Program Terminates.
     */
    void up(String currentState){
        int a = currentState.indexOf("0");
        if(a>2){
            String nextState = currentState.substring(0,a-3)+"0"+currentState.substring(a-2,a)+currentState.charAt(a-3)+currentState.substring(a+1);
            checkCompletion(currentState, nextState);
        }
    }

    void down(String currentState){
        int a = currentState.indexOf("0");
        if(a<6){
            String nextState = currentState.substring(0,a)+currentState.substring(a+3,a+4)+currentState.substring(a+1,a+3)+"0"+currentState.substring(a+4);
            checkCompletion(currentState, nextState);
        }
    }
    void left(String currentState){
        int a = currentState.indexOf("0");
        if(a!=0 && a!=3 && a!=6){
            String nextState = currentState.substring(0,a-1)+"0"+currentState.charAt(a-1)+currentState.substring(a+1);
            checkCompletion(currentState, nextState);
        }
    }
    void right(String currentState){
        int a = currentState.indexOf("0");
        if(a!=2 && a!=5 && a!=8){
            String nextState = currentState.substring(0,a)+currentState.charAt(a+1)+"0"+currentState.substring(a+2);
            checkCompletion(currentState, nextState);
        }
    }

    private void checkCompletion(String oldState, String newState) {
        add(newState, oldState);
        if(newState.equals(goal)) {
            System.out.println("Solution Exists at Level "+stateDepth.get(newState)+" of the tree");
            String traceState = newState;
            while (traceState != null) {
                String temp="";
                for(int i=0;i<9;i++)
                {
                    temp+=traceState.substring(i, i+1)+" ";
                    if(i==2||i==5||i==8)
                    {
                        temp+="\n";
                    }
                }
                System.out.println(temp);
                traceState = stateHistory.get(traceState);
            }
            System.exit(0);
        }
    }

}
