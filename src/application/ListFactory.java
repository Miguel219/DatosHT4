package application;
/**
 * Silvio Orozco 18282
 * Jose Castaneda 18161
 */
class ListFactory<E> {

	public AbstractList<E> getList(String entry) {
    // seleccion de la implementacion a utilizar:
	   if(entry.equals("Lista Simple")) {
			return new SinglyLinkedList<E>();
		}else if(entry.equals("Lista Doble")) {
			return new DoublyLinkedList<E>();
		}else {
			return new CircularList<E>();
		}
   }

}
