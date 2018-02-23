/**
 * 效率助手1.1
 * 开发时间：2014年8月22日20:55:00
 * 作者：贾世伟
 * 描述：java 效率监控器帮助用户管理和统计用户在生活学习上所花费的
 * 		 时间，旨在让用户了解时间是如何流逝的，据此做出计划于合理的
 * 		安排
 * 总纲：
 * 1、记录每天所做的事情的时间量
 * 2、然后可以根据数据分析做事的效率
 * 3、可以制定工作学习计划
 * 4、定时提醒功能
 * 5、人工智能给出合理化建议，关于时间分配
 * 
 * 原始版本：1.0
 * 功能：
 * 1.行为添加
 * 2.行为保存
 * 3.行为读取
 * 4.日程添加
 * 5.日程删除
 * 6.分析日程
 * 	6.1先分析保存到文件，暂时不创建对话框		
 * 
 * 当前版本：1.1
 * 1.分析日程的时候，修复需要人为添加最后行为的BUG
 * 	1.1获取当前时间，设置最后日程行为为end，然后加入vector统一管理
 * 2.新增支持分析标签页项所占总时间的功能
 * 	2.1在分析日程的时候，判断日程归属，并计算所属大项的总时间
 * 		2.1.1涉及到标签页间行为可能发生重复，所以在添加的时候不能在不同的标签页间添加完全相同的行为
 * 		2.1.2再涉及到从配置文件中读取的行为项时，总分析无效是因为从文件中读取的并没有放到容器中统一管理
 * 3.消除读取配置文件的bug，先清空右侧数组，然后再添加，并且判断为end的时候进行I+1
 * 4.行为菜单作为主菜单显示
 * 5.增加关于菜单
 * 6.自动弹出分析日至
 */
package com.test3;

import javax.swing.*;
import javax.swing.border.Border;
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
	
	//定义菜单
	JMenu jmHelp=null;
	JMenuItem jmiAbout=null;//关于菜单
	JMenuBar jmb=null;
	JMenu jmfile=null;//文件主菜单
	JMenuItem jmif1,jmif2,jmif3,jmif4;//开始记录，分析结果，保存记录，读取记录
	JMenu jmBehavior=null;//文件菜单下行为子菜单
	JMenuItem jmib1,jmib2,jmib3,jmib4;//行为菜单的子菜单项,读取，保存，删除，添加
	JPanel jp1,jp2,jp3;
	JList jl1,jl2;
	JLabel jla1,jla2;//日程，行为
	JButton jb1,jb2;//添加，移除
	//格式调整jpanel
	JPanel jp4,jp5,jp6;
	JPanel jp7,jp8;
	//添加新行为对话框
	//JDialog jd1=null;
	addAct addActDialog=null;
	AboutDialog aboutDialog=null;
	//滚动条添加
	JScrollPane jsb1,jsb2;
	//两个列表的内容
	//String dailyString[]={"睡觉","学习"};
	//String bString[]={""};
	//左边的为日程，右边的是行为列表
	Vector<String> strLeft=new Vector<String>();
	Vector<String> strRightStudy=new Vector<String>();
	Vector<String> strRightRelax=new Vector<String>();
	Vector<String> strRightAmusement=new Vector<String>();
	Vector<String> strRightOthers=new Vector<String>();
	//选项卡
	JTabbedPane jtb1,jtb2;//jtb1用来在主界面做为行为的分页，jtb2用来在分析对话框的分页
	//jp9,jp10分别是休息选项卡和娱乐选项卡
	JPanel jp9,jp10;
	JScrollPane jsp9,jsp10,jsp11;
	JList jl9,jl10,jl11;
	//时间变量
	Calendar calendar=null;
	//Vector<Vector> actList=new Vector<Vector>();
	Vector<Vector<String>> actList=new Vector<Vector<String>>();//把右侧4个列表内容统一管理
	//ArrayList<Vector> actList=new ArrayList<Vector>();
	Vector<JList> vLists=new Vector<JList>();//把右侧4个列表统一管理
	//actList.
	
	//文件分析
	//用来保存从列表中读取的时间和项目
	Vector<timeAndAct> taa=new Vector<timeAndAct>();
	//用来保存标签项所占的时间
	Vector<timeAndAct> tbb=new Vector<timeAndAct>();
	
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Effect1 effect1=new Effect1();
	}
	
	//
	public Effect1()
	{
		
		
		
		//菜单部分
		jmif1=new JMenuItem("开始新纪录");
		jmif2=new JMenuItem("读取记录");
		jmif3=new JMenuItem("保存记录");
		jmif4=new JMenuItem("分析记录");
		
		
		jmif1.addActionListener(this);
		jmif1.setActionCommand("newRec");
		jmif4.addActionListener(this);
		jmif4.setActionCommand("analyze");
		
		jmib1=new JMenuItem("读取行为");
		jmib2=new JMenuItem("保存行为");
		jmib3=new JMenuItem("增加行为");
		jmib4=new JMenuItem("删除行为");
		
		jmib3.addActionListener(this);
		jmib3.setActionCommand("addNewAct");
		
		jmib2.addActionListener(this);
		jmib2.setActionCommand("saveact");
		
		jmib1.addActionListener(this);
		jmib1.setActionCommand("readact");
		
		jmBehavior=new JMenu("行为");
		jmfile=new JMenu("文件");
		jmHelp=new JMenu("帮助");
		jmiAbout=new JMenuItem("关于");
		
		jmiAbout.addActionListener(this);
		jmiAbout.setActionCommand("about");
		
		jmHelp.add(jmiAbout);
		
		jmb=new JMenuBar();
		
		
		jmBehavior.add(jmib1);
		jmBehavior.add(jmib2);
		jmBehavior.add(jmib3);
		jmBehavior.add(jmib4);
		
		jmfile.add(jmif1);
		jmfile.add(jmif2);
		jmfile.add(jmif3);
		jmfile.add(jmif4);
		
		
		jmb.add(jmfile);
		jmb.add(jmBehavior);
		jmb.add(jmHelp);
		
		
		jmb.setVisible(true);
		
		//主面板部分
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
		
		jla1=new JLabel("日程");
		jla2=new JLabel("行为");
		
		
		
		//String str1="java";
		//String str2="玩游戏";
		//String str3="睡觉";
		//strRightStudy.add(str1);
		//strRightAmusement.add(str2);
		//strRightRelax.add(str3);
		
		//右侧四个选项卡String数组添加到vector统一管理
//		actList.add(strRightStudy);
//		actList.add(strRightAmusement);
//		actList.add(strRightRelax);
//		actList.add(strRightOthers);
		
		
		jl1=new JList(strLeft);
		jl2=new JList(strRightStudy);
		jl9=new JList(strRightAmusement);
		jl10=new JList(strRightRelax);
		jl11=new JList(strRightOthers);
		
		//右侧4个列表String内容统一管理，加入到Vector《String》类的容器中
		actList.add(strRightStudy);
		actList.add(strRightAmusement);
		actList.add(strRightRelax);
		actList.add(strRightOthers);
		
		//System.out.println("当前共有标签(in crater)  "+actList.size());
		vLists.add(jl2);
		vLists.add(jl9);
		vLists.add(jl10);
		vLists.add(jl11);
		
		
		
		jb1=new JButton("添加日程");
		jb2=new JButton("删除日程");
		
		
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
		jtb1.add("学习", jsb2);
		jtb1.add("娱乐",jsp9);
		jtb1.add("休息",jsp10);
		jtb1.add("其他",jsp11);
		//jtb1.add("学习", jsb2);
		//jtb1.add("学习", jsb2);
		
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
		//窗口创建
		
		this.setJMenuBar(jmb);
		
		this.setTitle("效率助手1.1");
		this.setSize(650,400);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//timeAndAct tempAct=new timeAndAct();//用来保存时间和项目的临时变量
		//关于
		
		if (e.getActionCommand().equals("about")) {
			//System.out.println("alsdfjfalsdjflasdjf");
			aboutDialog=new AboutDialog();
		}
		//添加行为到日程
		if (e.getActionCommand().equals("addact")) {
			//jl1.setjl2.getSelectedValue();
			
			String tempString=jtb1.getTitleAt(jtb1.getSelectedIndex());
			
			JList jltemp=new JList();
			if (tempString.equals("学习")) {
				jltemp=jl2;
			}
			else if (tempString.equals("娱乐")) {
				jltemp=jl9;
			}
			else if (tempString.equals("休息")) {
				jltemp=jl10;
			}
			else if (tempString.equals("其他")) {
				jltemp=jl11;
			}
			
			String string=jltemp.getSelectedValue().toString();
			
			//获得系统当前时间
			calendar=Calendar.getInstance();
			
			String string2="";
			string2+=String.valueOf(calendar.get(calendar.HOUR_OF_DAY))+":"+String.valueOf(calendar.get(calendar.MINUTE));
			string=string2+" "+string;
			strLeft.add(string);
			
			//jList.setListData(jl1.toArray());
			//刷新jlist
			jl1.setListData(strLeft);
			
			
		}
		//移除
		if (e.getActionCommand().equals("removeact")) {
			
			String string=jl1.getSelectedValue().toString();			

			strLeft.remove(string);
			
			//jList.setListData(jl1.toArray());
			//刷新jlist
			jl1.setListData(strLeft);
			
		}
		
		 if (e.getActionCommand().equals("newRec")) {
			strLeft.removeAllElements();
			jl1.setListData(strLeft);
		}
		
		if (e.getActionCommand().equals("addNewAct")) {
			
			addActDialog=new addAct(vLists,jtb1,actList);
			
			//this.add(jd1);
		}
		//保存行为列表
		if (e.getActionCommand().equals("saveact")) {
			Recorder recorder=new Recorder();
			recorder.setActs(vLists);
			recorder.setActTab(jtb1);
			recorder.SaveUserConf();
		}
		//读取行为列表
		if (e.getActionCommand().equals("readact")) {
			Recorder recorder=new Recorder(actList);
			recorder.setActs(vLists);
			recorder.setActTab(jtb1);
			recorder.ReadUserConf();
		}
		
		//分析并保存到文件
		if (e.getActionCommand().equals("analyze")) {
			taa.removeAllElements();
			tbb.removeAllElements();
			//添加最后表项，即当前的时间，行为为END，这样一来便无需在分析的时候加入新的行为
			String string="";
			calendar=Calendar.getInstance();
			
			String string2="";
			string2+=String.valueOf(calendar.get(calendar.HOUR_OF_DAY))+":"+String.valueOf(calendar.get(calendar.MINUTE));
			string=string2+" "+"end";
			strLeft.add(string);
			jl1.setListData(strLeft);
			//把左侧的数据分析后，分为时间和行为放入特定类然后加入容器
			//因为这里获取的是索引，索引由0开始，所以应该是小于等于
			//但又因为j+1会越界，另一方面实际情况最后一个表项为特殊情况，先不处理
			for (int j = 0; j < jl1.getLastVisibleIndex(); j++) {
				timeAndAct tempAct=new timeAndAct();
				String strs1[]=jl1.getModel().getElementAt(j).toString().split(" ");
				String strs2[]=jl1.getModel().getElementAt(j+1).toString().split(" ");
				
				
				tempAct.setTimes(Integer.parseInt(timeAnalyze(strs1[0], strs2[0])));
				tempAct.setActNamesString(strs1[1]);
				
				//分析标签页大项目所占用的总时间
				//System.out.println("当前共有标签  "+actList.size());
				for (int i = 0; i < jtb1.getTabCount(); i++) {
					//判断当前分析日程行为是否标签页
					//System.out.println(jtb1.getTitleAt(i)+" "+strs1[1]);
					//和四个标签页里的内容逐一比较
					System.out.println("当前标签页共有  "+actList.get(i).size()+" 个行为");
					for (int k = 0; k < actList.get(i).size(); k++) {
						System.out.println("右侧列表当前分析的行为 "+strs1[1]+"="+"左侧标签页列表的当前子项目  "+k+" "+actList.get(i).get(k));
						if (actList.get(i).get(k).equals(strs1[1])) {
							//tempact2用来临时保存标签项的时间和title
							timeAndAct tempAct2=new timeAndAct();
							tempAct2.setTimes(Integer.parseInt(timeAnalyze(strs1[0], strs2[0])));
							tempAct2.setActNamesString(jtb1.getTitleAt(i));
							//tbb用来保存总分析的实践项
							
							tbb.add(tempAct2);
						}
					}
//					//若列表中无标签项，则分析应为零，所以无论上面是否发现有标签项，这里都应加入0
//					timeAndAct tempAct3=new timeAndAct();
//					tempAct3.setTimes(0);
//					tempAct3.setActNamesString(jtb1.getTitleAt(i));
//					tbb.add(tempAct3);
//					
				}
				
				
				taa.add(tempAct);
				//System.out.println(taa.get(j).getActNamesString()+"ok"+jl1.getLastVisibleIndex());
				
				
			}
			
			for (int i = 0; i < tbb.size(); i++) {
				System.out.println("before tbb i "+tbb.get(i).getActNamesString());
			}
			
			//合并容器中重复的项目
			MergeItem(taa);
			MergeItem(tbb);
			
			for (int i = 0; i < tbb.size(); i++) {
				System.out.println("after tbb i "+tbb.get(i).getActNamesString());
			}
			//把结果保存到文件中
			FileWriter fWriter=null;
			BufferedWriter bWriter=null;
			String tempString="";
			try {
				fWriter = new FileWriter("./analyze.txt");
				bWriter=new BufferedWriter(fWriter);
				for (int i = 0; i < taa.size(); i++) {
					
					tempString+=taa.get(i).times+"  "+taa.get(i).getActNamesString()+"\r\n";
				//	System.out.println(tempString);
				}
				bWriter.write(tempString);
				tempString="";
				bWriter.write("\r\n");
				
				//System.out.println(tbb.size()+"");
				for (int i = 0; i < tbb.size(); i++) {
					
					tempString+=tbb.get(i).times+"  "+tbb.get(i).getActNamesString()+"\r\n";
					System.out.println("***** "+tempString+" *****");
				}
				System.out.println("tempString is "+tempString);
				bWriter.write(tempString);
				//bWriter.write("wrong");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					bWriter.close();
					fWriter.close();
					//打开程序Runtime.getRuntime().("./analyze.txt");
					File file=new File("./analyze.txt");
					Desktop desktop = Desktop.getDesktop();
					desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
			
		}
	}
	////合并容器中重复的项目函数
	//remove后容器的数量会改变进而影响到查重的正常运行，最好保存重复项，最后删除之,并且再删除的时候应该倒叙遍历，以防止序号改变
	public void MergeItem(Vector<timeAndAct> taa)
	{
		//Vector<NeedRemove> repetitionNumber=new Vector<NeedRemove>();
		//static int NeedRemove[100];
		//int []array=new int[100];
		Vector<Integer> array= new Vector<Integer>();
		System.out.println("taa.size="+taa.size());
		for (int i = 0; i < taa.size(); i++) {
			//int k=0;
			//System.out.println(taa.get(i).getActNamesString()+" "+taa.size());
			//System.out.println(taa.get(i+1).getActNamesString()+" "+taa.size());
			//这里J不应该从1开始而是从I+1开始
			array.removeAllElements();
			//String tempString="";
			
			for (int j = i+1; j < taa.size(); j++) {
				if (taa.get(i).getActNamesString().equals(taa.get(j).getActNamesString())) {
					taa.get(i).setTimes(taa.get(i).getTimes()+taa.get(j).getTimes());
					//taa.remove(j);
					//tempString+=String.valueOf(j)+" ";				
					//System.out.println("有重复");
					
					array.add(j);
					//j++;
				}
				
			}
			
//			String temp[]=tempString.split(tempString);
//			System.out.println(temp.length);
			System.out.println("-----------");
			for (int j = array.size()-1; j >=0; j--) {
				System.out.println(array.get(j));
				//taa.remove(Integer.valueOf(temp[j]));
				System.out.println("taa.size before remove "+taa.size());
				//remove(index)不知怎的无效，所以使用下面的方法
				taa.removeElementAt(array.get(j));
				
				System.out.println("taa.size after remove "+taa.size());
			}
		}
	}

	//时间分析函数，接受两个参数进行减法运算, e: timeAnalyze("18:20","19:39"),小在前，大在后
	public String timeAnalyze(String str1,String str2)
	{
		String timeString="";
		String str1s[]=str1.split(":");
		String str2s[]=str2.split(":");
		
		timeString=String.valueOf(Integer.parseInt(str2s[0])*60+Integer.parseInt(str2s[1])-Integer.parseInt(str1s[0])*60-Integer.parseInt(str1s[1]));
		return timeString;
	}

}

//分析后的数据存储类
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
//关于对话框
class AboutDialog extends JDialog
{
	JTextPane jtp=null;
	JPanel jPanel=null;
	JLabel jLabel=null;
	JLabel jLabel2=null;
	
	public AboutDialog() {
		jPanel=new JPanel();
		jLabel=new JLabel();
		jLabel2=new JLabel();
		jLabel.setText ("      Author  : JiaShiWei");
		jLabel2.setText("      Version : 1.1");
		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		jPanel.setLayout(new GridLayout(2,1,5,5));
		this.add(jPanel,BorderLayout.CENTER);
		this.setSize(180,120);
		this.setTitle("关于");
		
		
		this.setLocation(300,250);
		this.setVisible(true);
		
	}
}
//添加行为对话框类
class addAct extends JDialog implements ActionListener
{
	JDialog jd1=null;
	JLabel jLabel1,jLabel2;
	JButton jButton1;
	JPanel jPanel1,jPanel2,jPanel3;
	Vector<Vector<String>> actlistVector=new Vector<Vector<String>>();
	Vector<JList> jLists=new Vector<JList>();
	JComboBox jComboBox1=null;
	JTextField jTextField=null;
	
	JTabbedPane jtb=null;
	
	DefaultListModel model = new DefaultListModel();
	
	
	
	public addAct(Vector<JList> jli,JTabbedPane jtb,Vector<Vector<String>> actlistVector)
	{
		//this.jtb=new JTabbedPane();
		this.actlistVector=actlistVector;
		this.jtb=jtb;
		this.jLists=jli;
		//this.jLists=jLists;
		
		jLabel1=new JLabel("行为:");
		jLabel2=new JLabel("属性:");
		
		jPanel1=new JPanel();
		jPanel2=new JPanel();
		jPanel3=new JPanel();
		
		jButton1=new JButton("确定");
		jButton1.addActionListener(this);
		jButton1.setActionCommand("yes");

		//属性
		String string[]={"学习","娱乐","休息","其他"};
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
			
			//首先判断不同标签页中是否存在与当前所添加完全相同的内容
			Vector<String> tempStrings=new Vector<String>();
			for (int i = 0; i < jtb.getTabCount(); i++) {
				//Vector<String> tempStrings=new Vector<String>();
				//取出各个标签页的内容逐一与当前添加项对照
				for (int j = 0; j < actlistVector.get(i).size(); j++) {
					if (jTextField.getText().equals(actlistVector.get(i).get(j))) {
						//显示一个提示框
						JOptionPane.showMessageDialog(null, "与当前标签内容重复，不可添加");
						//System.out.print("重复");
						return;
					}
				}
				
			}
			
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


//记录类,同时也可以保存用户的设置
class Recorder
{
	//保存到文件
	private static FileWriter fwAct=null;
	private static BufferedWriter bwAct=null;
	//读取行为文件
	private static FileReader frAct=null;
	private static BufferedReader brAct=null;
	//行为设置配置文件
	private static File userSaveActPath=new File("./UserSaveActConf.txt");
	//用来获得右侧所有标签页的List内容
	private Vector<JList> acts=new Vector<JList>();
	//获得标签页
	private JTabbedPane actTab=null;
	
	Vector<Vector<String>> actTabList=new Vector<Vector<String>>();
	
	Vector<DefaultListModel> models=new Vector<DefaultListModel>();
	//DefaultListModel models[]=new DefaultListModel;
	DefaultListModel model1=new DefaultListModel();
	DefaultListModel model2=new DefaultListModel();
	DefaultListModel model3=new DefaultListModel();
	DefaultListModel model4=new DefaultListModel();
	
	
	public Recorder(Vector<Vector<String>> actTabList)
	{
		this.actTabList=actTabList;
	}
	
	public Recorder()
	{
		
	}
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
	
	//读取完后应该更新右侧容器string的内容
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
		
		//从配置文件中读取数据首先应该清除原容器的内容
		System.out.println("actTabList的大小是 "+actTabList.size());

		for (int j = 0; j < actTabList.size(); j++) {
			actTabList.get(j).removeAllElements();
		}
		
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
				//判断是不是标签名，若是子项目就加入到Model中,end为配置文件结束标志
				if (isItem&&!nString.equals("end")) {
					models.get(i+1).addElement(nString);
					//System.out.println(models.get(i).getElementAt(j).toString());
				}
				//判断一个标签页是否读完，是就进行更行,setmodel
				if (isFinish||nString.equals("end")) {
//					for (int j = 0; j < models.get(i).size(); j++) {
//						System.out.println(models.get(i).getElementAt(j).toString());
//					}
					//System.out.println("-------------------");
					if (nString.equals("end")) {
						i++;
					}
					acts.get(i).setModel(models.get(i));
					//model.removeAllElements();
					//Thread.sleep(3000);
					//更新标签页的管理List的数组
					for (int j = 0; j < models.get(i).getSize(); j++) {
						actTabList.get(i).add(models.get(i).getElementAt(j).toString());
					}
					
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
