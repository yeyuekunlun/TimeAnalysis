/**
 * 效率助手1.0
 * 开发时间：2014年8月20日22:25:37
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
 * 当前版本：1.0
 * 功能：
 * 1.行为添加
 * 2.行为保存
 * 3.行为读取
 * 4.日程添加
 * 5.日程删除
 * 6.分析日程
 * 	6.1先分析保存到文件，暂时不创建对话框		
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
	
	//定义菜单
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
	//Vector<Vector<String>> actList=new Vector<Vector<String>>();//把右侧4个列表内容统一管理
	//ArrayList<Vector> actList=new ArrayList<Vector>();
	Vector<JList> vLists=new Vector<JList>();//把右侧4个列表统一管理
	//actList.
	
	//文件分析
	//用来保存从列表中读取的时间和项目
	Vector<timeAndAct> taa=new Vector<timeAndAct>();
	
	
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
		
		
		
		String str1="java";
		String str2="玩游戏";
		String str3="睡觉";
		strRightStudy.add(str1);
		strRightAmusement.add(str2);
		strRightRelax.add(str3);
		
		//右侧四个选项卡添加到vector统一管理
//		actList.add(strRightStudy);
//		actList.add(strRightAmusement);
//		actList.add(strRightRelax);
//		actList.add(strRightOthers);
		
		
		jl1=new JList(strLeft);
		jl2=new JList(strRightStudy);
		jl9=new JList(strRightAmusement);
		jl10=new JList(strRightRelax);
		jl11=new JList(strRightOthers);
		
		//右侧4个列表统一管理
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
		
		this.setTitle("效率助手1.0");
		this.setSize(700,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//timeAndAct tempAct=new timeAndAct();//用来保存时间和项目的临时变量
		
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
			
			addActDialog=new addAct(vLists,jtb1);
			
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
			Recorder recorder=new Recorder();
			recorder.setActs(vLists);
			recorder.setActTab(jtb1);
			recorder.ReadUserConf();
		}
		
		//分析并保存到文件
		if (e.getActionCommand().equals("analyze")) {
			taa.removeAllElements();
			//把左侧的数据分析后，分为时间和行为放入特定类然后加入容器
			//因为这里获取的是索引，索引由0开始，所以应该是小于等于
			//但又因为j+1会越界，另一方面实际情况最后一个表项为特殊情况，先不处理
			for (int j = 0; j < jl1.getLastVisibleIndex(); j++) {
				timeAndAct tempAct=new timeAndAct();
				String strs1[]=jl1.getModel().getElementAt(j).toString().split(" ");
				String strs2[]=jl1.getModel().getElementAt(j+1).toString().split(" ");
				
				tempAct.setTimes(Integer.parseInt(timeAnalyze(strs1[0], strs2[0])));
				tempAct.setActNamesString(strs1[1]);
				
				taa.add(tempAct);
				//System.out.println(taa.get(j).getActNamesString()+"ok"+jl1.getLastVisibleIndex());
				
				
			}
			//添加最后表项，即当前的时间，行为为END，这样一来便无需在分析的时候加入新的行为
			//合并容器中重复的项目
			for (int i = 0; i < taa.size(); i++) {
				System.out.println(taa.get(i).getActNamesString()+" "+taa.size());
				//System.out.println(taa.get(i+1).getActNamesString()+" "+taa.size());
				//这里J不应该从1开始而是从I+1开始
				for (int j = i+1; j < taa.size(); j++) {
					if (taa.get(i).getActNamesString().equals(taa.get(j).getActNamesString())) {
						taa.get(i).setTimes(taa.get(i).getTimes()+taa.get(j).getTimes());
						taa.remove(j);
						System.out.println("有重复");
					}
					
				}
				
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
//添加行为对话框类
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
