import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

class DatePicker {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	JLabel l = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];

	public DatePicker(JFrame parent) {
		d = new JDialog();
		d.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));

		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6)
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						d.dispose();
					}
				});
			if (x < 7) {
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			p1.add(button[x]);
		}
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton("Next >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(parent);
		displayDate();
		d.setVisible(true);
	}

	public void displayDate() {
		for (int x = 7; x < button.length; x++)
			button[x].setText("");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
			button[x].setText("" + day);
		l.setText(sdf.format(cal.getTime()));
		d.setTitle("Date Picker");
	}

	public String setPickedDate() {
		if (day.equals(""))
			return day;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd-MM-yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		return sdf.format(cal.getTime());
	}
}

public class Picker {
    String end_date;
    String start_date;
    
    Picker()
    {
    }
	public static void main(String[] args)throws IOException  {
        JLabel label = new JLabel("Start Date:");
		final JTextField text = new JTextField(20);
		JButton b = new JButton("Select");
		JPanel p = new JPanel();
		p.add(label);
		p.add(text);
		p.add(b);

        JLabel label1 = new JLabel("End Date:");
		final JTextField text1 = new JTextField(20);
		JButton b1 = new JButton("Select");
		JPanel p1 = new JPanel();
		p1.add(label1);
		p1.add(text1);
		p1.add(b1);

        JButton b2 = new JButton("Download");
		JPanel p2 = new JPanel();
		p2.add(b2);

        JButton b3 = new JButton("Compile and Run");
		JPanel p3 = new JPanel();
		

		JButton b4 = new JButton("Chose Output File");
        p3.add(b4);
        p3.add(b3);

        JLabel ticker = new JLabel("Initializing....");
		JPanel p4 = new JPanel();
        p4.add(ticker);

        JPanel p5 = new JPanel();
        p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
		p5.add(p);
        p5.add(p1);
        p5.add(p2);
        p5.add(p3);
        p5.add(p4);
        
        final JFrame f = new JFrame("Assignment Checker");
        f.getContentPane().add(p5);
		f.pack();
		f.setVisible(true);

        Picker o = new Picker();

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                o.start_date = new DatePicker(f).setPickedDate();
				text.setText(o.start_date);
                ticker.setText("Start Date Selected"); 
			}
		});

        b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                o.end_date = new DatePicker(f).setPickedDate();
				text1.setText(o.end_date);
                ticker.setText("End Date Selected"); 
			}
		});

        b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                try { 
			// create a process 
			ProcessBuilder pb = new ProcessBuilder("/bin/bash"); 

			// take all commands as input in a text file 
			File commands = new File("commands.txt"); 

			// File where error logs should be written 
			File error = new File("useless.txt"); 

			// File where output should be written 
			File output = new File("useless2.txt"); 

			// redirect all the files 
			pb.redirectInput(commands); 
			pb.redirectOutput(output); 
			pb.redirectError(error); 

			// start the process 
            ticker.setText("Downloading..."); 
			pb.start();
		} 
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}  
			}
		});

       b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                try { 
			// create a process 
            ticker.setText("Running Programs.");
			ProcessBuilder pb3 = new ProcessBuilder("/bin/bash"); 

			// take all commands as input in a text file 
			File commands = new File("commands3.txt"); 

			// File where error logs should be written 
			File error = new File("error.txt"); 

			// File where output should be written 
			File output = new File("output.txt"); 

			// redirect all the files 
			pb3.redirectInput(commands); 
			pb3.redirectOutput(output); 
			pb3.redirectError(error); 

			// start the process 
			pb3.start(); 
            ticker.setText("Marks.csv Created");
		} 
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		} 
			}
		});
        
	}
}
