package Orders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.java.swing.plaf.windows.*;

public class OrderManager extends JFrame {

    public static final String newline = "\n";
    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";

    public static final String CHANGE_ORDER = "Update/Delete Order";

    public static final String EXIT = "Exit";
    public static final String CLEAR = "Clear";
    
    public static final String EMPTY_ORDER = "";
    public static final String CA_ORDER = "California Order";
    public static final String COL_ORDER = "Colombian Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";

    private JComboBox cmbOrderType;
    private JTextField txtOrderAmount, txtId;
    private JLabel lblOrderType, lblOrderAmount;

    private JLabel lblTotal, lblTotalValue;

    private JLabel lblId;

    private OrderVisitor objVisitor;

    private JTextArea taOrdenes;

    private JPanel paneldinamico;
    //ManyOrders ordenes;

    public OrderManager() {
        super("Visitor Pattern - Example");

        //Create the visitor instance
        objVisitor = new OrderVisitor();

        cmbOrderType = new JComboBox();
        cmbOrderType.addItem(OrderManager.EMPTY_ORDER);
        cmbOrderType.addItem(OrderManager.CA_ORDER);
        cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
        cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
        cmbOrderType.addItem(OrderManager.COL_ORDER);

        ComboBoxHandler cbhandler2 = new ComboBoxHandler(this);
        cmbOrderType.addItemListener(cbhandler2);

        taOrdenes = new JTextArea(15, 27);
        taOrdenes.setEditable(false);
        //DefaultCaret caret = (DefaultCaret)taOrdenes.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);    
        JScrollPane scrollPane = new JScrollPane(taOrdenes);
        //scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtOrderAmount = new JTextField(10);
        txtId = new JTextField(10);

        lblId = new JLabel("Order Id:");
        lblOrderType = new JLabel("Order Type:");
        lblOrderAmount = new JLabel("Order Amount:");
        paneldinamico = new JPanel();
        lblTotal = new JLabel("Result:");
        lblTotalValue = new JLabel("Click Create or GetTotal Button");

        //Create the open button
        JButton getTotalButton = new JButton(OrderManager.GET_TOTAL);
        JButton changeOrderButton = new JButton(OrderManager.CHANGE_ORDER);
        getTotalButton.setMnemonic(KeyEvent.VK_G);
        JButton createOrderButton = new JButton(OrderManager.CREATE_ORDER);
        getTotalButton.setMnemonic(KeyEvent.VK_C);
        JButton exitButton = new JButton(OrderManager.EXIT);
        exitButton.setMnemonic(KeyEvent.VK_X);
        ButtonHandler objButtonHandler = new ButtonHandler(this);
        JButton clearButton = new JButton(OrderManager.CLEAR);

        getTotalButton.addActionListener(objButtonHandler);
        createOrderButton.addActionListener(objButtonHandler);
        changeOrderButton.addActionListener(objButtonHandler);
        exitButton.addActionListener(new ButtonHandler());
        clearButton.addActionListener(new ButtonHandler(this));

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();

        JPanel panel = new JPanel();
        GridBagLayout gridbag2 = new GridBagLayout();
        panel.setLayout(gridbag2);
        GridBagConstraints gbc2 = new GridBagConstraints();
        panel.add(getTotalButton);
        panel.add(createOrderButton);
        //panel.add(changeOrderButton);
        panel.add(clearButton);
        panel.add(exitButton);
        
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gridbag2.setConstraints(createOrderButton, gbc2);
        
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gridbag2.setConstraints(getTotalButton, gbc2);
        
        gbc2.gridx = 3;
        gbc2.gridy = 0;
        gridbag2.setConstraints(exitButton, gbc2);
        
        gbc2.gridx = 4;
        gbc2.gridy = 0;
        gridbag2.setConstraints(clearButton, gbc2);

        //****************************************************
        GridBagLayout gridbag = new GridBagLayout();
        buttonPanel.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        buttonPanel.add(lblOrderType);
        buttonPanel.add(cmbOrderType);
        buttonPanel.add(lblOrderAmount);
        buttonPanel.add(txtOrderAmount);
        buttonPanel.add(paneldinamico);
        buttonPanel.add(txtId);
        buttonPanel.add(changeOrderButton);
        buttonPanel.add(lblId);
        buttonPanel.add(lblTotal);
        buttonPanel.add(lblTotalValue);
        buttonPanel.add(scrollPane);       

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblOrderType, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(cmbOrderType, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridbag.setConstraints(lblOrderAmount, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(txtOrderAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gridbag.setConstraints(paneldinamico, gbc);

        gbc.anchor = GridBagConstraints.WEST ;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gridbag.setConstraints(lblId, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 7;
        gridbag.setConstraints(txtId, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gridbag.setConstraints(changeOrderButton, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 10;
        gridbag.setConstraints(lblTotal, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gridbag.setConstraints(lblTotalValue, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gridbag.setConstraints(scrollPane, gbc);

        //****************************************************
        //Add the buttons and the log to the frame
        Container contentPane = getContentPane();//

        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
            SwingUtilities.updateComponentTreeUI(
                    OrderManager.this);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public JTextArea getTaOrdenes() {
        return taOrdenes;
    }

    public void setOrdenes(String ordenes) {
        taOrdenes.setText(ordenes);
    }

    public void clear() {
        System.out.println("antes de borrar");
        txtOrderAmount.setText("");
        txtId.setText("");
        paneldinamico.removeAll();
        paneldinamico.validate();
        validate();
    }

    public void MostarNuevaUI(JPanel panel) {
        paneldinamico.removeAll();
        paneldinamico.add(panel);
        paneldinamico.validate();
        validate();
    }

    public static void main(String[] args) {
        JFrame frame = new OrderManager();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);}}
        );
        
        frame.setSize(400, 520);
        frame.setVisible(true);
    }

    public void setTotalValue(String msg) {
        lblTotalValue.setText(msg);
    }

    public OrderVisitor getOrderVisitor() {
        return objVisitor;
    }

    public String getOrderType() {
        return (String) cmbOrderType.getSelectedItem();
    }

    public String getOrderAmount() {
        return txtOrderAmount.getText();
    }

    public String getId() {
        return txtId.getText();
    }
}