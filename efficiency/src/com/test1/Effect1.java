/**
 * Ч������1.0
 * ����ʱ�䣺2014��8��20��22:25:37
 * ���ߣ�����ΰ
 * ������java Ч�ʼ���������û������ͳ���û�������ѧϰ�������ѵ�
 * 		 ʱ�䣬ּ�����û��˽�ʱ����������ŵģ��ݴ������ƻ��ں����
 * 		����
 * �ܸ٣�
 * 1����¼ÿ�������������ʱ����
 * 2��Ȼ����Ը������ݷ������µ�Ч��
 * 3�������ƶ�����ѧϰ�ƻ�
 * 4����ʱ���ѹ���
 * 5���˹����ܸ����������飬����ʱ�����
 * 
 * ��ǰ�汾��1.0
 * ���ܣ�
 * 1.��Ϊ���
 * 2.��Ϊ����
 * 3.��Ϊ��ȡ
 * 4.�ճ����
 * 5.�ճ�ɾ��
 * 6.�����ճ�
 * 	6.1�ȷ������浽�ļ�����ʱ�������Ի���		
 */
package com.test1;

import javax.swing.*;
import javax.swing.text.TabableView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Spliterator;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * @author yesemili
 *
 */
public class Effect1 extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	
	//����˵�
	JMenuBar jmb=null;
	JMenu jmfile=null;//�ļ����˵�
	JMenuItem jmif1,jmif2,jmif3,jmif4;//��ʼ��¼����������������¼����ȡ��¼
	JMenu jmBehavior=null;//�ļ��˵�����Ϊ�Ӳ˵�
	JMenuItem jmib1,jmib2,jmib3,jmib4;//��Ϊ�˵����Ӳ˵���,��ȡ�����棬ɾ�������
	JPanel jp1,jp2,jp3;
	JList jl1,jl2;
	JLabel jla1,jla2;//�ճ̣���Ϊ
	JButton jb1,jb2;//��ӣ��Ƴ�
	//��ʽ����jpanel
	JPanel jp4,jp5,jp6;
	JPanel jp7,jp8;
	//�������Ϊ�Ի���
	//JDialog jd1=null;
	addAct addActDialog=null;
	//���������
	JScrollPane jsb1,jsb2;
	//�����б������
	//String dailyString[]={"˯��","ѧϰ"};
	//String bString[]={""};
	//��ߵ�Ϊ�ճ̣��ұߵ�����Ϊ�б�
	Vector<String> strLeft=new Vector<String>();
	Vector<String> strRightStudy=new Vector<String>();
	Vector<String> strRightRelax=new Vector<String>();
	Vector<String> strRightAmusement=new Vector<String>();
	Vector<String> strRightOthers=new Vector<String>();
	//ѡ�
	JTabbedPane jtb1,jtb2;//jtb1��������������Ϊ��Ϊ�ķ�ҳ��jtb2�����ڷ����Ի���ķ�ҳ
	//jp9,jp10�ֱ�����Ϣѡ�������ѡ�
	JPanel jp9,jp10;
	JScrollPane jsp9,jsp10,jsp11;
	JList jl9,jl10,jl11;
	//ʱ�����
	Calendar calendar=null;
	//Vector<Vector> actList=new Vector<Vector>();
	//Vector<Vector<String>> actList=new Vector<Vector<String>>();//���Ҳ�4���б�����ͳһ����
	//ArrayList<Vector> actList=new ArrayList<Vector>();
	Vector<JList> vLists=new Vector<JList>();//���Ҳ�4���б�ͳһ����
	//actList.
	
	//�ļ�����
	//����������б��ж�ȡ��ʱ�����Ŀ
	Vector<timeAndAct> taa=new Vector<timeAndAct>();
	
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Effect1 effect1=new Effect1();
	}
	
	//
	public Effect1()
	{
		
		
		
		//�˵�����
		jmif1=new JMenuItem("��ʼ�¼�¼");
		jmif2=new JMenuItem("��ȡ��¼");
		jmif3=new JMenuItem("�����¼");
		jmif4=new JMenuItem("������¼");
		
		jmif1.addActionListener(this);
		jmif1.setActionCommand("newRec");
		jmif4.addActionListener(this);
		jmif4.setActionCommand("analyze");
		
		jmib1=new JMenuItem("��ȡ��Ϊ");
		jmib2=new JMenuItem("������Ϊ");
		jmib3=new JMenuItem("������Ϊ");
		jmib4=new JMenuItem("ɾ����Ϊ");
		
		jmib3.addActionListener(this);
		jmib3.setActionCommand("addNewAct");
		
		jmib2.addActionListener(this);
		jmib2.setActionCommand("saveact");
		
		jmib1.addActionListener(this);
		jmib1.setActionCommand("readact");
		
		jmBehavior=new JMenu("��Ϊ");
		jmfile=new JMenu("�ļ�");
		jmb=new JMenuBar();
		
		
		jmBehavior.add(jmib1);
		jmBehavior.add(jmib2);
		jmBehavior.add(jmib3);
		jmBehavior.add(jmib4);
		
		jmfile.add(jmif1);
		jmfile.add(jmif2);
		jmfile.add(jmif3);
		jmfile.add(jmif4);
		jmfile.add(jmBehavior);
		
		jmb.add(jmfile);
		jmb.setVisible(true);
		
		//����岿��
		jp1=new JPanel();//left schedule
		jp2=new JPanel();//button panel
		jp3=new JPanel();//right Behavior
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		jp8=new JPanel();
		jp9=new JPanel();
		jp10=new JPanel();
		
		
		jtb1=new JTabbedPane();
		
		jla1=new JLabel("�ճ�");
		jla2=new JLabel("��Ϊ");
		
		
		
		String str1="java";
		String str2="����Ϸ";
		String str3="˯��";
		strRightStudy.add(str1);
		strRightAmusement.add(str2);
		strRightRelax.add(str3);
		
		//�Ҳ��ĸ�ѡ���ӵ�vectorͳһ����
//		actList.add(strRightStudy);
//		actList.add(strRightAmusement);
//		actList.add(strRightRelax);
//		actList.add(strRightOthers);
		
		
		jl1=new JList(strLeft);
		jl2=new JList(strRightStudy);
		jl9=new JList(strRightAmusement);
		jl10=new JList(strRightRelax);
		jl11=new JList(strRightOthers);
		
		//�Ҳ�4���б�ͳһ����
		vLists.add(jl2);
		vLists.add(jl9);
		vLists.add(jl10);
		vLists.add(jl11);
		
		jb1=new JButton("����ճ�");
		jb2=new JButton("ɾ���ճ�");
		
		jb1.addActionListener(this);
		jb1.setActionCommand("addact");
		jb2.addActionListener(this);
		jb2.setActionCommand("removeact");
		
		jsb1=new JScrollPane(jl1);
		jsb2=new JScrollPane(jl2);
		jsp9=new JScrollPane(jl9);
		jsp10=new JScrollPane(jl10);
		jsp11=new JScrollPane(jl11);
		//jl2.setVisibleRowCount(1);
		jtb1.add("ѧϰ", jsb2);
		jtb1.add("����",jsp9);
		jtb1.add("��Ϣ",jsp10);
		jtb1.add("����",jsp11);
		//jtb1.add("ѧϰ", jsb2);
		//jtb1.add("ѧϰ", jsb2);
		
		jp1.setLayout(new BorderLayout());
		jp2.setLayout(new GridLayout(3,1,20,20));
		jp3.setLayout(new BorderLayout());
		jp5.setLayout(new GridLayout(2,1,10,10));
		
		jp7.add(jla1);
		jp8.add(jla2);
		jp1.add(jp7,BorderLayout.NORTH);
		jp1.add(jsb1);
		jp2.add(jp4);
		jp2.add(jp5);
		jp2.add(jp6);
		jp3.add(jp8,BorderLayout.NORTH);
		jp3.add(jtb1);
		
		jp5.add(jb1);
		jp5.add(jb2);
		
		
		this.setLayout(new GridLayout(1,3,10,10));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//���ڴ���
		
		this.setJMenuBar(jmb);
		
		this.setTitle("Ч������1.0");
		this.setSize(700,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//timeAndAct tempAct=new timeAndAct();//��������ʱ�����Ŀ����ʱ����
		
		//�����Ϊ���ճ�
		if (e.getActionCommand().equals("addact")) {
			//jl1.setjl2.getSelectedValue();
			String tempString=jtb1.getTitleAt(jtb1.getSelectedIndex());
			
			JList jltemp=new JList();
			if (tempString.equals("ѧϰ")) {
				jltemp=jl2;
			}
			else if (tempString.equals("����")) {
				jltemp=jl9;
			}
			else if (tempString.equals("��Ϣ")) {
				jltemp=jl10;
			}
			else if (tempString.equals("����")) {
				jltemp=jl11;
			}
			
			String string=jltemp.getSelectedValue().toString();
			
			//���ϵͳ��ǰʱ��
			calendar=Calendar.getInstance();
			
			String string2="";
			string2+=String.valueOf(calendar.get(calendar.HOUR_OF_DAY))+":"+String.valueOf(calendar.get(calendar.MINUTE));
			string=string2+" "+string;
			strLeft.add(string);
			
			//jList.setListData(jl1.toArray());
			//ˢ��jlist
			jl1.setListData(strLeft);
			
			
		}
		//�Ƴ�
		if (e.getActionCommand().equals("removeact")) {
			
			String string=jl1.getSelectedValue().toString();			

			strLeft.remove(string);
			
			//jList.setListData(jl1.toArray());
			//ˢ��jlist
			jl1.setListData(strLeft);
			
		}
		
		 if (e.getActionCommand().equals("newRec")) {
			strLeft.removeAllElements();
			jl1.setListData(strLeft);
		}
		
		if (e.getActionCommand().equals("addNewAct")) {
			
			addActDialog=new addAct(vLists,jtb1);
			
			//this.add(jd1);
		}
		//������Ϊ�б�
		if (e.getActionCommand().equals("saveact")) {
			Recorder recorder=new Recorder();
			recorder.setActs(vLists);
			recorder.setActTab(jtb1);
			recorder.SaveUserConf();
		}
		//��ȡ��Ϊ�б�
		if (e.getActionCommand().equals("readact")) {
			Recorder recorder=new Recorder();
			recorder.setActs(vLists);
			recorder.setActTab(jtb1);
			recorder.ReadUserConf();
		}
		
		//���������浽�ļ�
		if (e.getActionCommand().equals("analyze")) {
			taa.removeAllElements();
			//���������ݷ����󣬷�Ϊʱ�����Ϊ�����ض���Ȼ���������
			//��Ϊ�����ȡ����������������0��ʼ������Ӧ����С�ڵ���
			//������Ϊj+1��Խ�磬��һ����ʵ��������һ������Ϊ����������Ȳ�����
			for (int j = 0; j < jl1.getLastVisibleIndex(); j++) {
				timeAndAct tempAct=new timeAndAct();
				String strs1[]=jl1.getModel().getElementAt(j).toString().split(" ");
				String strs2[]=jl1.getModel().getElementAt(j+1).toString().split(" ");
				
				tempAct.setTimes(Integer.parseInt(timeAnalyze(strs1[0], strs2[0])));
				tempAct.setActNamesString(strs1[1]);
				
				taa.add(tempAct);
				//System.out.println(taa.get(j).getActNamesString()+"ok"+jl1.getLastVisibleIndex());
				
				
			}
			//������������ǰ��ʱ�䣬��ΪΪEND������һ���������ڷ�����ʱ������µ���Ϊ
			//�ϲ��������ظ�����Ŀ
			for (int i = 0; i < taa.size(); i++) {
				System.out.println(taa.get(i).getActNamesString()+" "+taa.size());
				//System.out.println(taa.get(i+1).getActNamesString()+" "+taa.size());
				//����J��Ӧ�ô�1��ʼ���Ǵ�I+1��ʼ
				for (int j = i+1; j < taa.size(); j++) {
					if (taa.get(i).getActNamesString().equals(taa.get(j).getActNamesString())) {
						taa.get(i).setTimes(taa.get(i).getTimes()+taa.get(j).getTimes());
						taa.remove(j);
						System.out.println("���ظ�");
					}
					
				}
				
			}
			//�ѽ�����浽�ļ���
			FileWriter fWriter=null;
			BufferedWriter bWriter=null;
			String tempString="";
			try {
				fWriter = new FileWriter("./analyze.txt");
				bWriter=new BufferedWriter(fWriter);
				for (int i = 0; i < taa.size(); i++) {
					
					tempString+=taa.get(i).times+"  "+taa.get(i).getActNamesString()+"\r\n";
					//System.out.println(tempString);
				}
				bWriter.write(tempString);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					bWriter.close();
					fWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
			
		}
	}
	
	//ʱ��������������������������м�������, e: timeAnalyze("18:20","19:39"),С��ǰ�����ں�
	public String timeAnalyze(String str1,String str2)
	{
		String timeString="";
		String str1s[]=str1.split(":");
		String str2s[]=str2.split(":");
		
		timeString=String.valueOf(Integer.parseInt(str2s[0])*60+Integer.parseInt(str2s[1])-Integer.parseInt(str1s[0])*60-Integer.parseInt(str1s[1]));
		return timeString;
	}

}


//����������ݴ洢��
class timeAndAct
{
	int times;
	String actNamesString;
	
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getActNamesString() {
		return actNamesString;
	}
	public void setActNamesString(String actNamesString) {
		this.actNamesString = actNamesString;
	}
	
}
//�����Ϊ�Ի�����
class addAct extends JDialog implements ActionListener
{
	JDialog jd1=null;
	JLabel jLabel1,jLabel2;
	JButton jButton1;
	JPanel jPanel1,jPanel2,jPanel3;
	//Vector<Vector<String>> actlistVector=new Vector<Vector<String>>();
	Vector<JList> jLists=new Vector<JList>();
	JComboBox jComboBox1=null;
	JTextField jTextField=null;
	
	JTabbedPane jtb=null;
	
	DefaultListModel model = new DefaultListModel();
	
	
	
	public addAct(Vector<JList> jli,JTabbedPane jtb)
	{
		//this.jtb=new JTabbedPane();
		this.jtb=jtb;
		this.jLists=jli;
		//this.jLists=jLists;
		
		jLabel1=new JLabel("��Ϊ:");
		jLabel2=new JLabel("����:");
		
		jPanel1=new JPanel();
		jPanel2=new JPanel();
		jPanel3=new JPanel();
		
		jButton1=new JButton("ȷ��");
		jButton1.addActionListener(this);
		jButton1.setActionCommand("yes");

		//����
		String string[]={"ѧϰ","����","��Ϣ","����"};
		jComboBox1=new JComboBox(string);
		
		jTextField=new JTextField(10);
		
		jPanel1.add(jLabel1);
		jPanel1.add(jTextField);
		jPanel2.add(jLabel2);
		jPanel2.add(jComboBox1);
		jPanel3.add(jButton1);
		//jPanel3.add(jButton2);
		
		this.setLayout(new GridLayout(3,1,5,5));
		this.add(jPanel1);
		this.add(jPanel2);
		this.add(jPanel3);
		
		this.setSize(250,200);
		this.setLocation(300,200);
		//this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("yes")) {
			//System.out.print("test");
			for (int i = 0; i < jtb.getTabCount(); i++) {
				if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex())==jtb.getTitleAt(i)){
					//System.out.println(i+" "+jLists.get(i).getLastVisibleIndex());
					for (int j = 0; j <=jLists.get(i).getLastVisibleIndex(); j++) {
						//System.out.println(i+" "+jTextField.getText().toString());
						model.addElement(jLists.get(i).getModel().getElementAt(j));
					}
					model.addElement(jTextField.getText().toString());
					jLists.get(i).setModel(model);
					//jLists.addElement(jTextField.getText().toString());
					
					
				}
				//System.out.println(i+" "+jTextField.getText().toString());
			}
			this.dispose();
		}
	}
}


//��¼��,ͬʱҲ���Ա����û�������
class Recorder
{
	//���浽�ļ�
	private static FileWriter fwAct=null;
	private static BufferedWriter bwAct=null;
	//��ȡ��Ϊ�ļ�
	private static FileReader frAct=null;
	private static BufferedReader brAct=null;
	//��Ϊ���������ļ�
	private static File userSaveActPath=new File("./UserSaveActConf.txt");
	//��������Ҳ����б�ǩҳ��List����
	private Vector<JList> acts=new Vector<JList>();
	//��ñ�ǩҳ
	private JTabbedPane actTab=null;
	
	Vector<DefaultListModel> models=new Vector<DefaultListModel>();
	//DefaultListModel models[]=new DefaultListModel;
	DefaultListModel model1=new DefaultListModel();
	DefaultListModel model2=new DefaultListModel();
	DefaultListModel model3=new DefaultListModel();
	DefaultListModel model4=new DefaultListModel();
	
	
	public JTabbedPane getActTab() {
		return actTab;
	}

	public void setActTab(JTabbedPane actTab) {
		this.actTab = actTab;
	}

	public Vector<JList> getActs(Vector<JList> acts) {
		return acts;
	}

	public void setActs(Vector<JList> acts) {
		this.acts = acts;
	}

	public void SaveUserConf()
	{
		try {
			fwAct=new FileWriter(userSaveActPath);
			bwAct=new BufferedWriter(fwAct);
			for (int i = 0; i < actTab.getTabCount(); i++) {
				bwAct.write(actTab.getTitleAt(i)+"\r\n");
				for (int j = 0; j <= acts.get(i).getLastVisibleIndex(); j++) {
					bwAct.write(acts.get(i).getModel().getElementAt(j).toString()+"\r\n");
				}
			}
			bwAct.write("end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bwAct.close();
				fwAct.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void ReadUserConf()
	{
		models.add(model1);
		models.add(model2);
		models.add(model3);
		models.add(model4);
		//DefaultListModel model=new DefaultListModel();
		boolean isItem=false;
		boolean isFinish=false;
		int i=0;
		try {
			frAct=new FileReader(userSaveActPath);
			brAct=new BufferedReader(frAct);
			String nString;
			while((nString=brAct.readLine())!=null)
			{
				
				
				isFinish=false;
				isItem=true;
				for (int j = 0; j < actTab.getTabCount(); j++) {
					if (actTab.getTitleAt(j).equals(nString)) {
						//System.out.println(actTab.getTitleAt(j));
						i=j-1;
						isItem=false;
						if (j!=0) {
							isFinish=true;
						}						
						break;
					}
				}
				//�ж��ǲ��Ǳ�ǩ������������Ŀ�ͼ��뵽Model��,endΪ�����ļ�������־
				if (isItem&&!nString.equals("end")) {
					models.get(i+1).addElement(nString);
					//System.out.println(models.get(i).getElementAt(j).toString());
				}
				//�ж�һ����ǩҳ�Ƿ���꣬�Ǿͽ��и���,setmodel
				if (isFinish||nString.equals("end")) {
//					for (int j = 0; j < models.get(i).size(); j++) {
//						System.out.println(models.get(i).getElementAt(j).toString());
//					}
					//System.out.println("-------------------");
					acts.get(i).setModel(models.get(i));
					//model.removeAllElements();
					//Thread.sleep(3000);
					
				}
				
				
			}
			
//			for (int i = 0; i < actTab.getTabCount(); i++) {
//				bwAct.write("\r\n"+actTab.getTitleAt(i)+"\r\n\r\n");
//				for (int j = 0; j <= acts.get(i).getLastVisibleIndex(); j++) {
//					bwAct.write(acts.get(i).getModel().getElementAt(j).toString()+"\r\n");
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				brAct.close();
				frAct.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
