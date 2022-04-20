package org.example.mirai.plugin;

import java.util.Random;
import java.util.Vector;

import net.mamoe.mirai.Mirai;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.MessageChain;

public class EventHost extends SimpleListenerHost {
    public static String lst;
    public static Random random = new Random();
    public static MessageChain message;
    public static Vector<String> bottle = new Vector<String>();

    @EventHandler
    private void onGroupMessage(GroupMessageEvent event) {
        message = event.getMessage();
        String tmp = message.contentToString();
        if (tmp.startsWith("添加海猫转盘 ")) {
            event.getSubject().sendMessage("添加成功！");
            bottle.addElement(tmp.replace("添加海猫转盘 ", ""));
        }
        else if (tmp.startsWith("海猫转盘")) {
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
        }
        else if (tmp.startsWith("法老说")) {
            event.getSubject().sendMessage(tmp.replace("法老说", ""));
        }
        else if(tmp.startsWith("跟我说")) {
            event.getSubject().sendMessage(tmp.replace("跟我说", ""));
        }
        else if (tmp.startsWith("海猫") && !tmp.equals("海猫") && !tmp.startsWith("海猫转盘")) {
            String ttmp = tmp.replace("我", "你");
            event.getSubject().sendMessage(ttmp.replace("海猫", "我不好"));
        }
        else if (tmp.equals(lst) && random.nextInt(100) < 8) {
            if (tmp.equals("[图片]")) {
                if (random.nextBoolean())  event.getSubject().sendMessage(message);
            }
            else event.getSubject().sendMessage(message);
        }
        else if (random.nextInt(100) < 2) {
            event.getSubject().sendMessage(message);
        }
        lst = new String(tmp);
    }
}