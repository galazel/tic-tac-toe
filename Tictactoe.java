import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class Tictactoe extends JFrame implements ActionListener
{
    private Container con = getContentPane();
    private JButton[][] buttons = new JButton[3][3];
    private int count = 0;
    private static int computerWins = 0;
    private static int humanWins = 0;
    private JLabel human = new JLabel("Human Wins: "+humanWins);
    private JLabel computer = new JLabel("Computer Wins: "+computerWins);
    private JButton playAgain = new JButton("Play Again");
    private Font font = new Font("Monospaced",Font.PLAIN,20);
    public Tictactoe()
    {
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe");
        setContainer();
        setVisible(true);
    }
    private void setContainer()
    {
        con.setLayout(new BorderLayout());
        con.add(titlePanel(),BorderLayout.NORTH);
        con.add(mainPanel(),BorderLayout.CENTER);
        con.add(sidePanel(),BorderLayout.SOUTH);
    }
    private JPanel titlePanel()
    {
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("TIC TAC TOE");
        title.setFont(new Font("Monospaced",Font.BOLD,50));
        titlePanel.add(title);

        return titlePanel;
    }
    private JPanel mainPanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3,0,0));

        for(int i = 0; i < buttons.length; ++i)
        {
            for(int j = 0; j < buttons[i].length; ++j)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.WHITE);
                mainPanel.add(buttons[i][j]);
            }
        }

        return mainPanel;
    }
    private JPanel sidePanel()
    {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));
        human.setFont(font);
        computer.setFont(font);
        sidePanel.add(human);
        sidePanel.add(computer);
        sidePanel.add(playAgain);
        playAgain.addActionListener( e -> {

            Object source = e.getSource();
            if(source == playAgain)
            {
                for(int i = 0; i < buttons.length; ++i)
                {
                    for(int j = 0; j < buttons[i].length; ++j)
                    {
                        buttons[i][j].setText("");
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setBackground(Color.WHITE);
                        count = 0;
                    }
                }
            }

        });
        return sidePanel;
    }

    private void computer()
    {
        int row = ((int)Math.ceil(Math.random() * 3)) - 1;
        int col = ((int)Math.ceil(Math.random() * 3)) - 1;
        boolean isEmpty = false;

            for (int i = 0; i < buttons.length; ++i) {
                for (int j = 0; j < buttons[i].length; ++j) {
                    if (buttons[row][col].getText().isEmpty()) {
                        isEmpty = true;
                        break;
                    }else if(!buttons[row][col].getText().isEmpty()) {
                        do {
                            row = ((int) Math.ceil(Math.random() * 3)) - 1;
                            col = ((int) Math.ceil(Math.random() * 3)) - 1;
                        } while (!buttons[row][col].getText().isEmpty());
                        isEmpty = true;
                        break;
                    }else
                    {
                        break;
                    }

                }
            }

        if(isEmpty) {
            buttons[row][col].setText("O");
            buttons[row][col].setFont(new Font("Monospaced",Font.BOLD,40));
            buttons[row][col].setForeground(Color.BLACK);
            buttons[row][col].setEnabled(false);
        }


        if(determineComputerWinner()) {
            JOptionPane.showMessageDialog(null, "COMPUTER WINS");
            ++computerWins;
            computer.setText("Computer Wins: "+computerWins);

        }
        else if(count == 4)
        {
            JOptionPane.showMessageDialog(null, "DRAW");
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        for(int i = 0; i < buttons.length; ++i)
        {
            for(int j = 0; j < buttons[i].length; ++j)
            {
                if(source == buttons[i][j])
                {
                        buttons[i][j].setText("X");
                        buttons[i][j].setFont(new Font("Monospaced",Font.BOLD,40));
                        buttons[i][j].setForeground(Color.BLACK);
                        buttons[i][j].setEnabled(false);

                        if(determineHumanWinner()) {
                            JOptionPane.showMessageDialog(null, "HUMAN WINS");
                            ++humanWins;
                            human.setText("Human Wins: "+humanWins);

                        }
                        else if(count == 4)
                        {
                            JOptionPane.showMessageDialog(null, "DRAW");
                            for(JButton[] button: buttons)
                                for(JButton bttn: button)
                                    bttn.setEnabled(false);
                        }else {
                            JOptionPane.showMessageDialog(null,"Computer's Turn");
                            computer();
                        }
                        break;
                }
            }
        }
        ++count;

    }

    private boolean determineHumanWinner()
    {
        boolean isHumanWin = false;

        if(buttons[0][0].getText().equals("X") && buttons[0][1].getText().equals("X") && buttons[0][2].getText().equals("X"))
        {
            buttons[0][0].setBackground(Color.red);
            buttons[0][1].setBackground(Color.red);
            buttons[0][2].setBackground(Color.red);

            isHumanWin = true;

            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);

        }else if(buttons[1][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[1][2].getText().equals("X")) {
            buttons[1][0].setBackground(Color.red);
            buttons[1][1].setBackground(Color.red);
            buttons[1][2].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[2][0].getText().equals("X") && buttons[2][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            buttons[2][0].setBackground(Color.red);
            buttons[2][1].setBackground(Color.red);
            buttons[2][2].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][0].getText().equals("X") && buttons[1][0].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            buttons[0][0].setBackground(Color.red);
            buttons[1][0].setBackground(Color.red);
            buttons[2][0].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][1].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][1].getText().equals("X")) {
            buttons[0][1].setBackground(Color.red);
            buttons[1][1].setBackground(Color.red);
            buttons[2][1].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][2].getText().equals("X") && buttons[1][2].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            buttons[0][2].setBackground(Color.red);
            buttons[1][2].setBackground(Color.red);
            buttons[2][2].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);

        }
        else if(buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            buttons[0][0].setBackground(Color.red);
            buttons[1][1].setBackground(Color.red);
            buttons[2][2].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            buttons[0][2].setBackground(Color.red);
            buttons[1][1].setBackground(Color.red);
            buttons[2][0].setBackground(Color.red);

            isHumanWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
//        else if((!buttons[0][0].getText().equals("X") && !buttons[0][1].getText().equals("X") && !buttons[0][2].getText().equals("X")) || (!buttons[1][0].getText().equals("X") && !buttons[1][1].getText().equals("X") && !buttons[1][2].getText().equals("X")) || (!buttons[2][0].getText().equals("X") && !buttons[2][1].getText().equals("X") && !buttons[2][2].getText().equals("X")) || (!buttons[0][0].getText().equals("X") && !buttons[1][0].getText().equals("X") && !buttons[2][0].getText().equals("X")) || (!buttons[0][1].getText().equals("X") && !buttons[1][1].getText().equals("X") && !buttons[2][1].getText().equals("X")) || (!buttons[0][2].getText().equals("X") && !buttons[1][2].getText().equals("X") && !buttons[2][2].getText().equals("X")) || (!buttons[0][0].getText().equals("X") && !buttons[1][1].getText().equals("X") && !buttons[2][2].getText().equals("X")) || (!buttons[0][2].getText().equals("X") && !buttons[1][1].getText().equals("X") && !buttons[2][0].getText().equals("X")))
//        {
//            JOptionPane.showMessageDialog(null, "DRAW");
//            for(JButton[] button: buttons)
//                for(JButton bttn: button)
//                    bttn.setEnabled(false);
//        }
        return isHumanWin;
    }
    private boolean determineComputerWinner()
    {
        boolean isComputerWin = false;
        if(buttons[0][0].getText().equals("O") && buttons[0][1].getText().equals("O") && buttons[0][2].getText().equals("O"))
        {
            buttons[0][0].setBackground(Color.blue);
            buttons[0][1].setBackground(Color.blue);
            buttons[0][2].setBackground(Color.blue);

            isComputerWin = true;

            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);

        }else if(buttons[1][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[1][2].getText().equals("O")) {
            buttons[1][0].setBackground(Color.blue);
            buttons[1][1].setBackground(Color.blue);
            buttons[1][2].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[2][0].getText().equals("O") && buttons[2][1].getText().equals("O") && buttons[2][2].getText().equals("O")) {
            buttons[2][0].setBackground(Color.blue);
            buttons[2][1].setBackground(Color.blue);
            buttons[2][2].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][0].getText().equals("O") && buttons[1][0].getText().equals("O") && buttons[2][0].getText().equals("O")) {
            buttons[0][0].setBackground(Color.blue);
            buttons[1][0].setBackground(Color.blue);
            buttons[2][0].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][1].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][1].getText().equals("O")) {
            buttons[0][1].setBackground(Color.blue);
            buttons[1][1].setBackground(Color.blue);
            buttons[2][1].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][2].getText().equals("O") && buttons[1][2].getText().equals("O") && buttons[2][2].getText().equals("O")) {
            buttons[0][2].setBackground(Color.blue);
            buttons[1][2].setBackground(Color.blue);
            buttons[2][2].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);

        }
        else if(buttons[0][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][2].getText().equals("O")) {
            buttons[0][0].setBackground(Color.blue);
            buttons[1][1].setBackground(Color.blue);
            buttons[2][2].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }
        else if(buttons[0][2].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][0].getText().equals("O")) {
            buttons[0][2].setBackground(Color.blue);
            buttons[1][1].setBackground(Color.blue);
            buttons[2][0].setBackground(Color.blue);
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }else if(count == 9)
        {
            JOptionPane.showMessageDialog(null, "DRAW");
            isComputerWin = true;
            for(JButton[] button: buttons)
                for(JButton bttn: button)
                    bttn.setEnabled(false);
        }


        return isComputerWin;
    }


}

