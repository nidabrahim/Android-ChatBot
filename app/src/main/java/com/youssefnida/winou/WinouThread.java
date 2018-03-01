package com.youssefnida.winou;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by youssefNIDA on 14/03/2017.
 */

public class WinouThread extends Thread{

    ChatActivity chatActivity;

    public WinouThread(ChatActivity chatActivity){
        this.chatActivity = chatActivity;
    }

    public void run(){

        //PREMIERE MESSAGE
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    ChatMessage msg = new ChatMessage();
                    msg.setId(1);
                    msg.setMe(false);
                    //msg.setMessage("Salut, ça va ?");
                    msg.setMessage(chatActivity.getResponses().getFirstMsg());
                    msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                    chatActivity.addToChatHistory(msg);
                    //AFFICHAGE
                    for(int i=0; i<chatActivity.getChatHistory().size(); i++) {
                        ChatMessage message = chatActivity.getChatHistory().get(i);
                        chatActivity.displayMessageWinou(message);
                    }

                }
            });

        //ATTENDRE LES MESSAGES ET REPONDER LES.
        while (true){

            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            chatActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(chatActivity.getChatMessage() != null){

                        chatActivity.winouResponse(chatActivity.getChatMessage().getMessage());

                        chatActivity.setChatMessage(null);
                    }
                }
            });

        }


    }

}
