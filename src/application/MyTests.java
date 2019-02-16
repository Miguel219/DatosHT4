package application;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

public class MyTests {

    @Test
    public void ListFactoryTester() {
    	//Se inicializa la computadora
    	Calculator calculadora = new ImpCalculadora();
    	//Se inicializan los factorys
    	StackFactory<Integer> stackFactory = new StackFactory<Integer>();
    	ListFactory<Integer> listFactory = new ListFactory<Integer>();
    	Stack<Integer> stack;
    	AbstractList<Integer> lista;
    	
    	 lista = listFactory.getList("Lista Circular");
    	 CircularList<Integer> list = (CircularList<Integer>) lista;
        // assert statements
        //Aquí se ejecutara nuestro test.
        
        assertEquals(0, list.size(), "Se debe verificar que el list inicie vacío.");
        list.addLast(10);
        list.addLast(5);
        int num2=list.removeLast();
        int num1= list.removeLast();
        int suma=calculadora.Calculate(num1,num2 , "+");
        int resta=calculadora.Calculate(num1,num2 , "-");
        int multiplicacion=calculadora.Calculate(num1,num2 , "*");
        int division=(int)(calculadora.Calculate(num1,num2 , "/"));
        assertEquals(15, suma, "Se debe verificar que el resultado de la suma sea 15 y que funcione");
        assertEquals(5, resta, "Se debe verificar que el resultado de la resta sea 5 y que funcione");
        assertEquals(2, division, "Se debe verificar que el resultado de la division sea 2 y que funcione");
        assertEquals(50, multiplicacion, "Se debe verificar que el resultado de la multiplicacion sea 50 y que funcione");
    }
}