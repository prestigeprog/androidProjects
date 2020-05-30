package com.example.cryptochat;

import com.google.gson.Gson;

public class Protocol {
    public final static int GROUP_CHAT = 1;
    public final static int USER_STATUS = 1;
    public final static int MESSAGE = 2;
    public final static int USER_NAME = 3;

    static class UserName{
        private String name;

        public UserName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Message {
        private long sender;
        private String encodedText;
        private long receiver;

        public Message(String encodedText) {
            this.encodedText = encodedText;
        }

        public long getSender() {
            return sender;
        }

        public String getEncodedText() {
            return encodedText;
        }

        public long getReceiver() {
            return receiver;
        }

        public void setSender(long sender) {
            this.sender = sender;
        }

        public void setEncodedText(String encodedText) {
            this.encodedText = encodedText;
        }

        public void setReceiver(long receiver) {
            this.receiver = receiver;
        }
    }

    static class User {
        private String name;
        private long id;

        public User() {
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    static class UserStatus {
        private boolean connected;
        private User user;

        public UserStatus() {
        }

        public boolean isConnected() {
            return connected;
        }

        public User getUser() {
            return user;
        }

        public void setConnected(boolean connected) {
            this.connected = connected;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public static Message unpackMessage(String json) {
        Gson g = new Gson();
        return g.fromJson(json.substring(1), Message.class);
    }

    public static UserStatus unpackStatus(String json) {
        Gson g = new Gson();
        return g.fromJson(json.substring(1), UserStatus.class);
    }



    public static String packMessage(Message m) {
        Gson g = new Gson();
        return MESSAGE + g.toJson(m);
    }

    public static String packName(UserName name) {
        Gson g = new Gson();
        return USER_NAME + g.toJson(name);

    }
    public static int getType(String json) {
        if (json == null || json.length()==0) {
            return 1;
        }
        return Integer.parseInt(json.substring(0,1));
    }
}
