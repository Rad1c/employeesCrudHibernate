package com.zaposleni.inputFileds;

import com.zaposleni.app.App;
import com.zaposleni.enums.ActionEnum;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import net.miginfocom.swing.MigLayout;

public class SearchFiled {
    Button btnSearch;
    TextField txtSearch;
    
    public SearchFiled(Panel pnlSearch){
        pnlSearch.setLayout(new MigLayout());
        Label lblSearch = new Label("Unesite pojam za pretragu: ");
        txtSearch = new TextField(15);
        btnSearch = new Button("Pretraga");
        
        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);
        pnlSearch.add(btnSearch);
    }
    
    public String getSearchParam(){
        return txtSearch.getText();
    }
    
    public void setSearchListener(App app){
        btnSearch.addActionListener(app);
        btnSearch.setActionCommand(ActionEnum.SEARCH.toString());
    }
}
