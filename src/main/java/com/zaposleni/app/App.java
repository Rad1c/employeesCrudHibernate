package com.zaposleni.app;

import com.zaposleni.inputFileds.SearchFiled;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import com.zaposleni.table.GeneralTable;
import com.zaposleni.enums.ActionEnum;
import com.zaposleni.hibernate.dao.EmployeeDAO;
import com.zaposleni.hibernate.model.Employee;
import com.zaposleni.hibernate.util.HibernateUtil;
import com.zaposleni.inputFileds.UserInput;
import java.util.List;

public class App extends Frame implements ActionListener {
  Panel pnlInputFields;
  Panel pnlTable;
  Panel pnlSearch;
  DefaultTableModel model;
  SearchFiled searchFiled;
  UserInput userInput;
  final Dimension frameDim = new Dimension(700, 460);
  final String backColor = "#FAFAFA";

  @SuppressWarnings({
    "LeakingThisInConstructor",
    "ResultOfObjectAllocationIgnored"
  })
  public App() {
    initPanels();
    model = new DefaultTableModel();

    searchFiled = new SearchFiled(pnlSearch);
    searchFiled.setSearchListener(this);
    new GeneralTable(pnlTable, model, frameDim);
    userInput = new UserInput(pnlInputFields);
    userInput.setInputActionListeners(this);

    getData();

    add(pnlSearch, BorderLayout.NORTH);
    add(pnlTable, BorderLayout.CENTER);
    add(pnlInputFields, BorderLayout.SOUTH);

    setTitle("Zaposleni");
    setClosingWindow();
    setSize(frameDim.width, frameDim.height);
    centerWindow();
    setVisible(true);
    setResizable(false);
  }

  private void updateEmploye(String id, String name, String address, String age, String amount) {
    int updateEmployeeId = Integer.parseInt(id);
    try {
      Employee emp = new Employee();
      emp.setId(updateEmployeeId);
      emp.setName(name);
      emp.setAddress(address);
      emp.setAge(Integer.parseInt(age));
      emp.setAmount(Double.parseDouble(amount));
     
      EmployeeDAO.updateEmployeeById(updateEmployeeId, emp);
    } catch (NumberFormatException e) {}
  }

  private void addEmploye(String id, String name, String address, String age, String amount) {
    try {
      Employee emp = new Employee();
      emp.setId(Integer.parseInt(id));
      emp.setName(name);
      emp.setAddress(address);
      emp.setAmount(Double.parseDouble(amount));
      emp.setAge(Integer.parseInt(age));

      EmployeeDAO.saveEmployee(emp);
    } catch (NumberFormatException e) {}

  }

  private void initPanels() {
    setLayout(new BorderLayout());
    pnlTable = new Panel();
    pnlTable.setPreferredSize(new Dimension(frameDim.width, (int)(frameDim.height * 0.6)));
    pnlInputFields = new Panel();
    pnlInputFields.setPreferredSize(new Dimension(frameDim.width, (int)(frameDim.height / 2.5)));
    pnlInputFields.setBackground(Color.decode(backColor));
    pnlSearch = new Panel();
    pnlSearch.setPreferredSize(new Dimension(frameDim.width, (int)(frameDim.height / 10)));
    pnlSearch.setBackground(Color.decode(backColor));
  }

  private void deleteEmploye(String id) {
    EmployeeDAO.deleteEmployee(Integer.parseInt(id));
  }

  private void getData() {
    java.util.List < Employee > allEmployees = EmployeeDAO.getAllEmployees();
      showData(allEmployees);
  }
  
  public void searchEmployees(String searchParam){
      List<Employee> employees = EmployeeDAO.searchEmployees(searchParam);
      showData(employees);
  }
  
  private void showData(List<Employee> employees){
    model.setRowCount(0);
    if (employees != null)
      for (Employee emp: employees) {
        model.addRow(new Object[] {
          emp.getId(), emp.getName(), emp.getAge(), emp.getAddress(), emp.getAmount()
        });
      }
  }

  private void setClosingWindow() {
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
        HibernateUtil.shutdown();
        System.exit(0);
      }
    });
  }

  private void centerWindow() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int)((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int)((dimension.getHeight() - this.getHeight()) / 2);

    this.setLocation(x, y);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String id = userInput.getId();
    String name = userInput.getName();
    String address = userInput.getAddress();
    String age = userInput.getAge();
    String amount = userInput.getAmount();

    if (e.getActionCommand().equals(ActionEnum.SEARCH.toString())) {
      searchEmployees(searchFiled.getSearchParam());
      return;
    }

    if (e.getActionCommand().equals(ActionEnum.ADD.toString())) {
      userInput.resetValues();
      addEmploye(id, name, address, age, amount);
      getData();
      return;
    }

    if (e.getActionCommand().equals(ActionEnum.UPDATE.toString())) {
      updateEmploye(id, name, address, age, amount);
      userInput.resetValues();
      getData();
      return;
    }

    if (e.getActionCommand().equals(ActionEnum.DELETE.toString())) {
      deleteEmploye(userInput.getIdDelete());
      userInput.resetIdForDelete();
      getData();
      return;
    }

    if (e.getActionCommand().equals(ActionEnum.CLEAR.toString())) {
      userInput.resetValues();
    }
  }
}