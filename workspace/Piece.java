
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    

    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

      ArrayList<Square> moves = new ArrayList<Square>();

      /*if (!color && (start.getRow()+1<8)){

          moves.add(board[start.getRow()+1][start.getCol()]);
      }


      if (color && (start.getRow()+1<8)){

          moves.add(board[start.getRow()-1][start.getCol()]);
      }
*/

      if ((start.getRow()+1<8) && (start.getCol()+1<8)){
   
            moves.add(board[start.getRow()+1][start.getCol()+1]);
      }


      if ((start.getRow()-1>=0) && (start.getCol()+1<8)){

            moves.add(board[start.getRow()-1][start.getCol()+1]);
      }
     

      if ((start.getRow()-1>=0) && (start.getCol()-1>=0)){

            moves.add(board[start.getRow()-1][start.getCol()-1]);
      }


      if ((start.getRow()+1<8) && (start.getCol()-1>=0)){

            moves.add(board[start.getRow()+1][start.getCol()-1]); 
      }
     return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      //start by declaring the arrayList we wish to return at the end
      ArrayList<Square> moves = new ArrayList<Square>();

      Square s = null;
      Square sl = null;
    
      /*make sure the squares we are adding are in bounds. 
      (you’d also need to check to make sure they aren’t occupied by a piece unless it’s a piece of the opposite color in which case you can capture it.
      I leave this bit for you to figure out.*/
      if (!color && (start.getRow()+1<8)){

        s = b.getSquareArray()[start.getRow()+1][start.getCol()];
        if (!s.isOccupied()){
          moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()]);
        }

      }
      
      if (color && (start.getRow()-1>=0)){

        s = b.getSquareArray()[start.getRow()-1][start.getCol()];
        if (!s.isOccupied()){
          moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()]);
        }

      }

      if ((start.getRow()+2<8) && (start.getCol()+2<8)){

        s = b.getSquareArray()[start.getRow()+1][start.getCol()+1];
        sl = b.getSquareArray()[start.getRow()+2][start.getCol()+2];
        if (s.isOccupied() && s.getOccupyingPiece().getColor() != color ){
          if (!sl.isOccupied()){
            moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()+2]);
          }
        }

      }


      if ((start.getRow()-2>=0) && (start.getCol()+2<8)){

        s = b.getSquareArray()[start.getRow()-1][start.getCol()+1];
        sl = b.getSquareArray()[start.getRow()-2][start.getCol()+2];
        if (s.isOccupied() && s.getOccupyingPiece().getColor() != color ){
          if (!sl.isOccupied()){
            moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()+2]);
          }
        }
        
      }

     

      if ((start.getRow()-2>=0) && (start.getCol()-2>=0)){

        s = b.getSquareArray()[start.getRow()-1][start.getCol()-1];
        sl = b.getSquareArray()[start.getRow()-2][start.getCol()-2];
        if (s.isOccupied() && s.getOccupyingPiece().getColor() != color){
          if (!sl.isOccupied()){
            moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()-2]);
          }
        }
        
      }


      if ((start.getRow()+2<8) && (start.getCol()-2>=0)){

        s = b.getSquareArray()[start.getRow()+1][start.getCol()-1];
        sl = b.getSquareArray()[start.getRow()+2][start.getCol()-2];
        if (s.isOccupied() && s.getOccupyingPiece().getColor() != color){
          if (!sl.isOccupied()){
            moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()-2]);
          }
        }
        
      }
      
     return moves;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
    }
  }