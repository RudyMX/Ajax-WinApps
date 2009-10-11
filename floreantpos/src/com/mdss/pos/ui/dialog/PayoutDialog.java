/*
 * PayoutDialog.java
 *
 * Created on August 25, 2006, 8:44 PM
 */

package com.mdss.pos.ui.dialog;

import java.util.Date;

import com.mdss.pos.main.Application;
import com.mdss.pos.model.ActionHistory;
import com.mdss.pos.model.PayOutTransaction;
import com.mdss.pos.model.PayoutReason;
import com.mdss.pos.model.PayoutRecepient;
import com.mdss.pos.model.Terminal;
import com.mdss.pos.model.dao.ActionHistoryDAO;
import com.mdss.pos.model.dao.PayOutTransactionDAO;

/**
 *
 * @author  MShahriar
 */
public class PayoutDialog extends POSDialog {

	/** Creates new form PayoutDialog */
	public PayoutDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		setTitle(Application.getTitle() + ": PAY OUT");

		payOutView.initialize();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        transparentPanel1 = new com.mdss.pos.swing.TransparentPanel();
        payOutView = new com.mdss.pos.ui.views.PayOutView();
        transparentPanel2 = new com.mdss.pos.swing.TransparentPanel();
        btnFinish = new com.mdss.pos.swing.PosButton();
        btnCancel = new com.mdss.pos.swing.PosButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        transparentPanel1.setLayout(new java.awt.BorderLayout());

        transparentPanel1.setOpaque(true);
        transparentPanel1.add(payOutView, java.awt.BorderLayout.CENTER);

        transparentPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        btnFinish.setText("FINISH");
        btnFinish.setPreferredSize(new java.awt.Dimension(140, 50));
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doFinishPayout(evt);
            }
        });

        transparentPanel2.add(btnFinish);

        btnCancel.setText("CANCEL");
        btnCancel.setPreferredSize(new java.awt.Dimension(140, 50));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        transparentPanel2.add(btnCancel);

        transparentPanel1.add(transparentPanel2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void doFinishPayout(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doFinishPayout
		Application application = Application.getInstance();

		Terminal terminal = application.getTerminal();

		double payoutAmount = payOutView.getPayoutAmount();
		PayoutReason reason = payOutView.getReason();
		PayoutRecepient recepient = payOutView.getRecepient();
		String note = payOutView.getNote();
		
		terminal.setCurrentBalance(terminal.getCurrentBalance() - payoutAmount);

		PayOutTransaction payOutTransaction = new PayOutTransaction();
		payOutTransaction.setReason(reason);
		payOutTransaction.setRecepient(recepient);
		payOutTransaction.setNote(note);
		payOutTransaction.setSubtotalAmount(Double.valueOf(payoutAmount));
		payOutTransaction.setDiscountAmount(Double.valueOf(0));
		payOutTransaction.setTaxAmount(Double.valueOf(0));
		payOutTransaction.setTotalAmount(Double.valueOf(payoutAmount));
		
		payOutTransaction.setUser(Application.getCurrentUser());
		payOutTransaction.setTransactionTime(new Date());
		payOutTransaction.setTerminal(terminal);

		try {
			PayOutTransactionDAO dao = new PayOutTransactionDAO();
			dao.saveTransaction(payOutTransaction, terminal);
			setCanceled(false);
			
//			PAYOUT ACTION
			String actionMessage = "";
			actionMessage += "TOTAL:" + Application.formatNumber(payoutAmount);
			ActionHistoryDAO.getInstance().saveHistory(Application.getCurrentUser(), ActionHistory.PAY_OUT, actionMessage);
			
			dispose();
		} catch (Exception e) {
			POSMessageDialog.showError(e.getMessage(), e);
		}
	}//GEN-LAST:event_doFinishPayout

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
		canceled = true;
		dispose();
	}//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mdss.pos.swing.PosButton btnCancel;
    private com.mdss.pos.swing.PosButton btnFinish;
    private com.mdss.pos.ui.views.PayOutView payOutView;
    private com.mdss.pos.swing.TransparentPanel transparentPanel1;
    private com.mdss.pos.swing.TransparentPanel transparentPanel2;
    // End of variables declaration//GEN-END:variables

}
