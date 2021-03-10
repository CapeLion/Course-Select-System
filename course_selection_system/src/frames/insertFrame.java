package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import db_operate.admin_operate;
import role_model.Admin;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import role_model.*;
import java.awt.Color;
import java.awt.Toolkit;
public class insertFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String tN = "";
	/**
	 * Launch the application.
	 */
	public insertFrame() {
		getContentPane().setBackground(new Color(240, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\course_selection_system\\src\\icon\\\u7535\u8111.png"));
		setBackground(new Color(224, 255, 255));
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertFrame frame = new insertFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public insertFrame(String tableName) {
		tN = tableName;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(36, 96, 366, 50);
		contentPane.add(textField);
		textField.setColumns(12);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u5728\u6587\u672C\u6846\u4E2D\u8F93\u5165\u4FE1\u606F\uFF0C\u5404\u5B57\u6BB5\u7528\u201C#\u201D\u9694\u5F00");
		lblNewLabel.setBounds(36, 36, 366, 37);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertAct();
			}
		});
		btnNewButton.setBounds(36, 178, 152, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAct();
			}
		});
		btnNewButton_1.setBounds(250, 178, 152, 42);
		contentPane.add(btnNewButton_1);
	}
	public void insertAct() {
		String txtString = textField.getText();
		String [] info = txtString.split("\\#");
		boolean flag = new Admin().insertInfo(tN, info);
		if(flag) {
			JOptionPane.showMessageDialog(this, "添加成功");
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "添加失败,请检查数据是否存在冲突");
		}
	}
	public void closeAct() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}
