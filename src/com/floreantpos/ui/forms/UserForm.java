/*
 * UserForm2.java
 *
 * Created on February 8, 2008, 6:08 PM
 */

package com.floreantpos.ui.forms;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.PosException;
import com.floreantpos.model.User;
import com.floreantpos.model.UserType;
import com.floreantpos.model.dao.UserDAO;
import com.floreantpos.model.dao.UserTypeDAO;
import com.floreantpos.model.util.IllegalModelStateException;
import com.floreantpos.swing.DoubleTextField;
import com.floreantpos.swing.FixedLengthDocument;
import com.floreantpos.swing.FixedLengthTextField;
import com.floreantpos.ui.BeanEditor;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.POSUtil;

/**
 * 
 * @author rodaya
 */
public class UserForm extends BeanEditor {

	/** Creates new form UserForm2 */
	public UserForm() {
		initComponents();

		UserTypeDAO dao = new UserTypeDAO();
		List<UserType> userTypes = dao.findAll();

		cbUserType.setModel(new DefaultComboBoxModel(userTypes.toArray()));

		chkDriver = new JCheckBox("Driver");
		add(chkDriver, "cell 1 9");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		tfPassword1 = new javax.swing.JPasswordField(new FixedLengthDocument(4), "", 10);
		tfPassword1.setColumns(16);
		tfPassword2 = new javax.swing.JPasswordField(new FixedLengthDocument(4), "", 10);
		tfPassword2.setColumns(16);
		tfId = new FixedLengthTextField();
		tfSsn = new FixedLengthTextField();
		tfSsn.setLength(30);
		tfSsn.setColumns(30);
		tfFirstName = new FixedLengthTextField();
		tfFirstName.setColumns(30);
		tfFirstName.setLength(30);
		tfLastName = new FixedLengthTextField();
		tfLastName.setLength(30);
		tfLastName.setColumns(30);
		jLabel5 = new javax.swing.JLabel();
		tfCostPerHour = new DoubleTextField();
		jLabel6 = new javax.swing.JLabel();
		cbUserType = new javax.swing.JComboBox();
		setLayout(new MigLayout("", "[134px][204px,grow]", "[19px][][19px][19px][19px][19px][19px][19px][24px][]"));

		jLabel1.setText("ID");
		add(jLabel1, "cell 0 0,alignx trailing,aligny center");

		lblPhone = new JLabel("Phone");
		add(lblPhone, "cell 0 1,alignx trailing");

		tfPhone = new FixedLengthTextField();
		tfPhone.setLength(20);
		tfPhone.setColumns(20);
		add(tfPhone, "cell 1 1,growx");

		jLabel2.setText("SSN");
		add(jLabel2, "cell 0 2,alignx trailing,aligny center");

		jLabel3.setText("First Name");
		add(jLabel3, "cell 0 3,alignx trailing,aligny center");

		jLabel4.setText("Last Name");
		add(jLabel4, "cell 0 4,alignx trailing,aligny center");

		jLabel9.setText("Secret Key");
		add(jLabel9, "cell 0 5,alignx trailing,aligny center");

		jLabel10.setText("Confirm Secret Key");
		add(jLabel10, "cell 0 6,alignx trailing,aligny center");
		add(tfPassword1, "cell 1 5,growx,aligny center");
		add(tfPassword2, "cell 1 6,growx,aligny center");
		add(tfId, "cell 1 0,growx,aligny center");
		add(tfSsn, "cell 1 2,aligny center");
		add(tfFirstName, "cell 1 3,growx,aligny center");
		add(tfLastName, "cell 1 4,growx,aligny center");

		jLabel5.setText("Cost Per Hour");
		add(jLabel5, "cell 0 7,alignx trailing,aligny center");
		add(tfCostPerHour, "cell 1 7,growx,aligny center");

		jLabel6.setText("User Type");
		add(jLabel6, "cell 0 8,alignx trailing,aligny center");

		cbUserType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manager", "Cashier", "Server" }));
		add(cbUserType, "cell 1 8,growx,aligny center");
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox cbUserType;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel9;
	private DoubleTextField tfCostPerHour;
	private FixedLengthTextField tfFirstName;
	private FixedLengthTextField tfId;
	private FixedLengthTextField tfLastName;
	private javax.swing.JPasswordField tfPassword1;
	private javax.swing.JPasswordField tfPassword2;
	private FixedLengthTextField tfSsn;

	// End of variables declaration//GEN-END:variables
	@Override
	public void dispose() {

	}

	@Override
	public String getDisplayText() {
		if (isEditMode())
			return "Edit User";

		return "Add new user";
	}

	private boolean editMode;
	private JLabel lblPhone;
	private FixedLengthTextField tfPhone;
	private JCheckBox chkDriver;

	@Override
	public boolean save() {
		try {
			updateModel();
		} catch (IllegalModelStateException e) {
			POSMessageDialog.showError(this, e.getMessage());
			return false;
		}

		User user = (User) getBean();
		UserDAO userDAO = UserDAO.getInstance();

		if (!editMode) {
			if (userDAO.isUserExist(user.getUserId())) {
				POSMessageDialog.showError(this, "User with ID: " + user.getUserId() + " already exists.");
				return false;
			}
		}

		try {
			userDAO.saveOrUpdate(user, editMode);
		} catch (PosException x) {
			POSMessageDialog.showError(this, x.getMessage(), x);
			x.printStackTrace();
			return false;
		} catch (Exception x) {
			POSMessageDialog.showError(this, "Could not save user", x);
			x.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	protected boolean updateModel() throws IllegalModelStateException {
		User user = null;
		if (!(getBean() instanceof User)) {
			user = new User();
		}
		else {
			user = (User) getBean();
		}

		int id = 1000;
		try {
			id = Integer.parseInt(tfId.getText());
		} catch (Exception x) {
			throw new IllegalModelStateException("ID is not valid");
		}

		String ssn = tfSsn.getText();
		String firstName = tfFirstName.getText();
		String lastName = tfLastName.getText();
		String secretKey1 = new String(tfPassword1.getPassword());
		String secretKey2 = new String(tfPassword2.getPassword());

		if (POSUtil.isBlankOrNull(firstName)) {
			throw new IllegalModelStateException("First name cannot be empty");
		}
		if (POSUtil.isBlankOrNull(lastName)) {
			throw new IllegalModelStateException("Last name cannot be empty");
		}
		if (POSUtil.isBlankOrNull(secretKey1)) {
			throw new IllegalModelStateException("Secret key cannot be empty");
		}
		if (POSUtil.isBlankOrNull(secretKey2)) {
			throw new IllegalModelStateException("Secret key cannot be empty");
		}
		if (!secretKey1.equals(secretKey2)) {
			throw new IllegalModelStateException("Secret key did not match");
		}

		if (!isEditMode()) {
			User userBySecretKey = UserDAO.getInstance().findUserBySecretKey(secretKey1);
			if (userBySecretKey != null) {
				throw new IllegalModelStateException("Secret key must be unique. An user with the secret key already exists.");
			}
		}

		double cost = 0;

		try {
			cost = Double.parseDouble(tfCostPerHour.getText());
		} catch (Exception x) {
			throw new IllegalModelStateException("Cost per hour for " + firstName + " " + lastName + " is not valid.");
		}

		user.setNewUserType((UserType) cbUserType.getSelectedItem());
		user.setCostPerHour(cost);

		user.setSsn(ssn);
		user.setUserId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhoneNo(tfPhone.getText());
		user.setPassword(secretKey1);
		user.setDriver(chkDriver.isSelected());

		setBean(user);
		return true;
	}

	@Override
	protected void updateView() {
		if (!(getBean() instanceof User)) {
			return;
		}
		User user = (User) getBean();
		setData(user);
	}

	private void setData(User data) {
		if (data.getUserId() != null) {
			tfId.setText(String.valueOf(data.getUserId()));
		}
		else {
			tfId.setText("");
		}
		if (data.getSsn() != null) {
			tfSsn.setText(data.getSsn());
		}
		else {
			tfSsn.setText("");
		}
		tfFirstName.setText(data.getFirstName());
		tfLastName.setText(data.getLastName());
		tfPassword1.setText(data.getPassword());
		tfPassword2.setText(data.getPassword());
		tfPhone.setText(data.getPhoneNo());
		cbUserType.setSelectedItem(data.getNewUserType());
		
		Double costPerHour = data.getCostPerHour();
		if(costPerHour == null) {
			costPerHour = 0.0;
		}
		
		tfCostPerHour.setText(String.valueOf(costPerHour));
		chkDriver.setSelected(data.isDriver());
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		if (editMode) {
			tfId.setEditable(false);
		}
		else {
			tfId.setEditable(true);
		}
	}

	public void setId(Integer id) {
		if (id != null) {
			tfId.setText(String.valueOf(id.intValue()));
		}
	}
}