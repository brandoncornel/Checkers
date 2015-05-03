package Model; /**
 * Model.Piece.java
 *
 * Version:
 *   $Id: Model.Piece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: Model.Piece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

/**
 * This is an abstract class representing any piece that
 * know about it's color and possible moves and captures
 *
 * @author
 *
 */

import java.util.*;
import java.awt.*;

public interface Piece {
	
   /**
    * This method returns the color of this piece
    * 
    * @return the color for this piece
    */
   public Color getColor();
   
}// Model.Piece
