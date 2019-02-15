package application;

import java.io.File;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;

public class MainController {
	
	@FXML
	private TextField pathTextField;
	@FXML
	private Label result;
	@FXML	
	private FlowPane resultFlowPane;
	@FXML
	private ComboBox<String> comboBox1;
	@FXML
	private ComboBox<String> comboBox2;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	
	/**
	 * Objeto de las clase Stack 
	 */
	private Stack<Integer> stack;
	/**
	 * Factory Stack 
	 */
	private StackFactory<Integer> stackFactory;
	/**
	 * Factory lista 
	 */
	private ListFactory<Integer> listFactory;
	/**
	 * Objeto de la clase AbstractList 
	 */
	private AbstractList<Integer> list;
	/**
	 * objeto de la clase que implementa Calculator
	 */
	private ImpCalculadora calculator;
	/**
	 * Es en donde se almacena la informacion leida en el archivo txt
	 */
	private Scanner s;
	/**
	 * Es en donde se almacena el patron de diseno seleccionado
	 */
	private Integer selectedDesignPattern;
	private Integer selectedList;
	/**
	 *	cambia la informacion del comboBox2 
	 */
	public void changeComboBox() {
		String selectedValue = comboBox1.getValue();
		comboBox2.setDisable(false);
		if(selectedValue.equals("Stack")) {
			comboBox2.getItems().clear();
			comboBox2.getItems().addAll("ArrayList","Lista","Vector");
		}else if(selectedValue.equals("Lista")) {
			comboBox2.getItems().clear();
			comboBox2.getItems().addAll("Lista Simple","Lista Doble","Lista Circular");
		}
	}
	/**
	 *	setea el patron de diseno que se va a usar
	 */
	public void set() {
		if(comboBox2.getValue()!=null) {
			comboBox1.setDisable(true);
			comboBox2.setDisable(true);
			pathTextField.setDisable(false);
			button1.setDisable(true);
			button2.setDisable(false);
			String selectedValue1 = comboBox1.getValue();
			String selectedValue = comboBox2.getValue();
			
			if(selectedValue1.equals("Stack")) {
				stack = stackFactory.getStack(selectedValue);
				selectedDesignPattern = 1;
			}else if(selectedValue1.equals("Lista")) {
				list = listFactory.getList(selectedValue);
				selectedDesignPattern = 2;
			}
			
			if(selectedValue.equals("Lista Simple")) {
				selectedList=1;
			}else if(selectedValue.equals("Lista Doble")) {
				selectedList=2;
			}else if(selectedValue.equals("Lista Circular")) {
				selectedList=3;
			}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos seleccionados");
			alert.setContentText("Verifica que hayas seleccionado una opción");
			alert.showAndWait();
		}
	}
	/**
	 *	lee el archivo txt 
	 */
	public void read() {
		//Si ya se verifico y no hay error se lee el archivo
		if(verifyPath()) {
			//Se recorre todo el archivo
			String line = "";
			while (s.hasNextLine()) {
				line = s.nextLine()+ " ";
				//Se hace un arreglo de strings
				String[] text = line.split(" ");
				//Se recorre la linea del archivo txt
				for (int i = 0; i < text.length; i++) {
					String value = text[i];
					//Se guarda el valor en el stack
					try {
						//Se intenta convertir a int
						Integer intValue = Integer.parseInt(value);
						if(selectedDesignPattern==1)
							stack.push(intValue);
						else if(selectedDesignPattern==2)
							list.addLast(intValue);
					} catch (Exception e) {
						// TODO: handle exception
						//Si no es int es un operando entonces ya se puede efectuar la operacion
						//Se sacan la operacion y los dos operandos
						String operacion = value;
						int operando1 = 0;
						int operando2 = 0;
						if(selectedDesignPattern==1) {
							operando1 = stack.pop();
							operando2 = stack.pop();
						} else if(selectedDesignPattern==2) {
							if(selectedList==1) {
								SinglyLinkedList<Integer> currentList = (SinglyLinkedList<Integer>)list;
								operando2 = (int)currentList.removeFirst();
								operando1 = (int)currentList.removeFirst();
							}else if(selectedList==2) {
								DoublyLinkedList<Integer> currentList = (DoublyLinkedList<Integer>)list;
								operando1 = (int)currentList.removeLast();
								operando2 = (int)currentList.removeLast();
							}else if(selectedList==3) {
								CircularList<Integer> currentList = (CircularList<Integer>)list;
								operando1 = (int)currentList.removeLast();
								operando2 = (int)currentList.removeLast();
							}
						}
						//Se hace la operacion
						int resultado = calculator.Calculate(operando1, operando2, operacion);
						//Se guarda el resultado en la primera posicion del stack o lista
						if(selectedDesignPattern==1)
							stack.push(resultado);
						else if(selectedDesignPattern==2)
							list.addLast(resultado);
					}
				}
				int resultado = 0;
				if(selectedDesignPattern==1) {
					resultado = stack.pop();
				} else if(selectedDesignPattern==2) {
					if(selectedList==1) {
						SinglyLinkedList<Integer> currentList = (SinglyLinkedList<Integer>)list;
						resultado = (int)currentList.removeFirst();
					}else if(selectedList==2) {
						DoublyLinkedList<Integer> currentList = (DoublyLinkedList<Integer>)list;
						resultado = (int)currentList.removeLast();
					}else if(selectedList==3) {
						CircularList<Integer> currentList = (CircularList<Integer>)list;
						resultado = (int)currentList.removeLast();
					}
				}
				Label label = new Label("Resultado: "+resultado);
				Region p = new Region();
				p.setPrefSize(347.0, 4.0);
				Line linee = new Line(0, 0, 350, 0);
				Region p1 = new Region();
				p1.setPrefSize(347.0, 4.0);
				//Se agregan al FlowPane
				resultFlowPane.getChildren().add(label);
				resultFlowPane.getChildren().add(p);
				resultFlowPane.getChildren().add(linee);
				resultFlowPane.getChildren().add(p1);
			}
		}
	}
	
	/**
	 * Verifica que el path sea correcto 
	 * @return boolean = si se pudo abrir el path
	 */
	public boolean verifyPath() {
		try {
			//Se lee el archivo
			s = new Scanner(new File(pathTextField.getText()));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresado");
			alert.setContentText("Verifica la dirección del archivo ingresado sea correcta");
			alert.showAndWait();
			return false;
		}
	}
	
	@FXML
    public void initialize() {
		//Se inicializa la computadora
		calculator = new ImpCalculadora();
		//Se inicializan los factorys
		stackFactory = new StackFactory<Integer>();
		listFactory = new ListFactory<Integer>();
		//Se inicializan los combobox
		comboBox1.getItems().addAll("Stack","Lista");
		pathTextField.setDisable(true);
		comboBox2.setDisable(true);
		button2.setDisable(true);
    }
	
}
