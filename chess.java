import java.util.Scanner;
public class chess
{
   
   node head;
   public node rhead()
   {
    return head;
   }
   public chess()
   {
      create();
      create();
      Bpiece();
      nopiece();
      Wpiece();      
   }
   public void create()
   {
      if(head==null)
      {
         head=new node(8,'A');
      }
      else
      {  
         node col=head;
         node row=head;
         char a[]={'A','B','C','D','E','F','G','H'};
         for(int i=1;i<8;i++)
         {
            node temp=new node(8,a[i]);
            col.nextCol=temp;
            temp.prevCol=col;
            col=temp;
         }
         for(int i=1;i<8;i++)
         {
            node temp=new node(8-i,a[0]);
            row.nextRow=temp;
            temp.prevRow=row;
            row=temp;
         }
         node rowh=head.nextRow;
         col=head.nextCol;
         row=head.nextRow;
         for(int i=7;i>0;i--)
         {
            for(int j=1;j<8;j++)
            {
               node temp=new node(i,a[j]);
               col.nextRow=temp;
               row.nextCol=temp;
               temp.prevRow=col;
               temp.prevCol=row;
               row=row.nextCol;
               col=col.nextCol;                                        
            }                       
            col=rowh.nextCol;         
            rowh=rowh.nextRow;
            row=rowh;           
         }   
      }      
   }
   public void Bpiece()
   {
      node tempc=head;
      node temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {
            if((temp.row==8) && (temp.col=='A' || temp.col=='H'))
            {
               temp.Piece=new Rook(temp.row,temp.col,'b');
            }
            else if((temp.row==8) && (temp.col=='B' || temp.col=='G'))
            {
               temp.Piece=new Knight(temp.row,temp.col,'b');
            }
            else if((temp.row==8) && (temp.col=='C' || temp.col=='F'))
            {
               temp.Piece=new Bishop(temp.row,temp.col,'b');
            }
            else if((temp.row==8) && (temp.col=='D'))
            {
               temp.Piece=new Queen(temp.row,temp.col,'b');
            }
            else if((temp.row==8) && (temp.col=='E'))
            {
               temp.Piece=new King(temp.row,temp.col,'b');
            }
            else if((temp.row==7))
            {
               temp.Piece=new Pawn(temp.row,temp.col,'b');
            }
            

            temp=temp.nextCol;
         }     
         tempc=tempc.nextRow;
         temp=tempc;
      }
      
   }
   public void Wpiece()
   {
      node tempc=head;
      node temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {
            if((temp.row==1) && (temp.col=='A' || temp.col=='H'))
            {
               temp.Piece=new Rook(temp.row,temp.col,'w');
            }
            else if((temp.row==1) && (temp.col=='B' || temp.col=='G'))
            {
               temp.Piece=new Knight(temp.row,temp.col,'w');
            }
            else if((temp.row==1) && (temp.col=='C' || temp.col=='F'))
            {
               temp.Piece=new Bishop(temp.row,temp.col,'w');
            }
            else if((temp.row==1) && (temp.col=='D'))
            {
               temp.Piece=new Queen(temp.row,temp.col,'w');
            }
            else if((temp.row==1) && (temp.col=='E'))
            {
               temp.Piece=new King(temp.row,temp.col,'w');
            }
            else if((temp.row==2))
            {
               temp.Piece=new Pawn(temp.row,temp.col,'w');
            }
            temp=temp.nextCol;
         }        
         tempc=tempc.nextRow;
         temp=tempc;
      }      
   }
   
   public void nopiece()
   {
      node tempc=head;
      node temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {
            if((temp.row>=3) && (temp.row <=6))
            {
               temp.Piece=new NoName(temp.row,temp.col);
            }
            temp=temp.nextCol;
         }
         tempc=tempc.nextRow;
         temp=tempc;
      }      
    }
   public node pInitial=head;
   public node pFinal=head;

   // check if the initial position clicked is valid or not
   public boolean checkInitial(int row,char col)
   {
      node temp=head;    
      for(int i=8;i>0;i--)
      {
         if(temp.col==col)
         {
            break;
         }      
         temp=temp.nextCol;           
      }                 
      for(int i=8;i>0;i--)
      {
         if(temp.row==row)
         {
           break;
         }      
         temp=temp.nextRow;
      }

      if(temp.Piece.name().compareTo("null")==0)
      {
         return false;
      }
      else
      {
	 if(temp.Piece.name().charAt(0) == counterM())//condition to check whose move is next B or W
	 {
		 pInitial=temp;
		 return true;
	 }
	 else
		 return false;
      }
   }

   //function to check the final position of the piece being moved   
   public boolean checkFinal(int row,char col)
   {
      node temp=head;    
      for(int i=8;i>0;i--)
      {
         if(temp.col==col)
         {
            break;
         }      
         temp=temp.nextCol;           
      }                 
      for(int i=8;i>0;i--)
      {
         if(temp.row==row)
         {
           break;
         }      
         temp=temp.nextRow;
      }      
      pFinal=temp;
      boolean poss1=false;
      boolean poss2=false;
      if(pInitial.Piece.name().compareTo("bPawn")==0 || pInitial.Piece.name().compareTo("wPawn")==0)
      {
         boolean hit=false;
         if(pFinal.Piece.name().compareTo("null")!=0)
         hit=true;
         poss1=pInitial.Piece.poss(pInitial.col,pFinal.col,pInitial.row,pFinal.row,hit);         
      }
      else
      {
         poss1=pInitial.Piece.poss(pInitial.col,pFinal.col,pInitial.row,pFinal.row);
      }   
      if(poss1)
      {   
         poss2=checkmove(pInitial,pFinal);      
      }
      if(poss1 && poss2)
      {         
         return true;
      }
      else
      {  
         return false;
      }
   }
   
   public int wkingMove = 0;
   public int bkingMove = 0;
   public int wRrookMove = 0;//white right rook move
   public int wLrookMove = 0;
   public int bRrookMove = 0;
   public int bLrookMove = 0;

   public boolean castled=false;
   public boolean wPawnQ=false;
   public boolean bPawnQ=false;
   //function to make the move if initial and final positions are valid
   public void makeMove()
   {
	   // checking if rook and king have already moved
       if((pInitial.Piece.name().compareTo("bKing")==0)) 
	       bkingMove = 1;
       if((pInitial.Piece.name().compareTo("wKing")==0))
	       wkingMove = 1;
       if(pInitial.Piece.name().compareTo("bRook")==0)
          {
		  if(pInitial.col == 'A')
	               bLrookMove = 1;
                  if(pInitial.col == 'H')
	               bRrookMove = 1;

	  }
       if(pInitial.Piece.name().compareTo("wRook")==0)
          {
		  if(pInitial.col == 'A')
	               wLrookMove = 1;
                  if(pInitial.col == 'H')
	               wRrookMove = 1;

	  }
       // special move for castling of king
       if((pInitial.Piece.name().compareTo("bKing")==0) || ((pInitial.Piece.name().compareTo("wKing")==0)))
       {
		   if((Math.abs(pInitial.row-pFinal.row)==1 && Math.abs(pInitial.col-pFinal.col)==1)
            || (Math.abs(pInitial.row-pFinal.row)==1  && Math.abs(pInitial.col-pFinal.col)==0)
                || (Math.abs(pInitial.row-pFinal.row)==0 && Math.abs(pInitial.col-pFinal.col)==1))
                       {
     			 pFinal.Piece=pInitial.Piece;
                         pInitial.Piece=new NoName(pInitial.row,pInitial.col);
                         castled=false;
   
                       }
		   else//castling
	               {
			   if( (pFinal.col == 'G'))
                              {
				  pFinal.Piece=pInitial.Piece;
				  pInitial.nextCol.Piece = pFinal.nextCol.Piece;
				  pInitial.Piece=new NoName(pInitial.row,pInitial.col);
				  pFinal.nextCol.Piece=new NoName(pFinal.nextCol.row,pFinal.nextCol.col);
				  
			     }
                          else if (pFinal.col == 'C')
			     { 				  
				  pFinal.Piece=pInitial.Piece;
				  pInitial.prevCol.Piece = pFinal.prevCol.prevCol.Piece;
				  pInitial.Piece=new NoName(pInitial.row,pInitial.col);
				  pFinal.prevCol.prevCol.Piece=new NoName(pFinal.prevCol.prevCol.row,pFinal.prevCol.prevCol.col);

			     }
			  else
				 {}			  
            castled=true; //stores info if king got castled!
		  }
	    }
	 else
	 {
           castled=false;
	   pFinal.Piece=pInitial.Piece;
  	   pInitial.Piece=new NoName(pInitial.row,pInitial.col);
      
	 }
    
    wPawnQ=wPawnQueen();
    bPawnQ=bPawnQueen();  
    if(countM == 'w')
      countM = 'b';
    else
      countM = 'w';

   }
   public boolean ifCastled()
   {
      return castled;
   }
      
   //same as makemove func but used only for checkcheck() so that W and B play alternately
   public void makeMove1(node temp) //******
   {
      pFinal=temp; //********
      pFinal.Piece=pInitial.Piece;
      pInitial.Piece=new NoName(pInitial.row,pInitial.col);
      
   }

   
   //reverse move as checking for check one move ahead
   public void makeReverse(node temp)
   {
     pInitial.Piece=pFinal.Piece;
     pFinal.Piece=temp.Piece;
   }
  
   //variable used to keep whose turn B or W
   public char  countM = 'w';
   public char counterM()
   {
        return countM;	   
   }

   // checks if the move player is trying is valid or not   
   public boolean checkmove(node tempi,node tempf)
   {
      
      if(tempi.Piece.name().compareTo("wKnight")==0)
      {         
         if(tempf.Piece.name().charAt(0)!='w')
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      else if(tempi.Piece.name().compareTo("bKnight")==0)
      {
         if(tempf.Piece.name().charAt(0)!='b')
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      else if(tempi.Piece.name().compareTo("bPawn")==0)
      {       
         if((tempi.row - tempf.row ) < 0) 
         {
            return false;
         }

	      if(tempf.Piece.name().compareTo("null")==0)
	      {        
	         if((tempi.row == 7 &&  tempf.row == 5) && (((int)(tempi.col) - (int)(tempf.col))==0 ) 
				 && (tempi.nextRow.Piece.name().compareTo("null")==0))
		      {
				   return true;
			   }
	         if(((tempi.row - tempf.row ) == 1 ) && (((int)(tempi.col) - (int)(tempf.col))==0 ) )
			   {
				   return true;
			   }       		 
	     }
 	     if(tempf.Piece.name().compareTo("null")!=0)
	     {
		      if(tempi.col == 'H')
		      {
			      if(((tempi.row - tempf.row ) == 1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 )
                || (((int)(tempi.col) - (int)(tempf.col))== -1 ))
                    && ((tempi.nextRow.prevCol.Piece.name().charAt(0)=='w')) )
			      {
					   return true;
				   }
	       		else
				      return false;		

		  }
		  if(tempi.col == 'A')
		  {
		      if(((tempi.row - tempf.row ) == 1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 )
              || (((int)(tempi.col) - (int)(tempf.col))== -1 )) 
                    && ((tempi.nextRow.nextCol.Piece.name().charAt(0)=='w')) )
				{
				   return true;
				}
	     		else
				   return false;		
		  }	        
		  if(((tempi.row - tempf.row ) == 1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 )
           || (((int)(tempi.col) - (int)(tempf.col))== -1 )) 
           && ((tempi.nextRow.nextCol.Piece.name().charAt(0)=='w')
              || (tempi.nextRow.prevCol.Piece.name().charAt(0)=='w')) )
		  {
			   return true;
		  }	 
	   }	 
         
	return false;
      }  
      else if(tempi.Piece.name().compareTo("wPawn")==0)
      {
	 
         if((tempi.row - tempf.row ) > 0) 
         {
            return false;
         }
	 if(tempf.Piece.name().compareTo("null")==0)
	     {
        
		 if((tempi.row == 2 &&  tempf.row == 4) && (((int)(tempi.col) - (int)(tempf.col))==0 ) &&
				 (tempi.prevRow.Piece.name().compareTo("null")==0))
				 {
					 return true;
				 }
	         if(((tempi.row - tempf.row ) == -1 ) && (((int)(tempi.col) - (int)(tempf.col))==0 ) )
				 {
					   return true;
				 }       
		 
	     }
	if(tempf.Piece.name().compareTo("null")!=0)
	     {
		if(tempi.col == 'H')
		{
			if(((tempi.row - tempf.row ) == -1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 ) ||
				(((int)(tempi.col) - (int)(tempf.col))== -1 )) 
                && ((tempi.prevRow.prevCol.Piece.name().charAt(0)=='b')) )
				 {
					   return true;
				 }
	       		else
				return false;		

		}
		if(tempi.col == 'A')
		{
			if(((tempi.row - tempf.row ) == -1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 ) ||
				(((int)(tempi.col) - (int)(tempf.col))== -1 )) 
                    && ((tempi.prevRow.nextCol.Piece.name().charAt(0)=='b')) )
				 {
					   return true;
				 }
	       		else
				return false;		

		}
        
		if(((tempi.row - tempf.row ) == -1 ) && ((((int)(tempi.col) - (int)(tempf.col))==1 ) 
          || (((int)(tempi.col) - (int)(tempf.col))== -1 ))
			       	&& ((tempi.prevRow.nextCol.Piece.name().charAt(0)=='b')
                       || (tempi.prevRow.prevCol.Piece.name().charAt(0)=='b')) )
				 {
					   return true;
				 }	 
	     }	 
         
         return false;
      }  
      else if(tempi.Piece.name().compareTo("wRook")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col>0)
         {
            temp=temp.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevCol;
            }
         }
         else if(tempi.col-tempf.col<0)
         {
            temp=temp.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextCol;
            }
         }
         else if(tempi.row-tempf.row>0)
         {
            temp=temp.nextRow;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow;
            }
         }
         else if(tempi.row-tempf.row<0)
         {
            temp=temp.prevRow;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow;
            }
         }
         if(tempf.Piece.name().charAt(0)=='w')
         {
            x=false;
         }

         return x;
      }  
      else if(tempi.Piece.name().compareTo("bRook")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col>0)
         {
            temp=temp.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevCol;
            }
         }
         else if(tempi.col-tempf.col<0)
         {
            temp=temp.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextCol;
            }
         }
         else if(tempi.row-tempf.row>0)
         {
            temp=temp.nextRow;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow;
            }
         }
         else if(tempi.row-tempf.row<0)
         {
            temp=temp.prevRow;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow;
            }
         }
         if(tempf.Piece.name().charAt(0)=='b')
         {
            x=false;
         }

         return x;
      } 
      else if(tempi.Piece.name().compareTo("wKing")==0)
      {
         if(tempf.Piece.name().charAt(0)!='w')
         {
		 if((Math.abs(tempi.row-tempf.row)==1 && Math.abs(tempi.col-tempf.col)==1) 
         || (Math.abs(tempi.row-tempf.row)==1 && Math.abs(tempi.col-tempf.col)==0)
			   || (Math.abs(tempi.row-tempf.row)==0 && Math.abs(tempi.col-tempf.col)==1))
     	   	 {
            		return true;
     		 }
		 // checking conditions for castling of wking
		 else if(( wkingMove == 0) && ((wLrookMove * wRrookMove ==0)) && (!checkwhite()) 
             && ((tempi.row-tempf.row) == 0) && (Math.abs(tempi.col-tempf.col)==2)) 
		     {
			    if( ( tempf.row == 1 && tempf.col == 'G') && (tempi.nextCol.Piece.name().compareTo("null")==0) 
					 && (tempi.nextCol.nextCol.Piece.name().compareTo("null")==0) && (wRrookMove==0) )
				    return true;
			    else if( ( tempf.row == 1 && tempf.col == 'C') && (tempi.prevCol.Piece.name().compareTo("null")==0) 
				    && (tempi.prevCol.prevCol.Piece.name().compareTo("null")==0) 
                  && ( tempi.prevCol.prevCol.prevCol.Piece.name().compareTo("null")==0)&& (wLrookMove==0) )
				    return true;
			    else
				    return false;
		    }
		  else
			 return false;            
         }
         else
         {
            return false;
         }
      }
        
      else if(tempi.Piece.name().compareTo("bKing")==0)
      {
         if(tempf.Piece.name().charAt(0)!='b')
         {
      	 	if((Math.abs(tempi.row-tempf.row)==1 && Math.abs(tempi.col-tempf.col)==1)
              || (Math.abs(tempi.row-tempf.row)==1 && Math.abs(tempi.col-tempf.col)==0)
			            || (Math.abs(tempi.row-tempf.row)==0 && Math.abs(tempi.col-tempf.col)==1))
     	   	 {
            		return true;
     		    }
		// checking conditions for castling of bking
                else if(( bkingMove == 0) && ((bLrookMove * bRrookMove ==0) ) && (!checkblack()) 
                  && ((tempi.row-tempf.row) == 0) && (Math.abs(tempi.col-tempf.col)==2)) 
		     {
			    if( ( tempf.row == 8 && tempf.col == 'G') && (tempi.nextCol.Piece.name().compareTo("null")==0) 
					 && (tempi.nextCol.nextCol.Piece.name().compareTo("null")==0) && (bRrookMove ==0)  )
				    return true;
			    else if( ( tempf.row == 8 && tempf.col == 'C') && (tempi.prevCol.Piece.name().compareTo("null")==0) 
				    && (tempi.prevCol.prevCol.Piece.name().compareTo("null")==0) 
                  && ( tempi.prevCol.prevCol.prevCol.Piece.name().compareTo("null")==0)&& (bLrookMove ==0) )
				    return true;
			    else
				    return false;
		    }
	        else
			 return false;
	    }
	 else
		 return false;            
      }
    
  
    
      else if(tempi.Piece.name().compareTo("wBishop")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col>0 && tempi.row-tempf.row>0)
         {
            temp=temp.nextRow.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow.prevCol;
            }
         }
         else if(tempi.col-tempf.col>0 && tempi.row-tempf.row<0)
         {
            temp=temp.prevRow.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow.prevCol;
            }
         }
         else if(tempi.col-tempf.col<0 && tempi.row-tempf.row>0)
         {
            temp=temp.nextRow.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow.nextCol;
            }
         }
         else if(tempi.col-tempf.col<0 && tempi.row-tempf.row<0)
         {
            temp=temp.prevRow.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow.nextCol;
            }
         }
         if(tempf.Piece.name().charAt(0)=='w')
         {
            x=false;
         }
         return x;
      }
      else if(tempi.Piece.name().compareTo("bBishop")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col>0 && tempi.row-tempf.row>0)
         {
            temp=temp.nextRow.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow.prevCol;
            }
         }
         else if(tempi.col-tempf.col>0 && tempi.row-tempf.row<0)
         {
            temp=temp.prevRow.prevCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow.prevCol;
            }
         }
         else if(tempi.col-tempf.col<0 && tempi.row-tempf.row>0)
         {
            temp=temp.nextRow.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.nextRow.nextCol;
            }
         }
         else if(tempi.col-tempf.col<0 && tempi.row-tempf.row<0)
         {
            temp=temp.prevRow.nextCol;
            while(temp!=tempf)
            {
               if(temp.Piece.name().compareTo("null")!=0)
               {
                  x=false;
               }
               temp=temp.prevRow.nextCol;
            }
         }
         if(tempf.Piece.name().charAt(0)=='b')
         {
            x=false;
         }
         return x;
      }
      else if(tempi.Piece.name().compareTo("wQueen")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col==0)
         {
            if(tempi.row-tempf.row>0)
            {
               temp=temp.nextRow;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow;
               }   
            }
            else if(tempi.row-tempf.row<0)
            {
               temp=temp.prevRow;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow;
               }   
            }
         }
         else if(tempi.row-tempf.row==0)
         {
            if(tempi.col-tempf.col>0)
            {
               temp=temp.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevCol;
               }
            }
            else if(tempi.col-tempf.col<0)
            {
               temp=temp.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextCol;
               }   
            } 
         }
         else
         {
            if(tempi.col-tempf.col>0 && tempi.row-tempf.row>0)
            {
               temp=temp.nextRow.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow.prevCol;
               }
            }
            else if(tempi.col-tempf.col>0 && tempi.row-tempf.row<0)
            {
               temp=temp.prevRow.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow.prevCol;
               }
   
            }
            else if(tempi.col-tempf.col<0 && tempi.row-tempf.row>0)
            {
               temp=temp.nextRow.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow.nextCol;
               }
   
            }
            else if(tempi.col-tempf.col<0 && tempi.row-tempf.row<0)
            {
               temp=temp.prevRow.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow.nextCol;
               }   
            }         
         }
         if(tempf.Piece.name().charAt(0)=='w')
         {
            x=false;
         }
         return x;
      }
      else if(tempi.Piece.name().compareTo("bQueen")==0)
      {
         boolean x=true;
         node temp=tempi;
         if(tempi.col-tempf.col==0)
         {
            if(tempi.row-tempf.row>0)
            {
               temp=temp.nextRow;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow;
               }   
            }
            else if(tempi.row-tempf.row<0)
            {
               temp=temp.prevRow;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow;
               }   
            }
         }
         else if(tempi.row-tempf.row==0)
         {
            if(tempi.col-tempf.col>0)
            {
               temp=temp.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevCol;
               }
            }
            else if(tempi.col-tempf.col<0)
            {
               temp=temp.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextCol;
               }   
            } 
         }
         else
         {
            if(tempi.col-tempf.col>0 && tempi.row-tempf.row>0)
            {
               temp=temp.nextRow.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow.prevCol;
               }
            }
            else if(tempi.col-tempf.col>0 && tempi.row-tempf.row<0)
            {
               temp=temp.prevRow.prevCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow.prevCol;
               }   
            }
            else if(tempi.col-tempf.col<0 && tempi.row-tempf.row>0)
            {
               temp=temp.nextRow.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.nextRow.nextCol;
               }   
            }
            else if(tempi.col-tempf.col<0 && tempi.row-tempf.row<0)
            {
               temp=temp.prevRow.nextCol;
               while(temp!=tempf)
               {
                  if(temp.Piece.name().compareTo("null")!=0)
                  {
                     x=false;
                  }
                  temp=temp.prevRow.nextCol;
               }   
            }         
         }
         if(tempf.Piece.name().charAt(0)=='b')
         {
            x=false;
         }
         return x;
      }
      return true;
   }
   public boolean king()
   {
      node tempc=head;
      node temp=tempc;
      boolean white=false;
      boolean black=false;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {        
                               
            if(temp.Piece.name().compareTo("wKing")==0)
            {
               white=true;           
            }
            if(temp.Piece.name().compareTo("bKing")==0)
            {
               black=true;            
            }         
            temp=temp.nextCol;
         }         
         tempc=tempc.nextRow;
         temp=tempc;
      }
      return white && black;
   }   
   
   public boolean checkwhite()//checking if white king has check
   {
      return checkwhite1() || checkwhite2();
   }
   
   public boolean checkblack()//checking if black king has check
   {
      return checkblack1() || checkblack2();
   }
   
   public boolean checkwhite1()
   {
      boolean white=false;
      node whitek=head;
      node tempc=head;
      node temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {        
                               
            if(temp.Piece.name().compareTo("wKing")==0)
            {
               whitek=temp;
               break;           
            }
            temp=temp.nextCol;
         }         
         tempc=tempc.nextRow;
         temp=tempc;
      }
      tempc=head;
      temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {        
            if(temp.Piece.name().charAt(0)=='b' && temp.Piece.name().charAt(0)!='P')
            {      
               if(temp.Piece.poss(temp.col,whitek.col,temp.row,whitek.row))
               {                        
                  if(checkmove(temp,whitek))
                  {
                     white=true;          
                     break; 
                  }
               }
            }
            temp=temp.nextCol;
         }         
         tempc=tempc.nextRow;
         temp=tempc;
      }
      return white;
   }
   
   public boolean checkblack1()
   {
      boolean black=false;
      node blackk=head;
      node tempc=head;
      node temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {        
                               
            if(temp.Piece.name().compareTo("bKing")==0)
            {
               blackk=temp;  
               break;          
            }         
            temp=temp.nextCol;
         }         
         tempc=tempc.nextRow;
         temp=tempc;
      }
      tempc=head;
      temp=tempc;
      for(int i=8;i>0;i--)
      {
         for(int j=0;j<8;j++)
         {        
            if(temp.Piece.name().charAt(0)=='w' && temp.Piece.name().charAt(1)!='P')                  
            {
               if(temp.Piece.poss(temp.col,blackk.col,temp.row,blackk.row))
               {
                  if(checkmove(temp,blackk))
                  {
                     black=true; 
                     break;          
                  }
               }
            }
            temp=temp.nextCol;
         }         
         tempc=tempc.nextRow;
         temp=tempc;
      }
      return black;      
   }     
      
   public boolean checkwhite2()
   {
      boolean black=false;           
      node temp=head.nextCol;
      while(temp.nextCol!=null)
      {
         node temp1=temp.nextRow;
         for(int i=0;i<7;i++)
         {
            if(temp1.Piece.name().compareTo("bPawn")==0)
            {
               if(temp1.nextRow.nextCol.Piece.name().compareTo("wKing")==0 
                  || temp1.nextRow.prevCol.Piece.name().compareTo("wKing")==0)
               {
                  black=true;
               }
            }
            temp1=temp1.nextRow;
         }
         temp=temp.nextCol;
      }
      temp=temp.nextRow;
      for(int i=0;i<7;i++)
      {
         if(temp.Piece.name().compareTo("bPawn")==0)
         {
            if(temp.nextRow.prevCol.Piece.name().compareTo("wKing")==0)
            {
               black=true;
            }
         }
         temp=temp.nextRow;            
      }
      temp=head;
      for(int i=0;i<7;i++)
      {
         if(temp.Piece.name().compareTo("bPawn")==0)
         {
            if(temp.nextRow.nextCol.Piece.name().compareTo("wKing")==0)
            {
               black=true;
            }
         }

         temp=temp.nextRow;
      }
      return black;
   }   
   
   public boolean checkblack2()
   {
      boolean white=false;           
      node temp=head.nextCol;
      while(temp.nextCol!=null)
      {
         node temp1=temp.nextRow;
         for(int i=0;i<7;i++)
         {
            if(temp1.Piece.name().compareTo("wPawn")==0)
            {
               if(temp1.prevRow.nextCol.Piece.name().compareTo("bKing")==0 
                  || temp1.prevRow.prevCol.Piece.name().compareTo("bKing")==0)
               {
                  white=true;
               }
            }
            temp1=temp1.nextRow;
         }
         temp=temp.nextCol;
      }
      temp=temp.nextRow;
      for(int i=0;i<7;i++)
      {
         if(temp.Piece.name().compareTo("wPawn")==0)
         {
            if(temp.prevRow.prevCol.Piece.name().compareTo("bKing")==0)
            {
               white=true;
            }
         }
         temp=temp.nextRow;            
      }
      temp=head;
      for(int i=0;i<7;i++)
      {
         if(temp.Piece.name().compareTo("wPawn")==0)
         {
            if(temp.prevRow.nextCol.Piece.name().compareTo("bKing")==0)
            {
               white=true;
            }
         }

         temp=temp.nextRow;
      }
      return white;
   }   
   
   public boolean checkcheck()
   {
      boolean x;
      node temp=new node(0,'a');      
      temp.Piece=pFinal.Piece;
      char a=pInitial.Piece.name().charAt(0);
      makeMove1(pFinal);
      if(a=='w')
      {
         x=checkwhite();
      }
      else
      {
         x=checkblack();
      }
      makeReverse(temp);
      return x;      
   }
   
   public boolean checkcheck1(node temp1)
   {
      boolean x;
      node temp=new node(0,'a');      
      temp.Piece=pFinal.Piece;
      char a=pInitial.Piece.name().charAt(0);
      makeMove1(temp1);
      if(a=='w')
      {
         x=checkwhite();
      }
      else
      {
         x=checkblack();
      }
      makeReverse(temp1);
      return x;      
   }

   
   public String getStart()
   {
      return pInitial.Piece.name()+pInitial;
   }
   
   public boolean wPawnQueen()
   {
      node temp=head;
      for(int i=0;i<8;i++)
      {
         if(temp.Piece.name().charAt(1)=='P')
         {
            temp.Piece=new Queen(temp.row,temp.col,'w');
            return true;
         }
         temp=temp.nextCol;
      }
      return false;         
   }
   public boolean bPawnQueen()
   {
      node temp=head;
      for(int i=0;i<7;i++)
      {
         temp=temp.nextRow;
      }
      for(int i=0;i<8;i++)
      {
         if(temp.Piece.name().charAt(1)=='P')
         {
            temp.Piece=new Queen(temp.row,temp.col,'b');
            return true;
         }
         temp=temp.nextCol;
      }
      return false;   
   }
   public boolean wPawnQueencheck()
   {
      return wPawnQ;
   }
   public boolean bPawnQueencheck()
   {
      return bPawnQ;
   }
   
   public int wCheckYAStale()
   {
      boolean stale=wstaleMate();
      boolean mate=checkwhite();
      if(stale && mate)
      {
         return 2;
      }
      else if(stale)
      {
         return 1;
      } 
      else
      {
         return 0;
      } 
   }
   public int bCheckYAStale()
   {
      boolean stale=bstaleMate();
      boolean mate=checkblack();
      if(stale && mate)
      {
         return 2;
      }
      else if(stale)
      {
         return 1;
      } 
      else
      {
         return 0;
      } 
   }

   
   public boolean bstaleMate()
   {
      node temp=head;
      node tempc=head;
      for(int i=0;i<8;i++)
      {
         for(int j=0;j<8;j++)
         {
            if(temp.Piece.name().charAt(0)=='b')
            {
               if(bstaleMateCheck(temp))
               {
                  return false;
               }
            }
            temp=temp.nextCol;
         }
         tempc=tempc.nextRow;
         temp=tempc;
      }
      return true;
   }
   
   public boolean bstaleMateCheck(node temp)
   {
      node x=new node(0,'a');      
      node temp1=head;
      node tempc1=head;
      boolean noStale=false;
      for(int i=0;i<8 && !noStale;i++)
      {
         for(int j=0;j<8 && !noStale;j++)
         {          
            if(temp.Piece.name().charAt(1)!='P')
            {
               if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row))
               {
                  if(checkmove(temp,temp1))
                  {
                     x.Piece=temp1.Piece;
                     temp1.Piece=temp.Piece;
                     temp.Piece=new NoName(temp.row,temp.col); 
                     if(!checkblack())
                     {
                        noStale=true;
                     }
                     temp.Piece=temp1.Piece;
                     temp1.Piece=x.Piece;                                      
                  }
               }   
            }
            else
            {
               if(temp1.Piece.name().charAt(0)!='n')
               {
                  if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row,true))
                  {
                     if(checkmove(temp,temp1))
                     {
                        x.Piece=temp1.Piece;
                        temp1.Piece=temp.Piece;
                        temp.Piece=new NoName(temp.row,temp.col); 
                        if(!checkblack())
                        {
                           noStale=true;
                        }
                        temp.Piece=temp1.Piece;
                        temp1.Piece=x.Piece;                 
                     }
                  }   
                  else
                  {
                     if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row,false))
                     {
                        if(checkmove(temp,temp1))
                        {
                           x.Piece=temp1.Piece;
                           temp1.Piece=temp.Piece;
                           temp.Piece=new NoName(temp.row,temp.col); 
                           if(!checkblack())
                           {
                              noStale=true;
                           }
                           temp.Piece=temp1.Piece;
                           temp1.Piece=x.Piece;                 
                        }
                     }   
                  }
               }
            }
            temp1=temp1.nextCol;
         }
         tempc1=tempc1.nextRow;
         temp1=tempc1;
      }
      return noStale;

   }
   
   public boolean wstaleMate()
   {
      node temp=head;
      node tempc=head;
      for(int i=0;i<8;i++)
      {
         for(int j=0;j<8;j++)
         {
            if(temp.Piece.name().charAt(0)=='w')
            {
               if(wstaleMateCheck(temp))
               {
                  return false;
               }
            }
            temp=temp.nextCol;
         }
         tempc=tempc.nextRow;
         temp=tempc;
      }
      return true;
   }
   
   public boolean wstaleMateCheck(node temp)
   {
      node x=new node(0,'a');      
      node temp1=head;
      node tempc1=head;
      boolean noStale=false;
      for(int i=0;i<8 && !noStale;i++)
      {
         for(int j=0;j<8 && !noStale;j++)
         {          
            if(temp.Piece.name().charAt(1)!='P')
            {
               if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row))
               {
                  if(checkmove(temp,temp1))
                  {
                     x.Piece=temp1.Piece;
                     temp1.Piece=temp.Piece;
                     temp.Piece=new NoName(temp.row,temp.col); 
                     if(!checkwhite())
                     {
                        noStale=true;
                     }
                     temp.Piece=temp1.Piece;
                     temp1.Piece=x.Piece;                                      
                  }
               }   
            }
            else
            {
               if(temp1.Piece.name().charAt(0)!='n')
               {
                  if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row,true))
                  {
                     if(checkmove(temp,temp1))
                     {
                        x.Piece=temp1.Piece;
                        temp1.Piece=temp.Piece;
                        temp.Piece=new NoName(temp.row,temp.col); 
                        if(!checkwhite())
                        {
                           noStale=true;
                        }
                        temp.Piece=temp1.Piece;
                        temp1.Piece=x.Piece;                 
                     }
                  }   
                  else
                  {
                     if(temp.Piece.poss(temp.col,temp1.col,temp.row,temp1.row,false))
                     {
                        if(checkmove(temp,temp1))
                        {
                           x.Piece=temp1.Piece;
                           temp1.Piece=temp.Piece;
                           temp.Piece=new NoName(temp.row,temp.col); 
                           if(!checkwhite())
                           {
                              noStale=true;
                           }
                           temp.Piece=temp1.Piece;
                           temp1.Piece=x.Piece;                 
                        }
                     }   
                  }
               }
            }
            temp1=temp1.nextCol;
         }
         tempc1=tempc1.nextRow;
         temp1=tempc1;
      }
      return noStale;

   }


}
