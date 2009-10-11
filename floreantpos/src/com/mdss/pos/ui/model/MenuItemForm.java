/*
 * FoodItemEditor.java
 *
 * Created on August 2, 2006, 10:34 PM
 */

package com.mdss.pos.ui.model;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.mdss.pos.model.MenuGroup;
import com.mdss.pos.model.MenuItem;
import com.mdss.pos.model.MenuItemModifierGroup;
import com.mdss.pos.model.MenuItemShift;
import com.mdss.pos.model.Tax;
import com.mdss.pos.model.dao.MenuGroupDAO;
import com.mdss.pos.model.dao.MenuItemDAO;
import com.mdss.pos.model.dao.TaxDAO;
import com.mdss.pos.swing.ComboBoxModel;
import com.mdss.pos.swing.DoubleDocument;
import com.mdss.pos.swing.FixedLengthDocument;
import com.mdss.pos.swing.MessageDialog;
import com.mdss.pos.ui.BeanEditor;
import com.mdss.pos.ui.dialog.BeanEditorDialog;
import com.mdss.pos.ui.dialog.ConfirmDeleteDialog;
import com.mdss.pos.util.POSUtil;
import com.mdss.pos.util.ShiftUtil;

/**
 *
 * @author  MShahriar
 */
public class MenuItemForm extends BeanEditor implements ActionListener {
	ShiftTableModel shiftTableModel;
	
	/** Creates new form FoodItemEditor */
	public MenuItemForm() throws Exception {
		this(new MenuItem());

        tfDiscountRate.setDocument(new DoubleDocument());
	}

	public MenuItemForm(MenuItem menuItem) throws Exception {
		initComponents();
		
		tfName.setDocument(new FixedLengthDocument(30));
		
		MenuGroupDAO foodGroupDAO = new MenuGroupDAO();
		List<MenuGroup> foodGroups = foodGroupDAO.findAll();
		cbGroup.setModel(new ComboBoxModel(foodGroups));
		
		TaxDAO taxDAO = new TaxDAO();
		List<Tax> taxes = taxDAO.findAll();
		cbTax.setModel(new ComboBoxModel(taxes));
		
		menuItemModifierGroups = menuItem.getMenuItemModiferGroups();
		menuItemMGListModel = new MenuItemMGListModel();
		tableTicketItemModifierGroups.setModel(menuItemMGListModel);
		shiftTable.setModel(shiftTableModel = new ShiftTableModel(menuItem.getShifts()));
		
		btnNewModifierGroup.addActionListener(this);
		btnEditModifierGroup.addActionListener(this);
		btnDeleteModifierGroup.addActionListener(this);
		btnAddShift.addActionListener(this);
		btnDeleteShift.addActionListener(this);
		
		setBean(menuItem);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new com.mdss.pos.swing.FixedLengthTextField();
        jLabel4 = new javax.swing.JLabel();
        cbGroup = new javax.swing.JComboBox();
        btnNewGroup = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cbTax = new javax.swing.JComboBox();
        btnNewTax = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfDiscountRate = new javax.swing.JTextField();
        chkVisible = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnNewModifierGroup = new javax.swing.JButton();
        btnDeleteModifierGroup = new javax.swing.JButton();
        btnEditModifierGroup = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTicketItemModifierGroups = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnDeleteShift = new javax.swing.JButton();
        btnAddShift = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        shiftTable = new javax.swing.JTable();

        jLabel1.setText("Name:");

        jLabel4.setText("Group:");

        btnNewGroup.setText("...");
        btnNewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doCreateNewGroup(evt);
            }
        });

        jLabel3.setText("Price:");

        tfPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Tax:");

        btnNewTax.setText("...");
        btnNewTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTaxdoCreateNewTax(evt);
            }
        });

        jLabel2.setText("Discount Rate:");

        jLabel5.setText("%");

        chkVisible.setText("Visible");
        chkVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chkVisible.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel6)
                            .add(jLabel2)
                            .add(jLabel1))
                        .add(17, 17, 17)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tfName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(cbGroup, 0, 312, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnNewGroup))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(tfDiscountRate)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, cbTax, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tfPrice, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .add(chkVisible))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(btnNewTax)
                                    .add(jLabel5))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(tfName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(btnNewGroup)
                    .add(cbGroup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(tfPrice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel5)
                    .add(tfDiscountRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cbTax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnNewTax)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chkVisible)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel1);

        btnNewModifierGroup.setText("Add");
        btnNewModifierGroup.setActionCommand("AddModifierGroup");
        btnNewModifierGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewModifierGroupActionPerformed(evt);
            }
        });

        btnDeleteModifierGroup.setText("Delete");
        btnDeleteModifierGroup.setActionCommand("DeleteModifierGroup");

        btnEditModifierGroup.setText("Edit");
        btnEditModifierGroup.setActionCommand("EditModifierGroup");

        tableTicketItemModifierGroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableTicketItemModifierGroups);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .add(btnNewModifierGroup)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnEditModifierGroup)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnDeleteModifierGroup)
                .addContainerGap())
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 377, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnDeleteModifierGroup)
                    .add(btnEditModifierGroup)
                    .add(btnNewModifierGroup))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Modifier Groups", jPanel2);

        btnDeleteShift.setText("Delete Shift");

        btnAddShift.setText("Add Shift");

        shiftTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(shiftTable);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 387, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(btnAddShift)
                        .add(5, 5, 5)
                        .add(btnDeleteShift)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 281, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnAddShift)
                    .add(btnDeleteShift))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Shifts", jPanel3);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewTaxdoCreateNewTax(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTaxdoCreateNewTax
    }//GEN-LAST:event_btnNewTaxdoCreateNewTax

    private void btnNewModifierGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewModifierGroupActionPerformed
    	
    }//GEN-LAST:event_btnNewModifierGroupActionPerformed

	private void doCreateNewGroup(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doCreateNewGroup
		MenuGroupForm editor = new MenuGroupForm();
		BeanEditorDialog dialog = new BeanEditorDialog(editor, getParentFrame(), true);
		dialog.open();
		if (!dialog.isCanceled()) {
			MenuGroup foodGroup = (MenuGroup) editor.getBean();
			ComboBoxModel model = (ComboBoxModel) cbGroup.getModel();
			model.addElement(foodGroup);
			model.setSelectedItem(foodGroup);
		}
	}//GEN-LAST:event_doCreateNewGroup

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddShift;
    private javax.swing.JButton btnDeleteModifierGroup;
    private javax.swing.JButton btnDeleteShift;
    private javax.swing.JButton btnEditModifierGroup;
    private javax.swing.JButton btnNewGroup;
    private javax.swing.JButton btnNewModifierGroup;
    private javax.swing.JButton btnNewTax;
    private javax.swing.JComboBox cbGroup;
    private javax.swing.JComboBox cbTax;
    private javax.swing.JCheckBox chkVisible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable shiftTable;
    private javax.swing.JTable tableTicketItemModifierGroups;
    private javax.swing.JTextField tfDiscountRate;
    private com.mdss.pos.swing.FixedLengthTextField tfName;
    private javax.swing.JFormattedTextField tfPrice;
    // End of variables declaration//GEN-END:variables
    private List<MenuItemModifierGroup> menuItemModifierGroups;
	private MenuItemMGListModel menuItemMGListModel;
    
    private void addMenuItemModifierGroup() {
    	try {
			MenuItemModifierGroupForm form = new MenuItemModifierGroupForm();
			BeanEditorDialog dialog = new BeanEditorDialog(form, getParentFrame(), true);
			dialog.open();
			if (!dialog.isCanceled()) {
				MenuItemModifierGroup modifier = (MenuItemModifierGroup) form.getBean();
				menuItemMGListModel.add(modifier);
			}
		} catch (Exception x) {
			MessageDialog.showError("An error has occured, please restart the application", x);
		}
    }
    
    private void editMenuItemModifierGroup() {
    	try {
    		int index = tableTicketItemModifierGroups.getSelectedRow();
    		if(index < 0) return;
    		
    		MenuItemModifierGroup menuItemModifierGroup = menuItemMGListModel.get(index);
    		
    		MenuItemModifierGroupForm form = new MenuItemModifierGroupForm(menuItemModifierGroup);
    		BeanEditorDialog dialog = new BeanEditorDialog(form, getParentFrame(), true);
    		dialog.open();
    		if (!dialog.isCanceled()) {
    			menuItemMGListModel.fireTableDataChanged();
    		}
    	} catch (Exception x) {
    		MessageDialog.showError("An error has occured, please restart the application", x);
    	}
    }
    private void deleteMenuItemModifierGroup() {
    	try {
    		int index = tableTicketItemModifierGroups.getSelectedRow();
    		if(index < 0) return;
    		
    		if(ConfirmDeleteDialog.showMessage(this, "Delete sure?", "Confirm") == ConfirmDeleteDialog.YES){
    			menuItemMGListModel.remove(index);
    		}
    	} catch (Exception x) {
    		MessageDialog.showError("An error has occured, please restart the application", x);
    	}
    }
    
	@Override
	public boolean save() {
		try {
			if(!updateModel()) return false;
			
			MenuItem menuItem = (MenuItem) getBean();
			MenuItemDAO menuItemDAO = new MenuItemDAO();
			menuItemDAO.saveOrUpdate(menuItem);
		} catch (Exception e) {
			MessageDialog.showError("An error occured while saving food item...", e);
			return false;
		}
		return true;
	}

	@Override
	public void dispose() {
	}

	@Override
	protected void updateView() {
		MenuItem menuItem = (MenuItem) getBean();
		
		if(menuItem.getId() != null && !Hibernate.isInitialized(menuItem.getMenuItemModiferGroups())) {
			//initialize food item modifer groups.
			MenuItemDAO dao = new MenuItemDAO();
			Session session = dao.getSession();
			menuItem = (MenuItem) session.merge(menuItem);
			Hibernate.initialize(menuItem.getMenuItemModiferGroups());
			session.close();
		}
		
		tfName.setText(menuItem.getName());
		tfPrice.setValue(Double.valueOf(menuItem.getPrice()));
		tfDiscountRate.setText(String.valueOf(menuItem.getDiscountRate()));
		chkVisible.setSelected(menuItem.isVisible());
		
		if(menuItem.getId() == null) {
			cbGroup.setSelectedIndex(0);
			cbTax.setSelectedIndex(0);
		}
		else {
			cbGroup.setSelectedItem(menuItem.getParent());
			cbTax.setSelectedItem(menuItem.getTax());
		}
	}

	@Override
	protected boolean updateModel() {
		String itemName = tfName.getText();
		if(POSUtil.isBlankOrNull(itemName)) {
			MessageDialog.showError("Name is required");
			return false;
		}
		
		MenuItem menuItem = (MenuItem) getBean();
		menuItem.setName(itemName);
		menuItem.setParent((MenuGroup) cbGroup.getSelectedItem());
		menuItem.setPrice(Double.valueOf(tfPrice.getValue().toString()).doubleValue());
		menuItem.setTax((Tax) cbTax.getSelectedItem());
		menuItem.setVisible(chkVisible.isSelected());
		
        try {
            menuItem.setDiscountRate(Double.parseDouble(tfDiscountRate.getText()));
        } catch (Exception x){}
		menuItem.setMenuItemModiferGroups(menuItemModifierGroups);
		menuItem.setShifts(shiftTableModel.getShifts());
		return true;
	}
	
	public String getDisplayText() {
    	MenuItem foodItem = (MenuItem) getBean();
    	if(foodItem.getId() == null) {
    		return "New menu item";
    	}
    	return "Edit menu item";
    }
	
	class MenuItemMGListModel extends AbstractTableModel {
		String[] cn = {"Group Name", "Min Quantity", "Max Quantity"};
		
		MenuItemMGListModel(){
		}
		
		public MenuItemModifierGroup get(int index) {
			return menuItemModifierGroups.get(index);
		}
		
		public void add(MenuItemModifierGroup group) {
			if(menuItemModifierGroups == null) {
				menuItemModifierGroups = new ArrayList<MenuItemModifierGroup>();
			}
			menuItemModifierGroups.add(group);
			fireTableDataChanged();
		}
		
		public void remove(int index) {
			if(menuItemModifierGroups == null) {
				return;
			}
			menuItemModifierGroups.remove(index);
			fireTableDataChanged();
		}
		
		public void remove(MenuItemModifierGroup group) {
			if(menuItemModifierGroups == null) {
				return;
			}
			menuItemModifierGroups.remove(group);
			fireTableDataChanged();
		}

		public int getRowCount() {
			if(menuItemModifierGroups == null) return 0;
			
			return menuItemModifierGroups.size();

		}

		public int getColumnCount() {
			return cn.length;
		}
		
		@Override
		public String getColumnName(int column) {
			return cn[column];
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			MenuItemModifierGroup menuItemModifierGroup = menuItemModifierGroups.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return menuItemModifierGroup.getModifierGroup().getName();
					
				case 1:
					return Integer.valueOf(menuItemModifierGroup.getMinQuantity());
					
				case 2:
					return Integer.valueOf(menuItemModifierGroup.getMaxQuantity());
			}
			return null;
		}
	}
	
	class ShiftTableModel extends AbstractTableModel {
		List<MenuItemShift> shifts;
		String[] cn = {"Start Time", "End Time", "Price"};
		Calendar calendar = Calendar.getInstance();
		
		ShiftTableModel(List<MenuItemShift> shifts){
			if(shifts == null) {
				this.shifts = new ArrayList<MenuItemShift>();
			}
			else {
				this.shifts = new ArrayList<MenuItemShift>(shifts);
			}
		}
		
		public MenuItemShift get(int index) {
			return shifts.get(index);
		}
		
		public void add(MenuItemShift group) {
			if(shifts == null) {
				shifts = new ArrayList<MenuItemShift>();
			}
			shifts.add(group);
			fireTableDataChanged();
		}
		
		public void remove(int index) {
			if(shifts == null) {
				return;
			}
			shifts.remove(index);
			fireTableDataChanged();
		}
		
		public void remove(MenuItemShift group) {
			if(shifts == null) {
				return;
			}
			shifts.remove(group);
			fireTableDataChanged();
		}

		public int getRowCount() {
			if(shifts == null) return 0;
			
			return shifts.size();

		}

		public int getColumnCount() {
			return cn.length;
		}
		
		@Override
		public String getColumnName(int column) {
			return cn[column];
		}
		
		public List<MenuItemShift> getShifts() {
			return shifts;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			MenuItemShift shift = shifts.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return ShiftUtil.buildShiftTimeRepresentation(shift.getShift().getStartTime());
					
				case 1:
					return ShiftUtil.buildShiftTimeRepresentation(shift.getShift().getEndTime());
					
				case 2:
					return String.valueOf(shift.getShiftPrice());
			}
			return null;
		}
	}
	
	private void addShift() {
		//TODO: ???
		MenuItemShiftDialog dialog = new MenuItemShiftDialog((Dialog) this.getTopLevelAncestor());
		dialog.setSize(350, 220);
        dialog.open();
        
        if(!dialog.isCanceled()) {
        	MenuItemShift menuItemShift = dialog.getMenuItemShift();
        	shiftTableModel.add(menuItemShift);
        }
	}
	
	private void deleteShift() {
		int selectedRow = shiftTable.getSelectedRow();
		if(selectedRow >= 0) {
			shiftTableModel.remove(selectedRow);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("AddModifierGroup")) {
			addMenuItemModifierGroup();
		}
		else if(actionCommand.equals("EditModifierGroup")) {
			editMenuItemModifierGroup();
		}
		else if(actionCommand.equals("DeleteModifierGroup")) {
			deleteMenuItemModifierGroup();
		}
		else if(actionCommand.equals("Add Shift")) {
			addShift();
		}
		else if(actionCommand.equals("Delete Shift")) {
			deleteShift();
		}
	}
}
