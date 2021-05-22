package StudentManagementSystem;

import java.awt.HeadlessException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sujan
 */
public class NewUser extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement ps;
    String encrip_pwd=null;
    ResultSet rs;
    /**
     * Creates new form NewUser
     */
    public NewUser() {
        URL iconUrl = getClass().getResource("ssslogo200.png");
        ImageIcon imageicon=new ImageIcon(iconUrl);
        this.setIconImage(imageicon.getImage());
        initComponents();
        //conn=Db_connection.connect();
        conn=ServerConnectionPanel.connect();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNUname = new javax.swing.JTextField();
        txtNUpwd = new javax.swing.JPasswordField();
        txtNUCpwd = new javax.swing.JPasswordField();
        txtNUtype = new javax.swing.JComboBox<>();
        NUaddbtn = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  INSTITUTE   MANAGEMENT   SYSTEM");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sujan Software Solutions");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("User Name");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Password");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Confirm Password");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("User Type");

        txtNUname.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtNUname.setForeground(new java.awt.Color(0, 0, 255));

        txtNUpwd.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtNUpwd.setForeground(new java.awt.Color(0, 0, 255));

        txtNUCpwd.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtNUCpwd.setForeground(new java.awt.Color(0, 0, 255));

        txtNUtype.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtNUtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User Type", "Admin", "User" }));
        txtNUtype.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        NUaddbtn.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        NUaddbtn.setForeground(new java.awt.Color(0, 0, 255));
        NUaddbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_Add_User_Group_Man_Man_40px.png"))); // NOI18N
        NUaddbtn.setText("Add");
        NUaddbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NUaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUaddbtnActionPerformed(evt);
            }
        });

        btnCancel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnCancel1.setForeground(new java.awt.Color(0, 0, 255));
        btnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_cancel_40px.png"))); // NOI18N
        btnCancel1.setText("Cancel");
        btnCancel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNUname)
                            .addComponent(txtNUpwd)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(NUaddbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel1))
                            .addComponent(txtNUtype, 0, 282, Short.MAX_VALUE)
                            .addComponent(txtNUCpwd))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(txtNUname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(txtNUpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNUCpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNUtype, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NUaddbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NUaddbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUaddbtnActionPerformed
        
        if(txtNUname.getText().length() == 0){
            JOptionPane.showMessageDialog(this,"Please Type the User Name...");
        }
        else if(txtNUpwd.getText().length() == 0){
            JOptionPane.showMessageDialog(this,"Please Type the User Password...");
        }
        else if(txtNUpwd.getText().equals(txtNUCpwd.getText())==false){
            JOptionPane.showMessageDialog(this, "Password Not Matched...");
        }
        else if("Select User Type".equals(txtNUtype.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(this,"Please select User Type...");
        }
        else{
            if(conn!=null){
                String NUname=txtNUname.getText();
                try {
                        String sql ="SELECT * FROM user where username='"+NUname+"'";
                        ps=conn.prepareStatement(sql);
                        rs=ps.executeQuery(sql);
                        if(rs.next()){
                                JOptionPane.showMessageDialog(null,"Username: "+NUname+" Already Existed..");
                        }
                            else{
                                try {
                                        String NUpwd=txtNUCpwd.getText();
                                        byte[] byte_pwd=NUpwd.getBytes();
                                        MessageDigest md5=MessageDigest.getInstance("md5");
                                        md5.update(byte_pwd);
                                        byte[] BytePass=md5.digest();
                                        encrip_pwd=DatatypeConverter.printHexBinary(BytePass);

                                        String utype=txtNUtype.getSelectedItem().toString();

                                        ps=conn.prepareStatement("INSERT INTO user(username,password,utype)values(?,?,?)");
                                        ps.setString(1,txtNUname.getText());
                                        ps.setString(2, encrip_pwd);
                                        ps.setString(3, utype);
                                        ps.executeUpdate();
                                        JOptionPane.showMessageDialog(null,"New User Created Successfully....");
                                        txtNUname.setText("");
                                        txtNUpwd.setText("");
                                        txtNUCpwd.setText("");
                                        txtNUtype.setSelectedIndex(0);
                                        txtNUname.requestFocus();
                                    } catch (NoSuchAlgorithmException | SQLException | HeadlessException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                            
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            }
            else{
                JOptionPane.showMessageDialog(null,"Database Does not Connected...");
                System.exit(0);
            }
                
        }
    }//GEN-LAST:event_NUaddbtnActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
                    
                    try {
                        login lform=new login();
                        lform.setVisible(true);
                        lform.pack();
                        lform.setLocationRelativeTo(null);
                        this.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                            
    }//GEN-LAST:event_btnCancel1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton NUaddbtn;
    public static javax.swing.JButton btnCancel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPasswordField txtNUCpwd;
    private javax.swing.JTextField txtNUname;
    public static javax.swing.JPasswordField txtNUpwd;
    public static javax.swing.JComboBox<String> txtNUtype;
    // End of variables declaration//GEN-END:variables
}
