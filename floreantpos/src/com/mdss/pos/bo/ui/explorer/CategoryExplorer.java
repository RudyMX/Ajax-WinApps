package com.mdss.pos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.mdss.pos.main.Application;
import com.mdss.pos.model.MenuCategory;
import com.mdss.pos.model.dao.MenuCategoryDAO;
import com.mdss.pos.swing.MessageDialog;
import com.mdss.pos.swing.TransparentPanel;
import com.mdss.pos.ui.PosTableRenderer;
import com.mdss.pos.ui.dialog.BeanEditorDialog;
import com.mdss.pos.ui.dialog.ConfirmDeleteDialog;
import com.mdss.pos.ui.model.MenuCategoryForm;

public class CategoryExplorer extends TransparentPanel {
	private List<MenuCategory> categoryList;
	
	private JTable table;

	private CategoryExplorerTableModel tableModel;
	
	public CategoryExplorer() {
		MenuCategoryDAO dao = new MenuCategoryDAO();
		categoryList = dao.findAll();
		
		tableModel = new CategoryExplorerTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		setLayout(new BorderLayout(5,5));
		add(new JScrollPane(table));
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuCategoryForm editor = new MenuCategoryForm();
					BeanEditorDialog dialog = new BeanEditorDialog(editor, Application.getInstance().getBackOfficeWindow(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuCategory foodCategory = (MenuCategory) editor.getBean();
					tableModel.addCategory(foodCategory);
				} catch (Exception x) {
					MessageDialog.showError("An error has occured, please restart the application", x);
				}
			}
			
		});
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					MenuCategory category = categoryList.get(index);

					MenuCategoryForm editor = new MenuCategoryForm(category);
					BeanEditorDialog dialog = new BeanEditorDialog(editor, Application.getInstance().getBackOfficeWindow(), true);
					dialog.open();
					if (dialog.isCanceled())
						return;

					table.repaint();
				} catch (Throwable x) {
					MessageDialog.showError("An error has occured, please restart the application", x);
				}
			}
			
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					if (ConfirmDeleteDialog.showMessage(CategoryExplorer.this, "Sure Want to Delete?", "Delete") == ConfirmDeleteDialog.YES) {
						MenuCategory category = categoryList.get(index);
						MenuCategoryDAO dao = new MenuCategoryDAO();
						dao.delete(category);
						tableModel.deleteCategory(category, index);
					}
				} catch (Exception x) {
					MessageDialog.showError("An error has occured, please restart the application", x);
				}
			}
			
		});

		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		add(panel, BorderLayout.SOUTH);
	}
	
	class CategoryExplorerTableModel extends AbstractTableModel {
		String[] columnNames = {"Id", "Name", "Beverage", "Visible"};
		
		public int getRowCount() {
			if(categoryList == null) {
				return 0;
			}
			return categoryList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if(categoryList == null)
				return "";
			
			MenuCategory category = categoryList.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return String.valueOf(category.getId());
					
				case 1:
					return category.getName();
					
				case 2:
					return Boolean.valueOf(category.isBeverage());
					
				case 3:
					return Boolean.valueOf(category.isVisible());
			}
			return null;
		}

		public void addCategory(MenuCategory category) {
			int size = categoryList.size();
			categoryList.add(category);
			fireTableRowsInserted(size, size);
		}
		
		public void deleteCategory(MenuCategory category, int index) {
			categoryList.remove(category);
			fireTableRowsDeleted(index, index);
		}
	}
}
