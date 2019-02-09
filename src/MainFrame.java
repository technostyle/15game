package FifteenGamePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel topPanel;
    private JPanel buttonsPanel;
    private JButton[] buttons;
    private StateMatrix matrix;
    private ImageLoader imageLoader;
    //private static int N = 3;
    private static int N = 4;
    private static int NxN = N * N;

    public static int GetDimension() {
        return N;
    }

    private void Init() {
        matrix = new StateMatrix(N);
        buttons = new JButton[NxN];
        topPanel = new JPanel();
        buttonsPanel = new JPanel();
        imageLoader = new ImageLoader();        
    }

    private void Restart() {
        matrix.Reset();
        RenderState();
    }

    private void RenderState() {
        for(int i = 0; i < NxN; i++) {
            int stateIndex = matrix.getState(i);
            buttons[i].setIcon(new ImageIcon(imageLoader.numberImage[stateIndex]));                
        }
    }

    public void onButtonClick(String buttonIndex) {
        int index = Integer.parseInt(buttonIndex);
        matrix.MoveSquare(index);

        RenderState();
        if (matrix.GameIsFinished()) {
            Finalize();
            Restart();
        }    
    }

    public void Finalize() {
        JOptionPane.showMessageDialog(null, "Congratulations!");
    }

    public MainFrame() {
        Init();        

        setTitle("15 Game");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ConfigTopPanel();
        add(topPanel, BorderLayout.NORTH);

        ConfigButtonPanel();
        add(buttonsPanel,BorderLayout.CENTER);

        Restart();
    }

    private void ConfigTopPanel() {
        JButton startButton = new JButton();
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restart();
            }
        });

        topPanel.add(startButton);
    }

    private void ConfigButtonPanel() {
        buttonsPanel.setLayout(new GridLayout(N, N));
        
        for (int i = 0; i < NxN; i++) {
            buttons[i] = new JButton();
            buttons[i].setName("" + i);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onButtonClick(((JButton) e.getSource()).getName());
                }
            });
        
            buttonsPanel.add(buttons[i]);
        }
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}



