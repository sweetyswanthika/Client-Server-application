package chattingapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener {

	JTextField text;
	static JPanel p2;
	static Box vertical = Box.createVerticalBox();
	static DataOutputStream dout;
	static JFrame frame = new JFrame();
	
	Client()
	{
		frame.setLayout(null); //to set frame layout user defined
		
		JPanel p1 = new JPanel(); //for creation of panels on the frame
		//p1.setBackground(Color.GREEN);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70); //set size of panel -> x,y,width,height
		p1.setLayout(null);
		frame.add(p1);
		
		//for back arrow image on panel
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT); //setting image size
		ImageIcon i3 = new ImageIcon(i2); //need to convert to image icon to put on the label
		JLabel back = new JLabel(i3);
		back.setBounds(5, 20, 25, 25); //sets size of arrow image
		p1.add(back); //adding back arrow image to panel1
		
		//For mouse click on arrow to exit
		back.addMouseListener(new MouseAdapter() {
		  @Override
		public void mouseClicked(MouseEvent e) {
              System.exit(0);
		  } 
		  
		});
		
		//for profile image on panel
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/profile.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT); //setting image size
		ImageIcon i6 = new ImageIcon(i5); //need to convert to image icon to put on the label
		JLabel profile = new JLabel(i6);
		profile.setBounds(40, 10, 50, 50);  //setting label size on panel
		p1.add(profile); //adding image to panel1
		
		//for video image on panel
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); //setting image size
		ImageIcon i9 = new ImageIcon(i8); //need to convert to image icon to put on the label
		JLabel video = new JLabel(i9);
		video.setBounds(300, 20, 30, 30); //setting label size on panel
		p1.add(video); //adding image to panel1
		
		//for call image on panel
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT); //setting image size
		ImageIcon i12 = new ImageIcon(i11); //need to convert to image icon to put on the label
		JLabel call = new JLabel(i12);
		call.setBounds(360, 20, 35, 30); //setting label size on panel
		p1.add(call); //adding image to panel1
		
		//for more options image on panel
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14); //need to convert to image icon to put on the label
		JLabel moreopt = new JLabel(i15);
	    moreopt.setBounds(420, 20, 10, 25);  //setting label size on panel
		p1.add(moreopt); //adding image to panel1
		
		//jLabel helps in mainly writing on the frame
		
		//Writing your name on panel
		JLabel name = new JLabel("James");
		name.setBounds(110, 15, 100, 18); //Setting label size on panel
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
		p1.add(name);
		
		JLabel status = new JLabel("Active now");
		status.setBounds(110, 35, 100, 18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
		p1.add(status);
		
		//creating another panel for chat
		
		p2 = new JPanel();
		p2.setBounds(5, 75, 440, 570);
		frame.add(p2);
		
		//panel for typing chat text
		 text = new JTextField();
		text.setBounds(5, 655, 310, 40);
		text.setFont(new Font("SAN_SARIF", Font.PLAIN, 16));
		frame.add(text);
		
		//creating button for send
		JButton send = new JButton("Send");
		send.setBounds(320, 655, 123, 40);
		send.setBackground(new Color(7, 94, 84));
		send.setForeground(Color.WHITE);
		send.addActionListener(this);
		send.setFont(new Font("SAN_SARIF", Font.PLAIN, 16));
		frame.add(send);
		
		//setting main frame
		frame.setSize(450,700);
		frame.setLocation(800,50); //to set top and side distance
		frame.getContentPane().setBackground(Color.WHITE); //to set main frame background
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		String out = text.getText();

		//JLabel output = new JLabel(out);
		
		//JPanel p3 = new JPanel();
		
        JPanel p3 = formatLabel(out);
        
        //p3.add(output);

        p2.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p3, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        p2.add(vertical, BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        frame.repaint();
        frame.invalidate();
        frame.validate();
        text.setText(" ");
		}catch(Exception e1) {
			e1.printStackTrace();
		}
   }
	
	public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
	
	public static void main(String[] args) {
		new Client();
		try {
			Socket s = new Socket("127.0.0.1", 5000);
			DataInputStream din = new DataInputStream(s.getInputStream());
		    dout = new DataOutputStream(s.getOutputStream());
		    while(true) {
		    	p2.setLayout(new BorderLayout());
		        String msg = din.readUTF();
		        JPanel panel = formatLabel(msg);
		        
		        JPanel left = new JPanel(new BorderLayout());
		        left.add(panel, BorderLayout.LINE_START);
		        vertical.add(left);
		        
		        vertical.add(Box.createVerticalStrut(15));

		        p2.add(vertical, BorderLayout.PAGE_START);
		        
		      //directly with frame you cannot call from static method so create object of Jframe class to
		      // use the methods instead of extends
		        frame.validate();	        
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
