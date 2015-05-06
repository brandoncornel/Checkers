package GUI;/*
 * GUI.CheckerGUI.java
 * 
 * The actual board.
 *
 * Created on January 25, 2002, 2:34 PM
 * 
 * Version
 * $Id: GUI.CheckerGUI.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * 
 * Revisions
 * $Log: GUI.CheckerGUI.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 *
 */

import Model.Board;
import Model.KingPiece;
import System.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 * @version
 */

public class CheckerGUI extends JFrame implements ActionListener{

    private String blueSingle = "file:BlueSingle.gif";
    private String whiteSingle = "file:WhiteSingle.gif";
    private String blueKing = "file:BlueKing.gif";
    private String whiteKing = "file:WhiteKing.gif";


    private static Facade theFacade; //the facade

    private List<JButton> possibleSquares = new ArrayList<JButton>();//a vector of the squares
    private int timeRemaining;//the time remaining
    private List<JButton> validMoves = new ArrayList<JButton>();


    private JLabel PlayerOnelabel;
    private JLabel playerTwoLabel;
    private JLabel timeRemainingLabel;
    private JButton ResignButton;
    private JButton DrawButton;
    private JLabel warningLabel, whosTurnLabel;

    //the names and time left
    private static String playerOnesName="", playerTwosName="", timeLeft="";

    /**
     *
     * Constructor, creates the GUI and all its components
     *
     * @param facade the facade for the GUI to interact with
     * @param name1 the first players name
     * @param name2 the second players name
     *
     */

    public CheckerGUI( Facade facade, String name1, String name2 ) {

        super("Checkers");

        //long names mess up the way the GUI displays
        //this code shortens the name if it is too long
        String nameOne="", nameTwo="";
        if(name1.length() > 7 ){
            nameOne = name1.substring(0,7);
        }else{
            nameOne = name1;
        }
        if(name2.length() > 7 ){
            nameTwo = name2.substring(0,7);
        }else{
            nameTwo = name2;
        }

        playerOnesName = nameOne;
        playerTwosName = nameTwo;
        theFacade = facade;
        register();

        initComponents ();
        pack ();
        update();
        //updateTime();
    }
    
    
    /*
     * This method handles setting up the timer
     */

    private void register() {

        try{
            theFacade.addActionListener( this );

        }catch( Exception e ){

            System.err.println( e.getMessage() );

        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form. It initializes the components
     * adds the buttons to the Vecotr of squares and adds
     * an action listener to the components 
     *
     */
    private void initComponents() {

        this.setResizable( false );

        //sets the layout and adds listener for closing window
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;

        //add window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        int x = 0;
        int y = 1;
        int row = 1;
        for(int i = 0; i < 64; i++) {
            JButton button = new JButton();
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(80, 80));
            String name = Integer.toString(i);
            button.setActionCommand(name);
            if(row % 2 != 0) {
                if (i % 2 == 0) {
                    button.setBackground(Color.white);
                } else {
                    button.setBackground(new Color(204, 204, 153));
                    validMoves.add(button);
                }
            }
            else{
                if (i % 2 != 0) {
                    button.setBackground(Color.white);
                } else {
                    button.setBackground(new Color(204, 204, 153));
                    validMoves.add(button);
                }
            }
            gridBagConstraints1 = new java.awt.GridBagConstraints();
            gridBagConstraints1.gridx = x;
            gridBagConstraints1.gridy = y;
            x++;
            if(x == 8){
                y++;
                row++;
                x = 0;
            }
            getContentPane().add(button, gridBagConstraints1);
            possibleSquares.add(button);

        }

        PlayerOnelabel = new JLabel();
        playerTwoLabel = new JLabel();
        whosTurnLabel = new JLabel();

        warningLabel = new JLabel( );
        timeRemainingLabel = new JLabel();

        ResignButton = new JButton();
        ResignButton.addActionListener( this );

        DrawButton = new JButton();
        DrawButton.addActionListener( this );



        PlayerOnelabel.setText("Model.Player 1:     " + playerOnesName );
        PlayerOnelabel.setForeground( Color.black );

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 4;
        getContentPane().add(PlayerOnelabel, gridBagConstraints1);

        playerTwoLabel.setText("Model.Player 2:     " + playerTwosName );
        playerTwoLabel.setForeground( Color.black );

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 9;
        gridBagConstraints1.gridwidth = 4;
        getContentPane().add(playerTwoLabel, gridBagConstraints1);

        whosTurnLabel.setText("");
        whosTurnLabel.setForeground( new Color( 0, 100 , 0 ) );

        gridBagConstraints1.gridx=8;
        gridBagConstraints1.gridy=1;
        getContentPane().add(whosTurnLabel, gridBagConstraints1 );

        warningLabel.setText( "" );
        warningLabel.setForeground( Color.red );

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 2;
        getContentPane().add( warningLabel, gridBagConstraints1 );

        timeRemainingLabel.setText("Play Checkers");
        getContentPane().add(timeRemainingLabel, gridBagConstraints1);

        ResignButton.setActionCommand("resign");
        ResignButton.setText("Resign");

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 3;
        getContentPane().add(ResignButton, gridBagConstraints1);

        DrawButton.setActionCommand("draw");
        DrawButton.setText("Draw");

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 4;
        getContentPane().add(DrawButton, gridBagConstraints1);

    }


    private void exitForm(java.awt.event.WindowEvent evt) {
        theFacade.pressQuit();

    }

    public boolean movableSpot(ActionEvent e){
        for(JButton b : validMoves) {
            if (b.getActionCommand().equals(e.getActionCommand())) {
                System.out.println("HERE");
                return true;
            }
        }
        return false;
    }

    /**
     * Takes care of input from users, handles any actions performed
     *
     * @param e  the event that has occured
     *
     */

    public void actionPerformed( ActionEvent e ) {

        try{
            if(movableSpot(e) == true){
                theFacade.selectSpace(Integer.parseInt(e.getActionCommand()));
            }
            else if( e.getActionCommand().equals("draw") ){
                //does sequence of events for a draw
                theFacade.pressDraw();

                //if resign is pressed
            }else if( e.getActionCommand().equals( "resign" ) ) {
                //does sequence of events for a resign
                theFacade.pressQuit();

                //if the source came from the facade
            }else if( e.getSource().equals( theFacade ) ) {

                //if its a player switch event
                if ( (e.getActionCommand()).equals(theFacade.update) ) {
                    //update the GUI
                    update();
                } else {
                    throw new Exception( "unknown message from facade" );
                }
            }
            //catch various Exceptions
        }catch( NumberFormatException excep ){
            System.err.println(
                    "GUI exception: Error converting a string to a number" );
        }catch( NullPointerException exception ){
            System.err.println( "GUI exception: Null pointerException "
                    + exception.getMessage() );
            exception.printStackTrace();
        }catch( Exception except ){
            System.err.println( "GUI exception: other: "
                    + except.getMessage() );
            except.printStackTrace();
        }

    }


    private void placePic(JButton temp, int i, String pic){
        temp = (JButton)possibleSquares.get(i);
        try{
            temp.setIcon(
                    new ImageIcon( new URL(pic) ));
        }catch( MalformedURLException e ){
            System.out.println(e.getMessage());
        }
    }

    public boolean occupAndBlue(Board board, int i){
        return (board.occupied(i) && board.colorAt(i) == Color.blue);
    }


    private void update(){
        if( checkEndConditions() ){
            theFacade.showEndGame(" ");
        }
        Board board = theFacade.stateOfBoard();
        JButton temp =  new JButton();
        for( int i = 1; i < board.sizeOf(); i++ ){
            if (occupAndBlue(board, i)){
                if((!isKing(board,i))){
                    placePic(temp, i, blueSingle);
                }else if((isKing(board,i) )) {
                    placePic(temp, i, blueKing);
                }
            else if( board.colorAt( i ) == Color.white ){
                if((!isKing(board,i))){
                    placePic(temp, i, whiteSingle);
                }else if((isKing(board,i)) ){
                    placePic(temp,i,whiteKing);
                }
            }
            }else {
                //show no picture
                temp = (JButton)possibleSquares.get(i);
                temp.setIcon( null );
            }
        }

        //this code updates whos turn it is
        if(theFacade.whosTurn() == 2 ){
            playerTwoLabel.setForeground( Color.red );
            PlayerOnelabel.setForeground(Color.black );
            whosTurnLabel.setText( playerTwosName + "'s turn ");
        }else if( theFacade.whosTurn() == 1 ){
            PlayerOnelabel.setForeground( Color.red );
            playerTwoLabel.setForeground(Color.black );
            whosTurnLabel.setText( playerOnesName + "'s turn" );
        }
    }




    /**
     * Checks the ending condotions for the game
     * see if there a no pieces left
     *
     * @return the return value for the method
     *           true if the game should end
     *           false if game needs to continue 
     */

    public boolean checkEndConditions(){

        //the return value
        boolean retVal = false;
        try{
            //the number of each piece left
            int whitesGone = 0 , bluesGone = 0;

            //the board to work with
            Board temp = theFacade.stateOfBoard();

            //go through all the spots on the board
            for( int i=1; i<temp.sizeOf(); i++ ){
                //if there is a piece there
                if(occupAndBlue(temp, i)){
                        // increment number of blues
                    bluesGone++;
                        //if the piece is white
                }else if( (temp.getPieceAt( i )).getColor() == Color.white && temp.occupied(i) ){
                        //increment number of whites
                        whitesGone++;
                    }
            }//end of for loop

            //if either of the number are 0
            if( whitesGone == 0 || bluesGone == 0 ){
                retVal = true;
            }

        }catch( Exception e ){

            System.err.println( e.getMessage() );

        }
        return retVal;

    }//checkEndConditions

    private boolean isKing(Board board, int i){
        return board.getPieceAt(i) instanceof KingPiece;
    }

}//checkerGUI.java
