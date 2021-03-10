package frames;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;

import net.miginfocom.swing.MigLayout;
import role_model.teacher;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import db_operate.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
public class TeacherFrame extends MainFrame{
	private JTable table;
	public String t_id;
	private JTable table_stu;
	
	public JPanel panel_rt=new JPanel();
	public JPanel panel_rb=new JPanel();
	public JScrollPane scrollPane_stu=new JScrollPane();
	public teacher_operate t_user=new teacher_operate();
	public void set_gh(String t_gh)
	{
		t_id=t_gh;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherFrame frame = new TeacherFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public TeacherFrame() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		jsplitpane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow 1][grow 2][grow 1]", "[40px][5px][40px][5px][40px][]"));
		
		//退出按钮
				jbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				
		JButton btnNewButton = new JButton("\u5F00\u8BFE\u4FE1\u606F");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				set_classInfo();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(112, 128, 144));
		btnNewButton.setIcon(new ImageIcon(TeacherFrame.class.getResource("/icon/\u9009\u9879.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setOpaque(false);//设置按钮透明
		btnNewButton.setBorder(null);//取消边框
		panel.add(btnNewButton, "cell 1 0,grow");
		
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u540D\u5355");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				set_StudentInfo();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(112, 128, 144));
		btnNewButton_1.setIcon(new ImageIcon(TeacherFrame.class.getResource("/icon/\u9009\u9879.png")));
		btnNewButton_1.setOpaque(false);//设置按钮透明
		btnNewButton_1.setBorder(null);//取消边框
		panel.add(btnNewButton_1, "cell 1 2,grow");
		
		
		
	}
	public void set_classInfo()
	{
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		jsplitpane.setRightComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[40px:n:50px][grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[]", "[20px:25px]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(224, 255, 255));
		String selected_term=(String) jcombobox.getSelectedItem();
		lblNewLabel.setText(selected_term+"开课情况");
		panel_2.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 0 1,growx,aligny top");
		
		
		
		Vector<String[]> class_data=t_user.getClassSet(selected_term,t_id);
		//System.out.println(selected_term);
		// 表头（列名）
        String[] columnNames = {"#", "课程号", "课程名称", "学分", "上课时间","上课地点"};

        // 表格所有行数据
        String[][] rowData = {{"0","","","","",""}};
        int row_l=class_data.size();
        for(int i=0;i<row_l;i++)
        {
        	rowData[i][0]=i+1+"";
        	String[] str=class_data.get(i);
        	for(int j=1;j<columnNames.length;j++)
        	{
        		rowData[i][j]=str[j-1];
        	}
        }
		table = new JTable(rowData,columnNames);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"0", "", "", "", "", ""},
//			},
//			new String[] {
//				"#", "\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u5B66\u5206", "\u4E0A\u8BFE\u65F6\u95F4", "\u4E0A\u8BFE\u5730\u70B9"
//			}
//		));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setMinWidth(25);
		table.setBorder(new LineBorder(new Color(255, 255, 255)));
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
	}
	
	public void set_StudentInfo()
	{
		panel_rt.removeAll();
		panel_rb.removeAll();
		
		panel_rt.setBackground(new Color(224, 255, 255));
		jsplitpane.setRightComponent(panel_rt);
		panel_rt.setLayout(new MigLayout("", "[grow]", "[40px:n:45px,grow][grow]"));
		
		panel_rb.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel_rb.setBackground(new Color(224, 255, 255));
		panel_rt.add(panel_rb, "cell 0 0,grow");
		panel_rb.setLayout(new MigLayout("", "[120px:180px][120px:180px][]", "[20px:25px]"));
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u671F");
//		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		String selected_term=(String) jcombobox.getSelectedItem();
		lblNewLabel_1.setText(selected_term+"学期");
		panel_rb.add(lblNewLabel_1, "cell 0 0,alignx left,aligny center");
		
//		JLabel lblNewLabel_2 = new JLabel("\u8BFE\u53F7");
//		lblNewLabel_2.setBackground(new Color(224, 255, 255));
//		panel_2.add(lblNewLabel_2, "cell 1 0,alignx right");
		
		Vector<String> t_kh=t_user.getClass_kh(selected_term,t_id);
		panel_rt.add(scrollPane_stu, "cell 0 1,grow");
		JComboBox comboBox = new JComboBox(t_kh);
		comboBox.setBackground(new Color(224, 255, 255));
		panel_rb.add(comboBox, "cell 1 0,growx");
		JButton btnNewButton_3 = new JButton("\u786E\u5B9A");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_rt.remove(1);
				String select_kh=(String) comboBox.getSelectedItem();
				//System.out.println(select_kh);
				view_studentInfo(select_kh);
			}
		});
		btnNewButton_3.setBackground(new Color(224, 255, 255));
		panel_rb.add(btnNewButton_3, "cell 2 0");
		
	}
	public void view_studentInfo(String kh_km)
	{
		panel_rt.add(scrollPane_stu, "cell 0 1,growx,aligny top");
		String[] columnNames = {"#", "学号", "姓名","学院","联系方式"};
        // 表格所有行数据
        String[][] rowData = {{"0","","","",""}};
        teacher_operate t_user=new teacher_operate();
        String selected_term=(String) jcombobox.getSelectedItem();
        Vector<String[]> student_info=t_user.getStudentSet(selected_term, t_id, kh_km);
        int row_l=student_info.size();
        System.out.println(row_l);
        for(int i=0;i<row_l;i++)
        {
        	rowData[i][0]=i+1+"";
        	String[] str=student_info.get(i);
        	for(int j=1;j<columnNames.length;j++)
        	{
        		rowData[i][j]=str[j-1];
        	}
        }
		table_stu = new JTable(rowData,columnNames);
		table_stu.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_stu.getColumnModel().getColumn(0).setMinWidth(10);
		table_stu.setBorder(new LineBorder(new Color(255, 255, 255)));
		table_stu.setBackground(new Color(255, 255, 255));

		scrollPane_stu.setViewportView(table_stu);
//		System.out.println("输出");
		
	}

}
