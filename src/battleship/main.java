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

    /*
     main function()
     */
    public static void main(String[] args) {
        setupGUI();
    }

    /*
     setShip takes in a 10x10 board and marks a ships position with a 'X'
     value. It randomly chooses a position on the board and checks to see
     if the ship can fit in the part of the board.
     */
    public static void setShip(char[][] board, int n) {
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        int direction = rand.nextInt(2);
        if (direction == 0) {
            if (x + n > 9) {
                setShip(board, n);
            } else if (checkAhead(board, x, y, direction, n)) {
                for (int i = 0; i < n; i++) {
                    board[x + i][y] = 'X';
                }
            } else {
                setShip(board, n);
            }
        } else {
            if (y + n > 9) {
                setShip(board, n);
            } else if (checkAhead(board, x, y, direction, n)) {
                for (int i = 0; i < n; i++) {
                    board[x][y + i] = 'X';
                }
            } else {
                setShip(board, n);
            }
        }

    }

    /*
     checkAhead checks if the board already is marked with a ship ahead of it.
     If the ship cannot be inserted into the board without colliding with another
     ship, then it returns false. If it finds no collision, it returns true.
     */
    public static boolean checkAhead(char[][] board, int x, int y, int d, int n) {
        if (d == 0) {
            for (int i = 0; i < n; i++) {
                if (board[x + i][y] == 'X') {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (board[x][y + i] == 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     SetupGUI() creates a 10x10 grid of buttons that you can click.
     If you click a button, it will be marked with an 'X' if it is
     a hit, and marked with a 'O' if it is a miss.
     */
    public static void setupGUI() {
        char[][] board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 'O';
            }
        }
        setShip(board, 5);
        setShip(board, 4);
        setShip(board, 3);
        setShip(board, 3);
        setShip(board, 2);
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
                        if (board[currentI][currentJ] == 'X') {
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
}
