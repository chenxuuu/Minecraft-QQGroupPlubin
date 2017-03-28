package com.sweetcreeper.qqplugin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Qqplugin extends JavaPlugin implements Listener
{
	public String player="none233";
	public String msg="none233";
	
	@Override//��д����ķ���
	public void onEnable()
	{
		getLogger().info("QQGroupMessagePlugin is started successfully!");
		//ע�����
		Bukkit.getPluginManager().registerEvents(this,this);
		
		new BukkitRunnable(){     
		    int s = 0;//���ö�10���ִ��ĳ�δ���
		    @Override    
		    public void run(){
		    	if(s>=60)
		    	{
		    		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tm abc ��Ǯ����");
		    		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give * 1");
		    		s=0;
		    	}
		    	else
		    	{
		    		s++;
		    	}
		        //s--;//�����ݼ�,�ҿ��ٷ��Ľ̳���û�����,��û�Թ�,��Ҳ����ɾ������
		        //if(s==0){
		            //���д10���ִ�еĴ���(���綨��Ķ�ʱ��ÿ����1��)
		        //    cancel();//cancel����ȡ����ʱ��
		        //}else{
		            //�������дÿ�δ�����ʱ��ִ�еĴ���
		            try 
		            {
		                //1.�����ͻ���Socket��ָ����������ַ�Ͷ˿�
		                Socket socket=new Socket("localhost", 2333);
		                //2.��ȡ���������������˷�����Ϣ
		                OutputStream os=socket.getOutputStream();//�ֽ������
		                PrintWriter pw=new PrintWriter(os);//���������װΪ��ӡ��
		                if(player!="none233")
		                {
		                	pw.write(msg);
		                	player="none233";
		                }
		                else
		                {
		                	pw.write("getmsg");
		                }
		                pw.flush();
		                socket.shutdownOutput();//�ر������
		                //3.��ȡ������������ȡ�������˵���Ӧ��Ϣ
		                InputStream is=socket.getInputStream();
		                BufferedReader br=new BufferedReader(new InputStreamReader(is));
		                String info=null;
		                info=br.readLine();
		                String[] sourceStrArray=info.split("\\|\\|\\|\\|\\|");
		                for(int i=0;i<sourceStrArray.length;i++)
		                {
		                	if(sourceStrArray[i].indexOf("<")!=-1)
		                	{
		                		Bukkit.broadcastMessage(sourceStrArray[i]);
		                	}
		                	else if(sourceStrArray[i].indexOf("command>")!=-1)
		                	{
		                		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), sourceStrArray[i].replace("command>", ""));
		                		if(player!="none233")
		                		{
		                			msg+="]][[<��ʾ>"+sourceStrArray[i].replace("command>", "")+"��ִ��";
		                		}
		                		else
		                		{
		                			player="ok";
		                			msg="<��ʾ>"+sourceStrArray[i].replace("command>", "")+"��ִ��";
		                		}
		                	}
		                }
		                //Bukkit.broadcastMessage("debug:"+info);
		                //4.�ر���Դ
		                br.close();
		                is.close();
		                pw.close();
		                os.close();
		                socket.close();
		            } catch (UnknownHostException e) {
		                //e.printStackTrace();
		            } catch (IOException e) {
		                //e.printStackTrace();
		            }
		        //}
		    } 
		}.runTaskTimer(this, 0L, 20L);//������,���ࡢ�ӳ١�����������һ��,����5���Ǿ���5*20L
	}
	@Override
	public void onDisable()
	{
		getLogger().info("QQGroupMessagePlugin is stoped successfully!");
	}
	
	@EventHandler
	public void onPlayerSay(AsyncPlayerChatEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<"+event.getPlayer().getName()+">"+event.getMessage();
			//player="ok";
		}
		else
		{
			player="ok";
			msg="<"+event.getPlayer().getName()+">"+event.getMessage();
		}
		//Bukkit.broadcastMessage("player:"+event.getPlayer().getName()+",msg:"+event.getMessage());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<��Ϣ>"+event.getPlayer().getName()+"������";
		}
		else
		{
			player="ok";
			msg="<��Ϣ>"+event.getPlayer().getName()+"������";
		}
	}
	
	/*
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<��Ϣ>"+event.getPlayer().getName()+"��op�߳�ȥ��";
		}
		else
		{
			player="ok";
			msg="<��Ϣ>"+event.getPlayer().getName()+"��op�߳�ȥ��";
		}
	}*/
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<��Ϣ>"+event.getPlayer().getName()+"������";
		}
		else
		{
			player="ok";
			msg="<��Ϣ>"+event.getPlayer().getName()+"������";
		}
	}
	/*
	public void socket(String player, String msg)
	{
        try 
        {
            //1.�����ͻ���Socket��ָ����������ַ�Ͷ˿�
            Socket socket=new Socket("localhost", 2333);
            //2.��ȡ���������������˷�����Ϣ
            OutputStream os=socket.getOutputStream();//�ֽ������
            PrintWriter pw=new PrintWriter(os);//���������װΪ��ӡ��
            pw.write("<"+player+">"+msg);
            pw.flush();
            socket.shutdownOutput();//�ر������
            //3.��ȡ������������ȡ�������˵���Ӧ��Ϣ
            //InputStream is=socket.getInputStream();
            //BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //String info=null;
            //while((info=br.readLine())!="msg ok!"){
            	//Bukkit.broadcastMessage(br.readLine());
            //}
            //4.�ر���Դ
            //br.close();
            //is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}*/
}

