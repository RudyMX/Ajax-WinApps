/*
 * CashDropDialog.java
 *
 * Created on September 2, 2006, 12:22 AM
 */

package com.mdss.pos.ui.dialog;

import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.mdss.pos.main.Application;
import com.mdss.pos.model.CashDropTransaction;
import com.mdss.pos.model.Terminal;
import com.mdss.pos.model.dao.CashDropTransactionDAO;

/**
 *
 * @author  MShahriar
 */
public class CashDropDialog extends POSDialog {
	private List<CashDropTransaction> cashDropList;
	private DefaultListSelectionModel selectionModel;
	private CashDropTableModel tableModel;
	private String currencySymbol;

	/** Creates new form CashDropDialog */
	public CashDropDialog(Frame parent) {
		super(parent, true);
		initComponents();

		terminal = Application.getInstance().getTerminal();
		currencySymbol = Application.getCurrencySymbol();

		lblActiveCashDrop.setText("");
		TitledBorder titledBorder = new TitledBorder("ACTIVE CASH DROPS FOR" + terminal.getName());
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		midPanel.setBorder(titledBorder);

		selectionModel = new DefaultListSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCashDrops.setSelectionModel(selectionModel);

		tableCashDrops.setTableHeader(null);
		tableCashDrops.setDefaultRenderer(Object.class, new TableRenderer());
		tableModel = new CashDropTableModel();
		tableCashDrops.setModel(tableModel);
		
		setTitle("Drawer Bleed");
	}

	public void initDate() throws Exception {
		CashDropTransactionDAO dao = new CashDropTransactionDAO();

		cashDropList = dao.findUnsettled(terminal);
		tableModel.fireTableDataChanged();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        titlePanel1 = new com.mdss.pos.ui.TitlePanel();
        transparentPanel1 = new com.mdss.pos.swing.TransparentPanel();
        jSeparator1 = new javax.swing.JSeparator();
        transparentPanel3 = new com.mdss.pos.swing.TransparentPanel();
        btnNewCashDrop = new com.mdss.pos.swing.PosButton();
        btnDeleteSelected = new com.mdss.pos.swing.PosButton();
        btnClose = new com.mdss.pos.swing.PosButton();
        midPanel = new com.mdss.pos.swing.TransparentPanel();
        transparentPanel2 = new com.mdss.pos.swing.TransparentPanel();
        btnUp = new com.mdss.pos.swing.PosButton();
        btnDown = new com.mdss.pos.swing.PosButton();
        lblActiveCashDrop = new com.mdss.pos.swing.POSLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCashDrops = new javax.swing.JTable();

        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        titlePanel1.setTitle("REMOVE CASH FROM DRAWER");
        getContentPane().add(titlePanel1, java.awt.BorderLayout.NORTH);

        transparentPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        transparentPanel1.add(jSeparator1, java.awt.BorderLayout.NORTH);

        btnNewCashDrop.setText("NEW DRAWER BLEED");
        btnNewCashDrop.setPreferredSize(new java.awt.Dimension(160, 50));
        btnNewCashDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCashDropActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnNewCashDrop);

        btnDeleteSelected.setText("DELETE SELECTED");
        btnDeleteSelected.setPreferredSize(new java.awt.Dimension(140, 50));
        btnDeleteSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSelectedActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnDeleteSelected);

        btnClose.setText("CLOSE");
        btnClose.setPreferredSize(new java.awt.Dimension(140, 50));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnClose);

        transparentPanel1.add(transparentPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.SOUTH);

        midPanel.setLayout(new java.awt.BorderLayout(1, 5));

        transparentPanel2.setLayout(new java.awt.GridLayout(0, 1, 2, 2));

        transparentPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 5));
        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up_32.png")));
        btnUp.setPreferredSize(new java.awt.Dimension(80, 0));
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doScrollUp(evt);
            }
        });

        transparentPanel2.add(btnUp);

        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down_32.png")));
        btnDown.setPreferredSize(new java.awt.Dimension(80, 0));
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doScrollDown(evt);
            }
        });

        transparentPanel2.add(btnDown);

        midPanel.add(transparentPanel2, java.awt.BorderLayout.EAST);

        lblActiveCashDrop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActiveCashDrop.setText("ACTIVE CASH DROPS FOR TERMINAL");
        midPanel.add(lblActiveCashDrop, java.awt.BorderLayout.NORTH);

        tableCashDrops.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableCashDrops);

        midPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(midPanel, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-606)/2, (screenSize.height-372)/2, 606, 372);
    }// </editor-fold>//GEN-END:initComponents

	private void doScrollDown(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollDown
		if (cashDropList == null)
			return;

		int selectedRow = tableCashDrops.getSelectedRow();
		if (selectedRow < 0) {
			selectedRow = 0;
		}
		else if (selectedRow >= cashDropList.size() - 1) {
			selectedRow = 0;
		}
		else {
			++selectedRow;
		}

		selectionModel.setLeadSelectionIndex(selectedRow);
		Rectangle cellRect = tableCashDrops.getCellRect(selectedRow, 0, false);
		tableCashDrops.scrollRectToVisible(cellRect);
	}//GEN-LAST:event_doScrollDown

	private void doScrollUp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollUp
		if (cashDropList == null)
			return;

		int selectedRow = tableCashDrops.getSelectedRow();
		if (selectedRow <= 0) {
			selectedRow = cashDropList.size() - 1;
		}
		else if (selectedRow > (cashDropList.size() - 1)) {
			selectedRow = cashDropList.size() - 1;
		}
		else {
			--selectedRow;
		}

		selectionModel.setLeadSelectionIndex(selectedRow);
		Rectangle cellRect = tableCashDrops.getCellRect(selectedRow, 0, false);
		tableCashDrops.scrollRectToVisible(cellRect);
	}//GEN-LAST:event_doScrollUp

	private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
		dispose();
	}//GEN-LAST:event_btnCloseActionPerformed

	private void btnDeleteSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectedActionPerformed
		try {
			int index = tableCashDrops.getSelectedRow();
			if (index >= 0) {
				CashDropTransaction transaction = cashDropList.get(index);
				CashDropTransactionDAO dao = new CashDropTransactionDAO();
				dao.deleteCashDrop(transaction, Application.getInstance().getTerminal());
				tableModel.removeCashDrop(transaction);
			}
		} catch (Exception e) {
			POSMessageDialog.showError("An error has occured while deleting cash drop", e);
		}
	}//GEN-LAST:event_btnDeleteSelectedActionPerformed

	private void btnNewCashDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCashDropActionPerformed
		try {
			NumberSelectionDialog dialog = new NumberSelectionDialog();
			dialog.setTitle("DRAWER BLEED AMOUNT");
			dialog.setDecimalAllowed(true);
			dialog.open();

			if (!dialog.isCanceled()) {
				double amount = dialog.getValue();
				CashDropTransaction transaction = new CashDropTransaction();
				transaction.setDrawerResetted(false);
				transaction.setTerminal(Application.getInstance().getTerminal());
				transaction.setUser(Application.getCurrentUser());
				transaction.setTransactionTime(new Date());
				transaction.setSubtotalAmount(amount);
				transaction.setDiscountAmount(Double.valueOf(0));
				transaction.setTaxAmount(Double.valueOf(0));
				transaction.setTotalAmount(amount);

				CashDropTransactionDAO dao = new CashDropTransactionDAO();
				dao.saveNewCashDrop(transaction, Application.getInstance().getTerminal());

				tableModel.addCashDrop(transaction);
			}
		} catch (Exception e) {
			POSMessageDialog.showError("An error has occured while saving cash drop", e);
		}
	}//GEN-LAST:event_btnNewCashDropActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mdss.pos.swing.PosButton btnClose;
    private com.mdss.pos.swing.PosButton btnDeleteSelected;
    private com.mdss.pos.swing.PosButton btnDown;
    private com.mdss.pos.swing.PosButton btnNewCashDrop;
    private com.mdss.pos.swing.PosButton btnUp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.mdss.pos.swing.POSLabel lblActiveCashDrop;
    private com.mdss.pos.swing.TransparentPanel midPanel;
    private javax.swing.JTable tableCashDrops;
    private com.mdss.pos.ui.TitlePanel titlePanel1;
    private com.mdss.pos.swing.TransparentPanel transparentPanel1;
    private com.mdss.pos.swing.TransparentPanel transparentPanel2;
    private com.mdss.pos.swing.TransparentPanel transparentPanel3;
    // End of variables declaration//GEN-END:variables
	private Terminal terminal;

	class CashDropTableModel extends AbstractTableModel {

		public int getRowCount() {
			if (cashDropList == null)
				return 0;

			int size = cashDropList.size();
			return size;
		}

		public int getColumnCount() {
			return 2;
		}

		public void addCashDrop(int index, CashDropTransaction t) {
			if (cashDropList == null)
				return;

			cashDropList.add(index, t);
			fireTableRowsInserted(index, index);

			Rectangle cellRect = tableCashDrops.getCellRect(index, 0, false);
			tableCashDrops.scrollRectToVisible(cellRect);
			selectionModel.setLeadSelectionIndex(index);
		}

		public void addCashDrop(CashDropTransaction t) {
			int size = cashDropList.size();
			cashDropList.add(t);
			fireTableRowsInserted(size, size);

			Rectangle cellRect = tableCashDrops.getCellRect(size, 0, false);
			tableCashDrops.scrollRectToVisible(cellRect);
			selectionModel.setLeadSelectionIndex(size);
		}

		public void removeCashDrop(CashDropTransaction t) {
			int index = cashDropList.indexOf(t);
			if (index >= 0) {
				cashDropList.remove(index);
				fireTableRowsDeleted(index, index);
			}

		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (cashDropList == null)
				return "";

			CashDropTransaction t = cashDropList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return t.getTransactionTime();

				case 1:
					return Double.valueOf(t.getSubtotalAmount());
			}
			return "";
		}

	}

	class TableRenderer extends DefaultTableCellRenderer {
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
		private DecimalFormat numberFormat = new DecimalFormat("0.00");
		Font font = getFont().deriveFont(Font.BOLD, 14);
		/**
		 * 
		 */
		private JCheckBox checkBox = new JCheckBox();

		public TableRenderer() {
			checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof Boolean) {
				checkBox.setSelected(((Boolean) value).booleanValue());
				if (isSelected) {
					checkBox.setBackground(table.getSelectionBackground());
				}
				else {
					checkBox.setBackground(table.getBackground());
				}
				return checkBox;
			}
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			label.setFont(font);
			if (value instanceof Date) {
				String string = dateFormat.format(value);
				label.setText(string);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			if (value instanceof Double) {
				String string = numberFormat.format(((java.lang.Double) value).doubleValue());
				label.setText(currencySymbol + string);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			else {
				label.setHorizontalAlignment(SwingConstants.LEFT);
			}
			return label;
		}
	}
}
