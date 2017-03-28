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
	
	@Override//重写父类的方法
	public void onEnable()
	{
		getLogger().info("QQGroupMessagePlugin is started successfully!");
		//注册监听
		Bukkit.getPluginManager().registerEvents(this,this);
		
		new BukkitRunnable(){     
		    int s = 0;//设置定10秒后执行某段代码
		    @Override    
		    public void run(){
		    	if(s>=60)
		    	{
		    		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tm abc 发钱啦！");
		    		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give * 1");
		    		s=0;
		    	}
		    	else
		    	{
		    		s++;
		    	}
		        //s--;//迭代递减,我看官方的教程是没这个的,我没试过,你也可以删除试试
		        //if(s==0){
		            //这个写10秒后执行的代码(假如定义的定时器每次是1秒)
		        //    cancel();//cancel用来取消定时器
		        //}else{
		            //这里可以写每次触发定时器执行的代码
		            try 
		            {
		                //1.创建客户端Socket，指定服务器地址和端口
		                Socket socket=new Socket("localhost", 2333);
		                //2.获取输出流，向服务器端发送信息
		                OutputStream os=socket.getOutputStream();//字节输出流
		                PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
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
		                socket.shutdownOutput();//关闭输出流
		                //3.获取输入流，并读取服务器端的响应信息
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
		                			msg+="]][[<提示>"+sourceStrArray[i].replace("command>", "")+"已执行";
		                		}
		                		else
		                		{
		                			player="ok";
		                			msg="<提示>"+sourceStrArray[i].replace("command>", "")+"已执行";
		                		}
		                	}
		                }
		                //Bukkit.broadcastMessage("debug:"+info);
		                //4.关闭资源
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
		}.runTaskTimer(this, 0L, 20L);//参数是,主类、延迟、多少秒运行一次,比如5秒那就是5*20L
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
			msg+="]][[<消息>"+event.getPlayer().getName()+"上线了";
		}
		else
		{
			player="ok";
			msg="<消息>"+event.getPlayer().getName()+"上线了";
		}
	}
	
	/*
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<消息>"+event.getPlayer().getName()+"被op踢出去了";
		}
		else
		{
			player="ok";
			msg="<消息>"+event.getPlayer().getName()+"被op踢出去了";
		}
	}*/
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(player!="none233")
		{
			msg+="]][[<消息>"+event.getPlayer().getName()+"掉线了";
		}
		else
		{
			player="ok";
			msg="<消息>"+event.getPlayer().getName()+"掉线了";
		}
	}
	/*
	public void socket(String player, String msg)
	{
        try 
        {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket=new Socket("localhost", 2333);
            //2.获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write("<"+player+">"+msg);
            pw.flush();
            socket.shutdownOutput();//关闭输出流
            //3.获取输入流，并读取服务器端的响应信息
            //InputStream is=socket.getInputStream();
            //BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //String info=null;
            //while((info=br.readLine())!="msg ok!"){
            	//Bukkit.broadcastMessage(br.readLine());
            //}
            //4.关闭资源
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

