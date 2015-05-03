package Model; /**
 * Model.KingPiece.java
 *
 * Version:
 *   $Id: Model.KingPiece.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: Model.KingPiece.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */
import java.awt.*;

/**
 * This is a class representing a king piece (a piece that has been kinged)
 *
 * @author
 *
 */
public class KingPiece implements Piece {

    private Color color;
  
   /**
    * This constructor creates a king piece object
    * 
    * @param c - the color of this king piece
    * @param pc - the possible captures of this king piece
    * @param pm - the possible moves of this king piece
    */
   public KingPiece( Color c ) {
        this.color = c;
   }

    @Override
    public Color getColor() {
        return color;
    }
}//Model.KingPiece
