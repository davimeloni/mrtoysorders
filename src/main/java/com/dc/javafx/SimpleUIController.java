package com.dc.javafx;

import com.opencsv.CSVWriter;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SimpleUIController {

    @FXML
    public Label label;

    @FXML
    public Button button;

    FileChooser fileChooser = new FileChooser();


    SimpleUIController() { }

    @FXML
    public void initialize() {
        this.button.setOnAction(actionEvent -> {

            Stage stage = (Stage) this.button.getScene().getWindow();

            FileChooser chooser = new FileChooser();
            File defaultfile = chooser.showOpenDialog(stage);

            System.out.println(defaultfile);

            try {
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
                Document doc = dBuilder.parse(defaultfile);

                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                if (doc.hasChildNodes()) {

                    printNode2(doc.getChildNodes());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
//        this.button.setActionCommand(ActionEvent -> this::);
    }

    private static void printNode2(NodeList nodeList) throws IOException {
        Node purchaseOrdersNode = nodeList.item(0);

        String poNumber = purchaseOrdersNode.getFirstChild().getChildNodes().item(2).getTextContent();
        System.out.println(poNumber);
        Node branchDistributionsNode = purchaseOrdersNode.getFirstChild().getLastChild();

        NodeList branchDistributionsNodeChildNodes = branchDistributionsNode.getChildNodes();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        File file = new File("C:\\Users\\dsmel\\Desktop\\mrtoys\\mrtoys_orders_" + sdf.format(new Date()) + ".csv");
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] header = { "Email", "PO Custom Field", "Purchase Order ID", "Branch", "Order Line SKU", "Order Line QTY", "Ship Address Line 1", "Ship Address Line 2", "Ship City", "Ship State", "Ship Post Code", "Ship Country", "Order Status", "Approved"};
        writer.writeNext(header);

        for (int count2 = 0; count2 < branchDistributionsNodeChildNodes.getLength(); count2++) {
            NodeList bd = branchDistributionsNodeChildNodes.item(count2).getChildNodes();
            String branch = bd.item(1).getTextContent();

            NodeList addressNode = bd.item(2).getChildNodes();
            String al1 = addressNode.item(0).getTextContent();
            String al2 = addressNode.item(1).getTextContent();
            String city = addressNode.item(2).getTextContent();
            String state = addressNode.item(3).getTextContent();
            String postCode = addressNode.item(4).getTextContent();

            NodeList olList = bd.item(3).getChildNodes();

            for (int count3 = 0; count3 < olList.getLength(); count3++) {

                NodeList ol = olList.item(count3).getChildNodes();

                String quantity = ol.item(5).getTextContent();

                String[] dataRow = {"mrtoys@mrtoys.com.au", poNumber, poNumber + " " + postCode, branch, ol.item(1).getTextContent(), quantity.substring(0, quantity.indexOf(".")), al1, al2, city, state, postCode, "AU", "New", "Y"};
                writer.writeNext(dataRow);
            }
        }

        writer.close();

    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());


                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

}
