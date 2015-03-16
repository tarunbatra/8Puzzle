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

package BestFirst;

import java.util.HashMap;
import java.util.Scanner;


public class Main
{
    public static HashMap<String,Integer> tab=new HashMap<>();
    static String init,goal;
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Initial State");
        init=sc.next();
        System.out.println("Enter the Goal State");
        goal=sc.next();
        check(init);
        tab.put(init,calcH(init));
        String selected ="";
        while(!selected.equals(goal))
        {
            System.out.println(tab.toString());
            int minH=getMinH();
            for(String k :tab.keySet())
            {
                if(tab.get(k).equals(minH))
                {
                    tab.remove(k);
                    selected=k;
                    break;
                }
            }
            up(selected);
            down(selected);
            left(selected);
            right(selected);
        }
    }

    private static void check(String state) 
    {
        tab.putIfAbsent(state,calcH(state));
        if(state.equals(goal))
        {
            System.out.println(tab.toString());
            System.out.println("Goal State found !");
            System.exit(0);
        }
    }

    private static Integer calcH(String state)
    {
        int heu=0;
        for(int i=0;i<state.length();i++)
        {
            if(state.charAt(i)!=goal.charAt(i))
            {
                heu++;
            }
        }
         return heu;
    }

    private static int getMinH()
    {
        int heu=Integer.MAX_VALUE;
        for(Integer a : tab.values())
        {
            if(a<heu)
            {
                heu=a;
            }
        }
        return heu;
    }
    
    private static void up(String currentState)
    {
        int a = currentState.indexOf("0");
        if(a>2)
        {
            String nextState = currentState.substring(0,a-3)+"0"+currentState.substring(a-2,a)+currentState.charAt(a-3)+currentState.substring(a+1);
            check(nextState);
        }
    }

    private static void down(String currentState)
    {
        int a = currentState.indexOf("0");
        if(a<6)
        {
            String nextState = currentState.substring(0,a)+currentState.substring(a+3,a+4)+currentState.substring(a+1,a+3)+"0"+currentState.substring(a+4);
            check(nextState);
        }
    }
    private static void left(String currentState)
    {
        int a = currentState.indexOf("0");
        if(a!=0 && a!=3 && a!=6)
        {
            String nextState = currentState.substring(0,a-1)+"0"+currentState.charAt(a-1)+currentState.substring(a+1);
            check(nextState);      
        }
    }
    private static void right(String currentState)
    {
        int a = currentState.indexOf("0");
        if(a!=2 && a!=5 && a!=8)
        {
            String nextState = currentState.substring(0,a)+currentState.charAt(a+1)+"0"+currentState.substring(a+2);
            check(nextState);       
        }
    }
}