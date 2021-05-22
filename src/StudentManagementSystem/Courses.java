package StudentManagementSystem;

import static StudentManagementSystem.Main.jCombocourse;
import static StudentManagementSystem.Main.jTablecourceView;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Courses extends Main{
    //==========================================================================
        //THIS IS FOR CHECKING COURSE INPUT FEILDS
    //==========================================================================
    public static String res;
    public static String res_name;
    public boolean courseInputCheck(){
        return !(jTextC_Session_Id==null ||
                jTextC_Session_name==null||
                jTextC_theory_hourse==null||
                jTextC_Practical_hours==null||
                res==null||
                jTextC_remarks==null);
    }
    //==========================================================================
        //THIS CODE USE TO CLEAR TEXT FEILDS AND COURSE FORM.
    public void clearingcourseForm(){
        jComboBoxchCourse.setSelectedIndex(0);
        jTextC_Session_name.setText("");
        jTextC_Session_Id.setText("");
        jTextC_theory_hourse.setText("");
        jTextC_Practical_hours.setText("");   
        jTextC_remarks.setText(""); 
        jTextC_Session_name.requestFocus();
        res=null;
        res_name=null;
    }
       
    //==========================================================================
    //==========================================================================
        //THIS IS FOR CHOOICE COUIRSE RESOURCES....
    //==========================================================================
    public void chooiceResourse(){
        JFileChooser jfc= new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        JOptionPane.showMessageDialog(null,"You able to Select Multiple Files And Directories");
       
        jfc.setMultiSelectionEnabled(true);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        int returnValue =jfc.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File[] files=jfc.getSelectedFiles();
           // JOptionPane.showMessageDialog(null,"Directories Selected");
            //System.out.println("Directories found");
            Arrays.asList(files).forEach(x->{
                if(x.isDirectory()){
                    res= x.getAbsolutePath();
                    //System.out.println(x.getName());
                    res_name=x.getName();
                    JOptionPane.showMessageDialog(null,x.getName());
                }
            });
           // JOptionPane.showMessageDialog(null,"Files Selected");
            //System.out.println("Files found");
            Arrays.asList(files).forEach(x->{
                if(x.isFile()){
                    //System.out.println(x.getName());
                    res= x.getAbsolutePath();
                    res_name=x.getName();
                    JOptionPane.showMessageDialog(null,x.getName());
                }
            });
            
        }
    }
    //==========================================================================
    //==========================================================================
        //CODE FOR INSERTING BIO TECHNOLOGY DATA
    //==========================================================================
    public void insertCourse_B_Tech(){
        try {
            PreparedStatement ps=conn.prepareStatement("INSERT INTO `course_b_tech`(Session_id,Session_name,Session_theory,Session_practical,Session_resource,remarks,res_title)values(?,?,?,?,?,?,?)");
                ps.setInt(1,Integer.parseInt(jTextC_Session_Id.getText()));
                ps.setString(2,jTextC_Session_name.getText());
                ps.setInt(3,Integer.parseInt(jTextC_theory_hourse.getText()));
                ps.setInt(4,Integer.parseInt(jTextC_Practical_hours.getText()));
                byte[] bytes = res.getBytes(); 
                ps.setString(5,res);
                ps.setString(6,jTextC_remarks.getText());
                ps.setString(7, res_name);
                ps.executeUpdate();
                clearingcourseForm();
                JOptionPane.showMessageDialog(null, "data is Inserted...");
        } catch (SQLException | NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    //==========================================================================
        //CODE FOR INSERTING SCIENCE FOR TECHNOLOGY DATA
    //==========================================================================
    public void insertCourse_S_Tech(){
        try {
            PreparedStatement ps=conn.prepareStatement("INSERT INTO `course_s_tech`(Session_id,Session_name,Session_theory,Session_practical,Session_resource,remarks,res_title)values(?,?,?,?,?,?,?)");
                ps.setInt(1,Integer.parseInt(jTextC_Session_Id.getText()));
                ps.setString(2,jTextC_Session_name.getText());
                ps.setInt(3,Integer.parseInt(jTextC_theory_hourse.getText()));
                ps.setInt(4,Integer.parseInt(jTextC_Practical_hours.getText()));
                byte[] bytes = res.getBytes();
                ps.setString(5,res);
                ps.setString(6,jTextC_remarks.getText());
                ps.setString(7, res_name);
                ps.executeUpdate();
                clearingcourseForm();
                JOptionPane.showMessageDialog(null, "data is Inserted...");
        } catch (SQLException | NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    //==========================================================================
        //CODE FOR INSERTING Agriculture DATA
    //==========================================================================
    public void insertCourse_Agriculture(){
        try {
            PreparedStatement ps=conn.prepareStatement("INSERT INTO `course_agri`(Session_id,Session_name,Session_theory,Session_practical,Session_resource,remarks,res_title)values(?,?,?,?,?,?,?)");
                ps.setInt(1,Integer.parseInt(jTextC_Session_Id.getText()));
                ps.setString(2,jTextC_Session_name.getText());
                ps.setInt(3,Integer.parseInt(jTextC_theory_hourse.getText()));
                ps.setInt(4,Integer.parseInt(jTextC_Practical_hours.getText()));
                byte[] bytes = res.getBytes();
                ps.setString(5,res);
                ps.setString(6,jTextC_remarks.getText());
                ps.setString(7, res_name);
                ps.executeUpdate();
                clearingcourseForm();
                JOptionPane.showMessageDialog(null, "data is Inserted...");
        } catch (SQLException | NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    //==========================================================================
        //CODE FOR INSERTING COURSES INTO DATABASE
    //==========================================================================
    public void inserting_Course(){
        String selectedCourse=(String)jComboBoxchCourse.getSelectedItem();
        if("Select Course Code".equals(selectedCourse)){
            JOptionPane.showMessageDialog(null,"Please Select A course Code...");
        }
        else if(!courseInputCheck()){
            JOptionPane.showMessageDialog(null,"Please Check Input feilds...");
        }
        else{
            switch(selectedCourse){
                case "B_Tech":insertCourse_B_Tech();
                    break;
                case "S_Tech":insertCourse_S_Tech();
                    break;
                case "Agriculture":insertCourse_Agriculture();
                    break;
            }
        }
    }
    //==========================================================================
        //UPDATING COURSE B_Tech DATA
    //==========================================================================
    public void updateCourse_B_Tech(){
    String UpdateQuery =null;
    PreparedStatement ps =null;
        try {
          
          UpdateQuery="UPDATE `course_b_tech` SET `Session_name`=?,`Session_theory`=?,`Session_practical`=?,`Session_resource`=?,`remarks`=?,`res_title`=? WHERE `Session_id`=?";
          ps =conn.prepareStatement(UpdateQuery);
            ps.setString(1,jTextC_Session_name.getText());
            ps.setString(2,jTextC_theory_hourse.getText());
            ps.setString(3,jTextC_Practical_hours.getText());
            byte[] bytes = res.getBytes();
            ps.setString(4,res);
            ps.setString(5,jTextC_remarks.getText());
            ps.setString(6, res_name);
            ps.setString(7, jTextC_Session_Id.getText());
            ps.executeUpdate();
            clearingcourseForm();
            JOptionPane.showMessageDialog(null, "data is Updated...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    //==========================================================================
        //UPDATING COURSE S_Tech DATA
    //==========================================================================
    public void updateCourse_S_Tech(){
    String UpdateQuery =null;
    PreparedStatement ps =null;
        try {
          UpdateQuery="UPDATE `course_s_tech` SET `Session_name`=?,`Session_theory`=?,`Session_practical`=?,`Session_resource`=?,`remarks`=?,`res_title`=? WHERE `Session_id`=?";
          ps =conn.prepareStatement(UpdateQuery);
            ps.setString(1,jTextC_Session_name.getText());
            ps.setString(2,jTextC_theory_hourse.getText());
            ps.setString(3,jTextC_Practical_hours.getText());
            byte[] bytes = res.getBytes();
            ps.setString(4,res);
            ps.setString(5,jTextC_remarks.getText());
            ps.setString(6, res_name);
            ps.setString(7, jTextC_Session_Id.getText());
            ps.executeUpdate();
            clearingcourseForm();
            JOptionPane.showMessageDialog(null, "data is Updated...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    //==========================================================================
        //UPDATING COURSE Agriculture DATA
    //==========================================================================
    public void updateCourse_Agriculture(){
    String UpdateQuery =null;
    PreparedStatement ps =null;
        try {
          UpdateQuery="UPDATE `course_agri` SET `Session_name`=?,`Session_theory`=?,`Session_practical`=?,`Session_resource`=?,`remarks`=?,`res_title`=? WHERE `Session_id`=?";
          ps =conn.prepareStatement(UpdateQuery);
            ps.setString(1,jTextC_Session_name.getText());
            ps.setString(2,jTextC_theory_hourse.getText());
            ps.setString(3,jTextC_Practical_hours.getText());
            byte[] bytes = res.getBytes();
            ps.setString(4,res);
            ps.setString(5,jTextC_remarks.getText());
            ps.setString(6, res_name);
            ps.setString(7, jTextC_Session_Id.getText());
            ps.executeUpdate();
            clearingcourseForm();
            JOptionPane.showMessageDialog(null, "data is Updated...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
   
    //==========================================================================
        //UPDATING COURSE DATA INTO DATABASE
    //==========================================================================
    public void updating_Course(){
        String selectedCourse=(String)jComboBoxchCourse.getSelectedItem();
        if("Select Course Code".equals(selectedCourse)){
            JOptionPane.showMessageDialog(null,"Please Select A course Code...");
        }
        else{
            switch(selectedCourse){
                case "B_Tech":updateCourse_B_Tech();
                    break;
                case "S_Tech":updateCourse_S_Tech();
                    break;
                case "Agriculture":updateCourse_Agriculture();
                    break;
            }
        }
    }
    //==========================================================================
        //CODE FOR DELETING FROM DATABASE ROWS....
    //==========================================================================
    public void deleteCourseRows(){
        String DeleteQuery =null;
        PreparedStatement ps =null;
        String selectedCourse=(String)jComboBoxchCourse.getSelectedItem();
     
        if("Select Course Code".equals(selectedCourse)){
            JOptionPane.showMessageDialog(null,"Please Select A course Code...");
        }
        else{
            int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete?","Delete",JOptionPane.YES_NO_OPTION);
            if(opt==0){    
            switch(selectedCourse){
                case "B_Tech":
                    try {
                        DeleteQuery="DELETE FROM `course_b_tech` WHERE Session_id='"+jTextC_Session_Id.getText()+"'";
                        ps =conn.prepareStatement(DeleteQuery);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Deteted Successfully...");
                    } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                     }
                    break;
                case "S_Tech":
                    try {
                        DeleteQuery="DELETE FROM `course_s_tech` WHERE Session_id='"+jTextC_Session_Id.getText()+"'";
                        ps =conn.prepareStatement(DeleteQuery);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Deteted Successfully...");
                    } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                     }
                    break;
                case "Agriculture":
                    try {
                        DeleteQuery="DELETE FROM `course_agri` WHERE Session_id='"+jTextC_Session_Id.getText()+"'";
                        ps =conn.prepareStatement(DeleteQuery);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Deteted Successfully...");
                    } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                     }
                    break;
            }
        }
        }
    }
//==============================================================================
    //THIS CODE FOR VIEWING COURSE FROM DATABASE USING JTABLE....
//==============================================================================
    public void viewingCourse(){
        if(jCombocourse.getSelectedItem()=="Select cource"){
            JOptionPane.showMessageDialog(this, "Please select a cource!!!");
        }
        else if(jCombocourse.getSelectedItem()=="B-Tech"){
            try {
                String qry="select Session_Id,Session_name from course_b_tech";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTablecourceView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else if(jCombocourse.getSelectedItem()=="S-Tech"){
            try {
                String qry="select Session_Id,Session_name from course_s_tech";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTablecourceView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else{
            try {
                String qry="select Session_Id,Session_name from course_agri";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTablecourceView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        //jCombocourse.setSelectedIndex(0);
    }
    
//==============================================================================
    //SOWING ALREADY STORED RESOURCES IN DATA BASE
//==============================================================================
    public void showCourseResorce(){
        int index =jTablecourceView.getSelectedRow()+1;
        String CoursSel=(String) jCombocourse.getSelectedItem();
        switch(CoursSel){
            case "B-Tech":   try {
            String sql ="SELECT * FROM `course_b_tech` where Session_Id='"+index+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
                if(rs.next()){
                    if(index==rs.getInt("Session_Id")){
                        String f=rs.getString("Session_resource");
                        courceRePath.setText(f);
                        try {
                            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Sujan\\Desktop\\Name.docx");
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+f);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Notice Not Found!");
                    }
                }  
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
            //jCombocourse.setSelectedIndex(0);
                break;
            case "S-Tech":try {
            String sql ="SELECT * FROM `course_s_tech` where Session_Id='"+index+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
                if(rs.next()){
                    if(index==rs.getInt("Session_Id")){
                        String f=rs.getString("Session_resource");
                        courceRePath.setText(f);
                        try {
                            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Sujan\\Desktop\\Name.docx");
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+f);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Notice Not Found!");
                    }
                }  
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
            //jCombocourse.setSelectedIndex(0);
                break;
            case "Agriculture":try {
            String sql ="SELECT * FROM `course_agri` where Session_Id='"+index+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
                if(rs.next()){
                    if(index==rs.getInt("Session_Id")){
                        String f=rs.getString("Session_resource");
                        courceRePath.setText(f);
                        try {
                            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Sujan\\Desktop\\Name.docx");
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+f);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Notice Not Found!");
                    }
                }  
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
            //jCombocourse.setSelectedIndex(0);
                break;
        
        }
    }
//==============================================================================
}   
