//Sophia Babayev
//3/21/2025
//CheckerPawn
//It move the same way a checker piece would move. It captures by jumping over the piece diagonally next to it but moves foward by one when not capturing.
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
public class CheckerPawn extends Piece {

    
    public CheckerPawn(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
    

  @Override
  public String toString() {
    return "a "+ super.toString() + " CheckerPawn.";
  }

    
    //Pre- The inputs cant be null and the square referred to must be in bounds of the board.
    //Post- Returns what moves are possible for the peice to defend against.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

      ArrayList<Square> moves = new ArrayList<Square>();

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
    

    //Pre- The inputs cant be null and the square referred to must be in bounds of the board.Pre-
    //Post- Returns what moves are possible for the Piece to move to.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      //start by declaring the arrayList we wish to return at the end
      ArrayList<Square> moves = new ArrayList<Square>();

      Square s = null;
      Square sl = null;
    
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