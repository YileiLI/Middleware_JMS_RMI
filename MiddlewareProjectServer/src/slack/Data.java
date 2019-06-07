package slack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.impl.StringDataFormat;

public class Data {
    //les usernames predefini
    private String[] username = new String[3];
    private String[] password = new String[3];
    private String[] pseudo = new String[3];
    private List<String> group = new ArrayList<>();//use List for the case - add new groups
    private ArrayList<ArrayList<String>> subscribed = new ArrayList<>();
    
    public Data() {
        //initial groups
        group.add("Group1");
        group.add("Group2");
        group.add("Group3");
        group.add("Group4");
        
        //user 1
        username[0] = "user1";
        password[0] = "1qaz";
        pseudo[0] = "pseudo1";
        ArrayList<String> user1 = new ArrayList<>();
        user1.add(group.get(0));
        subscribed.add(user1);
        
        //user 2
        username[1] = "user2";
        password[1] = "1qaz";
        pseudo[1] = "pseudo2";
        ArrayList<String> user2 = new ArrayList<>();
        user2.add(group.get(0));
        user2.add(group.get(1));

        subscribed.add(user2);
        
        //user 2
        username[2] = "user3";
        password[2] = "1qaz";
        pseudo[2] = "pseudo3";
        ArrayList<String> user3 = new ArrayList<>();
        subscribed.add(user3);
    }
    
    public String[] getUsername() {
        return username;
    }
    
    public String[] getPassword() {
        return password;
    }
    
    public String[] getPseudo() {
        return pseudo;
    }
    
    public List<String> getGroups() {
        return group;
    }
    
    public ArrayList<ArrayList<String>> getSubscription() {
        return subscribed;
    }
    
    //return pseudo if success
    //return null if not
    public String login(String un, String pwd) {
        System.out.println("User " + un + " is trying to log in with password " + pwd);
        for (int i = 0; i < username.length; i++) {
            if (un.equals(username[i])) {
                if (pwd.equals(password[i])) {
                    return pseudo[i];
                }
            }
        }
        return null;
    }
    
    //return all the groups existed with a remark of subscribed groups
    public String getUserGroups(String un) {
        String out = "==================Group List====================\n";
        for (int i = 0; i < username.length; i++) {
            if (un.equals(username[i])) {
                for (String sub : group) {
                    out += sub;
                    if (subscribed.get(i).contains(sub)) {
                        out += " √";
                    } 
                    out += "\n";
                }
            }
        }
        System.out.println("User " + un + " is trying to get the group list.");
        return out;
    }
    
    //update the user's subscribed group list
    public boolean updateSubscribed(String un, String gr){
        for (int i = 0; i < username.length; i++) {
            if (un.equals(username[i])) {
                if (!subscribed.get(i).contains(gr) && group.contains(gr)) {
                    subscribed.get(i).add(gr);
                    System.out.println("User " + un + " succeeded to subscribe group " + gr);
                    return true;
                }
                
            }
        }
        System.out.println("User " + un + " failed to subscribe group " + gr);
        return false;
    }
    
    //if user subscribed group or not
    public boolean isSubscribed(String un, String gr){
        for (int i = 0; i < username.length; i++) {
            if (un.equals(username[i])) {
                return subscribed.get(i).contains(gr);
                
            }
        }
        return false;
    }

}
