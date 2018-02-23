package com.test2;
import java.awt.*;
import javax.swing.*;
public class Window011 extends JFrame{
	//设定组件
	//主页面北部
	JLabel jl1;//标签 
	
	//主页面南部
	JButton jb1,jb2,jb3;//按钮
	JPanel jp1;//面板
	
	//主页面中部
	JTabbedPane jtp;//选项卡窗格
	JPanel jp2,jp3,jp4;//面板
	
	JLabel jl2,jl3,jl4,jl5;//标签
	JTextField jtf;//文本框
	JPasswordField jpf;//密码框
	JButton jb4;//按钮
	JCheckBox jcb1,jcb2;//复选框
	
	//JPanel2组件
	JLabel jl6,jl7;//标签
	JTextField jtf1;
	JPasswordField jpf1;
	
	//JPanel3组件
	JLabel jl8,jl9;//标签
	JTextField jtf2;
	JPasswordField jpf2;
	
	public static void main(String[] args) {
		Window011 win=new Window011();
	}
	//构造函数
	public Window011(){
		//创建组件
		//创建JFrame北部JLabel1组件
		jl1=new JLabel(new ImageIcon("images/qqdl.jpg"));

		//创建JFrame中部JPanel2组件
		jl2=new JLabel("QQ号码",JLabel.CENTER);
		jl3=new JLabel("QQ密码",JLabel.CENTER);
		jl4=new JLabel("忘记密码",JLabel.CENTER);
		jl4.setFont(new Font("宋体",Font.PLAIN,16));//设置字体，字号
		jl4.setForeground(Color.BLUE);//设置字体颜色
		jl5=new JLabel("<html><a href='www.qq.com'>申请密码保护</a></html>");
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标移动到jl5后会变成手指图标
		
		jtf=new JTextField();
		jpf=new JPasswordField();
		jb4=new JButton("清除号码");//JButton上可以放图片。new JButton(new ImageIcon("图片路径"));
		
		jcb1=new JCheckBox("隐身登录");
		jcb2=new JCheckBox("记住密码");
		
		jtp=new JTabbedPane();//选项卡窗格
		jp2=new JPanel();
		jp3=new JPanel();
		//jp3.setBackground(Color.RED);//给面板设置背景色
		jp4=new JPanel();
		//jp4.setBackground(new Color(0,0,255));
		
		//创建JFrame中部JTabbedPane选项卡JPanel3组件
		jl6=new JLabel("手机号码",JLabel.CENTER);
		jl7=new JLabel("密　　码",JLabel.CENTER);
		jtf1=new JTextField(20);
		jpf1=new JPasswordField(20);
		
		//创建JFrame中部JTabbedPane选项卡JPanel4组件
		jl8=new JLabel("电子邮箱",JLabel.CENTER);
		jl9=new JLabel("密　　码",JLabel.CENTER);
		jtf2=new JTextField(20);
		jpf2=new JPasswordField(20);
		
		//创建JFrame南部JPanel1组件
		jp1=new JPanel();
		jb1=new JButton("登        录");
		jb2=new JButton("取        消");
		jb3=new JButton("注册向导");
		
		//设置布局管理器
		jp2.setLayout(new GridLayout(3, 3));
		//jp3.setLayout(new GridLayout(2, 2));
		//jp4.setLayout(new GridLayout(2, 2));
		
		
		//加入组件
		//将组件添加到JPanel2中
		jp2.add(jl2);//加入QQ号码标签
		jp2.add(jtf);//加入文本框
		jp2.add(jb4);//加入清除号码按扭
		
		jp2.add(jl3);//加入QQ密码标签
		jp2.add(jpf);//加入密码框
		jp2.add(jl4);//加入忘记密码
		
		jp2.add(jcb1);//加入隐身登陆复选框
		jp2.add(jcb2);//加入记住密码复选框
		jp2.add(jl5);//加入申请密码保护标签
		
		//将组件添加到JPanel3中
		jp3.add(jl6);
		jp3.add(jtf1);
		jp3.add(jl7);
		jp3.add(jpf1);
		
		//将组件添加到JPanel4中
		jp4.add(jl8);
		jp4.add(jtf2);
		jp4.add(jl9);
		jp4.add(jpf2);
		
		//添加到JPanel1中
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		//将面板添加到选项卡窗格上
		jtp.add("QQ号码",jp2);//第一个参数代表选项卡名称，第二个参数代表对应的面板
		jtp.add("手机号码",jp3);
		jtp.add("电子邮箱",jp4);
		
		//将JLabel1添加到Frame北部
		this.add(jl1,BorderLayout.NORTH);
		
		//将JPanle2添加到Frame中部
		this.add(jtp,BorderLayout.CENTER);
		
		//将JPanel1添加到Frame南部
		this.add(jp1,BorderLayout.SOUTH);
		
		//窗体设置
		this.setTitle("QQ登录界面");
		//ImageIcon icon=new ImageIcon("图片路径");
		//this.setIconImage(icon.getImage());
		this.setIconImage((new ImageIcon("images/qe.jpg")).getImage());
		this.setSize(350, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
}
