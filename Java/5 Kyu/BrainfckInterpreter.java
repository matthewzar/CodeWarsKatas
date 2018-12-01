//https://www.codewars.com/kata/526156943dfe7ce06200063e/
//Write an interpreter for the esoteric language Brainfuck

import java.util.*;

public class BrainLuck 
{
     String Instructions = "";
     String InputStream = "";
     int Pntr = 0;  
     String OutputString = "";
     int InstPointer = 0;
     List<Integer> Mem;
             
     
     public BrainLuck(String code) 
     {      
        this.Instructions = code;
        
        //http://www.programcreek.com/2013/04/how-to-convert-array-to-arraylist-in-java/
        Mem = new ArrayList<Integer>(Arrays.asList(new Integer[]{0}));
    
     }

     private void IncPntr()
     {
         this.Pntr++;
         if(this.Pntr >= this.Mem.size())
            this.Mem.add(0);
         this.InstPointer++;
     }
     
     private void DecPntr()
     {
         this.Pntr--;
         if(this.Pntr < 0)
         {
            this.Pntr = 0;
            this.Mem.add(0, 0);
         }
         this.InstPointer++;
     } 
     
     private void IncByte()
     {
        this.Mem.set(this.Pntr, (this.Mem.get(this.Pntr) + 1) % 256);
        this.InstPointer++;
     }
     
     private void DecByte()
     {
        this.Mem.set(this.Pntr, this.Mem.get(this.Pntr) - 1);
        if(this.Mem.get(this.Pntr) == -1)
            this.Mem.set(this.Pntr, 255);
        this.InstPointer++;
     }
     
     private void Output()
     {
         this.OutputString += (char)((int)this.Mem.get(this.Pntr));
         this.InstPointer++;
     }
     
     private void Input()
     {
         this.Mem.set(this.Pntr, (int)(this.InputStream.toCharArray()[0]));
         this.InputStream = this.InputStream.substring(1);
         this.InstPointer++;
     }
     
     private void JmpIfZero()
     {
         if(this.Mem.get(this.Pntr) != 0)
             this.InstPointer++;
         else
         {
             int matches = 1;
             while(true)
             {
                 this.InstPointer++;
                 if(this.Instructions.toCharArray()[this.InstPointer] == '[')
                    {matches++;}
                 if(this.Instructions.toCharArray()[this.InstPointer] == ']')
                    {matches--;}
                 if(matches == 0)
                 {
                     this.InstPointer++;
                     return;
                 }
             }   
         }
     }
     
     private void JmpIfNonZero()
     {
         if(this.Mem.get(this.Pntr) == 0)
             this.InstPointer++;
         else
         {
             int matches = 1;
             while(true)
             {
                 this.InstPointer--;
                 if(this.Instructions.toCharArray()[this.InstPointer] == '[')
                    {matches--;}
                 if(this.Instructions.toCharArray()[this.InstPointer] == ']')
                    {matches++;}
                 if(matches == 0)
                 {
                     this.InstPointer++;
                     return;
                 }
             }   
         }
     }
     
     public String process(String input) {
        this.InputStream = input;
        
        while(this.InstPointer < this.Instructions.length())
        {
            char op = this.Instructions.toCharArray()[this.InstPointer];
            try
            {
				//This should be converted to a dictionary<char, delegate>
                switch(op)
                {
                    case('>'):
                        this.IncPntr();
                        break;
                    case('<'):
                        this.DecPntr();
                        break;
                    case('+'):
                        this.IncByte();
                        break;
                    case('-'):
                        this.DecByte();
                        break;
                    case('.'):
                        this.Output();
                        break;
                    case(','):
                        this.Input();
                        break;
                    case('['):
                        this.JmpIfZero();
                        break;
                    case(']'):
                        this.JmpIfNonZero();
                        break;
                }
            }
            catch(RuntimeException e)
            {
                System.out.println("Error - " + e.getMessage());
                break;
            }
        }
        
        return this.OutputString;
    }
}