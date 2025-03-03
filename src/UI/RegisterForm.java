/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;
import javax.swing.JOptionPane;
import DB.Users;
import java.math.BigInteger;
import java.util.List;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shashika
 */
public class RegisterForm extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    public RegisterForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        picLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        fullNameInput = new javax.swing.JTextField();
        nicLabel = new javax.swing.JLabel();
        nicInput = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressInput = new javax.swing.JTextArea();
        userNameLabel = new javax.swing.JLabel();
        userNameInput = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        repeatPassLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        backToLoginButton = new javax.swing.JButton();
        repeatPasswordInput = new javax.swing.JPasswordField();
        passwordInput = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        terms = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Registration");
        jLabel1.setAlignmentY(0.0F);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        titleLabel.setForeground(new java.awt.Color(0, 51, 51));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Register new user for Books for future library");

        picLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        picLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/IMG/register_book.png"))); // NOI18N
        picLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        nameLabel.setForeground(new java.awt.Color(0, 51, 51));
        nameLabel.setLabelFor(fullNameInput);
        nameLabel.setText("Full Name*:");

        fullNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameInputActionPerformed(evt);
            }
        });

        nicLabel.setForeground(new java.awt.Color(0, 51, 51));
        nicLabel.setLabelFor(fullNameInput);
        nicLabel.setText("NIC number: ");

        nicInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicInputActionPerformed(evt);
            }
        });

        emailLabel.setForeground(new java.awt.Color(0, 51, 51));
        emailLabel.setLabelFor(fullNameInput);
        emailLabel.setText("e-mail address:");

        emailInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailInputActionPerformed(evt);
            }
        });

        addressLabel.setForeground(new java.awt.Color(0, 51, 51));
        addressLabel.setLabelFor(fullNameInput);
        addressLabel.setText("Home addrsss:");

        addressInput.setColumns(20);
        addressInput.setRows(5);
        jScrollPane1.setViewportView(addressInput);

        userNameLabel.setForeground(new java.awt.Color(0, 51, 51));
        userNameLabel.setLabelFor(userNameInput);
        userNameLabel.setText("User Name*:");

        userNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameInputActionPerformed(evt);
            }
        });

        passwordLabel.setForeground(new java.awt.Color(0, 51, 51));
        passwordLabel.setLabelFor(passwordInput);
        passwordLabel.setText("Password*:");

        repeatPassLabel.setForeground(new java.awt.Color(0, 51, 51));
        repeatPassLabel.setLabelFor(repeatPasswordInput);
        repeatPassLabel.setText("Repeat Password*:");

        registerButton.setBackground(new java.awt.Color(214, 220, 221));
        registerButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registerButton.setForeground(new java.awt.Color(0, 102, 102));
        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        backToLoginButton.setBackground(new java.awt.Color(221, 220, 214));
        backToLoginButton.setForeground(new java.awt.Color(153, 51, 0));
        backToLoginButton.setText("Back to login");
        backToLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToLoginButtonActionPerformed(evt);
            }
        });

        repeatPasswordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatPasswordInputActionPerformed(evt);
            }
        });

        passwordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordInputActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Terms and conditions"));

        terms.setText("I accept the terms and conditions.");
        terms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termsActionPerformed(evt);
            }
        });

        jLabel2.setText("( * ) -  Required fields");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(terms, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(terms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(11, 11, 11)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fullNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                            .addComponent(nicInput)
                                            .addComponent(emailInput)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(picLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(repeatPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(repeatPasswordInput))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backToLoginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(fullNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nicLabel)
                            .addComponent(nicInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(addressLabel)
                                .addGap(27, 27, 27))))
                    .addComponent(picLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userNameLabel)
                            .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(repeatPassLabel)
                            .addComponent(repeatPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(registerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backToLoginButton)
                .addGap(208, 208, 208))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void fullNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameInputActionPerformed
        String fullName = fullNameInput.getText().trim();

        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Full name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fullNameInputActionPerformed

    public void userNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameInputActionPerformed
        String userName = userNameInput.getText().trim();

        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (userName.length() < 5) {
            JOptionPane.showMessageDialog(this, "Username must be at least 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_userNameInputActionPerformed


    public void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String fullName = this.fullNameInput.getText();
        String userName = this.userNameInput.getText();
        String password = this.passwordInput.getText();
        String nic = this.nicInput.getText();
        String address = this.addressInput.getText();
        String email = this.emailInput.getText();
        
        //validate password length
        if(password.length()<4){
            JOptionPane.showMessageDialog(null, "Password needs to be longer than 4 characters","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        // validate repeat password
        if(!password.equals(this.repeatPasswordInput.getText())){
            JOptionPane.showMessageDialog(null, "Password and password repeat does not match","Error",JOptionPane.ERROR_MESSAGE);
            return;
            
        }
        // validate name
        if(fullName.length() ==0 ){
            JOptionPane.showMessageDialog(null, "Full Name can not be empty","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //validate user name
        if(userName.length()==0){
            JOptionPane.showMessageDialog(null, "User Name can not be empty","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }  
        Users users = new Users();
                
        // check user name availability 
        List results = users.get("`user_name` = '"+userName+"'");
        if(results.size()>0){
            JOptionPane.showMessageDialog(null, "User Name already exists.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        //terms and conditions test
        if(!this.terms.isSelected()){
            JOptionPane.showMessageDialog(null, "You need to accept terms and conditions in order to register");
            return;
        }
        
        //hashing password
        String hashtext = users.passwordHash(password);
        
        //store user in database         
        Map data = new HashMap<String, String>();
        data.put("full_name",fullName);
        data.put("user_name",userName);
        data.put("password",hashtext);
        data.put("role","user");
        data.put("nic",nic);
        data.put("mail",email);
        data.put("address",address);        
        users.insert(data);
        
//        return;
        JOptionPane.showMessageDialog(null, "Registration Successful. Log in to continue.");
        LoginForm welcomeForm = new LoginForm();
        welcomeForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerButtonActionPerformed

    public void backToLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToLoginButtonActionPerformed
        LoginForm welcomeForm = new LoginForm();
        welcomeForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToLoginButtonActionPerformed

    private void repeatPasswordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatPasswordInputActionPerformed
        String repeatPassword = repeatPasswordInput.getText();
        String password = passwordInput.getText();

        if (!repeatPassword.equals(password)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_repeatPasswordInputActionPerformed

    private void passwordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordInputActionPerformed
        String password = passwordInput.getText();

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_passwordInputActionPerformed

    private void nicInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicInputActionPerformed
        String nic = nicInput.getText();

        if (!nic.matches("[0-9]{12}")) {
            JOptionPane.showMessageDialog(this, "NIC must be 12 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_nicInputActionPerformed

    private void emailInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailInputActionPerformed
        String email = emailInput.getText();

        if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_emailInputActionPerformed

    private void termsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termsActionPerformed
        if (terms.isSelected()) {
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
        }
    }//GEN-LAST:event_termsActionPerformed


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
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea addressInput;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton backToLoginButton;
    public javax.swing.JTextField emailInput;
    private javax.swing.JLabel emailLabel;
    public javax.swing.JTextField fullNameInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    public javax.swing.JTextField nicInput;
    private javax.swing.JLabel nicLabel;
    public javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel repeatPassLabel;
    public javax.swing.JPasswordField repeatPasswordInput;
    public javax.swing.JCheckBox terms;
    private javax.swing.JLabel titleLabel;
    public javax.swing.JTextField userNameInput;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
