package StudentManagementSystem;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Teachers extends Main{
    String TECHERImgPath = null;
    //==========================********************************************====================
    //==========================********************************************====================
    //==========================********************************************====================
        public ImageIcon TResizeImage(String TECHERImgPath,byte[] pic)
        {
            ImageIcon myImage= null;
            if(TECHERImgPath != null)
            {
                myImage = new ImageIcon(TECHERImgPath);
            }else
            {
                myImage =new ImageIcon(pic);
            }
            
            Image img= myImage.getImage();
            Image img2= img.getScaledInstance(TECH_IMG_LBL.getWidth(),TECH_IMG_LBL.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon image =new ImageIcon(img2);
            return image;
        }
    //==========================********************************************====================    
        public void chooiceTeachImg(){
                JFileChooser file =new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter filter =new FileNameExtensionFilter("*.images","jpg","png");
                int result = file.showSaveDialog(null);

                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    TECH_IMG_LBL.setIcon(TResizeImage(path,null));
                    TECHERImgPath =path;
                }
                else{
                    JOptionPane.showMessageDialog(null,"please select student picture");
                }
        }
    //==========================********************************************====================
        public void addTechInfor(){
            if("".equals(TREG_name.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Teacher name");
            }
            else if("".equals(TREG_nic.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the NIC");
            }
            else if("".equals(TREG_dob.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Date of birth");
            }
            else if("".equals(TREG_subject.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Subject");
            }
            else if("".equals(TREG_email.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Email");
            }
            else if("".equals(TREG_tel.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the telephone ");
            }
            else if("".equals(TREG_qualific.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Qualification");
            }
            else if("".equals(TREG_joindate.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Joining date");
            }//
            else if("".equals(TREG_address.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Address");
            }
            else if(TECHERImgPath==null){
                 JOptionPane.showMessageDialog(null,"Please Upload teacher Image!!!");
            }
            else{
                try {
                ps=conn.prepareStatement("INSERT INTO teachersinformation(Name,NIC,DateOfBirth,Subject,Email,Telephone,Qualification,DateOfJoin,Address,Timage)values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,TREG_name.getText());
                ps.setString(2,TREG_nic.getText());
                ps.setString(3,TREG_dob.getText());
                ps.setString(4,TREG_subject.getText());
                ps.setString(5,TREG_email.getText());
                ps.setString(6,TREG_tel.getText());
                ps.setString(7,TREG_qualific.getText());
                ps.setString(8,TREG_joindate.getText());
                ps.setString(9,TREG_address.getText());
                InputStream img=new FileInputStream(new File(TECHERImgPath));
                ps.setBlob(10, img);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Teacher Data inserted...");
                clearinTechForm();
                } catch (SQLException | FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
     //==========================********************************************====================   
      //==========================********************************************====================
        public void clearinTechForm(){
            TREG_name.setText("");
            TREG_nic.setText("");
            TREG_dob.setText("");  
            TREG_subject.setText("");
            TREG_email.setText("");
            TREG_tel.setText("");
            TREG_qualific.setText("");
            TREG_joindate.setText("");
            TREG_address.setText("");
            TECH_IMG_LBL.setIcon(null); 
            TECHERImgPath=null;
            TREG_name.requestFocus();
        }
     //==========================********************************************====================
      //==========================********************************************====================
         public void UpdatTechInfor(){
            if("".equals(TREG_name.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Teacher name");
            }
            else if("".equals(TREG_nic.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the NIC");
            }
            else if("".equals(TREG_dob.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Date of birth");
            }
            else if("".equals(TREG_subject.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Subject");
            }
            else if("".equals(TREG_email.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Email");
            }
            else if("".equals(TREG_tel.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the telephone ");
            }
            else if("".equals(TREG_qualific.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Qualification");
            }
            else if("".equals(TREG_joindate.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Joining date");
            }//
            else if("".equals(TREG_address.getText())){
                JOptionPane.showMessageDialog(null,"Please Fill the Address");
            }
            else{
                String UpdateQuery =null;
                PreparedStatement ps =null;
                if(TECHERImgPath==null){
                    try {
                        UpdateQuery="UPDATE `teachersinformation` SET `Name`=?,`DateOfBirth`=?,`Subject`=?,`Email`=?,`Telephone`=?,`Qualification`=?,`DateOfJoin`=?,`Address`=? WHERE `NIC`='"+TREG_nic.getText()+"'";
                        ps =conn.prepareStatement(UpdateQuery);

                        ps.setString(1,TREG_name.getText());
                        ps.setString(2,TREG_dob.getText());
                        ps.setString(3,TREG_subject.getText());
                        ps.setString(4,TREG_email.getText());
                        ps.setString(5,TREG_tel.getText());
                        ps.setString(6,TREG_qualific.getText());
                        ps.setString(7,TREG_joindate.getText());
                        ps.setString(8,TREG_address.getText());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Teacher Data Updated...");
                        clearinTechForm();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                }
                else{
                    try {
                        UpdateQuery="UPDATE `teachersinformation` SET `Name`=?,`DateOfBirth`=?,`Subject`=?,`Email`=?,`Telephone`=?,`Qualification`=?,`DateOfJoin`=?,`Address`=?,`Timage`=? WHERE `NIC`='"+TREG_nic.getText()+"'";
                        ps =conn.prepareStatement(UpdateQuery);

                        ps.setString(1,TREG_name.getText());
                        ps.setString(2,TREG_dob.getText());
                        ps.setString(3,TREG_subject.getText());
                        ps.setString(4,TREG_email.getText());
                        ps.setString(5,TREG_tel.getText());
                        ps.setString(6,TREG_qualific.getText());
                        ps.setString(7,TREG_joindate.getText());
                        ps.setString(8,TREG_address.getText());
                        InputStream img=new FileInputStream(new File(TECHERImgPath));
                        ps.setBlob(9, img);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Teacher Data Updated...");
                        clearinTechForm();
                        } catch (SQLException | FileNotFoundException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                }
                
            }
        }
           //==========================********************************************====================
         
        public void seeTeacList(){
            try {
                        String qry="select `id`,`Name` from `teachersinformation`";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableTeacherView.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        //====================================================  ===================  ======================
        public void techViewTblinfoView(){
        int  index=jTableTeacherView.getSelectedRow()+1;
        //if(index==rs.getInt("Session_Id"))
        try {
            String sql ="SELECT * FROM `teachersinformation` where id='"+index+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
                if(rs.next()){
                    if(index==rs.getInt("id")){
                        String Name=rs.getString("Name");
                        String Nic=rs.getString("NIC");
                        String Dob=rs.getString("DateOfBirth");
                        String Subject=rs.getString("Subject");
                        String Tmail=rs.getString("Email");
                        String Tel=rs.getString("Telephone");
                        String TQualification=rs.getString("Qualification");
                        String Dojoin=rs.getString("DateOfJoin");
                        String Taddress=rs.getString("Address");
                        byte[] img=rs.getBytes("Timage");
                        ImageIcon image=new ImageIcon(img);
                        Image im=image.getImage();
                        Image myImg=im.getScaledInstance(TECH_IMG_LBL1.getWidth(),TECH_IMG_LBL1.getHeight(),Image.SCALE_SMOOTH);
                        ImageIcon newImage=new ImageIcon(myImg);
                        TECH_IMG_LBL1.setIcon(newImage);
                        jLabelTname.setText(Name);
                        jLabelTnic.setText(Nic);
                        jLabeldob.setText(Dob);
                        jLabelTsubject.setText(Subject);
                        jLabelTemail.setText(Tmail);
                        jLabelTtel.setText(Tel);
                        jLabelTqualific.setText(TQualification);
                        jLabelTdoJoin.setText(Dojoin);
                        jLabelTaddress.setText(Taddress);
                        //close............................
                    }
                        
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        }
}
