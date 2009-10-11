package com.mdss.pos.bo.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.mdss.pos.bo.ui.BackOfficeWindow;
import com.mdss.pos.bo.ui.explorer.CouponExplorer;
import com.mdss.pos.main.Application;
import com.mdss.pos.swing.MessageDialog;

public class CouponExplorerAction extends AbstractAction {

	public CouponExplorerAction() {
		super("Coupons & Discounts");
	}

	public CouponExplorerAction(String name) {
		super(name);
	}

	public CouponExplorerAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			BackOfficeWindow backOfficeWindow = Application.getInstance().getBackOfficeWindow();

			CouponExplorer explorer = null;
			JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
			int index = tabbedPane.indexOfTab("Coupon & Discount Explorer");
			if (index == -1) {
				explorer = new CouponExplorer();
				explorer.initData();
				tabbedPane.addTab("Coupon & Discount Explorer", explorer);
			}
			else {
				explorer = (CouponExplorer) tabbedPane.getComponentAt(index);
			}
			tabbedPane.setSelectedComponent(explorer);
		} catch (Exception x) {
			MessageDialog.showError("An unexpected error has occured, you may need to restart the application", x);
		}
	}

}
