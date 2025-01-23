package UI;

import DB.*;

import javax.swing.Box;
import UI.LoginForm;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MainUI extends javax.swing.JFrame {
    private HashMap user;
    public DefaultTableModel booksTableModel = new DefaultTableModel();
    public MainUI() {
        initComponents();
        this.setLocationRelativeTo(null); 
        search();
        
    }

    public MainUI(UserFetcher userFetcher) {
        initComponents();
        this.setLocationRelativeTo(null);
        search();
    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        registerBookButton = new javax.swing.JButton();
        manageMembersButton = new javax.swing.JButton();
        registerMemberButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        booksTable = new javax.swing.JTable();
        searchByNameLabel = new javax.swing.JLabel();
        searchByNameInput = new javax.swing.JTextField();
        searchByISBNLabel = new javax.swing.JLabel();
        searchByISBNInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        logoutButtom = new javax.swing.JMenuItem();
        exitButtom = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        registerBookButton.setBackground(new java.awt.Color(214, 220, 221));
        registerBookButton.setText("Register Book");
        registerBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBookButtonActionPerformed(evt);
            }
        });

        manageMembersButton.setBackground(new java.awt.Color(214, 220, 221));
        manageMembersButton.setText("Manage Members");
        manageMembersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageMembersButtonActionPerformed(evt);
            }
        });

        registerMemberButton1.setBackground(new java.awt.Color(214, 220, 221));
        registerMemberButton1.setText("Register Member");
        registerMemberButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerMemberButton1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/IMG/books.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(manageMembersButton, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(registerMemberButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerMemberButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageMembersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        booksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "Name", "Author", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(booksTable);

        searchByNameLabel.setText("Search book by name");

        searchByNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByNameInputActionPerformed(evt);
            }
        });

        searchByISBNLabel.setText("Search book by ISBN");

        searchByISBNInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByISBNInputActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Registration");
        jLabel1.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Books");
        jLabel2.setAlignmentY(0.0F);

        searchButton.setBackground(new java.awt.Color(214, 220, 221));
        searchButton.setText("Search");
        searchButton.setActionCommand("");
        searchButton.setAutoscrolls(true);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchByISBNLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchByNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchByNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                            .addComponent(searchByISBNInput))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(userNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchByNameLabel)
                            .addComponent(searchByNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchByISBNLabel)
                            .addComponent(searchByISBNInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenu1.setText("File");

        logoutButtom.setText("Logout");
        logoutButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtomActionPerformed(evt);
            }
        });
        jMenu1.add(logoutButtom);

        exitButtom.setText("Exit");
        exitButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtomActionPerformed(evt);
            }
        });
        jMenu1.add(exitButtom);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * Menu Logout function
     * @param evt
     */
    public void logoutButtomActionPerformed(java.awt.event.ActionEvent evt) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        this.dispose();
    }
    public void exitButtomActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    public void registerBookButtonActionPerformed(java.awt.event.ActionEvent evt) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.SetMode("new");
        bookDetails.setVisible(true);
        this.dispose();
    }
    public void searchByNameInputActionPerformed(java.awt.event.ActionEvent evt) {
        this.search();
    }
    public void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.search();
    }
    public void searchByISBNInputActionPerformed(java.awt.event.ActionEvent evt) {
        this.search();
    }
    public void manageMembersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ManageMembers manageMembers = new ManageMembers();
        manageMembers.setVisible(true);        
        this.dispose();
    }
    public void registerMemberButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        MemberDetails memberDetails = new MemberDetails();
        memberDetails.SetMode("new");
        memberDetails.setVisible(true);
        this.dispose();
    }
    public void setUser(String userName){
        Users users = new Users();
        List<HashMap> result = users.get("`user_name` = '"+userName+"'");
        this.user = result.getFirst();
        this.userNameLabel.setText(this.user.get("full_name").toString());
    }
    public void search(){
        String nameSearch = this.searchByNameInput.getText();
        String isbnSearch = this.searchByISBNInput.getText();
        nameSearch = nameSearch.replace("'","''");
        nameSearch = nameSearch.replace("\\","");
        isbnSearch = isbnSearch.replace("'","''");
        isbnSearch = isbnSearch.replace("\\","");
        Books books = new Books();
        List<HashMap> results = books.get("`name` LIKE '%"+nameSearch+"%' AND `isbn` LIKE '%"+isbnSearch+"%'");
        this.booksTableModel = new DefaultTableModel(
            new Object[][]{
            },
            new String [] {
                "ISBN", "Name", "Author", "State", "Action"
            });        
        this.booksTable.setModel(booksTableModel);
        for(HashMap result:results){  
            this.booksTableModel.insertRow(0,new Object[]{result.get("isbn"),result.get("name"),result.get("author"), result.get("state").equals(1)? "Available,"+result.get("id"): "Borrowed,"+result.get("id"),result.get("id")});
        }
        this.booksTable.getColumn("Action").setCellRenderer(new JTableButtonRenderer());
        this.booksTable.getColumn("Action").setCellEditor(new JTableButtonEditor(new JTextField(),this));
        this.booksTable.getColumn("State").setCellRenderer(new LendButtonRenderer());
        this.booksTable.getColumn("State").setCellEditor(new LendButtonEditor(new JTextField(),this));
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }
    public javax.swing.JTable booksTable;
    private javax.swing.JMenuItem exitButtom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem logoutButtom;
    private javax.swing.JButton manageMembersButton;
    private javax.swing.JButton registerBookButton;
    private javax.swing.JButton registerMemberButton1;
    private javax.swing.JButton searchButton;
    public javax.swing.JTextField searchByISBNInput;
    private javax.swing.JLabel searchByISBNLabel;
    public javax.swing.JTextField searchByNameInput;
    private javax.swing.JLabel searchByNameLabel;
    public javax.swing.JLabel userNameLabel;
    public static class LendButtonRenderer extends JButton implements TableCellRenderer {
        public LendButtonRenderer() {
            setOpaque(true);
        }
        @Override 
        public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row, int column) {
            String lbl = obj==null ? "":obj.toString();
            String [] cmd = lbl.split(",");
            if("Available".equals(cmd[0])){
                setText("Lend");
            }else{
                setText("Return");
            }
            return this;  
        }
    }
    public static class LendButtonEditor extends DefaultCellEditor  {
        protected JButton btn;
        private String lbl;
        private Boolean clicked;
        private JFrame srcFrame;
        public LendButtonEditor(JTextField txt, JFrame srcFrame){
            super(txt);
            this.srcFrame = srcFrame;
            btn = new JButton();
            btn.setOpaque(true);
            btn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  fireEditingStopped();
                }
            });
        }
        @Override
         public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col){
             lbl = obj==null ? "":obj.toString();
             btn.setText("Processing");
             clicked=true;
             return btn;
         }
        @Override
        public Object getCellEditorValue() {
           if(clicked){
               BookDetails bookDetails = new BookDetails();
               String [] cmd = lbl.split(",");
               if("Available".equals(cmd[0])){
                   LendBook lendBook = new LendBook();
                   lendBook.setBookId(Integer.parseInt(cmd[1]));                   
                   lendBook.setVisible(true);
                   this.srcFrame.dispose();
               }else{
                   int input = JOptionPane.showConfirmDialog(null, "Do you want mark this book as returned?");
                   if(input == 0){
                       LendFacade.returnBook(Integer.parseInt(cmd[1]));                       
                       JOptionPane.showMessageDialog(null, "Book returned.");
                       this.srcFrame.dispose();
                       MainUI mainUI = new MainUI();
                       mainUI.setVisible(true);
                   }
               } 
           }
           clicked = false;
           return new String(lbl);
        }
        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }    
    }

}
