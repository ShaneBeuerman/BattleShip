package battleship;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

    public static void main(String[] args) {
        char[][] board = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                board[i][j] = '-';
            }
        }
        setupGUI();
    }
    
    public static void enemySetShips(char[][] board){
        setShip(board, 5);
        setShip(board, 4);
        setShip(board, 3);
        setShip(board, 3);
        setShip(board, 2);
        
    }
    
    public static void setShip(char[][] board, int n){
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        int direction = rand.nextInt(2);
        int counter = 0;
        if(direction == 0){
            if(x + n > 9){
                setShip(board, n);
            }
            else{
                for(int i = 0; i < n; i++){
                    if(board[x+i][y] == 'X'){
                        setShip(board, n);
                    }
                    else{
                        counter++;
                    }
                }
                if(counter == n){
                    for(int i = 0; i < n; i++){
                        board[x+i][y] = 'X';
                    }
                }
            }
        } else {
            if (y + n > 9) {
                setShip(board, n);
            } else {
                for (int i = 0; i < n; i++) {
                    if (board[x][y+i] == 'X') {
                        setShip(board, n);
                    } else {
                        counter++;
                    }
                }
                if (counter == n) {
                    for (int i = 0; i < n; i++) {
                        board[x][y+i] = 'X';
                    }
                }
            }
        }
        
    }
    
    
    public static void mark(char[][] board, int x, int y){
        board[x][y] = 'X';
    }
    
    public static void setupGUI(){
        char[][] board = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                board[i][j] = 'O';
            }
        }
        enemySetShips(board);
        JFrame game = new JFrame("Battleship");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton[][] buttons = new JButton[10][10];
        GridLayout gridLayout = new GridLayout(10, 10);
        JPanel grid = new JPanel();
        grid.setLayout(gridLayout);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int currentI = i;
                int currentJ = j;
                buttons[i][j] = new JButton("-");
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg) {
                        if (hit(board, currentI, currentJ)) {
                            buttons[currentI][currentJ].setText("X");
                        } else {
                            buttons[currentI][currentJ].setText("O");
                        }
                    }
                });
                grid.add(buttons[i][j]);
            }
        }
        game.add(grid);
        game.pack();
        game.setVisible(true);

    }

    public static boolean hit(char[][] board, int x, int y) {
        if(board[x][y] == 'X'){
            return true;
        }
        return false;
    }

}
