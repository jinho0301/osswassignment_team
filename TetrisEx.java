import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class TetrisEx extends JFrame {
   Container c = getContentPane();
   TetrisPanel TP = new TetrisPanel();
   JDialog JD = new JDialog();
   TetrisThread th;
   static int blocksize = 20;
   private int count;
   
   int End = 0;
   int random = 0 , random2 = (int)(Math.random()*7);
   
   int score =0;
   
   int wid=100;
   int hgt= 0;
   int rotation = 0;
   
   boolean limit = false;
   
   int curX[]= new int[4], curY[] = new int [4];
   
   int blocks[][][][]  = {
	        {
		           //■
		           //■■■
		           {
		              {0,0,0,0},
		              {1,0,0,0},
		              {1,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,1,0},
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {1,1,1,0},
		              {0,0,1,0},
		              {0,0,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,1,0},
		              {0,0,1,0},
		              {0,1,1,0},
		              {0,0,0,0}
		           }
		        },
		        {
		              //  ■
		              //■■■
		           {
		              {0,0,0,0},
		              {0,0,1,0},
		              {1,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,1,0},
		              {1,0,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,1,0},
		              {0,0,1,0},
		              {0,0,1,0},
		              {0,0,0,0}
		           }
		        },
		        {
		              //  ■■
		              //  ■■
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           }
		        },
		        {
		              // ■■■■
		           {
		              {0,0,0,0},
		              {0,0,0,0},
		              {1,1,1,1},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,1,0,0}
		           },
		           {
		              {0,0,0,0},
		              {0,0,0,0},
		              {1,1,1,1},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,1,0,0},
		              {0,1,0,0}
		           }
		        },
		        {
		               //■
		              //■■■
		           {
		              {0,0,0,0},
		              {0,1,0,0},
		              {1,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,1,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,1,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {1,1,0,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           }   
		        },
		        {
		               //  ■■
		               //   ■■
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {0,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,1,0},
		              {0,1,1,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {1,1,0,0},
		              {0,1,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,1,0},
		              {0,1,1,0},
		              {0,1,0,0},
		              {0,0,0,0}
		           }
		        },
		        {
		               //  ■■
		              //  ■■
		           {
		              {0,0,0,0},
		              {0,1,1,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,1,0},
		              {0,0,1,0},
		              {0,0,0,0}
		           },
		           {
		              {0,0,0,0},
		              {0,1,1,0},
		              {1,1,0,0},
		              {0,0,0,0}
		           },
		           {
		              {0,1,0,0},
		              {0,1,1,0},
		              {0,0,1,0},
		              {0,0,0,0}
		           }   
		        }
		  };
   int[][] gameboard = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
		   	{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

   JButton btn = new JButton("재도전");
   JLabel lbl = new JLabel();
   JLabel lbl2 = new JLabel();
      
   TetrisEx(){
      setTitle("테트리스");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      TP.setSize(720,600);
      
      add(TP);
      th = new TetrisThread();
      JD.setTitle("점수");
      JD.setSize(250,190);
      JD.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
      lbl.setFont(new Font("arial",Font.PLAIN,15));
      lbl2.setText("점  수");
      lbl2.setFont(new Font("나눔고딕",Font.PLAIN,15));
      
      
      TP.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
               int keyCode = e.getKeyCode();
                        
               if(keyCode == KeyEvent.VK_UP)
                  TP.moveUp();
               if(keyCode == KeyEvent.VK_DOWN)
                  TP.moveDown();
               if(keyCode == KeyEvent.VK_LEFT)
                  TP.moveLeft();
               if(keyCode == KeyEvent.VK_RIGHT)
                  TP.moveRight();
            }
         });
      
      btn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            limit = false;
            for(int y=0; y<19;y++)
               for(int x=1; x<12; x++)
                  gameboard[y][x] =0 ;
            score =0;
            wid =100; hgt = 0;
         }
      });
      
      TP.setBackground(Color.WHITE);
      setSize(530,520);
      setVisible(true);
      Dimension frameSize = this.getSize();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
      JD.setLocation((screenSize.width - frameSize.width)/2 + 220, (screenSize.height - frameSize.height)/2 +220);
      TP.requestFocus(true);
      th.start();
   }
   
   
   
   class TetrisPanel extends JPanel{
      public void paintComponent(Graphics g){
         
         int cnt = 0 , cnt2 = 0;
         TP.requestFocus(true);
         super.paintComponent(g);
         lbl2.setLocation(353,120);
         TP.add(lbl2);
         
         lbl.setLocation(360,145);
         TP.add(lbl);
         lbl.setText(Integer.toString(score*100));
         Color[] randomcolor = {Color.CYAN, Color.BLUE, Color.ORANGE, Color.GREEN, Color.PINK, Color.RED, Color.YELLOW};
         g.setColor(randomcolor[count]); 
         blockLookAhead(g);
         gameOverCheck();
         removeBlock(cnt, cnt2, g);
         blockToWall();
         makeBlock(g);
         makeBorder(g);
         if(End == 1){
            random2 = (int)(Math.random()*7);
            End = 0;
         }
      }
      
      public void blockLookAhead(Graphics g){
         for(int a = 0; a<4 ;a++){
              for(int b = 0; b<4;b++){
                 if(blocks[random2][0][a][b] == 1){
                    g.fill3DRect(b*blocksize+120, a*blocksize, blocksize, blocksize, true);
                 }
              }
          }
      }
      
      public void gameOverCheck(){
         for(int x=1;x<12;x++)
             if(gameboard[2][x]==1){
                limit = true;
                JD.add(lbl);
                JD.add(btn);
                JD.setVisible(true);
             }
      }
      
      public void removeBlock(int cnt, int cnt2, Graphics g){
         for(int y =0;y<19;y++){
             for(int x =1; x<12 ; x++){
                if(gameboard[y][x] == 1){
                   cnt2++;
                }
             }
             if(cnt2 == 11){
                for(int i=y;i>1;i--)
                   for(int j=1;j<13;j++){
                      gameboard[i][j] = 0;
                      gameboard[i][j] = gameboard[i-1][j];
                   }
               score++;
             }else{
                blockDown(cnt,g);
             }
             cnt2 = 0 ;
          }
      }
      
      public void makeBlock(Graphics g){
         g.setColor(Color.GRAY); 
          for(int y=0; y<19;y++){
             for(int x=1; x<12; x++){
                if(gameboard[y][x]== 1){
                   g.fill3DRect( x*blocksize+20, y*blocksize+60, blocksize, blocksize, true);
                }
             }
          }
      }
      
      public void blockDown(int cnt, Graphics g){
         for(int j = 0; j<4 ;j++){
              for(int k = 0; k<4;k++){
                 if(blocks[random][rotation][j][k] == 1){
                    curX[cnt] = ((k*blocksize)+wid)/blocksize; curY[cnt] = ((j*blocksize)+hgt)/blocksize;
                    g.fill3DRect(curX[cnt]*blocksize+20, curY[cnt]*blocksize+60, blocksize, blocksize, true);
                    
                    cnt ++;
                 }
              }
           }
      }
      public void blockToWall(){
         try{
         for(int z = 0; z<4 ; z++)
              if(gameboard[curY[z]+1][curX[z]] == 1){
            	  count = (int)(Math.random()*7);
                    for (int j= 0; j<4;j++){
                      
                          gameboard[curY[j]][curX[j]] = 1;
                          wid =100; 
                          hgt =0;
                          End = 1;
                          rotation = 0;
                          random = random2;
                    	}
              		}
         }catch(ArrayIndexOutOfBoundsException e){ System.out.println("Error");
         for(int i=0; i<4 ; i++)
               System.out.print("("+curY[i]+","+curX[i]+")");
            System.out.println();}
         
      }
      public int collision_LEFT(){
         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]-1] == 1) 
               return 1;
         }
         return 0;
      }
      public int collision_RIGHT(){

         for(int i=0; i<4; i++){
            if(gameboard[curY[i]][curX[i]+1] == 1)   
               return 1;
         }
         return 0; 
      }
      public void rotationCheck(){
         int cnt2=0;
          for(int j = 0; j<4 ;j++){
              for(int k = 0; k<4;k++){
                 int rotation2 = (rotation%4)+1 ;
                 if(rotation2 == 4)
                    rotation2 = 0;
                 if(blocks[random][rotation2][j][k] == 1){
                    curX[cnt2] = ((k*blocksize)+wid)/blocksize; curY[cnt2] = ((j*blocksize)+hgt)/blocksize;
                    cnt2++;
                 }    
              }
          }
          int chk = 0;
          int blank =0;
          int error = 0;
                      if(gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1) || (random == 1 && gameboard[curY[1]][curX[1]] ==1 )){
                         chk = 1; 
                         error++;
                         System.out.println("chk1");
                         if(random == 3){ 
                            for(int i=1;i<5;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank < 4)
                               chk = 4;
                            
                              System.out.println(blank);
                         }else{ 
                            for(int i=1; i<4;i++)
                               if(gameboard[curY[0]][curX[0]+i] == 0)
                                  blank++;
                            if(blank <3)
                               chk = 4;
                            System.out.println("blank2");
                            System.out.println(blank);
                         }
                      }
                      else if(gameboard[curY[2]][curX[2]] == 1){
                        error++;
                       chk = 2;
                       System.out.println("chk2");
                       
                       for(int i=1; i<5;i++)
                          if(gameboard[curY[2]][curX[2]-i] == 0)
                             blank ++;
                       if(blank < 4)
                          chk = 4;
                       System.out.println("blank2");
                       System.out.println(blank);
                             
                       
                    }
                      else if(gameboard[curY[3]][curX[3]] == 1){
                       error++;
                       chk = 3; 
                       System.out.println("chk3");
                       for(int i=0; i<5;i++)
                          if(gameboard[curY[3]][curX[3]-i] == 0)
                             blank ++;
                       if(blank < 4)
                          chk = 4;
                       System.out.println(blank);
                       
                    }
          if(chk == 1){ 
             wid += blocksize;
             rotation++;
             rotation = rotation%4;
            }else if (chk ==2){
               wid -= blocksize*2;
               rotation++;
               rotation = rotation%4;
            }else if (chk ==3){
               wid -= blocksize;
               rotation++;
               rotation = rotation%4;
            }else if(chk == 4){
               System.out.println("ban");
            }else{
               rotation++;
                rotation = rotation%4;
            }
      }
      
      public void makeBorder(Graphics g){
         g.setColor(Color.BLACK);
         
         g.draw3DRect(28, 70, 5, 375,true);
         g.fill3DRect(28, 70, 5, 375,true);
         g.draw3DRect(265, 70, 5, 375, true);
         g.fill3DRect(265, 70, 5, 375, true);
         g.draw3DRect(28, 445, 242, 5,true);
         g.fill3DRect(28, 445, 242, 5,true);
         g.draw3DRect(28, 65, 242, 5, true);
         g.fill3DRect(28, 65, 242, 5, true);
      }
      
      void down(){
         hgt +=blocksize;
         TP.repaint();
      }
      void moveUp(){
         rotationCheck();
          if(limit == false)
             repaint();
      }
      void moveDown(){
         if(limit == false){
            hgt += blocksize;
            TP.repaint();
         }
      }
      void moveLeft(){
         int sel = collision_LEFT();
         if(sel == 0 && limit == false){
            wid -= blocksize;
               TP.repaint();
         }
      }
      void moveRight(){
         int sel = collision_RIGHT();
         if(sel == 0 && limit == false){ 
            wid += blocksize;
            TP.repaint();
         }
      }
   }
   
   class TetrisThread extends Thread{
      TetrisPanel TP = new TetrisPanel();
      public void run(){
         while(true){
            try{
               sleep(500);
               if(limit == false)
                  TP.down();
            }catch(InterruptedException e){
               return;
            }
         }
      }
   }
   
   public static void main(String[] args){
      new TetrisEx();
   }
}
