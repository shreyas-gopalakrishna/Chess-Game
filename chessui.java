import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class chessui {
   
    int count=1;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[9][9];
    
    private JPanel chessBoard;
    private ImageIcon[][] img= new ImageIcon[9][9];
    private final JLabel message = new JLabel("Simple chess game!");
    private static final String COLS = "ABCDEFGH";
    Color lgreen=new Color(255,0,0,0);
    private JLabel player = new JLabel();

   JFrame f = new JFrame("Smart Chess");
   
    chess ch=new chess();
        
   
    chessui() {
                
                initializeGui();
                f.add(getGui());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }

    public final void initializeGui() {
        // set up the main GUI
        player.setText("WHITE'S TURN");
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton New=new JButton("New");
        New.setActionCommand("new" );
        New.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent evt ) {
                        menuAction( evt.getActionCommand() ); }
                } );
        
        JButton About=new JButton("About");
        About.setActionCommand("about" );
        About.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent evt ) {
                        menuAction( evt.getActionCommand() ); }
                } );
        
        JButton Resign=new JButton("Resign");
        Resign.setActionCommand("resign" );
        Resign.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent evt ) {
                        menuAction( evt.getActionCommand() ); }
                } );
        
        JButton giveup=new JButton("GIVE UP");
        giveup.setActionCommand("giveup" );
        giveup.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent evt ) {
                        menuAction( evt.getActionCommand() ); }
                } );

        tools.add(New); 
        tools.add(Resign);
        tools.addSeparator();
        tools.add(About);
        tools.addSeparator();
        tools.add(message);
        tools.addSeparator();
        tools.addSeparator();
        tools.addSeparator();
        tools.add(player);
        tools.addSeparator();
        tools.addSeparator();
        tools.add(giveup);

        gui.add(new JLabel(" "), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.black));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        node tempc=ch.rhead();
        node temp=tempc;

        
        for (int ii = 8; ii >0; ii--) {
            for (int jj = 1; jj < 9; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                                
                if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } 
                else {
                    b.setBackground(Color.lightGray);
                }
                
                b.setFocusPainted( false );
                b.setActionCommand(temp.Piece.name()+temp+jj ); 
                b.setFont( new Font( "Dialog", 0, 48 ) );
                b.setPreferredSize( new Dimension( 100, 100 ) );
                b.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent evt ) {
                        buttonAction( evt.getActionCommand() ); }
                } );

                
                chessBoardSquares[ii][jj] = b;
                temp=temp.nextCol;
            }
            
            tempc=tempc.nextRow;
            temp=tempc;
        }
         
       //set images  
       
 
        img[8][1] = new ImageIcon("image/brook.gif");
           chessBoardSquares[8][1].setIcon(img[8][1]);
          
         img[8][2]= new ImageIcon("image/bnight.gif");
           chessBoardSquares[8][2].setIcon(img[8][2]);
   
         img[8][3]= new ImageIcon("image/bbishop.gif");
           chessBoardSquares[8][3].setIcon(img[8][3]);
   
         img[8][4]= new ImageIcon("image/bqueen.gif");
           chessBoardSquares[8][4].setIcon(img[8][4]);
   
         img[8][5]= new ImageIcon("image/bking.gif");
           chessBoardSquares[8][5].setIcon(img[8][5]);
   
         img[8][6]= new ImageIcon("image/bbishop.gif");
           chessBoardSquares[8][6].setIcon(img[8][6]);
     
         img[8][7]= new ImageIcon("image/bnight.gif");
           chessBoardSquares[8][7].setIcon(img[8][7]);

         img[8][8]= new ImageIcon("image/brook.gif");
           chessBoardSquares[8][8].setIcon(img[8][8]);

         img[7][1]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][1].setIcon(img[7][1]);

         img[7][2]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][2].setIcon(img[7][2]);

         img[7][3]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][3].setIcon(img[7][3]);

         img[7][4]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][4].setIcon(img[7][4]);

         img[7][5]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][5].setIcon(img[7][5]);

         img[7][6]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][6].setIcon(img[7][6]);

         img[7][7]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][7].setIcon(img[7][7]);

         img[7][8]= new ImageIcon("image/bpawn.gif");
           chessBoardSquares[7][8].setIcon(img[7][8]);

        img[2][1]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][1].setIcon(img[2][1]);

         img[2][2]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][2].setIcon(img[2][2]);

         img[2][3]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][3].setIcon(img[2][3]);

         img[2][4]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][4].setIcon(img[2][4]);

         img[2][5]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][5].setIcon(img[2][5]);

         img[2][6]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][6].setIcon(img[2][6]);

         img[2][7]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][7].setIcon(img[2][7]);

         img[2][8]= new ImageIcon("image/wpawn.gif");
           chessBoardSquares[2][8].setIcon(img[2][8]);

        img[1][1]= new ImageIcon("image/wrook.gif");
           chessBoardSquares[1][1].setIcon(img[1][1]);

         img[1][2]= new ImageIcon("image/wnight.gif");
           chessBoardSquares[1][2].setIcon(img[1][2]);

         img[1][3]= new ImageIcon("image/wbishop.gif");
           chessBoardSquares[1][3].setIcon(img[1][3]);

         img[1][4]= new ImageIcon("image/wqueen.gif");
           chessBoardSquares[1][4].setIcon(img[1][4]);

         img[1][5]= new ImageIcon("image/wking.gif");
           chessBoardSquares[1][5].setIcon(img[1][5]);

         img[1][6]= new ImageIcon("image/wbishop.gif");
           chessBoardSquares[1][6].setIcon(img[1][6]);

         img[1][7]= new ImageIcon("image/wnight.gif");
           chessBoardSquares[1][7].setIcon(img[1][7]);

         img[1][8]= new ImageIcon("image/wrook.gif");
           chessBoardSquares[1][8].setIcon(img[1][8]);

 
        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        // fill the board with all pieces
        for (int ii = 8; ii >0; ii--) {
            for (int jj = 0; jj < 9; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii),
                                SwingConstants.CENTER));
                                break;
                    default:
                        chessBoard.add(chessBoardSquares[ii][jj]);
                }
            }
        }
        
       }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }
    
    private void buttonAction( String btn ) {
        int p=0;
        int flag=0;
        String val=btn;
        String name= val.substring(0,(val.length()-3));
        String C= val.substring((val.length()-3),(val.length()-2));
        String R= val.substring((val.length()-2),(val.length()-1));
        int c2=Integer.parseInt(val.substring((val.length()-1),(val.length())));
        int r=Integer.parseInt(R);
        char c=val.charAt(val.length()-3);
        
	
        if(count%2 != 0) //source move
        {
         if(!ch.checkInitial(r,c))// func to check validity of first pos
         {
            flag=1;                                       
         }
         else
         {
            chessBoardSquares[r][c2].setBackground(Color.GREEN);
            
            node tempc=ch.rhead();
            node temp=tempc;

            for (int ii = 8; ii >0; ii--) {
            for (int jj = 1; jj < 9; jj++) {
               String t=""+temp;
               if((ii !=r || jj != c2)&& ch.checkFinal(Integer.parseInt(t.substring(1,t.length())),t.charAt(0)) && !ch.checkcheck())
               {
                  
                  chessBoardSquares[ii][jj].setBackground(Color.CYAN);
               }
             
             temp=temp.nextCol;
            }
            tempc=tempc.nextRow;
            temp=tempc;
          }
         }
        }
        else if(count%2 ==0){//destination move
        
          String str=ch.getStart();  // return string value of start pos
          String p1=str.substring(0,(str.length()-2));
          int r1=Integer.parseInt(str.substring((str.length()-1),(str.length())));
          char d=str.charAt(str.length()-2);
          int c1=d-64; 
   
          if(!ch.checkFinal(r,c)) // func to accept col row and check if move is valid
          {
            p=0;
          }
          else if(ch.checkcheck())
          {
             JOptionPane.showMessageDialog( f, "Causing Check..!!",
                                        "Chess", JOptionPane.INFORMATION_MESSAGE );
          }
          else          {
             ch.makeMove();  
             p=1;                                   
             if(ch.ifCastled())             {
                node tempc=ch.rhead();
                node temp=tempc;
 
                
                for (int ii = 8; ii >0; ii--) 
                {
                   for (int jj = 1; jj < 9; jj++) 
                   {
                      if (ii==8) 
                      {
                         if(temp.Piece.name().compareTo("bRook")==0)
                         {
                           img[ii][jj]=new ImageIcon("image/brook.gif");
                           chessBoardSquares[ii][jj].setIcon(img[ii][jj]);
                         }
                         if(temp.Piece.name().compareTo("null")==0)
                         {
                           chessBoardSquares[ii][jj].setIcon(null);
                         }
                         chessBoardSquares[ii][jj].setActionCommand(temp.Piece.name()+temp+jj ); 
                      } 
                      else if(ii==1)
                      {
                        if(temp.Piece.name().compareTo("wRook")==0)
                         {
                           img[ii][jj]=new ImageIcon("image/wrook.gif");
                           chessBoardSquares[ii][jj].setIcon(img[ii][jj]);
                         }
                         if(temp.Piece.name().compareTo("null")==0)
                         {
                           chessBoardSquares[ii][jj].setIcon(null);
                         }

                         chessBoardSquares[ii][jj].setActionCommand(temp.Piece.name()+temp+jj ); 
                      }
                      temp=temp.nextCol;
                   }
                   tempc=tempc.nextRow;
                   temp=tempc;
              } 
              img[r][c2]=img[r1][c1];
              chessBoardSquares[r][c2].setIcon(img[r][c2]);
              chessBoardSquares[r1][c1].setIcon(null);
              img[r1][c1]=null;
             }
              
    
             else if(ch.wPawnQueencheck())
              { 
                   img[r][c2]= new ImageIcon("image/wqueen.gif");
                   chessBoardSquares[r][c2].setIcon(img[r][c2]);
                   chessBoardSquares[r1][c1].setIcon(null);
                   img[r1][c1]=null;
              }
              else if(ch.bPawnQueencheck())
              {
                   img[r][c2]= new ImageIcon("image/bqueen.gif");
                   chessBoardSquares[r][c2].setIcon(img[r][c2]);
                   chessBoardSquares[r1][c1].setIcon(null);
                   img[r1][c1]=null;

              } 

           
            else           {                 
             chessBoardSquares[r][c2].setActionCommand(p1+c+r+c2 ); 
             img[r][c2]=img[r1][c1];
             chessBoardSquares[r][c2].setIcon(img[r][c2]);
             chessBoardSquares[r1][c1].setIcon(null);
             img[r1][c1]=null;
            }
             chessBoardSquares[r1][c1].setActionCommand("null"+d+r1+c1 );
             
             if(ch.checkwhite())
             {
                JOptionPane.showMessageDialog( f, "Check for white king..!!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
             }
             if(ch.checkblack())
             {
                JOptionPane.showMessageDialog( f, "Check for black king..!!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
             }
             if(ch.counterM() =='b')
             {    
             int x=ch.bCheckYAStale();  
               if(x==2)
               {
                  JOptionPane.showMessageDialog( f, "Its a CheckMate.. White WINS..!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
                  System.exit(0);
               }
               else if(x==1)
               {
                  JOptionPane.showMessageDialog( f, "Its a StaleMate.. Draw Match..!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
                  System.exit(0);
               }
             }
             else
             {
               int x=ch.wCheckYAStale();   
               if(x==2)
               {
                  JOptionPane.showMessageDialog( f, "Its a CheckMate.. Black WINS..!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
                  System.exit(0);
               }
               else if(x==1)
               {
                  JOptionPane.showMessageDialog( f, "Its a StaleMate.. Draw Match..!!",
                                         "Chess", JOptionPane.INFORMATION_MESSAGE );
                  System.exit(0);
               }
             }

             }
             for (int ii = 8; ii >0; ii--) {
                for (int jj = 1; jj < 9; jj++) {
 
             if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                        chessBoardSquares[ii][jj].setBackground(Color.WHITE);
                    } 
                    else {
                        chessBoardSquares[ii][jj].setBackground(Color.lightGray);
                     }
                  
                }
              }  
   
            
        }       
        if(flag != 1)
        {       
         count++;       
        }
        
        if(p !=0)
        {
         if(ch.counterM() =='w')
         {
            
          player.setText("WHITE'S TURN");
         }
         else
         {
            player.setText("BLACK'S TURN");
         }
         
        }
        
        
         
         if(!ch.king())
         {
           JOptionPane.showMessageDialog( f, "GAME OVER!!",
                                          "Smart Chess", JOptionPane.INFORMATION_MESSAGE );
           System.exit(0);                                        
          
         }           
    }
    
    private void menuAction( String btn ) {
        if(btn.compareTo("about")==0)
        {
         JOptionPane.showMessageDialog( f, "This simple game is created by Shrey , Ramabhadra"
                                          +" , Shreyas and Shouri","Smart Chess",
                                              JOptionPane.INFORMATION_MESSAGE );
        }
        else if(btn.compareTo("new")==0)
        {
         setNewGame();
        }
        
        else if(btn.compareTo("giveup")==0)
        {
         if(player.getText().charAt(0)=='W')
         {
            JOptionPane.showMessageDialog( f, "GAME OVER!! WINNER IS BLACK",
                                             "Smart Chess", JOptionPane.INFORMATION_MESSAGE );
            System.exit(0);
         }
         else
         {
              JOptionPane.showMessageDialog( f, "GAME OVER!! WINNER IS WHITE",
                                             "Smart Chess", JOptionPane.INFORMATION_MESSAGE );
              System.exit(0);
         }
        }

        
        else
        {
         int reply = JOptionPane.showConfirmDialog(f, "Do you really want to quit?",
                                                   "Smart Chess", JOptionPane.YES_NO_OPTION);
         if (reply == JOptionPane.YES_OPTION)
         {
           System.exit(0);
         }
        }
    
    
    }
    
        public void setNewGame() 
        {
            f.setVisible(false);
            chessui cb = new chessui();
        }  
}
