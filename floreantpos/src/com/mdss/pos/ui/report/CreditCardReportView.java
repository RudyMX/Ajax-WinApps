package com.mdss.pos.ui.report;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import org.jdesktop.swingx.JXDatePicker;

import com.mdss.pos.main.Application;
import com.mdss.pos.model.util.DateUtil;
import com.mdss.pos.report.CreditCardReport;
import com.mdss.pos.report.services.ReportService;
import com.mdss.pos.ui.dialog.POSMessageDialog;
import com.mdss.pos.util.PanelTester;

public class CreditCardReportView extends JPanel {
	private JXDatePicker fromDatePicker = new JXDatePicker();
	private JXDatePicker toDatePicker = new JXDatePicker();
	private JButton btnGo = new JButton("GO");
	private JPanel reportContainer;
	
	public CreditCardReportView() {
		super(new BorderLayout());
		
		JPanel topPanel = new JPanel(new MigLayout());
		
		topPanel.add(new JLabel("From:"), "grow");
		topPanel.add(fromDatePicker,"wrap");
		topPanel.add(new JLabel("To:"), "grow");
		topPanel.add(toDatePicker,"wrap");
		topPanel.add(btnGo, "skip 1, al right");
		add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(0, 10,10,10));
		centerPanel.add(new JSeparator(), BorderLayout.NORTH);
		
		reportContainer = new JPanel(new BorderLayout());
		centerPanel.add(reportContainer);
		
		add(centerPanel);
		
		btnGo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					viewReport();
				} catch (Exception e1) {
					POSMessageDialog.showError(CreditCardReportView.this, POSMessageDialog.ERROR_MESSAGE, e1);
				}
			}
			
		});
	}
	
	private void viewReport() throws Exception {
		Date fromDate = fromDatePicker.getDate();
		Date toDate = toDatePicker.getDate();
		
		if(fromDate.after(toDate)) {
			POSMessageDialog.showError(Application.getInstance().getBackOfficeWindow(), "From date cannot be greater than to date.");
			return;
		}
		
		fromDate = DateUtil.startOfDay(fromDate);
		toDate = DateUtil.endOfDay(toDate);
		
		ReportService reportService = new ReportService();
		CreditCardReport report = reportService.getCreditCardReport(fromDate, toDate);
		
		HashMap<String, String> map = new HashMap<String, String>();
		ReportUtil.populateRestaurantProperties(map);
		map.put("reportTitle", "========= CREDIT CARD REPORT ==========");
		map.put("fromDate", ReportService.formatShortDate(fromDate));
		map.put("toDate", ReportService.formatShortDate(toDate));
		map.put("reportTime", ReportService.formatFullDate(new Date()));
		
		map.put("salesCount", String.valueOf(report.getTotalSalesCount()));
		map.put("totalSales", Application.formatNumber(report.getTotalSales()));
		map.put("netTips", Application.formatNumber(report.getNetTips()));
		map.put("netTipsPaid", Application.formatNumber(report.getTipsPaid()));
		map.put("netCharge", Application.formatNumber(report.getNetCharge()));
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/mdss/pos/ui/report/credit_card_report.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRTableModelDataSource(report.getTableModel()));
		JRViewer viewer = new JRViewer(jasperPrint);
		reportContainer.removeAll();
		reportContainer.add(viewer);
		reportContainer.revalidate();
		
	}

	public static void main(String[] args) {
		PanelTester.width = 800;
		PanelTester.height = 500;
		PanelTester.test(new CreditCardReportView());
	}
}
