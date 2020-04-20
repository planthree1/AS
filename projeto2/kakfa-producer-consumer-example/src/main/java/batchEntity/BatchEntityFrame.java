package batchEntity;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class BatchEntityFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1116558856002320715L;

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
	public BatchEntityFrame() {
		setTitle("Batch Entity");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px][440px]", "[1px][][265px]"));
		
		JLabel lblBatchEntity = new JLabel("Batch Entity");
		lblBatchEntity.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(lblBatchEntity, "cell 0 0,grow");
		
		lblShowingRecordsFrom = new JLabel("Showing records from Bath Entity Topic:");
		contentPane.add(lblShowingRecordsFrom, "cell 0 1");
		
		txtOutput = new JTextArea(13, 200);
		txtOutput.setEditable(false);
		txtOutput.setLineWrap(true);
				
		JScrollPane scroll = new JScrollPane(txtOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setSize(getMinimumSize());
		contentPane.add(scroll, "cell 0 2 2 1,growx,aligny top");		
	}
}
