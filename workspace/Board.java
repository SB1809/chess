import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
    private static final String RESOURCES_WROOK_PNG = "wrook.png";
    private static final String RESOURCES_BROOK_PNG = "brook.png";
    private static final String RESOURCES_WKING_PNG = "wking.png";
    private static final String RESOURCES_BKING_PNG = "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    
    // Logical and graphical representations of board 
    private final Square[][] board;
    private final GameWindow g;
 
    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // Populate the board with squares
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            if (r % 2 == 0) {
                count = 0;
            } else {
                count = 1;
            }

            for (int c = 0; c < board[r].length; c++) {
                if (count % 2 == 0) {
                    board[r][c] = new Square(this, true, r, c);
                } else if (count % 2 == 1) {
                    board[r][c] = new Square(this, false, r, c);
                }
                this.add(board[r][c]);
                count++;
            }
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;
    }

    // Set up the board such that the black pieces are on one side and the white pieces on the other.
    private void initializePieces() {
        for (int i = 0; i < 8; i++) {
            board[1][i].put(new CheckerPawn(false, RESOURCES_BPAWN_PNG));
            board[6][i].put(new CheckerPawn(true, RESOURCES_WPAWN_PNG));
        }
        board[0][4].put(new King(false, RESOURCES_BKING_PNG));
        board[7][4].put(new King(true, RESOURCES_WKING_PNG));
        board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if (sq == fromMoveSquare)
                    sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
            }
        }

        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn) || (!currPiece.getColor() && !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            System.out.println(currPiece);
            fromMoveSquare = sq;
            if (!currPiece.getColor() == whiteTurn)
            {
                currPiece=null;
                return;
            }
                
            sq.setDisplay(false);
        }
        repaint();
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        Piece taken = null;
        
        // Check if the move is within bounds and the square is a valid targe
            //ArrayList<Square> legalMoves = currPiece.getLegalMoves(this, fromMoveSquare);
            
            // Check if the move is legal
            if (currPiece != null && currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
                // Move the piece to the new square
                if(endSquare.isOccupied()) {
                    taken = endSquare.getOccupyingPiece();
                    }
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
                
                if(currPiece instanceof CheckerPawn){
                    if(fromMoveSquare.getRow()-2 == endSquare.getRow() && fromMoveSquare.getCol()-2 == endSquare.getCol()){
                        taken = board[fromMoveSquare.getRow()-1][fromMoveSquare.getCol()-1].getOccupyingPiece();
                        board[fromMoveSquare.getRow()-1][fromMoveSquare.getCol()-1].removePiece();
                    }
                    if(fromMoveSquare.getRow()+2 == endSquare.getRow() && fromMoveSquare.getCol()+2 == endSquare.getCol()){
                        taken = board[fromMoveSquare.getRow()+1][fromMoveSquare.getCol()+1].getOccupyingPiece();
                        board[fromMoveSquare.getRow()+1][fromMoveSquare.getCol()+1].removePiece();
                    }
                    if(fromMoveSquare.getRow()-2 == endSquare.getRow() && fromMoveSquare.getCol()+2 == endSquare.getCol()){
                        taken = board[fromMoveSquare.getRow()-1][fromMoveSquare.getCol()+1].getOccupyingPiece();
                        board[fromMoveSquare.getRow()-1][fromMoveSquare.getCol()+1].removePiece();
                    }
                    if(fromMoveSquare.getRow()+2 == endSquare.getRow() && fromMoveSquare.getCol()-2 == endSquare.getCol()){
                        taken = board[fromMoveSquare.getRow()+1][fromMoveSquare.getCol()-1].getOccupyingPiece();
                        board[fromMoveSquare.getRow()+1][fromMoveSquare.getCol()-1].removePiece();
                    }
                }

                if (isInCheck(whiteTurn)) {
                    fromMoveSquare.put(currPiece);
                    endSquare.put(taken);
                    taken = null;
                    // Switch the turn to the other player
                    
                }
                else{
                    whiteTurn = !whiteTurn;
                }
                
            
        }
    
        
        // Clear the visual border of all squares
        for (Square[] row : board) {
            for (Square s : row) {
                s.setBorder(null);
            }
        }

        // Reset the current piece and refresh the display
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        if(currPiece != null) {
        ArrayList<Square> moves = currPiece.getLegalMoves(this, fromMoveSquare);
            for (Square s : moves) {
                s.setBorder(BorderFactory.createLineBorder(Color.red));
            }

            ArrayList<Square> movess = currPiece.getControlledSquares(board, fromMoveSquare);
            for (Square s : movess) {
                s.setBorder(BorderFactory.createLineBorder(Color.blue));
            }  
        }


        repaint();
    }


    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor) {
        Square king = null;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].isOccupied() && board[row][col].getOccupyingPiece() instanceof King && board[row][col].getOccupyingPiece().getColor() == kingColor) {
                    king = board[row][col];
                    break;
                }
            }
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col].isOccupied() && board[row][col].getOccupyingPiece().getColor() != kingColor) {
                    ArrayList < Square > contSq = board[row][col].getOccupyingPiece().getControlledSquares(board, board[row][col]);
                    if (contSq.contains(king)) {
                        if (kingColor)
                            System.out.println("white in check");
                        else
                            System.out.println("black in check");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
