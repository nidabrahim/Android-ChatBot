package com.youssefnida.winou;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youssefnida.winou.ChatResponses.WinoResponses;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    private RelativeLayout container;
    private static ChatAdapter adapter;
    private ChatMessage chatMessage;
    private ArrayList<ChatMessage> chatHistory;

    WinouThread chatThread;
    WinoResponses responses;

    public Bot bot;
    public static Chat chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /** MENU **/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initControls();

    }


    private void initControls() {

        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (Button) findViewById(R.id.chatSendButton);
        container = (RelativeLayout) findViewById(R.id.container);
        chatHistory = new ArrayList<ChatMessage>();

        /** ADAPTER **/
        adapter = new ChatAdapter(ChatActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);
        responses = new WinoResponses();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                displayMessage(chatMessage);


               // winouResponse(chatMessage.getMessage());

            }
        });

        loadChat();

    }

    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    public void displayMessageWinou(ChatMessage message) {

        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }


    private void loadChat(){

        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessageWinou(message);
        }

        chatThread = new WinouThread(this);
        chatThread.start();

    }

    public void winouResponse(String messagRecu){

        ChatMessage msg = new ChatMessage();
        msg.setId(2);
        msg.setMe(false);
        /*
        if( messagRecu.contains("toi") )    msg.setMessage("Super bien, c'est beau aujourd'hui n'est pas ?");
        else    msg.setMessage("Ok, desole je suis occupé ! on parlera tout à l'heure");
        */

        msg.setMessage(responses.getResponse(messagRecu));
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        //msg.setMessage(chat.multisentenceRespond(messagRecu));

        chatHistory.add(msg);

        displayMessageWinou(msg);

        chatMessage = null;
    }

    //check SD card availability
    public static boolean isSDCARDAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)? true :false;
    }
    //copying the file
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public void addToChatHistory(ChatMessage msg){
        chatHistory.add(msg);
    }

    public ArrayList<ChatMessage> getChatHistory(){
        return this.chatHistory;
    }

    public ChatMessage getChatMessage(){
        return this.chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage){
        this.chatMessage = chatMessage;
    }

    public WinoResponses getResponses(){
        return this.responses;
    }



}
