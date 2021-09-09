
package com.tech.blog.dao;
import com.tech.blog.entities.User;
import java.sql.*;

public class UserDao {
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }
    
    //method to insert user to data base :
    
    public boolean saveUser(User user)
    {
        boolean f=false;
        try{
            //user --->database
            
            String query ="insert into user_details(user_name,user_email,user_password,user_gender,about_user) values(?,?,?,?,?)";
            PreparedStatement pstmt=this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());
            

            pstmt.executeUpdate();
            f=true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }
    //get user by useremail and userpassword:
    
    public User getUserByEmailAndPassword(String email,String password){
        User user=null;
        
        try{
            String query="select * from user_details where user_email=? and user_password=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet set = pstmt.executeQuery();
            if(set.next()){
                user=new User();
                //data from database
                String name=set.getString("user_name");
                
                //set to user object
                user.setName(name);
                user.setId(set.getInt("user_id"));
                user.setEmail(set.getString("user_email"));
                user.setPassword(set.getString("user_password"));
                user.setGender(set.getString("user_gender"));
                user.setAbout(set.getString("about_user"));
                user.setDateTime(set.getTimestamp("r_date"));
                user.setProfile(set.getString("user_profile"));
                
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return user;
    }
    
    public boolean updateUser(User user){
        boolean f=false;
        try{
            String query="update user_details set user_name=? ,user_email=? ,user_password=? , user_gender=? , about_user=? , user_profile=? where user_id=?";
            PreparedStatement p=con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getAbout());
            p.setString(6, user.getProfile());
            p.setInt(7, user.getId());
            
            p.executeUpdate();
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }
    
    public User getUserByUserId(int userId){
        User user=null;
        
        try{
            String q="select * from user_details where user_id=?";
            PreparedStatement ps=this.con.prepareStatement(q);
            ps.setInt(1, userId);
            ResultSet set=ps.executeQuery();
            if(set.next()){
                user=new User();
                //data from database
                String name=set.getString("user_name");
                
                //set to user object
                user.setName(name);
                user.setId(set.getInt("user_id"));
                user.setEmail(set.getString("user_email"));
                user.setPassword(set.getString("user_password"));
                user.setGender(set.getString("user_gender"));
                user.setAbout(set.getString("about_user"));
                user.setDateTime(set.getTimestamp("r_date"));
                user.setProfile(set.getString("user_profile"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
