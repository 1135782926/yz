package com.rzxrsd.yz.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.rzxrsd.yz.R;

import java.util.List;
import java.util.UUID;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.commons.models.IUser;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
public class ChatActivity extends AppCompatActivity {
    private MessageList messageList;
    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    MsgListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setTitle("友至");
        messageList = findViewById(R.id.msg_list);
        adapter = new MsgListAdapter<>("0",null);
        messageList.setAdapter(adapter);
        MyMessage message = new MyMessage("aaa", IMessage.MessageType.RECEIVE_TEXT);
        adapter.addToStart(message,true);
        ChatInputView chatInputView =  findViewById(R.id.chat_input);
        chatInputView.setMenuContainerHeight(819);
//        final Context context = getApplicationContext();
//        final RelativeLayout parentLayout = findViewById(R.id.relative);
//        final View mLayout = getWindow().getDecorView();
//        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                parentLayout.getWindowVisibleDisplayFrame(r);
//                int screenHeight = mLayout.getRootView().getHeight();
//                int heightDiff = screenHeight - (r.bottom - r.top);
//                if(heightDiff > 100) {
//                    int statusBarHeight = 0;
//                    try {
//                        Class<?> c = Class.forName("com.android.internal.R$dimen");
//                        Object obj = c.newInstance();
//                        Field field = c.getField("status_bar_height");
//                        int x = Integer.parseInt(field.get(obj).toString());
//                        statusBarHeight = context.getResources().getDimensionPixelSize(x);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    int realKeyboardHeight = heightDiff - statusBarHeight;
//
//                }
//            }
//        });


        chatInputView.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                MyMessage m = new MyMessage(input.toString(), IMessage.MessageType.SEND_TEXT);
                adapter.addToStart(m,true);
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {

            }

            @Override
            public boolean switchToMicrophoneMode() {
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                return true;
            }
        });
    }
}
class MyMessage implements IMessage {

    private long id;
    private String text;
    private String timeString;
    private MessageType type;
    private IUser user;
    private String contentFile;
    private long duration;

    public MyMessage(String text, MessageType type) {
        this.text = text;
        this.type = type;
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }

    @Override
    public String getMsgId() {
        return String.valueOf(id);
    }

    @Override
    public IUser getFromUser() {
        if (user == null) {
            return new DefaultUser("0", "user1", null);
        }
        return user;
    }

    public void setUserInfo(IUser user) {
        this.user = user;
    }

    public void setMediaFilePath(String path) {
        this.contentFile = path;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public long getDuration() {
        return duration;
    }


    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String getTimeString() {
        return timeString;
    }

    @Override
    public MessageType getType() {
        return type;
    }



    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getMediaFilePath() {
        return contentFile;
    }
}
class DefaultUser implements IUser {

    private String id;
    private String displayName;
    private String avatar;

    public DefaultUser(String id, String displayName, String avatar) {
        this.id = id;
        this.displayName = displayName;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAvatarFilePath() {
        return avatar;
    }
}
