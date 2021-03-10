package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
//import util.StringUtil;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//自定义包
import role_model.*;
import db_operate.*;
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField user_id_textfield;
	private JPasswordField pswd_textfield;
	public static String role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setForeground(new Color(128, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/icon/登录.png")));
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 526);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow 1][50px,grow 5][243.00,grow 6][grow 2]", "[::70,grow 1][40:40:50][:20px:40,grow 1][30:30:40][:15:20,grow 1][30:30:40][:15:20,grow 1][30:30:40][:20:30,grow 1][30:30:40][::70,grow 1]"));
		
		JLabel lblNewLabel = new JLabel("\u4E0A\u6D77\u5927\u5B66\u9009\u8BFE\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/icon/大学.png")));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 23));
		contentPane.add(lblNewLabel, "cell 1 1 2 1,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel(" \u7528\u6237\u540D");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setIcon(new ImageIcon(LoginFrame.class.getResource("/icon/用户.png")));
		contentPane.add(lblNewLabel_3, "cell 1 3,alignx trailing,growy");
		
		user_id_textfield = new JTextField();
		user_id_textfield.setForeground(new Color(128, 128, 128));
		contentPane.add(user_id_textfield, "cell 2 3,alignx left,growy");
		user_id_textfield.setColumns(18);
		
		JLabel lblNewLabel_2 = new JLabel(" \u5BC6  \u7801");
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/icon/密码.png")));
		contentPane.add(lblNewLabel_2, "cell 1 5,alignx trailing,growy");
		
		pswd_textfield = new JPasswordField();
		pswd_textfield.setForeground(new Color(128, 128, 128));
		pswd_textfield.setColumns(18);
		contentPane.add(pswd_textfield, "cell 2 5,alignx left,growy");
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u7C7B\u578B");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/icon/角色.png")));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1, "cell 1 7,alignx trailing,growy");
		
		JComboBox role_textfield = new JComboBox();
		role_textfield.setForeground(new Color(0, 0, 0));
		role_textfield.setBackground(Color.WHITE);
		role_textfield.setFont(new Font("宋体", Font.PLAIN, 14));
		role_textfield.setModel(new DefaultComboBoxModel(new String[] {"", "\u5B66\u751F", "\u6559\u5E08", "\u7BA1\u7406\u5458"}));
		contentPane.add(role_textfield, "cell 2 7,alignx left,growy");
		
		JButton login_btn = new JButton("\u767B\u5F55");
//		登录按钮事件监听
//		对user_id与pswd内容查询数据库
//		成功则跳出选课界面
//		失败则提示错误
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_id = user_id_textfield.getText().toString();
				String password = String.valueOf(pswd_textfield.getPassword());//由于getPassword返回字符型数组，故
				//System.out.print(password);
				role = (String) role_textfield.getSelectedItem();
				if(user_id.isEmpty()||password.isEmpty()||role.isEmpty()) {
					if(user_id.isEmpty()||password.isEmpty())
					{
						JOptionPane.showMessageDialog(login_btn, "用户名或密码不能为空！");
					}
					else
					{
						JOptionPane.showMessageDialog(login_btn, "用户类型不能为空！");
					}
					return;
				}
				else {
					if(role.equals("学生"))
					{
						student studenttmp=new student();
						studenttmp.set_User_Id(user_id);
						studenttmp.set_Pswd(password);
						student_operate stu_operate=new student_operate();
						boolean flag=stu_operate.login_judge(studenttmp);
						if(flag==true)
						{
							JOptionPane.showMessageDialog(login_btn, "欢迎学生登录本系统！");
							StudentFrame studentframe=new StudentFrame(studenttmp);
							studentframe.setUserInfo(user_id, role);
							studentframe.setVisible(true);
							//将当前页面不可见
							//setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(login_btn, "用户名或密码错误！");
							return;
						}
					}
					else if(role.equals("教师"))
					{
						teacher t_tmp=new teacher();
						t_tmp.set_User_Id(user_id);
						t_tmp.set_Pswd(password);
						teacher_operate teacher_operate=new teacher_operate();
						boolean flag=teacher_operate.login_judge(t_tmp);
						if(flag==true)
						{
							JOptionPane.showMessageDialog(login_btn, "欢迎教师登录本系统！");
							TeacherFrame teacherframe=new TeacherFrame();
							System.out.println(user_id);
							teacherframe.set_gh(user_id);//
							teacherframe.setUserInfo(user_id, role);
							teacherframe.setVisible(true);
							//将当前页面不可见
							//setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(login_btn, "用户名或密码错误！");
							return;
						}
						
					}
					else if(role.equals("管理员"))
					{
						Admin admintmp=new Admin(user_id,password);
						if(admintmp.trylog())
						{
							JOptionPane.showMessageDialog(login_btn, "欢迎管理员登录本系统！");
							AdminFrame adminframe=new AdminFrame(admintmp);
							//将当前页面不可见
							//setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(login_btn, "用户名或密码错误！");
							return;
						}
					}
					
				}
			}
		});
		login_btn.setBackground(Color.WHITE);
		login_btn.setForeground(new Color(0, 0, 0));
		login_btn.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(login_btn, "flowx,cell 2 9,growy");
		
		JButton reset_btn = new JButton("\u91CD\u7F6E");
		//重置按钮事件监听
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_id_textfield.setText("");
				pswd_textfield.setText("");
				role_textfield.setSelectedIndex(0);
				
			}
		});
		reset_btn.setBackground(Color.WHITE);
		reset_btn.setForeground(new Color(0, 0, 0));
		reset_btn.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(reset_btn, "cell 2 9,growy");
	}

}
