package edu.bsu.cs222.twoweekproject;


@SuppressWarnings("WeakerAccess")
public final class Revision {

    public static final class Builder {
        private String user;
        private String timestamp;
        private Builder(){
        }

        public Builder setUser(String user){
            if (user == null) throw new IllegalArgumentException();
            this.user = user;
            return this;
        }

        public Builder setTimestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public Revision build(){
            return new Revision(this);
        }
    }

    public static Builder create(){
        return new Builder();
    }

    public final String user;
    public final String timestamp;

    private Revision(Builder builder){
        this.user = builder.user;
        this.timestamp = builder.timestamp;
    }

    public String getUser(){
        return user;
    }

    public String getTimestamp(){
        return timestamp;
    }
}