package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import role_model.Admin;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.xdevapi.Table;

import db_operate.admin_operate;

public class AdminFrame extends MainFrame {
	private String selected_term;
	private JTextField textField;
	private JTable infoJTable;
	private String nowTable = "s";
	private String nowPriKey = "xh";
	private boolean ableToChangeAll = false;
	private boolean isGrade = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(new Admin());
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
	public AdminFrame(Admin user) {
		jsplitpane.setResizeWeight(0.1);
		jcombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String term=jcombobox.getSelectedItem().toString();
				//System.out.println(term);
				selected_term=term;
			}
		});
		jbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		jsplitpane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow 1][grow 2][grow 1]", "[40][5][40][5][40][5][40][40][]"));
		JButton stuInfo = new JButton("\u5B66\u751F\u4FE1\u606F");
		stuInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoJTable.setModel(new DefaultTableModel( 
						new Object[][] {},
						new Object[]{"ѧ��","����","�Ա�","��������","����","�ֻ�����","Ժϵ��"}
				){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row,int col) {
						if(col==0)return false;
						return true;
					}
				});
				nowTable = "s";
				nowPriKey = "xh";
				setTable();
				ableToChangeAll = true;
				isGrade = false;
			}
		});
		stuInfo.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		stuInfo.setForeground(new Color(255, 255, 255));
		stuInfo.setBackground(new Color(128, 128, 128));
		panel.add(stuInfo, "cell 1 0,grow");
		stuInfo.setOpaque(false);//���ð�ť͸��
		stuInfo.setBorder(null);//ȡ���߿�
		JButton tchrInfo = new JButton("\u6559\u5E08\u4FE1\u606F");
		tchrInfo.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		tchrInfo.setForeground(new Color(255, 255, 255));
		tchrInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoJTable.setModel(new DefaultTableModel( 
						new Object[][] {},
						new Object[]{"����","����","�Ա�","��������","ְ��","��������","Ժϵ��"}
				){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row,int col) {
						if(col==0)return false;
						return true;
					}
				});
				nowTable = "t";
				nowPriKey = "gh";
				setTable();
				ableToChangeAll = true;
				isGrade = false;
			}
		});
		tchrInfo.setBackground(new Color(128, 128, 128));
		panel.add(tchrInfo, "cell 1 2,grow");
		tchrInfo.setOpaque(false);//���ð�ť͸��
		tchrInfo.setBorder(null);//ȡ���߿�
		JButton calssInfo = new JButton("\u8BFE\u7A0B\u4FE1\u606F");
		calssInfo.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		calssInfo.setForeground(new Color(255, 255, 255));
		calssInfo.setBackground(new Color(128, 128, 128));
		calssInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoJTable.setModel(new DefaultTableModel( 
						new Object[][] {},
						new Object[]{"�κ�","����","ѧ��","ѧʱ","Ժϵ��"}
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row,int col) {
						if(col==0)return false;
						return true;
					}
				}
				);
				nowTable = "c";
				nowPriKey = "kh";
				setTable();
				ableToChangeAll = true;
				isGrade = false;
			}
		});
		calssInfo.setOpaque(false);//���ð�ť͸��
		calssInfo.setBorder(null);//ȡ���߿�
		panel.add(calssInfo, "cell 1 4,grow");
		JButton gradeInfo = new JButton("\u6210\u7EE9\u4FE1\u606F");
		gradeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoJTable.setModel(new DefaultTableModel( 
						new Object[][] {},
						new Object[]{"ѧ��","ѧ������","ѧ��","�κ�","����","��ʦ����","��ʦ����","�����ɼ�"}
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row,int col) {
						if(col==0)return false;
						return true;
					}
				}
				);
				nowTable = "e";
				nowPriKey = "id";
				set_grade_Table();
				ableToChangeAll = false;
				isGrade = true;
			}
		});
		gradeInfo.setIcon(new ImageIcon("E:\\eclipsework\\course_selection_system\\src\\icon\\\u9009\u9879.png"));
		gradeInfo.setForeground(new Color(255, 255, 255));
		gradeInfo.setBackground(new Color(128, 128, 128));
		panel.add(gradeInfo, "cell 1 6,grow");
		gradeInfo.setOpaque(false);//���ð�ť͸��
		gradeInfo.setBorder(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(224, 255, 255));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.2);
		jsplitpane.setRightComponent(splitPane);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[100px:200px][5px:10px:20px][100px:200px]", "[:30px:40px][5px:10px:20px][:30px:40px][5px:10px:20px][]"));
		

		
		JButton btn_search = new JButton("\u8F93\u5165\u4EFB\u610F\u4FE1\u606F\u67E5\u8BE2");
		btn_search.setBackground(new Color(224, 255, 255));
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = textField.getText();
				DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				infoJTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(search));
				ableToChangeAll = false;
			}
		});
		panel_1.add(btn_search, "cell 2 0,grow");
		
		textField = new JTextField();
		panel_1.add(textField, "cell 0 0,grow");
		textField.setColumns(10);
		JButton btn_insert = new JButton("\u6DFB\u52A0\u8BB0\u5F55");
		btn_insert.setBackground(new Color(224, 255, 255));
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertFrame iFrame =  new insertFrame(nowTable);
				iFrame.setVisible(true);
				setTable();
			}
		});
		panel_1.add(btn_insert, "cell 0 2,grow");
		JButton btn_applySelected = new JButton("\u4FEE\u6539\u9009\u4E2D\u8BB0\u5F55");
		btn_applySelected.setBackground(new Color(224, 255, 255));
		btn_applySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
				int rowNum = infoJTable.getSelectedRow();
				if(rowNum!=-1) {
					int colNum = model.getColumnCount();
					String [] info = new String[colNum];
					for(int i=0;i<colNum;i++) {
						info[i] = (String) model.getValueAt(rowNum, i);
					}
					if(isGrade) {
						if(new admin_operate().updateGrade(info[2],info[0],info[3],info[5],Integer.parseInt(info[7]))) {
							showMess(true,"0");
						}
						else {
							showMess(false, "����Ҫ�޸ĵ������Ƿ���ڳ�ͻ��");
						}
						return;
					}
					if(new Admin().updateRow(nowTable,nowPriKey, info)) {
						showMess(true,"0");
					}
					else {
						showMess(false, "����Ҫ�޸ĵ������Ƿ���ڳ�ͻ��");
					}
				}
			}
		});
		panel_1.add(btn_applySelected, "cell 2 2,grow");
		
		JButton btn_delete = new JButton("\u5220\u9664\u9009\u4E2D\u4FE1\u606F");
		btn_delete.setBackground(new Color(224, 255, 255));
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
				int row = infoJTable.getSelectedRow();
				if(row!=-1) {
					int colNum = model.getColumnCount();
					String [] info = new String[colNum];
					for(int i=0;i<colNum;i++) {
						info[i] = (String) model.getValueAt(row, i);
					}
					if(isGrade) {
						if(new admin_operate().deleteGrade(info[2],info[0],info[3],info[5])) {
							showMess(true, "");
						}
						else {
							showMess(false, "");
						}
						set_grade_Table();
						return;
					}
					String id = info[0];
					boolean flag = new Admin().deleteRow(nowTable,nowPriKey, id);
					if(flag) {
						showMess(true, "");
					}
					else {
						showMess(false, "");
					}
					setTable();
				}
			}
		});
		panel_1.add(btn_delete, "cell 0 4,grow");
		
		JButton btn_applyAll = new JButton("\u5E94\u7528\u6240\u6709\u4FEE\u6539");
		btn_applyAll.setBackground(new Color(224, 255, 255));
		btn_applyAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ableToChangeAll==false) {
					showMess(false,"��ǰ��ͼ��֧��ȫ���޸ģ�");
					return;
				}
				if(applyChange()) {
					showMess(true,"");
				}
				else {
					showMess(false,"������Ϣ�Ƿ����Ҫ��");
				}
			}
		});
		panel_1.add(btn_applyAll, "cell 2 4,grow");
		
		
		//���ڴ��jtable
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setDividerSize(1);
		splitPane.setRightComponent(scrollPane);

		//��ʾ��Ϣ�ı��
		infoJTable = new JTable();
		scrollPane.setViewportView(infoJTable);
		//DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
		infoJTable.setAutoCreateRowSorter(true);
		//TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model); 
		setUserInfo(user.getName(),"����Ա");
		setVisible(true);
	}
	protected void setTable() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
		model.setRowCount(0);
		Vector<String[]> rowdata= new Admin().getData(nowTable);
		for(Object [] tmp:rowdata) {
			model.addRow(tmp);
		}
	}
	
	protected void set_grade_Table() {
		// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel)infoJTable.getModel();
				model.setRowCount(0);
				Vector<String[]> rowdata= new Admin().get_grade_data();
				for(Object [] tmp:rowdata) {
					model.addRow(tmp);
				}
	}
	public boolean applyChange() {
		Vector<String[]> newtable = new Vector<String[]>();
		DefaultTableModel model = (DefaultTableModel) infoJTable.getModel();
		int rowNum = infoJTable.getRowCount();
		int colNum = infoJTable.getColumnCount();
		for(int i=0;i<rowNum;i++) {
			String [] tmp = new String[colNum];
			for(int j=0;j<colNum;j++) {
				tmp[j] = (String) model.getValueAt(i, j);
			}
			newtable.add(tmp);
		}
		boolean successApply = new admin_operate().updateAlltable(nowTable,newtable);
		return successApply;
	}
	private void showMess(boolean flag,String mes) {
		String ret;
		if(flag) {
			ret = "�����ɹ���";
		}
		else {
			ret = "����ʧ��!"+mes;
		}
		JOptionPane.showMessageDialog(this, ret);
	}
}
