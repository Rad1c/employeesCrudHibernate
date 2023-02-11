package com.zaposleni.inputFileds;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import net.miginfocom.swing.MigLayout;
import com.zaposleni.app.App;
import com.zaposleni.enums.ActionEnum;

public class UserInput {
  TextField txtId;
  TextField txtName;
  TextField txtAddress;
  TextField txtAmount;
  TextField txtAge;
  TextField txtIdDelete;
  Button btnAdd;
  Button btnUpdate;
  Button btnDelete;
  Button btnClear;

  public UserInput(Panel pnlUserInput) {
    pnlUserInput.setLayout(new MigLayout());
    Label lblId = new Label("id");
    Label lblName = new Label("ime");
    Label lblAddress = new Label("adresa");
    Label lblAmount = new Label("visina dohotka: ");
    Label lblAge = new Label("broj godina");
    Panel pnlBtnHolder = new Panel(new MigLayout());
    Panel pnlDeleteFiledsHolder = new Panel(new MigLayout());
    
    txtId = new TextField(15);
    txtName = new TextField(15);
    txtAddress = new TextField(15);
    txtAmount = new TextField(15);
    txtAge = new TextField(15);
    txtIdDelete = new TextField(15);

    btnAdd = new Button("Dodaj zaposlenog");
    btnUpdate = new Button("izmijeni podatke");
    btnDelete = new Button("Obrisi zaposlenog");
    btnDelete.setForeground(Color.red);
    btnClear = new Button("Ocisti polja");

    pnlUserInput.add(lblId);
    pnlUserInput.add(txtId);
    pnlUserInput.add(lblName);
    pnlUserInput.add(txtName);
    pnlUserInput.add(lblAge);
    pnlUserInput.add(txtAge, "wrap");
    pnlUserInput.add(lblAmount);
    pnlUserInput.add(txtAmount);
    pnlUserInput.add(lblAddress);
    pnlUserInput.add(txtAddress, "span 2, wrap");
    
    pnlBtnHolder.add(btnAdd);
    pnlBtnHolder.add(btnUpdate, "gapleft 20");
    pnlBtnHolder.add(btnClear, "gapleft 20");
    
    pnlDeleteFiledsHolder.add(new Label("Brisanje (unesite ID zaposlenog): "));
    pnlDeleteFiledsHolder.add(txtIdDelete);
    pnlDeleteFiledsHolder.add(btnDelete);
    pnlUserInput.add(pnlBtnHolder, "span, grow, gaptop 10, gapbottom 15");
    pnlUserInput.add(pnlDeleteFiledsHolder, "span, grow");
  }

  public void setInputActionListeners(App app) {
    btnAdd.setActionCommand(ActionEnum.ADD.toString());
    btnUpdate.setActionCommand(ActionEnum.UPDATE.toString());
    btnDelete.setActionCommand(ActionEnum.DELETE.toString());
    btnClear.setActionCommand(ActionEnum.CLEAR.toString());

    btnAdd.addActionListener(app);
    btnUpdate.addActionListener(app);
    btnDelete.addActionListener(app);
    btnClear.addActionListener(app);
  }

  public void resetValues() {
    txtId.setText("");
    txtName.setText("");
    txtAddress.setText("");
    txtAmount.setText("");
    txtAge.setText("");
  }
  
  public void resetIdForDelete(){
    txtIdDelete.setText("");
  }
  
  public String getId() {
    return txtId.getText();
  }

  public String getName() {
    return txtName.getText();
  }

  public String getAddress() {
    return txtAddress.getText();
  }

  public String getAmount() {
    return txtAmount.getText();
  }

  public String getIdDelete() {
    return txtIdDelete.getText();
  }

  public String getAge() {
    return txtAge.getText();
  }
}