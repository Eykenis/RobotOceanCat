package org.example.mirai.plugin;

import java.util.Random;

import net.mamoe.mirai.Mirai;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.MessageChain;

public class EventHost extends SimpleListenerHost {
    public static String lst;
    public static Random random = new Random();
    public static MessageChain message;
    @EventHandler
    private void onGroupMessage(GroupMessageEvent event) {
        message = event.getMessage();
        String tmp = message.contentToString();
        if (tmp.startsWith("海猫说")) {
            event.getSubject().sendMessage(tmp.replace("海猫说", ""));
        }
        else if (tmp.startsWith("法老说")) {
            event.getSubject().sendMessage(tmp.replace("法老说", ""));
        }
        else if(tmp.startsWith("跟我说")) {
            event.getSubject().sendMessage(tmp.replace("跟我说", ""));
        }
        if (tmp.equals(lst) && random.nextInt(10) > 5) {
            event.getSubject().sendMessage(message);
        }
        lst = new String(tmp);
    }
}