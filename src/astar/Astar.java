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
package astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author tbking
 */
public class Astar {

    public static HashMap<String,Integer> gmap=new HashMap<>();
    public static HashMap<String,Integer> hmap=new HashMap<>();
    public static HashMap<String,Integer> fmap=new HashMap<>();
    public static ArrayList<String> closed=new ArrayList<>();
    static String init,goal;
    static int g=-1,gUpdated;
    
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Initial State");
        init=sc.next();
        System.out.println("Enter the Goal State");
        goal=sc.next();
        check(init);
        String selected ="";
        while(!selected.equals(goal))
        {
            System.out.println("G = "+gmap.toString());
            System.out.println("H = "+hmap.toString());
            System.out.println("F = "+fmap.toString()+"\n");
            int minF=getMinF();
            for(String k :fmap.keySet())
            {
                if(fmap.get(k).equals(minF))
                {
                    fmap.remove(k);
                    gmap.remove(k);
                    hmap.remove(k);
                    System.out.println("Selected Successor: "+k);
                    closed.add(k);
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
        int newF=calcF(state);
        if(fmap.containsKey(state))
        {
            if(fmap.get(state)>newF)
            {
                fmap.remove(state);
                hmap.remove(state);
                gmap.remove(state);
                fmap.putIfAbsent(state, newF);
                hmap.putIfAbsent(state, newF);
                gmap.putIfAbsent(state, newF);
            }
        }
        else if(!closed.contains(state))
        {
            fmap.putIfAbsent(state,newF);
        }
        if(state.equals(goal))
        {
            System.out.println("G = "+gmap.toString());
            System.out.println("H = "+hmap.toString());
            System.out.println("F = "+fmap.toString()+"\n");
            System.out.println("Goal State found : "+goal);
            System.exit(0);
        }
    }

    private static Integer calcF(String state)
    {
        int meraH=calcH(state);
        hmap.putIfAbsent(state, meraH);
        int meraG=calcG(state);
        gmap.putIfAbsent(state, meraG);
        int meraF=meraH+meraG;
         return meraF;
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
    private static Integer calcG(String state)
    {
        int ge;
        if(gUpdated==0)
        {
            g++;
            gUpdated=1;
        }
        ge=g;
         return ge;
    }

    private static int getMinF()
    {
        int fx=Integer.MAX_VALUE;
        for(Integer a : fmap.values())
        {
            if(a<fx)
            {
                fx=a;
            }
        }
        return fx;
    }
    
    private static void up(String currentState)
    {
        gUpdated=0;
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
