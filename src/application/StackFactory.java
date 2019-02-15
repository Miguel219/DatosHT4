package application;
/**
 * Silvio Orozco 18282
 * Jose Castaneda 18161
 */
class StackFactory<E> {
//selecciona la implementacion a utilizar para un stack
//se utiliza el patron Factory
   public Stack<E> getStack(String entry) {
    // seleccion de la implementacion a utilizar:
	   if(entry.equals("ArrayList")) {
			return new StackArrayList<E>();
		}else if(entry.equals("Lista")) {
			return new StackList<E>();
		}else {
			return new StackVector<E>();
		}
   }
}