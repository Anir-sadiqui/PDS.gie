package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.giefront.DTO.Ventes;
import org.giefront.DTO.CommandV;
import org.giefront.DTO.EtatVentes;
import org.giefront.Service.VentesService;
import org.giefront.Service.CommandVService;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommandVController implements Initializable {

    public static CommandV c;
    @FXML
    private TextArea Details;
    @FXML
    private DatePicker Calendrier;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ChoiceBox CB_four;
    @FXML
    private TableView Tab;
    @FXML
    private TableColumn C_id;
    @FXML
    private TableColumn C_date;
    @FXML
    private TableColumn C_Etat;

    private CommandVService cs = new CommandVService();
    ProductService ps = new ProductService();
    CommandV cmd = new CommandV();
    private double prixTot = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab.setRowFactory(tv -> {
            TableRow<CommandV> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    CommandV clickedRow = row.getItem();
                    showDetails(clickedRow);
                    cmd=row.getItem();
                }
            });
            return row;
        });
        getAll();
        Details.setEditable(false);
        loadE();

    }

    private void showDetails(CommandV clickedRow) {
        VentesService as = new VentesService();
        String s = "";
        for (Ventes a : as.getByComm(Math.toIntExact(clickedRow.getId()))) {
            s +=  a.getDetails().getProduct().getName() + " :" + a.getDetails().getQuantity() + "pieces" + "\n";
        }
        Details.setText("Command made on :" + " " + clickedRow.getPurchaseDate() + "\n"
                + "Contains :" + "\n" + s + "\n" +"Total Price :" + calculPT(clickedRow) + "\n" + "Has been :"+ clickedRow.getE() );

    }
    private double calculPT(CommandV c){
        double p = 0 ;
        VentesService as = new VentesService();
        List<Ventes> Ventes =  as.getByComm(Math.toIntExact(c.getId()));
        if (!Ventes.isEmpty()) {
            for (Ventes a : Ventes) {
                p +=  1.3 * a.getDetails().getTotalPrice() ;
            }
        }
        return p;
    }



    public void onAfch(ActionEvent event) {
        if( CB_four.getValue() != null){
            ObservableList<CommandV> CommandVs = FXCollections.observableList(cs.getByEtat((String) CB_four.getValue()));
            if (Calendrier.getValue()==null){
                remlirTab(CommandVs);
            }
            else {
                List<CommandV> CommandVs1 = cs.getByDate(String.valueOf(Calendrier.getValue()));
                List<CommandV> CommandVs2 = new ArrayList<>();
                for (CommandV c : CommandVs1){
                    if(c.getE().name()==CB_four.getValue()){
                        CommandVs2.add(c);
                    }
                }
                remlirTab(FXCollections.observableList(CommandVs2));
            }
        }
        else {
            if (Calendrier.getValue()!=null){
                ObservableList<CommandV> CommandVs = FXCollections.observableList(cs.getByDate(String.valueOf(Calendrier.getValue())));
                remlirTab(CommandVs);
            }
            else {
                getAll();
            }
        }
    }

    public void onAjout(ActionEvent event) {
        List<Ventes> Ventes = new ArrayList<>();
        CommandV c = new CommandV(Ventes);
        cs.addCom(c);
        showMessage("Your order has been initialised. You can add sales by modifying it  ");
        System.out.println(c);
        getAll();
    }

    public void onAnnuler(ActionEvent event) throws IOException {
        CommandV cmd = (CommandV) Tab.getSelectionModel().getSelectedItem();
        if (cmd.getE()== EtatVentes.In_Preparation || cmd.getE()== EtatVentes.Pending){
            cs.deleteComm(Math.toIntExact(cmd.getId()));
            showMessage("This order has been canceled");
            getAll();
        }
        else showMessage("Impossible to cancel this order");
    }

    public void OnMod(ActionEvent event) {
        c= (CommandV) Tab.getSelectionModel().getSelectedItem();
        if (c.getE()== EtatVentes.In_Preparation ) {
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("/org/Interfaces/Ventes interface.fxml"));
                Node n = f.load();
                mainAnchor.getChildren().setAll(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            showMessage("Impossible to modify this order, it has already been sent");
        }
        System.out.println(c);
    }

    public void onVal(ActionEvent event)  throws IOException {
        CommandV c = (CommandV) Tab.getSelectionModel().getSelectedItem();
        VentesService as = new VentesService();
        List<Ventes> Ventes = as.getByComm(Math.toIntExact(c.getId()));
        if(c.getE().equals(EtatVentes.In_Preparation)) {
            if (!Ventes.isEmpty()) {
                cs.validerComm(Math.toIntExact(c.getId()));
                showMessage("The order has been delivered");
                getAll();
            }
            else {
                showMessage("Please fill this command with sales");
            }
        }
        else if (c.getE().equals(EtatVentes.Pending)){
            majQ(c);
            cs.validerComm(Math.toIntExact(c.getId()));
            showMessage("The order has been delivered");
            getAll();
        }
        else  {
            showMessage("Impossible to validate this order");
        }
    }
    public void majQ(CommandV c) throws IOException {
        VentesService as = new VentesService();
        List<Ventes> Ventes = as.getByComm(Math.toIntExact(c.getId()));
        for (Ventes a : Ventes) {
            int q = a.getDetails().getQuantity();
            int id = Math.toIntExact(a.getDetails().getProduct().getId());
            ps.retirerQ(q, id);
        }
    }


    public void onRef(ActionEvent event) {
        getAll();
        CB_four.setValue(null);
        Calendrier.setValue(null);
    }

    private void getAll(){
        remlirTab(FXCollections.observableList(cs.getAll()));
    }
    private void remlirTab(ObservableList<CommandV> CommandVs){
        C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        C_date.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        C_Etat.setCellValueFactory(new PropertyValueFactory<>("e"));
        Tab.setItems(CommandVs);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadE() {
        List<String> e = new ArrayList<>();
        for (org.giefront.DTO.EtatVentes etat : EtatVentes.values()){
            e.add(etat.name());
        }
        CB_four.getItems().addAll(e);

    }

}
