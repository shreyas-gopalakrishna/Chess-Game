public class node
{
   int row;
   char col;
   node nextRow;
   node prevRow;
   node nextCol;
   node prevCol;
   node Piece;
   public node(int row,char col)
   {
      this.row=row;
      this.col=col;
      node nextRow=null;
      node prevRow=null;
      node nextCol=null;
      node prevCol=null;
      Piece=null;
   }
   public String toString()
   {
      return ""+col+""+row;
   }   
   public String name()
   {
      return "";
   }
   public boolean poss(char icol,char fcol,int irow,int frow)
   {
      return false;
   }
   public boolean poss(char icol,char fcol,int irow,int frow,boolean hit)
   {
      return false;
   }
   public static node rhead()
   {
    return null;
   }

}
class Pawn extends node
{  
   char colour;
   public Pawn(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"Pawn";
      return na;
   }
   
   public boolean poss(char icol,char fcol,int irow,int frow,boolean hit)
   {
      if(hit)
      {
         if(Math.abs(irow-frow)==1 && Math.abs(icol-fcol)==1)
         {
            return true;
         }
         return false;
      }  
      else if(irow==2 || irow==7)
      {
         if(colour=='w')
         {
            if(Math.abs(icol-fcol)==0 && irow-frow==-1 || irow-frow==-2)
            {               
               return true;
            }
            return false;
         }
         else
         {
            if(Math.abs(icol-fcol)==0 && irow-frow==1 || irow-frow==2)
            {               
               return true;
            }
            return false;

         }
         
      }
      else
      {
         if(colour=='w')
         {         
            if(irow-frow==-1 && icol-fcol==0)
            {
               return true;
            }
            return false;
         }
         else
         {         
            if(irow-frow==1 && icol-fcol==0)
            {
               return true;
            }
            return false;
         }

      }
   }  
    
}
class Knight extends node
{
   char colour;
   public Knight(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"Knight";
      return na;
   }

   public boolean poss(char icol,char fcol,int irow,int frow)
   {
      if((Math.abs(irow-frow)==2 && Math.abs(icol-fcol)==1) || (Math.abs(irow-frow)==1 && Math.abs(icol-fcol)==2))
      {
         return true;
      }
      return false;
   }
}
class Bishop extends node
{
   char colour;
   public Bishop(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"Bishop";
      return na;
   }

   public boolean poss(char icol,char fcol,int irow,int frow)
   {
      if(Math.abs(irow-frow)==Math.abs(icol-fcol))
      {
         return true;
      }
      return false;
   }
}
class Rook extends node
{
   char colour;
   public Rook(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"Rook";
      return na;
   }

  public boolean poss(char icol,char fcol,int irow,int frow)
   {
      if((Math.abs(irow-frow)==0 && Math.abs(icol-fcol)!=0) 
                  || (Math.abs(irow-frow)!=0 && Math.abs(icol-fcol)==0))
      {
         return true;
      }
      return false;
   }
}
class Queen extends node
{
   char colour;
   public Queen(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"Queen";
      return na;
   }

   public boolean poss(char icol,char fcol,int irow,int frow)
   {
      if(((Math.abs(irow-frow)==0 && Math.abs(icol-fcol)!=0) 
           || (Math.abs(irow-frow)!=0 && Math.abs(icol-fcol)==0))
                     || (Math.abs(irow-frow)==Math.abs(icol-fcol)) )
      {
         return true;
      }
      return false;
   }
}
class King extends node
{
   char colour;
   public King(int row,char col,char colour)
   {
      super(row,col);   
      this.colour=colour;
   }
   
   public String name()
   {
      String na=colour+"King";
      return na;
   }

   public boolean poss(char icol,char fcol,int irow,int frow)
   {
      if((Math.abs(irow-frow)==1 && Math.abs(icol-fcol)==1)
         || (Math.abs(irow-frow)==1 && Math.abs(icol-fcol)==0)
             || (Math.abs(irow-frow)==0 && Math.abs(icol-fcol)==1))
      {
         return true;
      }
      //castling conditions 
      else if((Math.abs(irow-frow)==0 && Math.abs(icol-fcol)==2) && (irow == 8 || irow == 1))
	      return true;
      else
              return false;
   }
}
class NoName extends node
{
   char colour;
   public NoName(int row,char col)
   {
      super(row,col);   
   }
   
   public String name()
   {
      return "null";
   }
}

 
