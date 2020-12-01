/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module4_assessment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author hp
 */
public class server extends javax.swing.JFrame {

    /**
     * Creates new form server
     */
    ServerSocket ab;
    HashMap Clientc = new HashMap();
    
    public server() {
        try{
            
    
        initComponents();
        ab = new ServerSocket(2789);
        this.status.setText("Server Started.");
        new ClientAccept().start();
        }
     catch(Exception ex){
     ex.printStackTrace();
     }
        
    }
    class ClientAccept extends Thread {
        public void run(){
            while(true){
              try{  
                  Socket a = ab.accept();
                  String i =new DataInputStream(a.getInputStream()).readUTF();
                  if(Clientc.containsKey(i)){
                      DataOutputStream out = new DataOutputStream(a.getOutputStream());
                      out.writeUTF("YOU ARE ALREADY REGISTERED!");
                  }else{
                      Clientc.put(i, a);
                      msgtext.append(i+" Joined !\n");
                       DataOutputStream out = new DataOutputStream(a.getOutputStream());
                      out.writeUTF("");
                      new Read (a,i).start();
                      new PrepareClientList ().start();
                  }
                  
            }catch(Exception ex)
            {
                  ex.printStackTrace();
                    }
    }
    }
} 
    class Read extends Thread{
Socket a;
String ID;
        private Read(Socket a, String ID) {
            this.a=a;
            this.ID=ID;
           
        }
        public void run(){
            while(!Clientc.isEmpty()){
                try{
                   String i = new DataInputStream(a.getInputStream()).readUTF();
                   if(i.equals("gdshfjsdhfxty")){
                       Clientc.remove(ID);
                       msgtext.append(ID+": removed! \n");
                       new PrepareClientList().start();
                       
                       Set yu=Clientc.keySet();
                       Iterator tor = yu.iterator();
                       while (tor.hasNext()){
                          String key=(String)tor.next();
                          if(!key.equalsIgnoreCase(ID)){
                              try{
                                new DataOutputStream(((Socket)Clientc.get(key)).getOutputStream()).writeUTF(ID);
                              }catch(Exception ex){
                                  Clientc.remove(key);
                                  msgtext.append(key+": removed!");
                                  new PrepareClientList().start();
                              }
                          }
                       }
                   
                   }
                   else if(i.contains("hdskjhfkjc365327*^*&^&%"))  {
                       i=i.substring(20);
                       StringTokenizer st = new StringTokenizer(i,":");
                       String id= st.nextToken();
                       i=st.nextToken();
                       try{
                        new DataOutputStream (((Socket)Clientc.get(id)).getOutputStream()).writeUTF("< "+ID+" to"+id+" > "+i);
                       }catch(Exception ex){
                           Clientc.remove(id);
                           msgtext.append(id+": removed!");
                           new PrepareClientList().start();
                       }
                   }
                   else{
                       Set k=Clientc.keySet();
                       Iterator tor = k.iterator();
                       while(tor.hasNext()){
                           String key=(String)tor.next();
                          if(!key.equalsIgnoreCase(ID)){
                               try{
                                new DataOutputStream(((Socket)Clientc.get(key)).getOutputStream()).writeUTF("< "+ID+" to All > "+i);
                              }catch(Exception ex){
                                  Clientc.remove(key);
                                  msgtext.append(key+": removed!");
                                  new PrepareClientList().start();
                          }
                       }
                   }
                        
                        
                   }  
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            
        }
        
    }
    class PrepareClientList extends Thread {
        public void run (){
            try{      
            String id ="";
            Set ke=Clientc.keySet();
               Iterator tor = ke.iterator();
                       while(tor.hasNext()){
                           String k=(String)tor.next(); 
                           id+=ke+",";
                       }
                       if(id.length()!=0)
                           id=id.substring(0,id.length()-1);
                       
                       tor=ke.iterator();
                       while(tor.hasNext()){
                           String key=(String)tor.next();
                           try{
                  new DataOutputStream( ((Socket)Clientc.get(key)).getOutputStream()).writeUTF(":;.,/="+id);
                           }catch (Exception ex){
                               Clientc.remove(key);
                               msgtext.append(key+": remomove!");
                           }
                       }
            } catch (Exception ex)
                     {
                    ex.printStackTrace();
                    }
            }
        }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgtext = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyServer");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(153, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("STATUS");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 4, true));

        status.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        status.setText(".....");
        status.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));

        msgtext.setColumns(20);
        msgtext.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        msgtext.setRows(5);
        msgtext.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jScrollPane1.setViewportView(msgtext);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgtext;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
