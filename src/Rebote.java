import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
public class Rebote extends JFrame {
    private final int w = 400;
    private final int h = 200;
    private Timer t;
    private int x = 200;
    private int y = 100;
    private int step = 5;
    private int numRebotes = 1;
    private ImageIcon ball;
    private String direccion = "r";
    private int ballW, ballH;
    private final DrawCanvas canvas;

    public Rebote() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Rebotes: 0");
        add(label);

        ball = new ImageIcon(getClass().getResource("/resources/pacman.gif"));
        ballW = ball.getIconWidth();
        ballH = ball.getIconHeight();
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(w, h));
        add(canvas);

        setTitle("Rebotes");
        setResizable(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        canvas.setFocusable(true);
        canvas.requestFocusInWindow();
        canvas.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    direccion = "u";
                }else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    direccion = "d";
                }else if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    direccion = "l";
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    direccion = "r";
                }
            }
        });

        t = new Timer(10, new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (direccion.equals("r"))
                {
                    if (x < w - ballW - step)
                    {
                        x += step;
                    }else {
                        direccion = "l";
                        label.setText("Rebotes: " + numRebotes++);
                    }
                }
                else if (direccion.equals("l"))
                {
                    if (x >= step) {
                        x -= step;
                    }else {
                        direccion = "r";
                        label.setText("Rebotes: " + numRebotes++);
                    }
                }else if (direccion.equals("u"))
                {
                    if (y >= step)
                    {
                        y -= step;
                    }else {
                        direccion = "d";
                        label.setText("Rebotes: " + numRebotes++);
                    }
                }else if (direccion.equals("d"))
                {
                    if (y < h - ballH - step)
                    {
                        y += step;
                    }else {
                        direccion = "u";
                        label.setText("Rebotes: " + numRebotes++);
                    }
                }
                repaint();
            }
        });
    }

    public void init() {
        t.start();
    }

    class DrawCanvas extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.GRAY);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
            ball.paintIcon(this, g, x, y);
        }
    }
}