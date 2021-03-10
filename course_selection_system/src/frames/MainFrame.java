package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPopupMenu;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;


import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import db_operate.*;
import javax.swing.DefaultComboBoxModel;
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private String user_type;
	public String user_id;

	public JLabel userInfo_lb;
	//供子类修改
	public JSplitPane jsplitpane;
	public JComboBox jcombobox;
	public JButton jbutton;
	public void setType(String type) {
		user_type=type;
	}
	public void setId(String id) {
		user_id=id;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipsework\\course_selection_system\\src\\icon\\\u767B\u5F55.png"));
		setTitle("\u4E0A\u6D77\u5927\u5B66\u9009\u8BFE\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 612, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][grow][][][grow][]", "[grow]"));
		JLabel lb_userInfo = new JLabel("userInfo");
		lb_userInfo.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/\u7528\u6237w.png")));
		lb_userInfo.setBackground(new Color(0, 0, 0));
		lb_userInfo.setForeground(new Color(255, 255, 255));
		userInfo_lb=lb_userInfo;
		panel.add(lb_userInfo, "cell 0 0");
		
		JLabel lb_version = new JLabel(" ");
		lb_version.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		lb_version.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/\u7248\u672C.png")));
		panel.add(lb_version, "cell 1 0,alignx trailing");
		
		JLabel lb_consult = new JLabel("\u9009\u8BFE\u95EE\u9898\u54A8\u8BE2 ");
		lb_consult.setForeground(new Color(255, 255, 255));
		lb_consult.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/\u54A8\u8BE2.png")));
		panel.add(lb_consult, "cell 2 0,alignx trailing");
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/\u65E5 \u5386.png")));
		panel.add(lblNewLabel_3, "cell 3 0,alignx trailing");
		//调用getXqVector函数，获得学期
		termSet getterm=new termSet();
		Vector v_term=getterm.getTermSet();
		//含参的构造函数
	    JComboBox comboBox_xq = new JComboBox(v_term);
	    comboBox_xq.setModel(new DefaultComboBoxModel(new String[] {"2020-2021\u51AC\u5B63", "2019-2020\u79CB\u5B63", "2019-2020\u51AC\u5B63"}));
		comboBox_xq.setForeground(new Color(255, 255, 255));
		comboBox_xq.setBackground(new Color(135, 206, 250));
		panel.add(comboBox_xq, "cell 4 0,growx");
		jcombobox=comboBox_xq;//
		
		JButton btn_exit = new JButton("\u9000\u51FA");
		btn_exit.setOpaque(false);//设置按钮透明
		btn_exit.setBorder(null);//取消边框
		btn_exit.setBackground(new Color(240, 255, 255));
		btn_exit.setForeground(new Color(255, 255, 255));
		btn_exit.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/\u9000\u51FA.png")));
		panel.add(btn_exit, "cell 5 0,growy");
		jbutton=btn_exit;//
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setForeground(new Color(240, 255, 255));
		splitPane.setDividerSize(1);
		contentPane.add(splitPane, BorderLayout.CENTER);
		jsplitpane=splitPane;
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		splitPane.setRightComponent(panel_1);
	}
	
	public void setUserInfo(String user_id,String user_type) {
		userInfo_lb.setText(user_type+":"+user_id);
	}
}
