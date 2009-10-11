package com.mdss.pos.ui.report.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.mdss.pos.bo.ui.BackOfficeWindow;
import com.mdss.pos.main.Application;
import com.mdss.pos.ui.report.PayrollReportView;

public class PayrollReportAction extends AbstractAction {

	public PayrollReportAction() {
		super("Payroll Report");
	}

	public PayrollReportAction(String name) {
		super(name);
	}

	public PayrollReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = Application.getInstance().getBackOfficeWindow();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		PayrollReportView reportView = null;
		int index = tabbedPane.indexOfTab("Payroll Report");
		if (index == -1) {
			reportView = new PayrollReportView();
			tabbedPane.addTab("Payroll Report", reportView);
		}
		else {
			reportView = (PayrollReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}
