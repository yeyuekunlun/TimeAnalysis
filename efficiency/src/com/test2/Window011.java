package com.test2;
import java.awt.*;
import javax.swing.*;
public class Window011 extends JFrame{
	//�趨���
	//��ҳ�汱��
	JLabel jl1;//��ǩ 
	
	//��ҳ���ϲ�
	JButton jb1,jb2,jb3;//��ť
	JPanel jp1;//���
	
	//��ҳ���в�
	JTabbedPane jtp;//ѡ�����
	JPanel jp2,jp3,jp4;//���
	
	JLabel jl2,jl3,jl4,jl5;//��ǩ
	JTextField jtf;//�ı���
	JPasswordField jpf;//�����
	JButton jb4;//��ť
	JCheckBox jcb1,jcb2;//��ѡ��
	
	//JPanel2���
	JLabel jl6,jl7;//��ǩ
	JTextField jtf1;
	JPasswordField jpf1;
	
	//JPanel3���
	JLabel jl8,jl9;//��ǩ
	JTextField jtf2;
	JPasswordField jpf2;
	
	public static void main(String[] args) {
		Window011 win=new Window011();
	}
	//���캯��
	public Window011(){
		//�������
		//����JFrame����JLabel1���
		jl1=new JLabel(new ImageIcon("images/qqdl.jpg"));

		//����JFrame�в�JPanel2���
		jl2=new JLabel("QQ����",JLabel.CENTER);
		jl3=new JLabel("QQ����",JLabel.CENTER);
		jl4=new JLabel("��������",JLabel.CENTER);
		jl4.setFont(new Font("����",Font.PLAIN,16));//�������壬�ֺ�
		jl4.setForeground(Color.BLUE);//����������ɫ
		jl5=new JLabel("<html><a href='www.qq.com'>�������뱣��</a></html>");
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//����ƶ���jl5�������ָͼ��
		
		jtf=new JTextField();
		jpf=new JPasswordField();
		jb4=new JButton("�������");//JButton�Ͽ��Է�ͼƬ��new JButton(new ImageIcon("ͼƬ·��"));
		
		jcb1=new JCheckBox("�����¼");
		jcb2=new JCheckBox("��ס����");
		
		jtp=new JTabbedPane();//ѡ�����
		jp2=new JPanel();
		jp3=new JPanel();
		//jp3.setBackground(Color.RED);//��������ñ���ɫ
		jp4=new JPanel();
		//jp4.setBackground(new Color(0,0,255));
		
		//����JFrame�в�JTabbedPaneѡ�JPanel3���
		jl6=new JLabel("�ֻ�����",JLabel.CENTER);
		jl7=new JLabel("�ܡ�����",JLabel.CENTER);
		jtf1=new JTextField(20);
		jpf1=new JPasswordField(20);
		
		//����JFrame�в�JTabbedPaneѡ�JPanel4���
		jl8=new JLabel("��������",JLabel.CENTER);
		jl9=new JLabel("�ܡ�����",JLabel.CENTER);
		jtf2=new JTextField(20);
		jpf2=new JPasswordField(20);
		
		//����JFrame�ϲ�JPanel1���
		jp1=new JPanel();
		jb1=new JButton("��        ¼");
		jb2=new JButton("ȡ        ��");
		jb3=new JButton("ע����");
		
		//���ò��ֹ�����
		jp2.setLayout(new GridLayout(3, 3));
		//jp3.setLayout(new GridLayout(2, 2));
		//jp4.setLayout(new GridLayout(2, 2));
		
		
		//�������
		//�������ӵ�JPanel2��
		jp2.add(jl2);//����QQ�����ǩ
		jp2.add(jtf);//�����ı���
		jp2.add(jb4);//����������밴Ť
		
		jp2.add(jl3);//����QQ�����ǩ
		jp2.add(jpf);//���������
		jp2.add(jl4);//������������
		
		jp2.add(jcb1);//���������½��ѡ��
		jp2.add(jcb2);//�����ס���븴ѡ��
		jp2.add(jl5);//�����������뱣����ǩ
		
		//�������ӵ�JPanel3��
		jp3.add(jl6);
		jp3.add(jtf1);
		jp3.add(jl7);
		jp3.add(jpf1);
		
		//�������ӵ�JPanel4��
		jp4.add(jl8);
		jp4.add(jtf2);
		jp4.add(jl9);
		jp4.add(jpf2);
		
		//��ӵ�JPanel1��
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		//�������ӵ�ѡ�������
		jtp.add("QQ����",jp2);//��һ����������ѡ����ƣ��ڶ������������Ӧ�����
		jtp.add("�ֻ�����",jp3);
		jtp.add("��������",jp4);
		
		//��JLabel1��ӵ�Frame����
		this.add(jl1,BorderLayout.NORTH);
		
		//��JPanle2��ӵ�Frame�в�
		this.add(jtp,BorderLayout.CENTER);
		
		//��JPanel1��ӵ�Frame�ϲ�
		this.add(jp1,BorderLayout.SOUTH);
		
		//��������
		this.setTitle("QQ��¼����");
		//ImageIcon icon=new ImageIcon("ͼƬ·��");
		//this.setIconImage(icon.getImage());
		this.setIconImage((new ImageIcon("images/qe.jpg")).getImage());
		this.setSize(350, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
}
