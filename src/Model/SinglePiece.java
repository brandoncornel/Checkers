package Model; /**
 * Model.SinglePiece.java
 *
 * Version:
 *   $Id: Model.SinglePiece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: Model.SinglePiece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

import java.awt.*;

/**
 * This is a class representing a single piece (a piece that has not been
 * kinged yet)
 *
 * @author
 *
 */
public class SinglePiece implements Piece {

    private Color color;
   
   /**
    * This constructor creates a single piece checker object
    * 
    * @param c - the color of this single piece
    * @param pc - the possible captures of this single piece
    * @param pm - the possible moves of this single piece
    */
   public SinglePiece( Color c  ) {
       this.color = c;
   }

    @Override
    public Color getColor() {
        return color;
    }

}// Model.SinglePiece
