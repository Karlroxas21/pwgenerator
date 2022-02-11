import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame implements ActionListener {

    static final int num_six= 6;
    static final int num_twelve= 12;
    static final int num_eighteen= 18;
    static final int num_twentyone= 21;
    private final JPanel panel = new JPanel();
    private final JButton generateButton = new JButton("GENERATE ME PASSWORD");
    private final JRadioButton six = new JRadioButton("6");
    private final JRadioButton twelve = new JRadioButton("12");
    private final JRadioButton eigthteen = new JRadioButton("18");
    private final JRadioButton twenty_one = new JRadioButton("21");
    private final ButtonGroup group = new ButtonGroup();
    private final JButton resetButton = new JButton("Reset");
    private final JButton copyButton = new JButton("Copy");
    password_generator pw = new password_generator();
    String password_holder = "PASSWORD OUT";
    int num_length = 0;
    private JTextField password_out = new JTextField(password_holder);




    public Main(){

        generateButton.setBounds(10, 0, 410,30);
        generateButton.setFocusable(false);

        group.add(six);
        group.add(twelve);
        group.add(eigthteen);
        group.add(twenty_one);



        panel.setLayout(null);
//        panel.setBackground(Color.gray);
        panel.setBounds(25, 25, 430, 85);

        six.setBounds(20,30,50,50);
        six.setFocusable(false);
        twelve.setBounds(130,30,50,50);
        twelve.setFocusable(false);
        eigthteen.setBounds(250,30,50,50);
        eigthteen.setFocusable(false);
        twenty_one.setBounds(360,30,50,50);
        twenty_one.setFocusable(false);


        password_out.setBounds(2,130,480,50);
        password_out.setEditable(false);
        password_out.setHorizontalAlignment(JTextField.CENTER);
        password_out.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        resetButton.setBounds(35, 185, 70, 20);
        resetButton.setFocusable(false);

        copyButton.setFocusable(false);
        copyButton.setBounds(110, 185, 70, 20);


        panel.add(generateButton);

        panel.add(six);
        panel.add(twelve);
        panel.add(eigthteen);
        panel.add(twenty_one);

        generateButton.addActionListener(this);
        resetButton.addActionListener(this);
        copyButton.addActionListener(this);
        six.addActionListener(this);
        twelve.addActionListener(this);
        eigthteen.addActionListener(this);
        twenty_one.addActionListener(this);



        this.add(panel);
        this.add(resetButton);
        this.add(copyButton);

        this.add(password_out);

        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 250);
        this.setLayout(null);
        this.setVisible(true);
        this.validate();


    }

    public static void main(String [] args){
        new Main();
    }

    static String dateAndTime(){
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");


        return myDate.format(myFormat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == six){
            num_length = num_six;
        }
        if(e.getSource() == twelve){
            num_length = num_twelve;
        }
        if(e.getSource() == eigthteen){
            num_length = num_eighteen;
        }
        if(e.getSource() == twenty_one){
            num_length = num_twentyone;
        }
        if(e.getSource() == copyButton){
            StringSelection stringSelection = new StringSelection(password_out.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
        if(e.getSource() == resetButton){
            password_holder = pw.password = "";
            password_out.setText(password_holder);
            num_length = 0;
            group.clearSelection();
        }
        if(e.getSource() == generateButton){
            password_holder = pw.setPassword(num_length-1);
            password_out.setText(password_holder);

            String dateTime = dateAndTime();

            String url = "jdbc:sqlserver://DESKTOP-C280F8T\\MSSQLSERVER;DatabaseName=Credentials";
            String user = "papers";
            String password = "papersarewhite";

            try{

                Connection connection = DriverManager.getConnection(url, user, password);


                String sql = "INSERT INTO password_generated(password_, date_generated) VALUES('"+password_out.getText()+"','"+dateTime+"')";

                Statement statement = connection.createStatement();
                statement.execute(sql);

                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    //TODO:  Insert into SQL DATABASE
}
