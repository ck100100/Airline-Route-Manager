package pages;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.ButtonFactory; // Import ButtonFactory

public class JobListPage extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable jobTable;
    private DefaultTableModel tableModel;

    // Sample job data (to be replaced with JobController.load())
    private final Object[][] jobData = {
            {"Engine Refit", "Maintenance", "10th March 2025", "contractor@gmail.com", "in-progress"},
            {"Engine Refit", "Maintenance", "10th March 2025", "contractor@gmail.com", "awaiting payment"},
            {"Engine Refit", "Maintenance", "10th March 2025", "contractor@gmail.com", "in-progress"},
            {"Engine Refit", "Maintenance", "10th March 2025", "contractor@gmail.com", "completed"},
            {"Engine Refit", "Maintenance", "10th March 2025", "contractor@gmail.com", "completed"}
    };

    public JobListPage() {
        super("Job List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Header with back arrow
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        JButton backButton = new JButton("<");
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> dispose());
        JLabel titleLabel = new JLabel("Job List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane for filters
        tabbedPane = new JTabbedPane();
        String[] tabs = {"All", "in-progress", "awaiting payment", "completed"};
        for (String tab : tabs) {
            tabbedPane.addTab(tab, createJobPanel());
        }
        add(tabbedPane, BorderLayout.CENTER);

        // New Contracted Job button using ButtonFactory
        JButton newJobButton = ButtonFactory.primary("New Contracted Job");
        newJobButton.addActionListener(e -> {
            dispose();
            new AssignJobPage();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newJobButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
        loadJobs();
    }

    private JPanel createJobPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Job Name");
        tableModel.addColumn("Job Type");
        tableModel.addColumn("Start Date");
        tableModel.addColumn("Contact Details");
        tableModel.addColumn("Actions");
        tableModel.addColumn("Job Status");

        // Populate table with data
        for (Object[] job : jobData) {
            Object[] rowData = new Object[6];
            System.arraycopy(job, 0, rowData, 0, 4);
            rowData[4] = createViewButton(job);
            rowData[5] = createStatusButtons((String) job[4]);
            tableModel.addRow(rowData);
        }

        jobTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };
        jobTable.setRowHeight(50); // Increased row height to 50 for better button visibility

        // Set preferred widths for columns
        TableColumn actionsColumn = jobTable.getColumnModel().getColumn(4);
        actionsColumn.setPreferredWidth(80); // Increased width for Actions column
        TableColumn statusColumn = jobTable.getColumnModel().getColumn(5);
        statusColumn.setPreferredWidth(180); // Increased width for Job Status column to accommodate both buttons

        jobTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        jobTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        jobTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        jobTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(jobTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createViewButton(Object[] job) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton viewButton = ButtonFactory.primary("View");
        viewButton.addActionListener(e -> {
            dispose();
            new ViewJobPage();
        });
        buttonPanel.add(viewButton);
        return buttonPanel;
    }

    private JPanel createStatusButtons(String status) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Increased horizontal gap to 10
        JButton workButton = ButtonFactory.primary("work");
        JButton finishedButton = ButtonFactory.primary("finished");

        workButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Work started on this job!");
        });
        finishedButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Job marked as completed!");
        });

        if (!status.equals("in-progress")) workButton.setEnabled(false);
        if (!status.equals("awaiting payment") && !status.equals("in-progress")) finishedButton.setEnabled(false);

        buttonPanel.add(workButton);
        buttonPanel.add(finishedButton);
        return buttonPanel;
    }

    private void loadJobs() {
        // Placeholder for JobController.load()
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JobListPage::new);
    }
}

class ButtonRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (Component) value;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JPanel buttonPanel;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        buttonPanel = new JPanel();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        buttonPanel = (JPanel) value;
        return buttonPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return buttonPanel;
    }
}