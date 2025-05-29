package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class FormDropdownMultiSelect extends JPanel {
    private JComboBox dropdown;
    private ArrayList<String> selectedItems;
    private ArrayList<String> options;
    private JPanel listPanel;

    public FormDropdownMultiSelect(String labelText, ArrayList<String> options, String[] alreadySelectedItems) {
        this.options = options;

        selectedItems = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 0, 10, 0));

        var labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new FormLabel(labelText);
        labelPanel.add(label);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        listPanel.setSize(new Dimension(500, 200));

        if(alreadySelectedItems != null)
            for(String item : alreadySelectedItems) {
                addListItem(item);
                selectedItems.add(item);
            }


        add(labelPanel);
        add(generateDropdown(options));
        add(listPanel);
    }

    private JPanel generateDropdown(ArrayList<String> options) {
        var panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Vector<String> formOptions = new Vector<>();
        formOptions.addAll(options);
        formOptions.add(0, "Select:");

        dropdown = new JComboBox(formOptions);
        dropdown.setMaximumSize(new Dimension(200, 30));
        dropdown.setSize(new Dimension(200, 30));
        dropdown.setAlignmentX(Component.LEFT_ALIGNMENT);

        var addBtn = ButtonFactory.primary("add");
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddItem();
            }
        });

        panel.add(dropdown);
        panel.add(addBtn);

        return panel;
    }

    private void onAddItem() {
        String selectedItem;
        try {
            selectedItem = getText();
        } catch(Exception e) {
            return;
        }

        selectedItems.add(selectedItem);
        addListItem(selectedItem);
    }

    private void addListItem(String itemText) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(200, 30));

        JLabel label = new JLabel(itemText);
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            listPanel.remove(itemPanel);
            selectedItems.remove(itemText);
            listPanel.revalidate();
            listPanel.repaint();
        });

        itemPanel.add(label, BorderLayout.CENTER);
        itemPanel.add(removeButton, BorderLayout.EAST);

        listPanel.add(itemPanel);
        listPanel.revalidate();
        listPanel.repaint();
    }

    public void setActive(boolean state) {
        dropdown.setEnabled(state);
    }

    public boolean validateInput() {
        if (dropdown.getSelectedIndex() == 0)
            return false;

        return true;
    }

    public String getText() throws Exception {
        if (validateInput() == false)
            throw new Exception("Invalid input");

        boolean found = false;
        int i = 0;
        while(!found && i < selectedItems.size()) {
            if(selectedItems.get(i) == dropdown.getSelectedItem())
                found = true;
            i++;
        }
        if(found)
            throw new Exception("Unnable to add duplicate items");

        return (String) dropdown.getSelectedItem();
    }

    public ArrayList<Integer> getSelectedItemIndexes() throws Exception {
        ArrayList<Integer> selectedItemIndexes = new ArrayList<>();
        for(String selectedItem : selectedItems) {
            boolean found = false;
            int i = 0;
            while (!found && i < options.size()) {
                if (selectedItem == options.get(i))
                    found = true;
                else
                    i++;
            }
            if (found)
                selectedItemIndexes.add(i);
            else
                throw new Exception("Couldn't find option in dropdown!");
        }
        return selectedItemIndexes;
    }
}