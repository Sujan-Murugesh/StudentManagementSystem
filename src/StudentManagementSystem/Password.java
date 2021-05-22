    package StudentManagementSystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sujan
 */
public class Password extends Main{
       String encrip_pwd=null;
    public void changeUserPassword() throws NoSuchAlgorithmException{
        String NewPwd=pwdNew.getText();
        String PwdCon=pwdConfirm.getText();
        
        if(!"".equals(NewPwd) && !"".equals(PwdCon)&&(NewPwd.equals(PwdCon)) ){
                
                 try {
                        byte[] byte_pwd=PwdCon.getBytes();
                        MessageDigest md5=MessageDigest.getInstance("md5");
                        md5.update(byte_pwd);
                        byte[] BytePass=md5.digest();
                        encrip_pwd=DatatypeConverter.printHexBinary(BytePass);
                   String UpdateQuery = "UPDATE user SET password =? WHERE username ='"+pwdUserId.getText()+"'";
                   ps =conn.prepareStatement(UpdateQuery);
                   ps.setString(1,encrip_pwd);
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null, "Password Changed successfully...");
               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null,ex.getMessage());
               } 
        pwdNew.setText("");
        pwdConfirm.setText("");
        //jPanelChangePwd.setVisible(false);
        }
        else{
                JOptionPane.showMessageDialog(null,"check Inputs","Your Password not Matching! TryAgain!...",2);
        }
    }
    
}
