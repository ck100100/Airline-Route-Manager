package pages;

import components.ButtonFactory;
import Object.Job;
import controllers.JobController;
import main.Main;
import utils.Colors;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class JobListPage extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable jobTable;
    private DefaultTableModel tableModel;
    private JobController jobController = new JobController();
    private String[] tabs = {"All", "in-progress", "awaiting payment", "completed"};

    public JobListPage() {
        super("Job List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 600); // Made wider for more columns
        setLocationRelativeTo(null);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Colors.secondary);

        JButton backButton = ButtonFactory.primary("<");
        backButton.setBackground(Colors.accent1);
        backButton.setForeground(Colors.primary);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> {
            dispose();
            Main.showPageSelectionDialog();
        });

        JLabel titleLabel = new JLabel("Job List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane for status filters
        tabbedPane = new JTabbedPane();
        for (String tab : tabs) {
            tabbedPane.addTab(tab, createJobPanel(tab));
        }
        tabbedPane.addChangeListener(e -> refreshTab(tabbedPane.getSelectedIndex()));
        add(tabbedPane, BorderLayout.CENTER);

        // New Job button
        JButton newJobButton = ButtonFactory.primary("New Contracted Job");
        newJobButton.setBackground(Colors.primary);
        newJobButton.setForeground(Colors.secondary);
        newJobButton.addActionListener(e -> {
            dispose();
            new AssignJobPage();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(newJobButton);
        add(buttonPanel, BorderLayout.SOUTH);

        this.getContentPane().setBackground(Colors.secondary);
        setVisible(true);
    }

    private JPanel createJobPanel(String statusFilter) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Colors.secondary);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Job Name");
        tableModel.addColumn("Job Type");
        tableModel.addColumn("Start Date");
        tableModel.addColumn("Contact Details");
        tableModel.addColumn("Status");
        tableModel.addColumn("Airport");
        tableModel.addColumn("Description");
        tableModel.addColumn("Cost (€)");
        tableModel.addColumn("Actions");

        List<Job> jobs = jobController.getAllJobs();
        if (!statusFilter.equals("All")) {
            jobs = jobs.stream().filter(j -> j.status.equals(statusFilter)).collect(Collectors.toList());
        }

        for (Job job : jobs) {
            Object[] rowData = new Object[9];
            rowData[0] = job.name;
            rowData[1] = job.type;
            rowData[2] = job.date;
            rowData[3] = job.contractor.name;
            rowData[4] = job.status;
            rowData[5] = job.airport;
            rowData[6] = job.description;
            rowData[7] = job.cost;
            rowData[8] = createActionPanel(job); // Action buttons
            tableModel.addRow(rowData);
        }

        jobTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };
        jobTable.setRowHeight(56);
        jobTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        jobTable.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(jobTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createActionPanel(Job job) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setOpaque(false);
        JButton viewButton = ButtonFactory.primary("View");
        viewButton.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        JButton finishButton = ButtonFactory.primary("Work Finished");
        viewButton.setBackground(Colors.accent1);
        finishButton.setBackground(Colors.primary);
        viewButton.setForeground(Colors.secondary);
        finishButton.setForeground(Colors.secondary);

        viewButton.addActionListener(e -> {
            dispose();
            new ViewJobPage(job.name, job.type, job.airport, job.date, job.description, job.cost, job.contractor.contact);
        });
        finishButton.setEnabled(job.status.equals("in-progress"));
        finishButton.addActionListener(e -> {
            jobController.updateStatus(job.id, "completed");
            refreshTab(tabbedPane.getSelectedIndex());
        });

        buttonPanel.add(viewButton);
        buttonPanel.add(finishButton);
        return buttonPanel;
    }

    private void refreshTab(int tabIndex) {
        tabbedPane.setComponentAt(tabIndex, createJobPanel(tabs[tabIndex]));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JobListPage::new);
    }
}

// ButtonRenderer and ButtonEditor as before
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
    public Object getCellEditorValue() { return buttonPanel; }
}
