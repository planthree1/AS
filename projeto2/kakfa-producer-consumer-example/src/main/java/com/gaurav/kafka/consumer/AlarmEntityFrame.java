package com.gaurav.kafka.consumer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class AlarmEntityFrame extends JFrame {

	private JPanel contentPane;
	
	// Custom variables
	private JTextArea txtOutput;
	private JLabel lblShowingRecordsFrom;
	
	
	// Getters and Setters
	public String getTxtOutput() {
		return txtOutput.getText();
	}

	public void setTxtOutput(String str) {
		this.txtOutput.setText(str);
	}

	/**
	 * Create the frame.
	 */
	public AlarmEntityFrame() {
		setTitle("Alarm Entity");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px][440px]", "[1px][][265px]"));
		
		JLabel lblBatchEntity = new JLabel("Alarm Entity");
		lblBatchEntity.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(lblBatchEntity, "cell 0 0,grow");
		
		lblShowingRecordsFrom = new JLabel("Showing records from Alarm Entity Topic:");
		contentPane.add(lblShowingRecordsFrom, "cell 0 1");
		
		txtOutput = new JTextArea(13, 200);
		txtOutput.setEditable(false);
		txtOutput.setLineWrap(true);
				
		JScrollPane scroll = new JScrollPane(txtOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setSize(getMinimumSize());
		contentPane.add(scroll, "cell 0 2 2 1,growx,aligny top");
	}
}
