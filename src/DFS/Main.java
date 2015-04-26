/*
 * Copyright 2015 tbking.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package DFS;

import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author tbking
 */
public class Main
{
        int level=0;
        Stack<String> states = new Stack<>();
        Stack<String> op = new Stack<>();
        
    public static void main(String[] args)
    {
        Main obj = new Main();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the initial state");
        String initial = sc.nextLine();
        System.out.println("Enter the goal state");
        String goal = sc.nextLine();
        obj.states.push(initial);
            System.out.println("Initial configuration of the stack:");
            System.out.println(obj.states.toString());
        obj.pushOp();
            System.out.println("Initial configuration of the operations:");
            System.out.println(obj.op.toString());
            
            
        String operation,state;
        while(!obj.op.isEmpty() && !obj.states.isEmpty())
        {
            operation =obj.op.pop();
            System.out.println("Operation performed: "+operation);
                state = obj.genState(obj.states.peek(),operation);
                System.out.println("State generated : "+state);
                //System.out.println(obj.op.toString());
            if(state != null)
            {
                if(state.equals(goal))
                {
                    obj.level++;
                    System.out.println("Level: "+obj.level);
                    System.exit(0);
                }
                if(obj.states.contains(state))
                {
                    System.out.println("State already there.");
                    continue;
                }
                
                obj.op.push("backtrack");
                obj.level++;
                System.out.println("Level: "+obj.level);
                obj.pushOp();
                obj.states.push(state);
                System.out.println(obj.states.toString()+"\n"+obj.op.toString()+"\n\n");
            }
            
        }
        
    }
    
    public String genState(String state, String oper)
    {
        String nextState=null;
        switch(oper)
        {
            case "up":
                nextState = up(state);
                break;
            case "down":
                nextState = down(state);
                break;
            case "left":
                nextState = left(state);
                break;
            case "right":
                nextState = right(state);
                break;
            case "backtrack":
                nextState = nullify(state);
                break;
        }
        return nextState;
    }
    
    public void pushOp()
    {
        op.push("left");
        op.push("right");
        op.push("down");
        op.push("up");
    }

    private String up(String currentState) {
        String  nextState=null;
        int a = currentState.indexOf("0");
         if(a>2)
        {
            nextState = currentState.substring(0,a-3)+"0"+currentState.substring(a-2,a)+currentState.charAt(a-3)+currentState.substring(a+1);
           
        }
        return nextState;
    }
//013425786
    private String down(String currentState) {
        String  nextState=null;
        int a = currentState.indexOf("0");
        if(a<6)
        {
            nextState = currentState.substring(0,a)+currentState.substring(a+3,a+4)+currentState.substring(a+1,a+3)+"0"+currentState.substring(a+4);
            
        }
            return nextState;
    }

    private String left(String currentState) {
        String  nextState=null;
        int a = currentState.indexOf("0");
        if(a!=0 && a!=3 && a!=6)
        {
           nextState = currentState.substring(0,a-1)+"0"+currentState.charAt(a-1)+currentState.substring(a+1);
                
        }   return nextState;
    }

    private String right(String currentState) {
        String  nextState=null;
        int a = currentState.indexOf("0");
        if(a!=2 && a!=5 && a!=8)
        {
            nextState = currentState.substring(0,a)+currentState.charAt(a+1)+"0"+currentState.substring(a+2);
                  
        }
            return nextState;
    }

    private String nullify(String currentState) {
        level-=2;
        op.pop();
        states.pop();
        return null;
    }
    
    private String swap(String str, int a, int b)
    {
        char[] charArray = str.toCharArray();
        char temp=charArray[a];
        charArray[a]=charArray[b];
        charArray[b]=temp;
        String swapped = new String(charArray);
        return swapped;
    }   
    
}