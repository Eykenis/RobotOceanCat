package org.example.mirai.plugin;
import java.io.*;
import java.util.Random;
import java.util.Vector;
import java.util.Scanner;

import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

public class EventHost extends SimpleListenerHost {
    public static String lst;
    public static Random random = new Random();
    public static MessageChain message;
    public static Vector<String> bottle = new Vector<String>();
    File ff = new File("bottleLog.txt");
    FileInputStream inStream;
    FileWriter outStream;
    Scanner s;
    boolean flag = false;


    @EventHandler
    private void onGroupMessage(GroupMessageEvent event) {
        if (flag == false) {
            if (!ff.exists()) {
                try {
                    ff.createNewFile();
                } catch (IOException e) {
                    System.out.println("IOEXCEPTION\n");
                }
            }
            System.out.println("Get name: " + ff.getName());
            try {
                inStream = new FileInputStream(ff);
            } catch (IOException e) {
                //
            }
            s = new Scanner(inStream);

            while (s.hasNextLine()) {
                bottle.addElement(s.nextLine());
            }
            flag = true;
            s.close();
            try {
                inStream.close();
                outStream = new FileWriter(ff, true);
            } catch (IOException e) {

            }
        }
        message = event.getMessage();
        String tmp = message.contentToString();
        if (tmp.startsWith("添加海猫转盘 ")) {
            bottle.addElement(tmp.replace("添加海猫转盘 ", ""));
            try {
                outStream.write(tmp.replace("添加海猫转盘 ", ""));
                outStream.write(new String("\n"));
                outStream.flush();
            } catch (IOException e) {
                // do nothing
                event.getSubject().sendMessage("添加失败！");
                return;
            }
            event.getSubject().sendMessage("添加成功！");
        } else if (tmp.startsWith("海猫转盘")) {
            int size = bottle.size();
            if (bottle.isEmpty()) {
                event.getSubject().sendMessage("这个转盘还是空的，小钟问你想抽什么？");
                return;
            }
            event.getSubject().sendMessage("你从转盘中抽到了：" + bottle.get(random.nextInt(size) % size) + ", 恭喜你！");
            return;
        }
        if (tmp.startsWith("海猫说")) {
            event.getSubject().sendMessage(tmp.replace("海猫说", ""));
        } else if (tmp.startsWith("法老说")) {
            event.getSubject().sendMessage(tmp.replace("法老说", ""));
        } else if (tmp.startsWith("跟我说")) {
            event.getSubject().sendMessage(tmp.replace("跟我说", ""));
        } else if (tmp.startsWith("海猫") && !tmp.equals("海猫") && !tmp.startsWith("海猫转盘")) {
            String ttmp = tmp.replace("我", "你");
            event.getSubject().sendMessage(ttmp.replace("海猫", "我不好"));
        }
        lst = new String(tmp);
    }
}