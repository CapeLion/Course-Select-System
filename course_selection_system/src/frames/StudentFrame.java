package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db_operate.student_operate;
import net.miginfocom.swing.MigLayout;
import role_model.Course;
import role_model.student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.function.ObjDoubleConsumer;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentFrame extends MainFrame {
	public String selected_term;
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel panel_work_area;//右侧页面
	//选课
	//public JPanel xk_select_panel;//
	//public JPanel xk_input_panel;
	//public JPanel xk_course_panel;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable kjxk_out;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	//退课
	private JTable infoShow;
	private JTextField Course_id_input;
	private JTextField Course_name_input;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private student user;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student tmpStudent = new student();
					tmpStudent.set_User_Id("1104");
					StudentFrame frame = new StudentFrame(tmpStudent);
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
	public StudentFrame(student stu) {
		jcombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String term=jcombobox.getSelectedItem().toString();
				//System.out.println(term);
				selected_term=term;
			}
		});
		//退出按钮
		jbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		user=stu;
		getContentPane().setBackground(new Color(0, 191, 255));
		jsplitpane.setBackground(new Color(240, 255, 255));
		//panel_work_area=jsplitpane;
		//setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//左侧
		JPanel panel_left = new JPanel();
		panel_left.setForeground(new Color(255, 255, 255));
		panel_left.setBackground(new Color(128, 128, 128));
		jsplitpane.setLeftComponent(panel_left);
		panel_left.setLayout(new MigLayout("", "[0px:2px:20px,grow 2][grow 2][0:2:20,grow 2]", "[40][10][40px][10][40][10][40][10][40][10][40][][]"));
		
		JButton btn_xk = new JButton("\u9009\u8BFE");
		//btn_xk.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		btn_xk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//出现xk_panel1
				//先把上一次点击结果清除
				panel_work_area.removeAll();
				set_xk_select_panel();
				jsplitpane.setRightComponent(panel_work_area);
			}
		});
		btn_xk.setForeground(new Color(255, 255, 255));
		btn_xk.setBackground(new Color(112, 128, 144));
		panel_left.add(btn_xk, "cell 1 0,grow");
		btn_xk.setOpaque(false);//设置按钮透明w
		JButton btn_tk = new JButton("\u9000\u8BFE");
		btn_tk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_work_area.removeAll();
				set_tk_select_panel();
				jsplitpane.setRightComponent(panel_work_area);
			}
		});
		btn_tk.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		btn_tk.setForeground(new Color(255, 255, 255));
		btn_tk.setBackground(new Color(112, 128, 144));
		panel_left.add(btn_tk, "cell 1 2,grow");
		btn_tk.setOpaque(false);//设置按钮透明
		btn_tk.setBorder(null);
		JButton btn_kccx = new JButton("\u8BFE\u7A0B\u67E5\u8BE2");
		btn_kccx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_work_area.removeAll();
				JScrollPane scrollPane = new JScrollPane();
				infoShow = new JTable();
				infoShow.setModel(new DefaultTableModel(
						new Object[][]{},
						new Object [] {"课号","课名","教师姓名","上课时间","上课地点"}
				));
				DefaultTableModel model = (DefaultTableModel) infoShow.getModel();
				Vector<String[]> data = new student_operate().getChosenClass(stu.get_User_Id());
				model.setRowCount(0);
				for(Object tmp[]:data) {
					model.addRow(tmp);
				}
				scrollPane.setViewportView(infoShow);
				jsplitpane.setRightComponent(scrollPane);
			}
		});
		btn_kccx.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		btn_kccx.setForeground(new Color(255, 255, 255));
		btn_kccx.setBackground(new Color(112, 128, 144));
		panel_left.add(btn_kccx, "cell 1 4,grow");
		btn_kccx.setOpaque(false);//设置按钮透明
		btn_kccx.setBorder(null);
		
		
		
		
		
		
		//右侧
		JPanel panel_right = new JPanel();
		
		panel_right.setBackground(new Color(224, 255, 255));
		
		panel_right.setLayout(new MigLayout("", "[grow]", "[40px:40px:70px][100px:165px:180px][grow]"));
		
		panel_work_area=panel_right;
		//
		//jsplitpane.setRightComponent(panel_work_area);
		
		
		//出现xk_panel_2
				
				

	}
	public void set_xk_select_panel() {
		//点击选课出现xk_panel_1
		
		JPanel xk_panel_1 = new JPanel();
		xk_panel_1.setBackground(new Color(248, 248, 255));
		panel_work_area.add(xk_panel_1, "cell 0 0,grow");
		xk_panel_1.setLayout(new MigLayout("", "[][5px:5px:10px][][5px:5px:10px][]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u7535\u8111.png"));
		xk_panel_1.add(lblNewLabel_1, "cell 0 0");
		
		ButtonGroup group_select = new ButtonGroup();;
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u5FEB\u6377\u9009\u8BFE");
		group_select.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//先将panel_work_area的下标为1的清空
				//xk_input_panel.removeAll();
				if(panel_work_area.getComponents().length>1)
				   panel_work_area.remove(1);
				set_xk_input_panel();
				//panel_work_area.add(xk_input_panel);
				jsplitpane.setRightComponent(panel_work_area);
			}
		});
		rdbtnNewRadioButton.setBackground(new Color(248, 248, 255));
		
		xk_panel_1.add(rdbtnNewRadioButton, "cell 2 0");

		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u6A21\u7CCA\u67E5\u8BE2");
		group_select.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//先将panel_work_area的下标为1的清空
				//xk_input_panel.removeAll();
				if(panel_work_area.getComponents().length>1)
				   panel_work_area.remove(1);
				set_mhcx_input();
				//panel_work_area.add(xk_input_panel);
				jsplitpane.setRightComponent(panel_work_area);
			}
		});
		rdbtnNewRadioButton_1.setBackground(new Color(248, 248, 255));
		xk_panel_1.add(rdbtnNewRadioButton_1, "cell 4 0");
		
		
		
	}
	public void set_tk_select_panel() {
		
		JPanel panel_tk = new JPanel();
		jsplitpane.setRightComponent(panel_tk);
		panel_work_area.add(panel_tk, "cell 0 0,grow");
		
		JLabel lblNewLabel_13 = new JLabel("\u8BFE\u7A0B\u53F7");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_13_1 = new JLabel("\u6559\u5E08\u53F7");
		lblNewLabel_13_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_13_1_1 = new JLabel("\u6559\u5E08\u53F7");
		lblNewLabel_13_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_13_2 = new JLabel("\u8BFE\u7A0B\u53F7");
		lblNewLabel_13_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		
		textField_21 = new JTextField();
		textField_21.setColumns(10);
		
		textField_22 = new JTextField();
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setColumns(10);
		
		textField_24 = new JTextField();
		textField_24.setColumns(10);
		
		textField_25 = new JTextField();
		textField_25.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("1");
		
		JLabel lblNewLabel_14_1 = new JLabel("2");
		
		JLabel lblNewLabel_14_1_1 = new JLabel("3");
		
		JLabel lblNewLabel_14_1_1_1 = new JLabel("4");
		
		JLabel lblNewLabel_14_1_1_2 = new JLabel("5");
		
		JLabel lblNewLabel_14_1_1_3 = new JLabel("6");
		panel_tk.setBackground(new Color(224, 255, 255));
		panel_tk.setLayout(new MigLayout("", "[28px][66px][66px][28px][66px][66px]", "[50px][21px][21px][21px][]"));
		panel_tk.add(lblNewLabel_13, "cell 1 0,alignx left,growy");
		panel_tk.add(lblNewLabel_13_1, "cell 2 0,alignx center,aligny center");
		panel_tk.add(lblNewLabel_13_1_1, "cell 5 0,alignx left,aligny center");
		panel_tk.add(lblNewLabel_13_2, "cell 4 0,alignx left,growy");
		panel_tk.add(textField_14, "cell 1 1,alignx left,aligny top");
		panel_tk.add(textField_15, "cell 2 1,alignx left,aligny top");
		panel_tk.add(textField_16, "cell 4 1,alignx left,aligny top");
		panel_tk.add(textField_17, "cell 5 1,alignx left,aligny top");
		panel_tk.add(textField_18, "cell 1 2,alignx left,aligny top");
		panel_tk.add(textField_19, "cell 2 2,alignx left,aligny top");
		panel_tk.add(textField_20, "cell 4 2,alignx left,aligny top");
		panel_tk.add(textField_21, "cell 5 2,alignx left,aligny top");
		panel_tk.add(textField_22, "cell 1 3,alignx left,aligny top");
		panel_tk.add(textField_23, "cell 2 3,alignx left,aligny top");
		panel_tk.add(textField_24, "cell 4 3,alignx left,aligny top");
		panel_tk.add(textField_25, "cell 5 3,alignx left,aligny top");
		panel_tk.add(lblNewLabel_14, "cell 0 1,grow");
		panel_tk.add(lblNewLabel_14_1, "cell 0 2,growx,aligny top");
		panel_tk.add(lblNewLabel_14_1_1, "cell 0 3,grow");
		panel_tk.add(lblNewLabel_14_1_1_1, "cell 3 1,grow");
		panel_tk.add(lblNewLabel_14_1_1_2, "cell 3 2,grow");
		panel_tk.add(lblNewLabel_14_1_1_3, "cell 3 3,grow");
		
		JButton tkButton = new JButton("\u9000\u8BFE");
		tkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean can = true;
				if(textField_14.getText().length()>1&&textField_15.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_14.getText(),textField_15.getText());
				}
				if(textField_16.getText().length()>1&&textField_17.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_16.getText(),textField_17.getText());
				}
				if(textField_18.getText().length()>1&&textField_19.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_18.getText(),textField_19.getText());
				}
				if(textField_20.getText().length()>1&&textField_21.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_20.getText(),textField_21.getText());
				}
				if(textField_22.getText().length()>1&&textField_23.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_22.getText(),textField_23.getText());
				}
				if(textField_24.getText().length()>1&&textField_25.getText().length()>1) {
					can=new student_operate().delete(user.get_User_Id(),textField_24.getText(),textField_25.getText());
				}
			}
		});
		panel_tk.add(tkButton, "cell 4 4");
		
		JButton refresh = new JButton("\u91CD\u7F6E");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_14.setText("");
				textField_15.setText("");
				textField_16.setText("");
				textField_17.setText("");
				textField_18.setText("");
				textField_19.setText("");
				textField_20.setText("");
				textField_21.setText("");
				textField_22.setText("");
				textField_23.setText("");
				textField_25.setText("");
				textField_24.setText("");
			}
		});
		panel_tk.add(refresh, "cell 5 4");
	}
	public void set_xk_input_panel() {
		//出现xk_panel_2
		JPanel xk_panel_2 = new JPanel();
		xk_panel_2.setBackground(new Color(248, 248, 255));
		panel_work_area.add(xk_panel_2, "cell 0 1,grow");
		xk_panel_2.setLayout(new MigLayout("", "[][80px:150px:170px,grow][40px:70px:90px][10px:15px:20px][80px:150px:170px,grow][40px:70px:90px]", "[][][][][]"));
		
		JLabel lblNewLabel_2 = new JLabel("\u8BFE\u7A0B\u53F7");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 18));
		xk_panel_2.add(lblNewLabel_2, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("\u6559\u5E08\u53F7");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 18));
		xk_panel_2.add(lblNewLabel_3, "cell 2 0,alignx center");
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		xk_panel_2.add(lblNewLabel, "cell 4 0,alignx center");
		
		JLabel lblNewLabel_8 = new JLabel("\u6559\u5E08\u53F7");
		lblNewLabel_8.setFont(new Font("宋体", Font.BOLD, 18));
		xk_panel_2.add(lblNewLabel_8, "cell 5 0,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("1");
		xk_panel_2.add(lblNewLabel_4, "cell 0 1,alignx trailing");
		
		textField_2 = new JTextField();
		xk_panel_2.add(textField_2, "cell 1 1,growx");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		xk_panel_2.add(textField_3, "cell 2 1,growx");
		textField_3.setColumns(10);
		
		JButton affirm = new JButton("\u786E\u5B9A");
		affirm.setBackground(new Color(248, 248, 255));//设置按钮颜色
		affirm.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u786E\u5B9A.png"));
		affirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("2"+textField_2.getText());
				System.out.println("3"+textField_3.getText());
				boolean can = true;
				if(textField_2.getText().length()>1&&textField_2.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_2.getText(),textField_3.getText());
				}
				System.out.println("4"+textField_4.getText());
				System.out.println("5"+textField_5.getText());
				if(textField_4.getText().length()>1&&textField_5.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_4.getText(),textField_5.getText());
				}
				System.out.println("6"+textField_6.getText());
				System.out.println("7"+textField_7.getText());
				if(textField_6.getText().length()>1&&textField_7.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_6.getText(),textField_7.getText());
				}
				System.out.println("8"+textField_8.getText());
				System.out.println("9"+textField_9.getText());
				if(textField_8.getText().length()>1&&textField_9.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_8.getText(),textField_9.getText());
				}
				System.out.println("10"+textField_10.getText());
				System.out.println("11"+textField_11.getText());
				if(textField_10.getText().length()>1&&textField_11.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_10.getText(),textField_11.getText());
				}
				System.out.println("12"+textField_12.getText());
				System.out.println("13"+textField_13.getText());
				if(textField_12.getText().length()>1&&textField_13.getText().length()>1) {
					can=new student_operate().addSelectedCourse(user.get_User_Id(),textField_12.getText(),textField_13.getText());
				}
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("2");
		xk_panel_2.add(lblNewLabel_9, "cell 3 1,alignx right");
		
		textField_8 = new JTextField();
		xk_panel_2.add(textField_8, "cell 4 1,growx");
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		xk_panel_2.add(textField_9, "cell 5 1,growx");
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("3");
		xk_panel_2.add(lblNewLabel_5, "cell 0 2,alignx trailing");
		
		textField_4 = new JTextField();
		xk_panel_2.add(textField_4, "cell 1 2,growx");
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		xk_panel_2.add(textField_5, "cell 2 2,growx");
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("4");
		xk_panel_2.add(lblNewLabel_10, "cell 3 2,alignx right");
		
		textField_10 = new JTextField();
		xk_panel_2.add(textField_10, "cell 4 2,growx");
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		xk_panel_2.add(textField_11, "cell 5 2,growx");
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("5");
		xk_panel_2.add(lblNewLabel_6, "cell 0 3,alignx trailing");
		
		textField_6 = new JTextField();
		xk_panel_2.add(textField_6, "cell 1 3,growx");
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		xk_panel_2.add(textField_7, "cell 2 3,growx");
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("6");
		xk_panel_2.add(lblNewLabel_11, "cell 3 3,alignx right");
		
		textField_12 = new JTextField();
		xk_panel_2.add(textField_12, "cell 4 3,growx");
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		xk_panel_2.add(textField_13, "cell 5 3,growx");
		textField_13.setColumns(10);
		xk_panel_2.add(affirm, "flowx,cell 1 4");
		
		
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");
			}
		});
		reset.setBackground(new Color(248, 248, 255));//设置按钮颜色
		reset.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u91CD\u7F6E.png"));
		xk_panel_2.add(reset, "cell 1 4");
		
	}
	public void set_course_panel() {
		//选课结果
		JPanel xk_panel_3 = new JPanel();
		xk_panel_3.setBackground(new Color(248, 248, 255));
		panel_work_area.add(xk_panel_3, "cell 0 2,grow");
		xk_panel_3.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JLabel lblNewLabel_7 = new JLabel("\u9009\u8BFE\u7ED3\u679C");
		xk_panel_3.add(lblNewLabel_7, "cell 0 0");
		
		kjxk_out = new JTable();
		xk_panel_3.add(kjxk_out, "cell 0 1,grow");
		
	}
	public void set_mhcx_input() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel_work_area.add(panel, "cell 0 1,grow");
		
		JLabel lblNewLabel_12 = new JLabel("\u8BFE\u53F7:");
		lblNewLabel_12.setFont(new Font("宋体", Font.BOLD, 18));
		
		Course_id_input = new JTextField();
		Course_id_input.setColumns(10);
		
		JLabel lblNewLabel_12_1 = new JLabel("\u8BFE\u540D:");
		lblNewLabel_12_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		Course_name_input = new JTextField();
		Course_name_input.setColumns(10);
		
		JButton FindButton = new JButton("\u67E5\u627E");
		FindButton.setBackground(new Color(224, 255, 255));
		FindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course_name=Course_name_input.getText();
				String course_id=Course_id_input.getText();
				course_name="'%"+course_name+"%'";
				course_id="'%"+course_id+"%'";
				List<Course> ans;
				ans=new student_operate().getCourseList(course_id,course_name);
				infoShow = new JTable();
				infoShow.setModel(new DefaultTableModel(
						new Object[][]{},
						new Object[] {"课号","课名","教师姓名","上课时间","上课地点"}
				));
				DefaultTableModel model = (DefaultTableModel)infoShow.getModel();
				model.setRowCount(0);
				for(Course tmp:ans) {
					String [] tmpStrings = new String[5];
					tmpStrings[0] = tmp.getKh();
					tmpStrings[1] = tmp.getKm();
					tmpStrings[2] = tmp.getGh();
					tmpStrings[3] = tmp.getSksj();
					tmpStrings[4] = tmp.getSkdd();
					model.addRow(tmpStrings);
				}
				if(panel_work_area.getComponents().length>2)
					   panel_work_area.remove(2);
						set_mhcx_info();
					//panel_work_area.add(xk_input_panel);
					jsplitpane.setRightComponent(panel_work_area);
				scrollPane.setViewportView(infoShow);
			}
		});
		FindButton.setFont(new Font("宋体", Font.BOLD, 20));
		panel.setLayout(new MigLayout("", "[45px:45px:50px,grow][100px:208px:250px,grow][10px:20px:30px,grow][50px:80px:100px,grow][]", "[20px:25px:30px,grow][10px:15px:20px][20px:25px:30px,grow]"));
		panel.add(lblNewLabel_12_1, "cell 0 2,alignx right,growy");
		panel.add(Course_name_input, "cell 1 2,grow");
		panel.add(lblNewLabel_12, "cell 0 0,alignx right,growy");
		panel.add(Course_id_input, "cell 1 0,grow");
		panel.add(FindButton, "cell 3 0 1 3,grow");
	
	}
	public void set_mhcx_info() {
		scrollPane = new JScrollPane();
		panel_work_area.add(scrollPane, "cell 0 2,grow");
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
