import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.*;
import org.jvnet.substance.*;
public class LoginForm extends JFrame implements ActionListener
    {
    private JLabel lbluid=new JLabel("User Name: ");
    private JLabel lblpwd=new JLabel("Password : ");
    private JTextField txtuid=new JTextField(15);
    private JPasswordField txtpwd=new JPasswordField(15);
    private JButton btnok=new JButton("Login");
    private JButton btnclose=new JButton("Log Out ");
    private JPanel sp=new JPanel(),
                   np=new JPanel(),
                   cp=new JPanel();
    Container cpane;
    String uid=null,pwd=null;
		     static {
        try {

            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMetalWallWatermark");
            SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSunfireRedTheme");
            SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
            SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.StandardButtonShaper");
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public LoginForm()
        {
        setSize(320,200);        
        setLocation(350,200);
		  setResizable(false);
        //sp.setBackground(Color.blue);
        //np.setBackground(Color.gray);
        //cp.setBackground(Color.cyan);
        
        btnok.addActionListener(this);		  
        btnclose.addActionListener(this);
        
        lbluid.setBounds(10,10,100,25);
        txtuid.setBounds(110,10,150,25);
        lblpwd.setBounds(10,40,100,25);
        txtpwd.setBounds(110,40,150,25);
       
        JLabel title=new JLabel("Login");
        np.add(title);
        cp.setLayout(null);
        cp.add(lbluid);cp.add(txtuid);
        cp.add(lblpwd);cp.add(txtpwd);

        sp.add(btnok);sp.add(btnclose);

        cpane=getContentPane();
        cpane.add(np,"North");
        cpane.add(cp,"Center");
        cpane.add(sp,"South");
		 
        }
    public void actionPerformed(ActionEvent e)
        {
        Object cmd=e.getSource();
        if (cmd.equals(btnok))
            {
                
                Statement st = null;
ResultSet rs = null;
				try{
            String auid=txtuid.getText();
            String apwd=txtpwd.getText();	
            Class.forName("com.mysql.jdbc.Driver");	
	Connection		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datahide","root","root");
            st = con.createStatement();
	String qry ="select * from user where name='"+auid+"' AND password='"+apwd+"'"; 
	rs = st.executeQuery(qry);
        while(rs.next()){
            
         
            
            
				if ((auid.equals(rs.getString("name"))) && (apwd.equals(rs.getString("password"))))
					 		{							
							JOptionPane.showMessageDialog(null,"Successfully Login","Admin",JOptionPane.INFORMATION_MESSAGE);
							new MainClient();							
							this.dispose();					
							
							}
					 else
					 		{
							JOptionPane.showMessageDialog(null,"Invalid Login,Try Again","Admin",JOptionPane.INFORMATION_MESSAGE);
							}					 
        }
					}
					catch(Exception e1){}
					
            }
		 
        if (cmd.equals(btnclose))
            {
            System.exit(0);
            }
        }

    public static void main(String arg[])
        {
        LoginForm frm=new LoginForm();
        frm.setVisible(true);
        }
    }
	
